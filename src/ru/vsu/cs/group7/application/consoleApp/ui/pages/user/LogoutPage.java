package ru.vsu.cs.group7.application.consoleApp.ui.pages.user;

import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.MenusEnum;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.fabric.UserMenu;

public class LogoutPage extends UserPages {

    public LogoutPage(UserMenu userMenu) {
        super(userMenu, "");
        setAction(input -> {
            System.out.println("Выполняется выход...");
            getUserService().logout();
            System.out.println("Успешно\n");
            backToMenu(userMenu, MenusEnum.AuthMenu, false);
//            userMenu.getController().getMenuManager().switchMenu(MenusEnum.AuthMenu, userMenu.getController());
        });
    }
}
