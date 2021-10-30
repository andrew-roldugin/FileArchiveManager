package ru.vsu.cs.group7.application.consoleApp;

import ru.vsu.cs.group7.application.consoleApp.config.ApplicationContext;
import ru.vsu.cs.group7.application.consoleApp.config.Services;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.MenuManager;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.MenusEnum;

import java.util.Scanner;

public class Controller {

    private Scanner scanner = new Scanner(System.in);
    private final ApplicationContext context = new ApplicationContext();
    private final Services services = new Services(context);
    private final MenuManager menuManager = new MenuManager(MenusEnum.MainMenu, services);
    private static Controller instance;

    private Controller() { }

    public static Controller getInstance() {
        if (instance == null)
            instance = new Controller();
        return instance;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public MenuManager getMenuManager() {
        return menuManager;
    }

    public Services getServices() {
        return services;
    }

    public ApplicationContext getContext() {
        return context;
    }

    public boolean isLoggedIn() {
        return context.isLoggedIn();
    }
}
