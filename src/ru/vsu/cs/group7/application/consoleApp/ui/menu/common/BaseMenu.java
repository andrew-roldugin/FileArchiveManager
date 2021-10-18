package ru.vsu.cs.group7.application.consoleApp.ui.menu.common;

import ru.vsu.cs.group7.application.consoleApp.Controller;
import ru.vsu.cs.group7.application.consoleApp.ui.pages.Page;
import ru.vsu.cs.group7.exception.ActionCancelled;
import ru.vsu.cs.group7.exception.ApplicationException;

import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class BaseMenu {

    protected String content;
    private Page currentPage;

    public void printMenu() {
        if (currentPage != null) {
            try {
                currentPage.show();
            } catch (ActionCancelled ex) {
                System.out.println(ex.getMessage());
                setCurrentPage(null);
            } catch (ApplicationException e) {
                System.out.println(e.getMessage() + '\n');
                if (getCurrentPage().getIsWait()) {
                    System.out.println("Для продолжения введите любой символ...");
                    getScanner().next();
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
                Controller.setScanner(new Scanner(System.in));
            }
        }
    }

    public static BaseMenu switchMenu(MenusEnum backTo) {
        return Controller.getMenuManager().switchMenu(backTo);
    }

    protected abstract void onSelect(Integer choice);

    private Integer getChoice() {
        return getScanner().nextInt();
    }

    private Scanner getScanner() {
        return Controller.getScanner();
    }

    public void setCurrentPage(Page currentPage) {
        this.currentPage = currentPage;
    }

    public Page getCurrentPage() {
        return currentPage;
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
