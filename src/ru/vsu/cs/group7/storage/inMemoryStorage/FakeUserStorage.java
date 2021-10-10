package ru.vsu.cs.group7.storage.inMemoryStorage;

import ru.vsu.cs.group7.model.User;

import java.util.*;

public class FakeUserStorage extends FakeStorage<User> implements UserStorage {

//    private final Set<User> users = new HashSet<>();

    public FakeUserStorage() {
        super(new HashSet<>());
        storage.addAll(Arrays.asList(
                new User(UUID.fromString("13671c26-16f5-4bc2-978c-3dc6a5544b0b"), "user", "usr1"),
                new User(UUID.fromString("d150f135-f4b9-4b50-9018-51a1ed5982bf"), "admin", "pass", User.RoleEnum.Admin),
                new User(UUID.fromString("6bed28b0-902d-49f6-a322-b63b8ec5e6a7"), "Test", "123")
        ));
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
