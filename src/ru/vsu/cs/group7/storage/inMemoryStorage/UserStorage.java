package ru.vsu.cs.group7.storage.inMemoryStorage;

import ru.vsu.cs.group7.model.User;
import ru.vsu.cs.group7.storage.Storage;

import java.util.Optional;

public interface UserStorage extends Storage<User> {
    Optional<User> getOneByLogin(String login);
}
