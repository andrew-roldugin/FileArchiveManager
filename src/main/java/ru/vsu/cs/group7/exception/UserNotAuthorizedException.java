package ru.vsu.cs.group7.exception;

public class UserNotAuthorizedException extends ApplicationException {
    public UserNotAuthorizedException() {
        super("Пользователь не авторизован");
    }

    public UserNotAuthorizedException(String message) {
        super(message);
    }
}
