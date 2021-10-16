package ru.vsu.cs.group7.application.consoleApp;

import ru.vsu.cs.group7.application.consoleApp.config.ApplicationContext;
import ru.vsu.cs.group7.application.consoleApp.config.Services;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.MenuManager;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.MenusEnum;
import ru.vsu.cs.group7.model.User;

import java.util.Scanner;

public class Controller {

    private Scanner scanner;
    private final Services services;
    private final ApplicationContext context = new ApplicationContext();
    private final MenuManager menuManager = new MenuManager(MenusEnum.MainMenu, this);

    public Controller() {
        this.scanner = new Scanner(System.in);
        this.services = new Services(context);
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

    public User getUser() {
        return context.getUser();
    }

    public ApplicationContext getContext() {
        return context;
    }

    public boolean isLoggedIn() {
        return context.isLoggedIn();
    }

}
