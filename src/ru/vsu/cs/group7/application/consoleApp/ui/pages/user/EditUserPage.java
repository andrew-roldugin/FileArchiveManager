package ru.vsu.cs.group7.application.consoleApp.ui.pages.user;

import ru.vsu.cs.group7.application.consoleApp.Controller;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.MenusEnum;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.fabric.UserMenu;
import ru.vsu.cs.group7.exception.ActionCancelled;
import ru.vsu.cs.group7.exception.ApplicationException;
import ru.vsu.cs.group7.model.User;
import ru.vsu.cs.group7.service.UserService;

public class EditUserPage extends UserPages {

    public EditUserPage(UserService userService) {
        super("========================== Редактирование данных пользователя ===========================", userService);
    }

    @Override
    public void openPage() throws ApplicationException, ActionCancelled {
        Long userId = null;
        if (Controller.getInstance().getContext().getUser().getRole().equals(User.RoleEnum.Admin)) {
            System.out.print("Введите id пользователя: ");
            try {
                userId = Long.parseLong(readUserInput());
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
                return;
            }
        }
        System.out.print("Введите новый логин: ");
        String login = readUserInput();
        System.out.print("Введите новый пароль: ");
        String password = readUserInput();

        if (userId != null)
            getUserService().updateById(userId, login, password);
        else
            getUserService().updateCurrentUser(login, password);
        System.out.println("Успешно\n");
        backToMenu(MenusEnum.UserMenu, getIsWait());
    }
}
