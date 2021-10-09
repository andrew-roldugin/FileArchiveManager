package ru.vsu.cs.group7.application.simple_console_app.ui.menu.fabric;

import ru.vsu.cs.group7.application.simple_console_app.Controller;
import ru.vsu.cs.group7.application.simple_console_app.ui.menu.common.BaseMenu;
import ru.vsu.cs.group7.service.Service;

public class FileMenu extends BaseMenu {
    public FileMenu(Controller controller, Service fileService) {
        super(controller, fileService);
    }

    @Override
    public void onSelect(Integer choice) {

    }
}
