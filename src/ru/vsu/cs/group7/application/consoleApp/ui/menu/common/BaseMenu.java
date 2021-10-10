package ru.vsu.cs.group7.application.consoleApp.ui.menu.common;

import ru.vsu.cs.group7.application.consoleApp.Controller;
import ru.vsu.cs.group7.application.consoleApp.ui.pages.Page;
import ru.vsu.cs.group7.exception.ApplicationException;
import ru.vsu.cs.group7.service.Service;

import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class BaseMenu {
    private final Controller controller;
    protected String content;
    private Page currentPage;
    private final Service service;

    public BaseMenu(Controller controller, Service service) {
        this.controller = controller;
        this.service = service;
    }

    public void printMenu() {
        if (currentPage != null) {
            try {
                currentPage.show();
            } catch (ApplicationException e) {
                System.out.println(e.getMessage() + '\n');
                if (getCurrentPage().getIsWait()) {
                    System.out.println("Для продолжения введите любой символ...");
                    controller.getScanner().next();
                }
                setCurrentPage(null);
            }
        } else {
            System.out.println(content);
            try {
                System.out.print("Введите пункт меню: ");
                Integer choice = getChoice();
                onSelect(choice);
            } catch (InputMismatchException ex) {
                System.out.println("Некорректный ввод при выборе пункта. Ожидалось число");
                controller.setScanner(new Scanner(System.in));
            }
        }
    }

    protected abstract void onSelect(Integer choice);

    public Integer getChoice() {
        return controller.getScanner().nextInt();
    }

    public Controller getController() {
        return controller;
    }

    public void setCurrentPage(Page currentPage) {
        this.currentPage = currentPage;
    }

    public Page getCurrentPage() {
        return currentPage;
    }

    public Service getService() {
        return service;
    }

    public void exit() {
        try {
            System.out.println("Завершение работы...");
            Thread.sleep(500);
            System.exit(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
