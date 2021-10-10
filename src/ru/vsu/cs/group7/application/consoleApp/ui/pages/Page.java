package ru.vsu.cs.group7.application.consoleApp.ui.pages;

import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.MenusEnum;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.BaseMenu;
import ru.vsu.cs.group7.exception.ApplicationException;

import java.util.Scanner;

public abstract class Page {
    private final String header;
    //    private Consumer<Void> action;
    private final Scanner scanner;
    private final BaseMenu parentMenu;
    protected boolean isWait;

    public Page(BaseMenu parentMenu, String header) {
        this.header = header;
        this.scanner = parentMenu.getController().getScanner();
        this.parentMenu = parentMenu;
    }

    public abstract void openPage() throws ApplicationException;

    public void show() throws ApplicationException {
        System.out.println(header);
        openPage();
//        try {
//            openPage();
//        } catch (ApplicationException e) {
//            System.out.println(e.getMessage() + '\n');
//
//            System.out.println("Для продолжения введите любой символ...");
//            scanner.next();
//        }
    }

//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }

//    public Consumer<Void> getAction() {
//        return action;
//    }
//
//    public void setAction(Consumer<Void> action) {
//        this.action = action;
//    }

    public BaseMenu getParentMenu() {
        return parentMenu;
    }

    public void backToMenu(BaseMenu currMenu, MenusEnum backTo, boolean wait) {
        if (wait) {
            System.out.println("Для возврата в меню введите любой символ...");
            currMenu.getController().getScanner().next();
        }
        currMenu.getController().getMenuManager().switchMenu(backTo, currMenu.getController());
    }

    public Scanner getScanner() {
        return scanner;
    }

    public boolean getIsWait() {
        return isWait;
    }
}
