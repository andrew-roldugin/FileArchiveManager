package ru.vsu.cs.group7.application.consoleApp.ui.pages.user;

import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.MenusEnum;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.fabric.UserMenu;
import ru.vsu.cs.group7.exception.ActionCancelled;
import ru.vsu.cs.group7.exception.ApplicationException;
import ru.vsu.cs.group7.model.User;

import java.util.UUID;

public class EditUserPage extends UserPages {

    public EditUserPage(UserMenu userMenu) {
        super(userMenu, "========================== Редактирование данных пользователя ===========================");
    }

    @Override
    public void openPage() throws ApplicationException, ActionCancelled {
        Long userId = null;
        if (getUserService().getApplicationContext().getUser().getRole().equals(User.RoleEnum.Admin)) {
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
        backToMenu(getParentMenu(), MenusEnum.UserMenu, getIsWait());
    }
}
