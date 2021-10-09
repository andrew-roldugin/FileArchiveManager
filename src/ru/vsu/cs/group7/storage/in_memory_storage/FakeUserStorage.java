package ru.vsu.cs.group7.storage.in_memory_storage;

import ru.vsu.cs.group7.exception.NotFoundException;
import ru.vsu.cs.group7.model.User;

import java.util.*;

public class FakeUserStorage extends FakeStorage<User> implements UserStorage {

//    private final Set<User> users = new HashSet<>();

    public FakeUserStorage() {
        super(new HashSet<>());
        storage.addAll(Arrays.asList(
                new User("user", "usr1"),
                new User("admin", "pass", User.RoleEnum.Admin),
                new User("Test", "123")
        ));
    }

    public Optional<User> getOneByLogin(String login) {
        return storage.stream()
                .filter(item -> item.getLogin().equals(login))
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
