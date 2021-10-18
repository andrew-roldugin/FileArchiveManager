package ru.vsu.cs.group7.storage.interfaces;

import ru.vsu.cs.group7.model.User;
import ru.vsu.cs.group7.storage.interfaces.Storage;

import java.util.Optional;

public interface UserStorage extends Storage<User> {
    Optional<User> getOneByLogin(String login);
}
