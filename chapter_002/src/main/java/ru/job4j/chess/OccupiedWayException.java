package ru.job4j.chess;

public class OccupiedWayException extends Exception {
    OccupiedWayException(String msg) {
        super(msg);
    }
}
