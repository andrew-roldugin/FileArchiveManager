package ru.vsu.cs.group7.application.simple_console_app;

import ru.vsu.cs.group7.exception.UserNotAuthorizedException;
import ru.vsu.cs.group7.model.User;

public class ApplicationStorage {
    private User user;
//    private static ApplicationStorage INSTANCE;
//    protected final MenuManager menuManager = new MenuManager();

//    private ApplicationStorage() {
//    }

//    public MenuManager getMenuManager() {
//        return this.menuManager;
//    }

//    public static ApplicationStorage getINSTANCE() {
//        if (INSTANCE == null) {
//            INSTANCE = new ApplicationStorage();
//        }
//        return INSTANCE;
//    }

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
