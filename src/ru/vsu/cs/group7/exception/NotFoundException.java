package ru.vsu.cs.group7.exception;

import ru.vsu.cs.group7.model.Storeable;

public class NotFoundException extends Exception {
    public NotFoundException(String message) {
        super(message);
    }
}
