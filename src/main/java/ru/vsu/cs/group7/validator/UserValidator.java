package ru.vsu.cs.group7.validator;

import ru.vsu.cs.group7.exception.ApplicationException;
import ru.vsu.cs.group7.exception.UserValidationException;
import ru.vsu.cs.group7.model.User;

public class UserValidator {
    public static void validate(User user) throws ApplicationException {
        if (user == null) throw new ApplicationException("Объект не может быть пустым");
        if (user.getLogin().isBlank()) throw new UserValidationException("логин");
        if (user.getPassword().isBlank()) throw new UserValidationException("пароль");
    }
}
