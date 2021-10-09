package ru.vsu.cs.group7.exception;

import java.util.UUID;

public class NotAllowedExceptions extends ApplicationException {
    public NotAllowedExceptions(UUID userId, UUID targetId, String action) {
        super(String.format("Пользователю с id=%s запрещено %s %s", userId, action, targetId));
    }
    public NotAllowedExceptions(UUID userId, String action) {
        super(String.format("Пользователю с id=%s запрещено %s", userId, action));
    }
}
