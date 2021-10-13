package ru.vsu.cs.group7.validator;

import ru.vsu.cs.group7.exception.UserValidationException;
import ru.vsu.cs.group7.model.User;

public class UserValidator {
    public static void validate(User user) throws UserValidationException {
        if (user.getLogin().isBlank()) throw new UserValidationException("логин");
        if (user.getPassword().isBlank()) throw new UserValidationException("пароль");
    }
}
