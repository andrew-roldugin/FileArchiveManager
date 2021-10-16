package ru.vsu.cs.group7.application.consoleApp;

import ru.vsu.cs.group7.application.Application;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.BaseMenu;

public class ConsoleApp implements Application {
    private final Controller controller = new Controller();

    @Override
    public void start(String[] args) {

        while (true)
            getCurrentMenu().printMenu();
    }

    public BaseMenu getCurrentMenu() {
        return controller.getMenuManager().getCurrentMenu();
    }
}