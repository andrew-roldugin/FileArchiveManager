package ru.vsu.cs.group7.application.simple_console_app.ui.menu.pages;

import ru.vsu.cs.group7.application.simple_console_app.ui.menu.common.MenusEnum;
import ru.vsu.cs.group7.application.simple_console_app.ui.menu.common.BaseMenu;

import java.util.Scanner;
import java.util.function.Consumer;

public abstract class Page {
    private String content;
    private Consumer<Void> action;
    private final Scanner scanner;
    private final BaseMenu parentMenu;

    public Page(BaseMenu parentMenu, String content) {
        this.content = content;
        this.scanner = parentMenu.getController().getScanner();
        this.parentMenu = parentMenu;
    }

    public void show(String msg){
        System.out.println(msg);
        if (this.action != null)
            this.action.accept(null);
    }
    public void show() {
        show(content);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Consumer<Void> getAction() {
        return action;
    }

    public void setAction(Consumer<Void> action) {
        this.action = action;
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
