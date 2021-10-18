package ru.vsu.cs.group7.application.consoleApp.ui.pages.user;

import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.MenusEnum;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.fabric.AuthMenu;
import ru.vsu.cs.group7.exception.ActionCancelled;
import ru.vsu.cs.group7.exception.ApplicationException;
import ru.vsu.cs.group7.model.User;
import ru.vsu.cs.group7.service.Service;
import ru.vsu.cs.group7.service.UserService;

public class CreateUserPage extends UserPages {

    public CreateUserPage(UserService userService) {
        super("====================================== Регистрация ======================================", userService);
    }

    @Override
    public void openPage() throws ApplicationException, ActionCancelled {
        System.out.print("Введите логин: ");
        String login = readUserInput();
        System.out.print("Введите пароль: ");
        String password = readUserInput();

        User user = getUserService().createUser(login, password);
        getUserService().login(user);
        System.out.println("Успешно\n");
        backToMenu(MenusEnum.UserMenu, getIsWait());
    }
}
