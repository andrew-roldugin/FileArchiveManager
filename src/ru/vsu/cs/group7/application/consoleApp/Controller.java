package ru.vsu.cs.group7.application.consoleApp;

import ru.vsu.cs.group7.application.consoleApp.config.ApplicationStorage;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.MenuManager;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.MenusEnum;
import ru.vsu.cs.group7.application.consoleApp.config.Services;
import ru.vsu.cs.group7.exception.ApplicationException;
import ru.vsu.cs.group7.model.User;

import java.util.Scanner;

public class Controller {
    private Scanner scanner;
//    private static User user;
    private final Services services;
    private final ApplicationStorage applicationStorage = new ApplicationStorage();
    private final MenuManager menuManager = new MenuManager(MenusEnum.MainMenu, this);

    public Controller() {
        this.scanner = new Scanner(System.in);
        this.services = new Services(applicationStorage);
//        this.applicationStorage = applicationStorage;
//        services.getUserService().setAppStorage(applicationStorage);
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public MenuManager getMenuManager() {
        return this.menuManager;
    }

    public Services getServices() {
        return services;
    }

    public User getUser() {
        return applicationStorage.getUser();
    }

    public boolean isLoggedIn() {
        return getUser() != null;
    }

}
