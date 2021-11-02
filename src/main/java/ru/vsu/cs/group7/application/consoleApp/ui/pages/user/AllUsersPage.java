package ru.vsu.cs.group7.application.consoleApp.ui.pages.user;

import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.MenusEnum;
import ru.vsu.cs.group7.exception.ActionCancelled;
import ru.vsu.cs.group7.exception.ApplicationException;
import ru.vsu.cs.group7.model.User;
import ru.vsu.cs.group7.service.UserService;

public class AllUsersPage extends UserPages {

    public AllUsersPage(UserService userService) {
        super("=========================== Информация обо всех пользователях ===========================", userService);
        this.isWait = true;
    }

    @Override
    public void openPage() throws ApplicationException, ActionCancelled {
        printTable(new String[]{"ID", "Логин", "Роль"}, getUserService().findAll(User.RoleEnum.Admin));
        backToMenu(MenusEnum.UserMenu, getIsWait());
    }
}
