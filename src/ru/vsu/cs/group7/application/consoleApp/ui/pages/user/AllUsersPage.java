package ru.vsu.cs.group7.application.consoleApp.ui.pages.user;

import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.MenusEnum;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.fabric.UserMenu;
import ru.vsu.cs.group7.exception.ActionCancelled;
import ru.vsu.cs.group7.exception.ApplicationException;
import ru.vsu.cs.group7.model.User;

import java.util.Collection;

public class AllUsersPage extends UserPages {

    public AllUsersPage(UserMenu userMenu) {
        super(userMenu, "=========================== Информация обо всех пользователях ===========================");
        this.isWait = true;
    }

    @Override
    public void openPage() throws ApplicationException, ActionCancelled {
        StringBuilder sb = new StringBuilder();
        Collection<User> users = getUserService().findAll(User.RoleEnum.Admin);
        sb.append("id\tлогин\tроль").append("\n");
        users.forEach(user -> {
            sb.append(user.toString()).append("\n");
        });
        System.out.println(sb);
        backToMenu(getParentMenu(), MenusEnum.UserMenu, getIsWait());
    }
}
