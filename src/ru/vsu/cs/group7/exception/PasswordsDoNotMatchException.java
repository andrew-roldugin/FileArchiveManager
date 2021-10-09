package ru.vsu.cs.group7.exception;

public class PasswordsDoNotMatchException extends ApplicationException {
    public PasswordsDoNotMatchException() {
        super("Пароли не совпадают");
    }

    public PasswordsDoNotMatchException(String message) {
        super(message);
    }
}
