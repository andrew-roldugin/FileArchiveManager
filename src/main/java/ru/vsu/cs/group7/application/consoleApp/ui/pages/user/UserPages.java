package ru.vsu.cs.group7.application.consoleApp.ui.pages.user;

import ru.vsu.cs.group7.application.consoleApp.config.Services;
import ru.vsu.cs.group7.application.consoleApp.ui.pages.Page;
import ru.vsu.cs.group7.service.UserService;

public abstract class UserPages extends Page {

    private final UserService userService;

    public UserPages(String content, UserService userService) {
        super(content);
        this.userService = userService;
    }

    public UserService getUserService() {
        return userService;
    }
}
