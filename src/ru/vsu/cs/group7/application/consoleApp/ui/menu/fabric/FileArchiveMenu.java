package ru.vsu.cs.group7.application.consoleApp.ui.menu.fabric;

import ru.vsu.cs.group7.application.consoleApp.Controller;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.BaseMenu;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.MenusEnum;
import ru.vsu.cs.group7.application.consoleApp.ui.pages.archive.AllArchivesPage;
import ru.vsu.cs.group7.application.consoleApp.ui.pages.archive.CreateArchivePage;
import ru.vsu.cs.group7.application.consoleApp.ui.pages.archive.RemoveArchivePage;
import ru.vsu.cs.group7.application.consoleApp.ui.pages.archive.UpdateArchivePage;
import ru.vsu.cs.group7.service.Service;

public class FileArchiveMenu extends BaseMenu {

    public FileArchiveMenu(Controller controller, Service fileArchiveService) {
        super(controller, fileArchiveService);
        this.content = """
                 _________________________________________________________________________________
                /								   Меню архивов     							  \\
                |---------------------------------------------------------------------------------|
                | 1) Вывести все архивы;                                                          |
                | 2) Переименовать архив;                                                         |
                | 3) Удалить архив;																  |
                | 4) Создать новый архив;														  |
                | 5) -> К меню файлы;															  |
                | 6) -> К меню пользователи;													  |
                |               ___________________________________________________               |
                | 0) Завершение работы;                                                           |
                |_________________________________________________________________________________|
                """;
    }

    @Override
    public void onSelect(Integer choice) {
        switch (choice) {
            case 0 -> exit();

            case 1 -> setCurrentPage(new AllArchivesPage(this));
            case 2 -> setCurrentPage(new UpdateArchivePage(this));
            case 3 -> setCurrentPage(new RemoveArchivePage(this));
            case 4 -> setCurrentPage(new CreateArchivePage(this));

            case 5 -> switchMenu(MenusEnum.FileMenu);
            case 6 -> switchMenu(MenusEnum.UserMenu);
        }
    }
}
