package ru.vsu.cs.group7.application.consoleApp.ui.menu.common;

import ru.vsu.cs.group7.application.consoleApp.Controller;
import ru.vsu.cs.group7.exception.ApplicationException;

public class MenuManager {
    private BaseMenu currentMenu;
//    private final Map<MenusEnum, BaseMenu> menus = new HashMap<>();

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
//        BaseMenu m;
//        if ((m = menus.get(menu)) == null) {
//            m = MenuFabric.createMenu(menu, controller);
//            menus.put(menu, m);
//            System.out.println("создаем новый класс для меню" + m);
//        }
//        System.out.println("загружено из кеша" + menu);
//        this.currentMenu = m;
//        return m;
        BaseMenu menu = MenuFabric.createMenu(menuName, controller);
        setCurrentMenu(menu);

//        if (getCurrentMenu() != null)

//        if (!(menu instanceof MainMenu))
//            this.currentMenu.printMenu();

        return menu;
    }
}

