package ru.vsu.cs.group7.application.consoleApp.ui.pages.user;

import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.MenusEnum;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.fabric.AuthMenu;
import ru.vsu.cs.group7.exception.ActionCancelled;
import ru.vsu.cs.group7.exception.ApplicationException;

public class LoginPage extends UserPages {

    public LoginPage(AuthMenu authMenu) {
        super(authMenu, "====================================== Авторизация ======================================");
//        setAction((input) -> {
//            System.out.print("Введите логин: ");
//            String login = getScanner().next();
//            System.out.print("Введите пароль: ");
//            String password = getScanner().next();
//
//            try {
//                getUserService().login(login, password);
//                System.out.println("Успешно\n");
//                backToMenu(authMenu, MenusEnum.UserMenu, false);
////                authMenu.getController().getMenuManager().switchMenu(MenusEnum.UserMenu, authMenu.getController());
//            } catch (ApplicationException e) {
//                System.out.println(e.getMessage() + "\n");
////                authMenu.printMenu();
//            }
//        });
    }

    @Override
    public void openPage() throws ApplicationException, ActionCancelled {
        System.out.print("Введите логин: ");
        String login = readUserInput();
        System.out.print("Введите пароль: ");
        String password = readUserInput();

        getUserService().login(login, password);
        System.out.println("Успешно\n");
        backToMenu(getParentMenu(), MenusEnum.UserMenu, getIsWait());
    }
}
