package ru.job4j.chess;

public class ImpossibleMoveException extends Exception {
    ImpossibleMoveException(String msg) {
        super(msg);
    }
}
