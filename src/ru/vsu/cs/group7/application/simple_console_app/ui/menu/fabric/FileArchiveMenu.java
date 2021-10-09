package ru.vsu.cs.group7.application.simple_console_app.ui.menu.fabric;

import ru.vsu.cs.group7.application.simple_console_app.Controller;
import ru.vsu.cs.group7.application.simple_console_app.ui.menu.common.BaseMenu;
import ru.vsu.cs.group7.application.simple_console_app.ui.menu.pages.archive.AllArchivesPage;
import ru.vsu.cs.group7.application.simple_console_app.ui.menu.common.MenusEnum;
import ru.vsu.cs.group7.application.simple_console_app.ui.menu.pages.FirstPage;
import ru.vsu.cs.group7.application.simple_console_app.ui.menu.pages.archive.CreateArchivePage;
import ru.vsu.cs.group7.application.simple_console_app.ui.menu.pages.archive.RemoveArchivePage;
import ru.vsu.cs.group7.application.simple_console_app.ui.menu.pages.archive.UpdateArchivePage;
import ru.vsu.cs.group7.service.Service;

public class FileArchiveMenu extends BaseMenu {
    private final String content = """
            |---------------------------------------------------------------------------------|
            | 1) Вывести все архивы;                                                          |
            | 2) Переименовать архив;                                                         |
            | 3) Удалить архив;																  |
            | 4) Создать новый архив;														  |
            | 5) К меню файлы;																  |
            | 5) К меню пользователи;														  |
            |               ___________________________________________________               |
            |0) Завершение работы;                                                            |
            |_________________________________________________________________________________|
            """;

    public FileArchiveMenu(Controller controller, Service fileArchiveService) {
        super(controller, fileArchiveService);
        setCurrentPage(new FirstPage(this, content));
    }

    @Override
    public void onSelect(Integer choice) {
        switch (choice){
            case 1 -> new AllArchivesPage(this);
            case 2 -> new UpdateArchivePage(this);
            case 3 -> new RemoveArchivePage(this);
            case 4 -> new CreateArchivePage(this);
            case 5 -> getCurrentPage().backToMenu(this, MenusEnum.FileMenu, false);
        }
    }
}
