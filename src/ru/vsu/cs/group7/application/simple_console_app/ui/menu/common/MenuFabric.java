package ru.vsu.cs.group7.application.simple_console_app.ui.menu.common;

import ru.vsu.cs.group7.application.simple_console_app.Controller;
import ru.vsu.cs.group7.application.simple_console_app.ui.menu.fabric.AuthMenu;
import ru.vsu.cs.group7.application.simple_console_app.ui.menu.fabric.FileArchiveMenu;
import ru.vsu.cs.group7.application.simple_console_app.ui.menu.fabric.FileMenu;
import ru.vsu.cs.group7.application.simple_console_app.ui.menu.fabric.UserMenu;
import ru.vsu.cs.group7.config.Services;


public class MenuFabric {

    public static BaseMenu createMenu(MenusEnum menu, Controller controller) {
        BaseMenu m = null;
        Services services = controller.getServices();
        switch (menu) {
            case MainMenu -> m = new MainMenu(controller);
            case FileMenu -> m = new FileMenu(controller, services.getFileService());
            case UserMenu -> m = new UserMenu(controller, services.getUserService());
            case FileArchiveMenu -> m = new FileArchiveMenu(controller, services.getFileArchiveService());
            case AuthMenu -> m = new AuthMenu(controller, services.getUserService());
        }
        return m;
    }
}
