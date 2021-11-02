package ru.vsu.cs.group7.application.consoleApp.ui.menu.common;

import ru.vsu.cs.group7.application.consoleApp.config.Services;

public class MenuManager {
    private final Services services;
    private BaseMenu currentMenu;

    public MenuManager(MenusEnum firstMenu, Services services) {
        this.services = services;
        switchMenu(firstMenu);
    }

    public BaseMenu getCurrentMenu() {
        return currentMenu;
    }

    public void setCurrentMenu(BaseMenu currentMenu) {
        this.currentMenu = currentMenu;
    }

    public BaseMenu switchMenu(MenusEnum menuName) {
        BaseMenu menu;
        if (menuName != null)
            menu = MenuFabric.createMenu(menuName, services);
        else {
            getCurrentMenu().setCurrentPage(null);
            menu = getCurrentMenu();
        }
        setCurrentMenu(menu);
        return menu;
    }
}

