package ru.vsu.cs.group7.application.consoleApp.ui.menu.common;

import ru.vsu.cs.group7.application.consoleApp.Controller;

public class MenuManager {
    private BaseMenu currentMenu;

    public MenuManager(MenusEnum firstMenu, Controller controller) {
        switchMenu(firstMenu, controller);
    }

    public BaseMenu getCurrentMenu() {
        return currentMenu;
    }

    public void setCurrentMenu(BaseMenu currentMenu) {
        this.currentMenu = currentMenu;
    }

    public BaseMenu switchMenu(MenusEnum menuName, Controller controller) {
        BaseMenu menu;
        if (menuName != null)
            menu = MenuFabric.createMenu(menuName, controller);
        else {
            getCurrentMenu().setCurrentPage(null);
            menu = getCurrentMenu();
        }
        setCurrentMenu(menu);
        return menu;
    }
}

