package ru.job4j.bomberman2;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Character implements Runnable {

    //игровое поле в виде двумерного массива
    protected Board board;

    //имя персонажа
    private String name;

    //координаты на доске по х
    protected int x;

    //координаты на доске по y
    protected int y;

    //текущая ячейка. Блокируется при нахождении на ней
    protected ReentrantLock cell;

    //флаг остановки
    protected volatile boolean stopped = false;

    Character(Board board, String name, int x, int y) {
        this.board = board;
        this.cell = this.board.getCell(x, y);
        this.name = name;
        this.x = x;
        this.y = y;
    }

    /**
     * Метод случайного выбора направления из четырех возможных
     * @return массив с двумя числами: перемещение по х и перемещение по y
     */
    protected int[] getDirection() {
        Random random = new Random();
        int[] direction = new int[2];

        switch (random.nextInt(4)) {
            case 0: //право
                direction[0] = 1;
                direction[1] = 0;
                break;
            case 1: //лево
                direction[0] = -1;
                direction[1] = 0;
                break;
            case 2: //вверх
                direction[0] = 0;
                direction[1] = 1;
                break;
            case 3: //вниз
                direction[0] = 0;
                direction[1] = -1;
                break;
            default: break;
        }
        return direction;
    }

    /**
     * Метод перемещения
     * @param moveX новые координаты по х
     * @param moveY новые координаты по y
     * @throws InterruptedException бросает исключение
     */
    protected void move(int moveX, int moveY) throws InterruptedException {
        ReentrantLock moveCell = this.board.getCell(moveX, moveY);
        boolean move = moveCell.tryLock(500, TimeUnit.MILLISECONDS);

        //если получилось залочить клетку, то перемещаемся на нее, освобождаем предыдущую
        if (move) {
            this.x = moveX;
            this.y = moveY;
            this.cell.unlock();
            this.cell = moveCell;

            System.out.println(String.format("%s moved to: x %s y %s", Thread.currentThread().getName(), x, y));
        }
    }

    /**
     * Метод получения имени
     * @return имя персонажа
     */
    protected String getName() {
        return this.name;
    }

    /**
     * Метод остановки движения
     */
    protected void doStop() {
        Thread.currentThread().interrupt();
        this.stopped = true;
    }
}
