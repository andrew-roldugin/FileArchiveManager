package ru.vsu.cs.group7.application.consoleApp.ui.pages;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.BaseMenu;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.MenusEnum;
import ru.vsu.cs.group7.exception.ActionCancelled;
import ru.vsu.cs.group7.exception.ApplicationException;
import ru.vsu.cs.group7.model.Entity;

import java.util.Collection;
import java.util.Scanner;

public abstract class Page {
    private final String header;
    private final Scanner scanner;
    private final BaseMenu parentMenu;
    protected boolean isWait;

    public Page(BaseMenu parentMenu, String header) {
        this.header = header;
        this.scanner = parentMenu.getController().getScanner();
        this.parentMenu = parentMenu;
    }

    public abstract void openPage() throws ApplicationException, ActionCancelled;

    public void show() throws ApplicationException, ActionCancelled {
        System.out.println(header);
        openPage();
    }

    protected String readUserInput() throws ActionCancelled {
        String next = getScanner().next();
        if (next.equals(":q"))
            throw new ActionCancelled();

        return next;
    }

    public BaseMenu getParentMenu() {
        return parentMenu;
    }

    public void backToMenu(BaseMenu currMenu, MenusEnum backTo, boolean wait) {
        if (wait) {
            System.out.println("Для возврата в меню введите любой символ...");
            scanner.next();
        }
        currMenu.switchMenu(backTo);
    }

    public Scanner getScanner() {
        return scanner;
    }

    public boolean getIsWait() {
        return isWait;
    }

    protected <T extends Entity> void renderTable(String[] header, Collection<T> collection) {
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
