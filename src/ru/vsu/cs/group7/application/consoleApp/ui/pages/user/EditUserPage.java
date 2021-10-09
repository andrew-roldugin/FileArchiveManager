package ru.vsu.cs.group7.application.consoleApp.ui.pages.user;

import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.MenusEnum;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.fabric.UserMenu;
import ru.vsu.cs.group7.model.User;

public class EditUserPage extends UserPages {
    public EditUserPage(UserMenu userMenu) {
        super(userMenu, "========================== Редактирование данных пользователя ===========================");
        setAction((input) -> {
            System.out.print("Введите новый логин: ");
            String login = getScanner().next();
            System.out.print("Введите новый пароль: ");
            String password = getScanner().next();

            try {
                User newUser = getUserService().update(login, password);
                System.out.println("Успешно\n");
                backToMenu(userMenu, MenusEnum.UserMenu, false);
//                userMenu.getController().getMenuManager().switchMenu(MenusEnum.UserMenu, userMenu.getController());

            } catch (Exception e) {
                System.out.println(e.getMessage() + "\n");
                userMenu.printMenu();
            }
        });
    }
}
