package ru.vsu.cs.group7.application.consoleApp.ui.menu.common;

import ru.vsu.cs.group7.application.consoleApp.Controller;

public class MainMenu extends BaseMenu {

    @Override
    public void printMenu() {
        if (Controller.getInstance().isLoggedIn())
            switchMenu(MenusEnum.UserMenu);
        else
            switchMenu(MenusEnum.AuthMenu);
    }

    @Override
    public void onSelect(Integer choice) {}
}
