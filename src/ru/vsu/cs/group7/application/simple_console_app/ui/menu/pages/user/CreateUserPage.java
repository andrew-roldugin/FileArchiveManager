package ru.vsu.cs.group7.application.simple_console_app.ui.menu.pages.user;

import ru.vsu.cs.group7.application.simple_console_app.ui.menu.common.MenusEnum;
import ru.vsu.cs.group7.application.simple_console_app.ui.menu.fabric.AuthMenu;
import ru.vsu.cs.group7.exception.UserNotFoundException;
import ru.vsu.cs.group7.model.User;

public class CreateUserPage extends UserPages {

    public CreateUserPage(AuthMenu authMenu){
        super(authMenu,
                "====================================== Регистрация ======================================"
        );
        setAction((input) -> {
            System.out.print("Введите логин: ");
            String login = getScanner().next();
            System.out.print("Введите пароль: ");
            String password = getScanner().next();

            try {
                User user = getUserService().createUser(login, password);
                getUserService().login(user);
                System.out.println("Успешно\n");
                backToMenu(authMenu, MenusEnum.UserMenu, false);
//                authMenu.getController().getMenuManager().switchMenu(MenusEnum.UserMenu, authMenu.getController());
            } catch (Exception | UserNotFoundException e) {
                System.out.println(e.getMessage() + "\n");
                authMenu.printMenu();
            }
        });
    }
}
