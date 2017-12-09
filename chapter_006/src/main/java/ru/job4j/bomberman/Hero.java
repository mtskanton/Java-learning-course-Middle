package ru.job4j.bomberman;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Hero implements Runnable {

    //игровое поле в виде двумерного массива
    private Board board;

    //текущая ячейка. Блокируется при нахождении на ней
    private ReentrantLock cell;

    //координаты на доске по х
    private int x;

    //координаты на доске по y
    private int y;

    //флаг остановки игры
    private volatile boolean stopped = false;

    Hero(Board board, int x, int y) {
        this.x = x;
        this.y = y;
        this.board = board;
        this.cell = this.board.getCell(x, y);
    }

    public void run() {
        //блокируем ячейку, на которой находимся
        this.cell.lock();

        //герой перемещается каждую секунду до остановки игры
        while (!stopped) {
            int[] direction = getDirection();
            int moveX = this.x + direction[0];
            int moveY = this.y + direction[1];

            //если новые координаты в рамках поля
            if (moveX >= 0 && moveX < board.getWidth() && moveY >= 0 && moveY < board.getHeight()) {
                try {
                    moveHero(moveX, moveY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Метод случайного выбора направления из четырех возможных
     * @return массив с двумя числами: перемещение по х и перемещение по y
     */
    private int[] getDirection() {
        Random random = new Random();
        int[] move = new int[2];

        switch (random.nextInt(4)) {
            case 0: //право
                move[0] = 1;
                move[1] = 0;
                break;
            case 1: //лево
                move[0] = -1;
                move[1] = 0;
                break;
            case 2: //вверх
                move[0] = 0;
                move[1] = 1;
            break;
            case 3: //вниз
                move[0] = 0;
                move[1] = -1;
            break;
            default: break;
        }
        return move;
    }

    /**
     * Метод перемещения
     * @param moveX новые координаты по х
     * @param moveY новые координаты по y
     * @throws InterruptedException бросает исключение
     */
    private void moveHero(int moveX, int moveY) throws InterruptedException {
        ReentrantLock moveCell = this.board.getCell(moveX, moveY);
        boolean move = moveCell.tryLock(500, TimeUnit.MILLISECONDS);

        //если получилось залочить клетку, то перемещаемся на нее, освобождаем предыдущую
        if (move) {
            this.x = moveX;
            this.y = moveY;
            this.cell.unlock();
            this.cell = moveCell;

            System.out.println("Hero coordinates. x " + x + " y " + y);

            //герой должен каждую секунду двигаться на новую клетку.
            Thread.sleep(1000);
        }
    }

    /**
     * Метод остановки движения
     */
    public void doStop() {
        Thread.currentThread().interrupt();
        this.stopped = true;
    }
}
