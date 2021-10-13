package ru.vsu.cs.group7.service;

import ru.vsu.cs.group7.application.consoleApp.config.ApplicationContext;
import ru.vsu.cs.group7.exception.*;
import ru.vsu.cs.group7.model.User;
import ru.vsu.cs.group7.storage.inMemoryStorage.*;
import ru.vsu.cs.group7.validator.UserValidator;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserService implements Service {

    private final UserStorage userStorage;
    private final FileArchiveStorage fileArchiveStorage;
    private final FileStorage fileStorage;
    private final ApplicationContext context;

    public UserService(UserStorage userStorage, FileArchiveStorage fileArchiveStorage, FileStorage fileStorage, ApplicationContext context) {
        this.userStorage = userStorage;
        this.fileArchiveStorage = fileArchiveStorage;
        this.fileStorage = fileStorage;
        this.context = context;
    }

    public UserService(ApplicationContext context) {
        this(new FakeUserStorage(), new FakeFileArchiveStorage(), new FakeFileStorage(), context);
    }

    public User createUser(String login, String password) throws UserValidationException, UserAlreadyExistsException {
        User user = new User(login, password);
        UserValidator.validate(user);
        if (userStorage.getOneByLogin(login).isPresent()) {
            throw new UserAlreadyExistsException(login);
        }
        userStorage.save(user);
        return user;
    }

    public List<User> findAll(User.RoleEnum requiredRole) throws UserNotAuthorizedException, NotAllowedExceptions {
        context.checkLogin();
        User user = context.getUser();
        if (!user.getRole().equals(requiredRole))
            throw new NotAllowedExceptions(user.getId(), "просматривать всех пользователей");
        return userStorage.getAll().stream().toList();
    }

    public User getOneUserByLogin(String login) throws UserNotFoundException {
        return userStorage.getOneByLogin(login).orElseThrow(() -> new UserNotFoundException("с логином", login));
    }

    public User getOneUserById(UUID id) throws UserNotFoundException {
        return userStorage.getOneById(id).orElseThrow(() -> new UserNotFoundException("с id", id.toString()));
    }

    public User update(String newLogin, String newPassword) throws UserValidationException, UserNotAuthorizedException, LoginAlreadyInUseException, NotAllowedExceptions {
        return update(context.getUser().getId(), newLogin, newPassword);
    }

    public User update(UUID id, String newLogin, String newPassword) throws LoginAlreadyInUseException, UserValidationException, UserNotAuthorizedException, NotAllowedExceptions {
        context.checkLogin();

        User newUser = new User(id, newLogin, newPassword);
        UserValidator.validate(newUser);

        Optional<User> oneByLogin = userStorage.getOneByLogin(newLogin);
        if (oneByLogin.isPresent())
            throw new LoginAlreadyInUseException(newLogin);

        if (!context.getUser().getId().equals(id) && !context.getUser().getRole().equals(User.RoleEnum.Admin))
            throw new NotAllowedExceptions();

        userStorage.updateById(newUser);
        context.setUser(newUser);
        return newUser;
    }

    public void removeUserByLogin(String login) throws UserNotAuthorizedException, UserNotFoundException, NotAllowedExceptions {
        UUID userId = getOneUserByLogin(login).getId();
        remove(userId);
    }

    public void removeUserById(UUID id) throws UserNotAuthorizedException, UserNotFoundException, NotAllowedExceptions {
        UUID userId = getOneUserById(id).getId();
        remove(userId);
    }

    public void removeUser() throws UserNotAuthorizedException, NotAllowedExceptions {
        UUID userId = context.getUser().getId();
        remove(userId);
    }

    private void remove(UUID userId) throws UserNotAuthorizedException, NotAllowedExceptions {
        context.checkLogin();
//        UUID userId = getOneUserById(id).getId();
        if (!context.getUser().getId().equals(userId) && !context.getUser().getRole().equals(User.RoleEnum.Admin))
            throw new NotAllowedExceptions();

        if (userId.equals(context.getUser().getId()))
            context.setUser(null);
        userStorage.removeById(userId);
        fileArchiveStorage.getAllArchivesByUserId(userId).forEach(fileArchive -> {
            fileStorage.removeAllByArchiveId(fileArchive.getId());
            fileArchiveStorage.removeById(fileArchive.getId());
        });
    }

    public void login(User user) throws UserNotFoundException, PasswordsDoNotMatchException, UserValidationException {
        login(user.getLogin(), user.getPassword());
    }

    public void login(String login, String password) throws UserNotFoundException, PasswordsDoNotMatchException, UserValidationException {
        User user = new User(login, password);
        UserValidator.validate(user);

        Optional<User> oneByLogin = userStorage.getOneByLogin(login);
        if (oneByLogin.isEmpty())
            throw new UserNotFoundException(login);
        if (!oneByLogin.get().getPassword().equals(password))
            throw new PasswordsDoNotMatchException();

        context.setUser(oneByLogin.get());
    }

    public void logout() {
        if (context.isLoggedIn())
            context.setUser(null);
    }

    public ApplicationContext getApplicationContext() {
        return context;
    }
}
