package ru.vsu.cs.group7.exception;

public class PasswordsDoNotMatchException extends Exception {
    public PasswordsDoNotMatchException() {
        super("Пароли не совпадают");
    }

    public PasswordsDoNotMatchException(String message) {
        super(message);
    }
}
