package ru.job4j.liftsimulator;

public class WrongFloorException extends RuntimeException {
    public WrongFloorException(String text) {
        super(text);
    }
}
