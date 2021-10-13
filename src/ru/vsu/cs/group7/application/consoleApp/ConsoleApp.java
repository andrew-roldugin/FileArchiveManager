package ru.vsu.cs.group7.application.consoleApp;

import ru.vsu.cs.group7.application.Application;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.BaseMenu;
import ru.vsu.cs.group7.service.Service;

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