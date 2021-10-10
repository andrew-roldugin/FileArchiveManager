package ru.vsu.cs.group7.application.consoleApp.ui.pages;

import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.BaseMenu;
import ru.vsu.cs.group7.exception.ApplicationException;

public abstract class FirstPage extends Page {
    public FirstPage(BaseMenu parentMenu, String content) {
        super(parentMenu, content);
    }
}
