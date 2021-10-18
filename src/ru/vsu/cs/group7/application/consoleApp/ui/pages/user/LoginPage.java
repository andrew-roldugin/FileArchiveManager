package ru.vsu.cs.group7.application.consoleApp.ui.pages.user;

import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.MenusEnum;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.fabric.AuthMenu;
import ru.vsu.cs.group7.exception.ActionCancelled;
import ru.vsu.cs.group7.exception.ApplicationException;
import ru.vsu.cs.group7.service.Service;
import ru.vsu.cs.group7.service.UserService;

public class LoginPage extends UserPages {

    public LoginPage(UserService userService) {
        super("====================================== Авторизация ======================================", userService);
    }

    @Override
    public void openPage() throws ApplicationException, ActionCancelled {
        System.out.print("Введите логин: ");
        String login = readUserInput();
        System.out.print("Введите пароль: ");
        String password = readUserInput();

        getUserService().login(login, password);
        System.out.println("Успешно\n");
        backToMenu(MenusEnum.UserMenu, getIsWait());
    }
}
