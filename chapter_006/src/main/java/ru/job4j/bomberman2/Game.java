package ru.job4j.bomberman2;

import java.util.ArrayList;

public class Game {

    //игровое поле
    Board board;

    //список персонажей
    ArrayList<Character> characters;

    Game(int width, int height) {
        board = new Board(width, height);
        characters = new ArrayList<>();
    }

    /**
     * Метод создания главного героя
     * @param name персонажа
     * @param x координата начального положения
     * @param y координата начального положения
     */
    public void createHero(String name, int x, int y) {
        characters.add(new Hero(board, name, x, y));
    }

    /**
     * Метод создания монстра
     * @param name персонажа
     * @param x координата начального положения
     * @param y координата начального положения
     */
    public void createMonster(String name, int x, int y) {
        characters.add(new Monster(board, name, x, y));
    }

    /**
     * Создание заблокированной области
     * @param x координата области
     * @param y координата области
     */
    public void createBlockedArea(int x, int y) {
        this.board.blockCell(x, y);
        System.out.println(String.format("Заблокирована област с координатами x %s y %s", x, y));
    }

    /**
     * Старт игры
     */
    public void startGame() {
        for (Character c : characters) {
            new Thread(c, c.getName()).start();
        }
    }

    /**
     * Остановка игры
     */
    public void stopGame() {
        for (Character c : characters) {
            c.doStop();
        }
    }

    public static void main(String[] args) {
        Game game = new Game(10, 10);
        game.createBlockedArea(4, 6);
        game.createBlockedArea(8, 2);
        game.createHero("SuperHero", 5, 5);
        game.createMonster("Monster1", 3, 3);
        game.createMonster("Monster2", 7, 7);
        game.startGame();

        //пять секунд на игру в демонстрационных целях
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        game.stopGame();
    }
}
