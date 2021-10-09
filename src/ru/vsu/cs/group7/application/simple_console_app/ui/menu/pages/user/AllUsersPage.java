package ru.vsu.cs.group7.application.simple_console_app.ui.menu.pages.user;

import ru.vsu.cs.group7.application.simple_console_app.ui.menu.common.MenusEnum;
import ru.vsu.cs.group7.application.simple_console_app.ui.menu.fabric.UserMenu;
import ru.vsu.cs.group7.exception.UserNotAuthorizedException;
import ru.vsu.cs.group7.model.User;

import java.util.Collection;

public class AllUsersPage extends UserPages {
    public AllUsersPage(UserMenu userMenu) {
        super(userMenu, "=========================== Информация обо всех пользователях ===========================");
        setAction(input -> {
            try {
                StringBuilder sb = new StringBuilder();
                Collection<User> users = getUserService().findAll();
                sb.append("id\tlogin").append("\n");
                users.forEach(user -> {
                    sb.append(user.toString()).append("\n");
                });
                System.out.println(sb);
                backToMenu(userMenu, MenusEnum.UserMenu, true);

//                userMenu.getController().getMenuManager().switchMenu(MenusEnum.UserMenu, userMenu.getController());
            } catch (UserNotAuthorizedException e) {
                System.out.println(e.getMessage() + "\n");
                userMenu.printMenu();
            }
        });
    }
}
