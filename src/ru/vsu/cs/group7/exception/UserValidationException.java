package ru.vsu.cs.group7.exception;

public class UserValidationException extends Exception {
    public UserValidationException(String field) {
        super(String.format("Поле %s должно быть заполнено", field));
    }
}
