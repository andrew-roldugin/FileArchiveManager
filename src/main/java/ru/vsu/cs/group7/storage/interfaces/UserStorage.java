package ru.vsu.cs.group7.storage.interfaces;

import ru.vsu.cs.group7.model.User;

import java.sql.SQLException;
import java.util.Optional;

public interface UserStorage<ID> extends Storage<User, ID> {
    Optional<User> getOneByLogin(String login) throws SQLException;
}
