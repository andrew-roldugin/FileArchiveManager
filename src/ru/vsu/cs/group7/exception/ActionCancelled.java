package ru.vsu.cs.group7.exception;

public class ActionCancelled extends RuntimeException {
    public ActionCancelled() {
        super("Действие отменено");
    }
}
