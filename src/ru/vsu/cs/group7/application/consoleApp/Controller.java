package ru.vsu.cs.group7.application.consoleApp;

import ru.vsu.cs.group7.application.consoleApp.config.ApplicationContext;
import ru.vsu.cs.group7.application.consoleApp.config.Services;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.MenuManager;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.MenusEnum;

import java.util.Scanner;

public class Controller {

    private static Scanner scanner = new Scanner(System.in);
    private static final ApplicationContext context = new ApplicationContext();
    private static final Services services;
    private static final MenuManager menuManager = new MenuManager(MenusEnum.MainMenu);

    static {
        services = new Services(context);
    }

    public static Scanner getScanner() {
        return scanner;
    }

    public static void setScanner(Scanner scanner) {
        Controller.scanner = scanner;
    }

    public static MenuManager getMenuManager() {
        return menuManager;
    }

    public static Services getServices() {
        return services;
    }

    public static ApplicationContext getContext() {
        return context;
    }

    public static boolean isLoggedIn() {
        return context.isLoggedIn();
    }
}
