package ru.vsu.cs.group7.application.simple_console_app.ui.menu.pages.user;

import ru.vsu.cs.group7.application.simple_console_app.ui.menu.pages.Page;
import ru.vsu.cs.group7.application.simple_console_app.ui.menu.common.BaseMenu;
import ru.vsu.cs.group7.service.UserService;

public abstract class UserPages extends Page {
    private final UserService userService;

    public UserPages(BaseMenu parentMenu, String content) {
        super(parentMenu, content);
        this.userService = (UserService) parentMenu.getService();
    }

    public UserService getUserService() {
        return userService;
    }
}
