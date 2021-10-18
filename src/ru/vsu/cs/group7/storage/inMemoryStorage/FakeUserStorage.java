package ru.vsu.cs.group7.storage.inMemoryStorage;

import ru.vsu.cs.group7.model.User;
import ru.vsu.cs.group7.storage.interfaces.UserStorage;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class FakeUserStorage extends FakeStorage<User> implements UserStorage {

    public FakeUserStorage() {
        super(new LinkedList<>());
    }

    public FakeUserStorage(List<User> users) {
        super(users);
    }

    public Optional<User> getOneByLogin(String login) {
        return storage.stream()
                .filter(user -> user.getLogin().equals(login))
                .findFirst();
    }

    @Override
    public User updateById(Long userId, User newUserData) {
        return updateById(userId, user -> {
            user.setLogin(newUserData.getLogin());
            user.setPassword(newUserData.getPassword());
        });
    }
}
