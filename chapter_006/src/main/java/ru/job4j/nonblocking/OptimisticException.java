package ru.job4j.nonblocking;

public class OptimisticException extends RuntimeException {
    OptimisticException(String oe) {
        super(oe);
    }
}
