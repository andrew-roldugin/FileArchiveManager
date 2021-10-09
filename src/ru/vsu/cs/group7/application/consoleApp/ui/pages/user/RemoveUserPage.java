package ru.vsu.cs.group7.application.consoleApp.ui.pages.user;

import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.MenusEnum;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.fabric.UserMenu;
import ru.vsu.cs.group7.exception.UserNotAuthorizedException;
import ru.vsu.cs.group7.exception.UserNotFoundException;

import java.util.UUID;

public class RemoveUserPage extends UserPages {
    public RemoveUserPage(UserMenu userMenu) {
        super(userMenu, "=================================== Удаление аккаунта ===================================");
        setAction(x -> {
            System.out.print("Введите логин или id пользователя: ");
            String input = getScanner().next();

            try {
                UUID id = UUID.fromString(input);
                getUserService().removeUserById(id);
                System.out.println("Успешно\n");
                backToMenu(userMenu, MenusEnum.UserMenu, false);
            } catch (IllegalArgumentException ex) {
                try {
                    getUserService().removeUserByLogin(input);
                    System.out.println("Успешно\n");
                    backToMenu(userMenu, MenusEnum.UserMenu, false);
                } catch (UserNotAuthorizedException | UserNotFoundException e) {
                    System.out.println(e.getMessage() + "\n");
                    userMenu.printMenu();
                }
            } catch (UserNotFoundException | UserNotAuthorizedException e) {
                System.out.println(e.getMessage() + "\n");
                userMenu.printMenu();
            }
        });
    }
}
