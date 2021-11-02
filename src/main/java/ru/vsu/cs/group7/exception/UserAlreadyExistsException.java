package ru.vsu.cs.group7.exception;

public class UserAlreadyExistsException extends ApplicationException {
    public UserAlreadyExistsException(String login) {
        super(String.format("Пользователь с логином %s уже существует", login));
    }
}
