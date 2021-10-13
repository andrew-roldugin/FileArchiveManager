package ru.vsu.cs.group7.storage.inMemoryStorage;

import ru.vsu.cs.group7.model.User;

import java.util.*;

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
    public void updateById(User newUserData) {
        updateById(newUserData, user -> {
            user.setLogin(newUserData.getLogin());
            user.setPassword(newUserData.getPassword());
        });
    }
}
