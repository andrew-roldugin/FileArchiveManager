package ru.vsu.cs.group7.application.consoleApp.ui.pages.user;

import ru.vsu.cs.group7.application.consoleApp.config.ApplicationStorage;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.MenusEnum;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.fabric.UserMenu;
import ru.vsu.cs.group7.exception.ApplicationException;
import ru.vsu.cs.group7.model.User;

import java.util.UUID;

public class EditUserPage extends UserPages {
    public EditUserPage(UserMenu userMenu) {
        super(userMenu, "========================== Редактирование данных пользователя ===========================");
//        setAction((input) -> {
//            System.out.print("Введите новый логин: ");
//            String login = getScanner().next();
//            System.out.print("Введите новый пароль: ");
//            String password = getScanner().next();
//
//            try {
//                User newUser = getUserService().update(login, password);
//                System.out.println("Успешно\n");
//                backToMenu(userMenu, MenusEnum.UserMenu, false);
////                userMenu.getController().getMenuManager().switchMenu(MenusEnum.UserMenu, userMenu.getController());
//
//            } catch (Exception e) {
//                System.out.println(e.getMessage() + "\n");
////                userMenu.printMenu();
//            }
//        });
    }

    @Override
    public void openPage() throws ApplicationException {
        UUID userId = null;
        if (getUserService().getApplicationStorage().getUser().getRole().equals(User.RoleEnum.Admin)){
            System.out.print("Введите id пользователя: ");
            try {
                userId = UUID.fromString(getScanner().next());
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
                return;
            }
        }
        System.out.print("Введите новый логин: ");
        String login = getScanner().next();
        System.out.print("Введите новый пароль: ");
        String password = getScanner().next();

        if (userId != null)
            getUserService().update(userId, login, password);
        else
            getUserService().update(login, password);
        System.out.println("Успешно\n");
        backToMenu(getParentMenu(), MenusEnum.UserMenu, getIsWait());
    }
}
