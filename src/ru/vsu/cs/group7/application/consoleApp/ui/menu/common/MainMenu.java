package ru.vsu.cs.group7.application.consoleApp.ui.menu.common;

import ru.vsu.cs.group7.application.consoleApp.Controller;

public class MainMenu extends BaseMenu {

    public MainMenu(Controller controller) {
        super(controller, null);
    }

    @Override
    public void printMenu() {
        if (getController().isLoggedIn())
            switchMenu(MenusEnum.UserMenu);
        else
            switchMenu(MenusEnum.AuthMenu);
    }

    @Override
    public void onSelect(Integer choice) { }
}
