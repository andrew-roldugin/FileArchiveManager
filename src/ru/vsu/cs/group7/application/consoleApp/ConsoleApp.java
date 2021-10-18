package ru.vsu.cs.group7.application.consoleApp;

import ru.vsu.cs.group7.application.Application;
import ru.vsu.cs.group7.application.consoleApp.config.ApplicationContext;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.BaseMenu;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.MenuManager;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.MenusEnum;

public class ConsoleApp implements Application {

    @Override
    public void start(String[] args) {
        while (true)
            getCurrentMenu().printMenu();
    }

    public BaseMenu getCurrentMenu() {
        return Controller.getMenuManager().getCurrentMenu();
    }
}