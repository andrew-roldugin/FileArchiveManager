package ru.vsu.cs.group7.application.consoleApp.ui.pages.user;

import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.MenusEnum;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.fabric.UserMenu;
import ru.vsu.cs.group7.exception.NotAllowedExceptions;
import ru.vsu.cs.group7.exception.UserNotAuthorizedException;
import ru.vsu.cs.group7.model.User;

import java.util.Collection;

public class AllUsersPage extends UserPages {
    public AllUsersPage(UserMenu userMenu) {
        super(userMenu, "=========================== Информация обо всех пользователях ===========================");
        this.isWait = true;
//        setAction(input -> {
//            try {
//                StringBuilder sb = new StringBuilder();
//                Collection<User> users = getUserService().findAll(User.RoleEnum.Admin);
//                sb.append("id\tlogin").append("\n");
//                users.forEach(user -> {
//                    sb.append(user.toString()).append("\n");
//                });
//                System.out.println(sb);
//                backToMenu(userMenu, MenusEnum.UserMenu, true);
//
////                userMenu.getController().getMenuManager().switchMenu(MenusEnum.UserMenu, userMenu.getController());
//            } catch (UserNotAuthorizedException | NotAllowedExceptions e) {
//                System.out.println(e.getMessage() + "\n");
////                userMenu.printMenu();
//            }
//        });
    }

    @Override
    public void openPage() throws UserNotAuthorizedException, NotAllowedExceptions {
        StringBuilder sb = new StringBuilder();
        Collection<User> users = getUserService().findAll(User.RoleEnum.Admin);
        sb.append("id\tlogin").append("\n");
        users.forEach(user -> {
            sb.append(user.toString()).append("\n");
        });
        System.out.println(sb);
        backToMenu(getParentMenu(), MenusEnum.UserMenu, getIsWait());
    }
}
