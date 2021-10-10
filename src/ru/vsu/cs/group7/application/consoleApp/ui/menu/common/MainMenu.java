package ru.vsu.cs.group7.application.consoleApp.ui.menu.common;

import ru.vsu.cs.group7.application.consoleApp.Controller;
import ru.vsu.cs.group7.exception.ApplicationException;

public class MainMenu extends BaseMenu {

    public MainMenu(Controller controller) {
        super(controller, null);
    }

    @Override
    public void printMenu() {
        BaseMenu newMenu;
        if (getController().isLoggedIn())
            newMenu = switchMenu(MenusEnum.UserMenu);
        else
            newMenu = switchMenu(MenusEnum.AuthMenu);

//        newMenu.printMenu();
    }

    private BaseMenu switchMenu(MenusEnum menu){
        return getController().getMenuManager().switchMenu(menu, getController());
    }

    @Override
    public void onSelect(Integer choice) {
//        getController().getMenuManager().getCurrentMenu().onSelect(choice);
    }
}
