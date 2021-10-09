package ru.vsu.cs.group7.application.consoleApp.config;

import ru.vsu.cs.group7.exception.UserNotAuthorizedException;
import ru.vsu.cs.group7.model.User;

public class ApplicationStorage {

    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public void checkLogin() throws UserNotAuthorizedException {
        if (!isLoggedIn()) throw new UserNotAuthorizedException();
    }

    public boolean isLoggedIn() {
        return this.getUser() != null;
    }

    public User getUser() {
        return user;
    }
}
