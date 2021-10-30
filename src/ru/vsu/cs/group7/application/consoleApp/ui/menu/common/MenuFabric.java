package ru.vsu.cs.group7.application.consoleApp.ui.menu.common;

import ru.vsu.cs.group7.application.consoleApp.Controller;
import ru.vsu.cs.group7.application.consoleApp.config.Services;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.fabric.*;

public class MenuFabric {

    public static BaseMenu createMenu(MenusEnum menu, Services services) {
        BaseMenu m = null;
        switch (menu) {
            case MainMenu -> m = new MainMenu();
            case FileMenu -> m = new FileMenu(services.getFileService());
            case UserMenu -> m = new UserMenu(services.getUserService());
            case FileArchiveMenu -> m = new FileArchiveMenu(services.getFileArchiveService());
            case AuthMenu -> m = new AuthMenu(services.getUserService());
            case CurrentArchiveMenu -> m = new CurrentArchiveMenu(services.getFileArchiveService(), services.getFileService());
            case CurrentFileMenu -> m = new SelectedFileMenu(services.getFileService());
        }
        return m;
    }
}
