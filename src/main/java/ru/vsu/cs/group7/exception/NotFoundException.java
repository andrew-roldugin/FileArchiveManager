package ru.vsu.cs.group7.exception;

import java.sql.SQLException;

public class NotFoundException extends ApplicationException {
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException() {
        super("Ничего не найдено");
    }
}
