package ru.vsu.cs.group7.application.consoleApp.ui.pages.user;

import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.MenusEnum;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.fabric.UserMenu;
import ru.vsu.cs.group7.exception.ActionCancelled;
import ru.vsu.cs.group7.exception.ApplicationException;
import ru.vsu.cs.group7.model.User;

import java.util.UUID;

public class RemoveUserPage extends UserPages {

    public RemoveUserPage(UserMenu userMenu) {
        super(userMenu, "=================================== Удаление аккаунта ===================================");
    }

    @Override
    public void openPage() throws ApplicationException, ActionCancelled {
        if (getUserService().getApplicationContext().getUser().getRole().equals(User.RoleEnum.Admin)) {

            System.out.print("Введите логин или id пользователя: ");
            String input = readUserInput();
            try {
                Long id = Long.parseLong(input);
                getUserService().removeUserById(id);
                System.out.println("Успешно\n");
                backToMenu(getParentMenu(), MenusEnum.MainMenu, getIsWait());
            } catch (NumberFormatException ex) {
                getUserService().removeUserByLogin(input);
                System.out.println("Успешно\n");
                backToMenu(getParentMenu(), MenusEnum.MainMenu, getIsWait());
            }
        } else {
            getUserService().removeCurrentUser();
            System.out.println("Успешно\n");
            backToMenu(getParentMenu(), MenusEnum.MainMenu, getIsWait());
        }
    }
}
