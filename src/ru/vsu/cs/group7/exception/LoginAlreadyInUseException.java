package ru.vsu.cs.group7.exception;

public class LoginAlreadyInUseException extends ApplicationException {
    public LoginAlreadyInUseException(String login) {
        super(String.format("Логин %s уже занят", login));
    }
}
