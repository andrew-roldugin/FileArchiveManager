package ru.vsu.cs.group7.service;

import ru.vsu.cs.group7.application.simple_console_app.ApplicationStorage;
import ru.vsu.cs.group7.exception.*;
import ru.vsu.cs.group7.model.User;
import ru.vsu.cs.group7.storage.in_memory_storage.FakeFileArchiveStorage;
import ru.vsu.cs.group7.storage.in_memory_storage.FakeUserStorage;
import ru.vsu.cs.group7.storage.in_memory_storage.FileArchiveStorage;
import ru.vsu.cs.group7.storage.in_memory_storage.UserStorage;
import ru.vsu.cs.group7.validator.UserValidator;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserService implements Service {

    private final UserStorage userStorage;
    private final FileArchiveStorage fileArchiveStorage;
    private final ApplicationStorage applicationStorage;

    public UserService(UserStorage userStorage, FileArchiveStorage fileArchiveStorage, ApplicationStorage applicationStorage) {
        this.userStorage = userStorage;
        this.fileArchiveStorage = fileArchiveStorage;
        this.applicationStorage = applicationStorage;
    }

    public UserService(ApplicationStorage applicationStorage) {
        this(new FakeUserStorage(), new FakeFileArchiveStorage(), applicationStorage);
    }

    public User createUser(String login, String password) throws Exception {
        User user = new User(login, password);
        UserValidator.validate(user);
        if (userStorage.getOneByLogin(login).isPresent()) {
            throw new UserAlreadyExistsException(login);
        }
        userStorage.save(user);
        return user;
    }

    public List<User> findAll() throws UserNotAuthorizedException {
        applicationStorage.checkLogin();
        return userStorage.getAll().stream().toList();
    }

    public User getOneUserByLogin(String login) throws UserNotFoundException {
        return userStorage.getOneByLogin(login).orElseThrow(() -> new UserNotFoundException("с логином", login));
    }

    public User getOneUserById(UUID id) throws UserNotFoundException {
        return userStorage.getOneById(id).orElseThrow(() -> new UserNotFoundException("с id", id.toString()));
    }

    public User update(String newLogin, String newPassword) throws Exception {
        return update(applicationStorage.getUser().getId(), newLogin, newPassword);
    }

    public User update(UUID id, String newLogin, String newPassword) throws Exception {
        applicationStorage.checkLogin();

        User newUser = new User(id, newLogin, newPassword);
        UserValidator.validate(newUser);

        Optional<User> oneByLogin = userStorage.getOneByLogin(newLogin);
        if (oneByLogin.isPresent())
            throw new LoginAlreadyInUseException(newLogin);

        userStorage.updateById(newUser);
        applicationStorage.setUser(newUser);
        return newUser;
    }

    public void removeUserByLogin(String login) throws UserNotAuthorizedException, UserNotFoundException {
        UUID userId = getOneUserByLogin(login).getId();
        remove(userId);
    }

    public void removeUserById(UUID id) throws UserNotAuthorizedException, UserNotFoundException {
        UUID userId = getOneUserById(id).getId();
        remove(userId);
    }

    public void removeUser() throws UserNotAuthorizedException, UserNotFoundException {
        UUID userId = applicationStorage.getUser().getId();
        remove(userId);
    }

    private void remove(UUID id) throws UserNotAuthorizedException, UserNotFoundException {
        applicationStorage.checkLogin();
        UUID userId = getOneUserById(id).getId();
        userStorage.removeById(userId);
        fileArchiveStorage.removeAllByUserId(userId);
    }

    public void login(User user) throws UserNotFoundException, Exception {
        login(user.getLogin(), user.getPassword());
    }

    public void login(String login, String password) throws UserNotFoundException, Exception {
        User user = new User(login, password);
        UserValidator.validate(user);

        Optional<User> oneByLogin = userStorage.getOneByLogin(login);
        if (oneByLogin.isEmpty())
            throw new UserNotFoundException(login);
        if (!oneByLogin.get().getPassword().equals(password))
            throw new PasswordsDoNotMatchException();

        applicationStorage.setUser(oneByLogin.get());
    }

    public void logout() {
        if (applicationStorage.isLoggedIn())
            applicationStorage.setUser(null);
    }
}
