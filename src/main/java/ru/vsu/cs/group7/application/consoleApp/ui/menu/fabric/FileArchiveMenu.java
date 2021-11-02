package ru.vsu.cs.group7.application.consoleApp.ui.menu.fabric;

import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.BaseMenu;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.MenusEnum;
import ru.vsu.cs.group7.application.consoleApp.ui.pages.archive.*;
import ru.vsu.cs.group7.service.FileArchiveService;

public class FileArchiveMenu extends BaseMenu {

    private final FileArchiveService archiveService;

    public FileArchiveMenu(FileArchiveService archiveService) {
        this.archiveService = archiveService;
        this.content = """
                 _________________________________________________________________________________
                /								   Меню архивов     							  \\
                |---------------------------------------------------------------------------------|
                | 1) Вывести все архивы;                                                          |
                | 2) Переименовать архив;                                                         |
                | 3) Удалить архив;																  |
                | 4) Создать новый архив;														  |
                | 5) Открыть архив;                                                               |
                | 6) -> К меню файлы;															  |
                | 7) -> К меню пользователи;													  |
                |               ___________________________________________________               |
                | 0) Завершение работы;                                                           |
                |_________________________________________________________________________________|
                """;
    }

    @Override
    public void onSelect(Integer choice) {
        switch (choice) {
            case 0 -> exit();

            case 1 -> setCurrentPage(new AllArchivesPage(archiveService));
            case 2 -> setCurrentPage(new UpdateArchivePage(archiveService));
            case 3 -> setCurrentPage(new RemoveArchivePage(archiveService));
            case 4 -> setCurrentPage(new CreateArchivePage(archiveService));
            case 5 -> setCurrentPage(new OpenArchivePage(archiveService));

            case 6 -> switchMenu(MenusEnum.FileMenu);
            case 7 -> switchMenu(MenusEnum.UserMenu);
        }
    }

    public FileArchiveService getArchiveService() {
        return archiveService;
    }
}
