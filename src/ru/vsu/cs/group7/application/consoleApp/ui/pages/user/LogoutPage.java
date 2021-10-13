package ru.vsu.cs.group7.application.consoleApp.ui.pages.user;

import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.MenusEnum;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.fabric.UserMenu;
import ru.vsu.cs.group7.exception.ActionCancelled;
import ru.vsu.cs.group7.exception.ApplicationException;

public class LogoutPage extends UserPages {

    public LogoutPage(UserMenu userMenu) {
        super(userMenu, "");
    }

    @Override
    public void openPage() throws ApplicationException, ActionCancelled {
        System.out.println("Выполняется выход...");
        getUserService().logout();
        System.out.println("Успешно\n");
        backToMenu(getParentMenu(), MenusEnum.AuthMenu, getIsWait());
    }
}
