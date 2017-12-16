package ru.job4j.bomberman2;

import java.util.concurrent.locks.ReentrantLock;

public class Board {

    private final ReentrantLock[][] board;
    private final int width;
    private final int height;

    Board(int width, int height) {
        this.width = width;
        this.height = height;

        board = new ReentrantLock[this.height][this.width];
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                board[i][j] = new ReentrantLock();
            }
        }
    }

    //получить ширину поля
    public int getWidth() {
        return this.width;
    }

    //получить высоту поля
    public int getHeight() {
        return this.height;
    }

    //получить замок ячейки доски
    public ReentrantLock getCell(int x, int y) {
        return this.board[y][x];
    }

    //заблокировать ячейку поля, куда нельзя ходить
    public void blockCell(int x, int y) {
        this.getCell(x, y).lock();
    }
}
