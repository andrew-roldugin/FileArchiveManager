package ru.vsu.cs.group7.application.consoleApp.ui.pages.user;

import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.MenusEnum;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.fabric.UserMenu;
import ru.vsu.cs.group7.exception.ActionCancelled;
import ru.vsu.cs.group7.service.UserService;

public class LogoutPage extends UserPages {

    public LogoutPage(UserService userService) {
        super("", userService);
    }

    @Override
    public void openPage() throws ActionCancelled {
        System.out.println("Выполняется выход...");
        getUserService().logout();
        System.out.println("Успешно\n");
        backToMenu(MenusEnum.AuthMenu, getIsWait());
    }
}
