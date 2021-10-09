package ru.vsu.cs.group7.exception;

public class UserAlreadyExistsException extends Exception{
    public UserAlreadyExistsException(String login) {
        super(String.format("Пользователь с логином %s уже существует", login));
    }
}
