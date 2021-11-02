package ru.vsu.cs.group7.application.consoleApp.ui.pages;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import ru.vsu.cs.group7.application.consoleApp.Controller;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.BaseMenu;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.MenusEnum;
import ru.vsu.cs.group7.exception.ActionCancelled;
import ru.vsu.cs.group7.exception.ApplicationException;
import ru.vsu.cs.group7.model.Entity;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Scanner;

public abstract class Page {

    private final String header;
    protected boolean isWait;

    public Page(String header) {
        this.header = header;
    }

    public abstract void openPage() throws ApplicationException, ActionCancelled, SQLException, NoSuchFieldException, IllegalAccessException;

    public void show() throws ApplicationException, ActionCancelled, SQLException, NoSuchFieldException, IllegalAccessException {
        System.out.println(header);
        openPage();
    }

    protected String readUserInput() throws ActionCancelled {
        String next = getScanner().next();
        if (next.equals(":q"))
            throw new ActionCancelled();

        return next;
    }

    public static void backToMenu(MenusEnum switchTo, boolean wait) {
        if (wait) {
            System.out.println("Для возврата в меню введите любой символ...");
            getScanner().next();
        }
        BaseMenu.switchMenu(switchTo);
    }

    public static Scanner getScanner() {
        return Controller.getInstance().getScanner();
    }

    public boolean getIsWait() {
        return isWait;
    }

    protected <T extends Entity> void printTable(String[] header, Collection<T> collection) {
        AsciiTable at = new AsciiTable();
        at.addRule();
        at.addRow(header);
        at.setTextAlignment(TextAlignment.CENTER);
        at.addRule();
        collection.forEach(item -> {
            at.addRow(item.toString().split("\t"));
            at.addRule();
        });
        System.out.println(at.render());
    }
}
