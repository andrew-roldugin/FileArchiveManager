package ru.vsu.cs.group7.application.consoleApp.ui.menu.common;

import ru.vsu.cs.group7.application.consoleApp.Controller;
import ru.vsu.cs.group7.application.consoleApp.ui.pages.Page;
import ru.vsu.cs.group7.service.Service;

public abstract class BaseMenu {
    private final Controller controller;
    private Page currentPage;
    private final Service service;

    public BaseMenu(Controller controller, Service service) {
        this.controller = controller;
        this.service = service;
    }

    public void printMenu() {
        currentPage.show();
    }

    public abstract void onSelect(Integer choice);

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
        currentPage.exit();
    }
}
