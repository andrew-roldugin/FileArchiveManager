package ru.vsu.cs.group7.exception;

public class PasswordsDoNotMatchException extends ApplicationException {
    public PasswordsDoNotMatchException() {
        super("Вы ввели неверный пароль");
    }

    public PasswordsDoNotMatchException(String message) {
        super(message);
    }
}
