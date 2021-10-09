package ru.vsu.cs.group7.exception;

public class UserNotFoundException extends Throwable {
    public UserNotFoundException(String filter, String value) {
        super(String.format("Пользователь %s %s не найден", filter, value));
    }

    public UserNotFoundException(String login) {
        this(login, "с логином");
    }
}
