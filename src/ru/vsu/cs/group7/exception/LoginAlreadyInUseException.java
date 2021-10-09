package ru.vsu.cs.group7.exception;

public class LoginAlreadyInUseException extends Exception {
    public LoginAlreadyInUseException(String login) {
        super(String.format("Логином %s уже занят", login));
    }
}
