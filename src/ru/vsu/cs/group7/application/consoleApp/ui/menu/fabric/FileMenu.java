package ru.vsu.cs.group7.application.consoleApp.ui.menu.fabric;

import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.BaseMenu;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.MenusEnum;
import ru.vsu.cs.group7.application.consoleApp.ui.pages.file.AddFilePage;
import ru.vsu.cs.group7.application.consoleApp.ui.pages.file.FilesInArchivePage;
import ru.vsu.cs.group7.application.consoleApp.ui.pages.file.RemoveFilePage;
import ru.vsu.cs.group7.application.consoleApp.ui.pages.file.UpdateFilePage;
import ru.vsu.cs.group7.service.FileService;
import ru.vsu.cs.group7.service.Service;

public class FileMenu extends BaseMenu {
    private final FileService fileService;

    public FileMenu(FileService fileService) {
        this.fileService = fileService;
        this.content = """
                 _________________________________________________________________________________
                /								 	Меню файлов		 							  \\
                |---------------------------------------------------------------------------------|
                | 1) Список файлов в архиве;													  |
                | 2) Переименовать файл;														  |
                | 3) Удалить файл; 																  |
                | 4) Добавить файл;																  |
                | 5) -> Перейти в меню архивы;												      |
                | 6) -> Перейти в меню пользователи;											  |
                |               ___________________________________________________               |
                | 0) Завершение работы															  |
                |_________________________________________________________________________________|
                """;
    }

    @Override
    public void onSelect(Integer choice) {
        switch (choice) {
            case 0 -> exit();

            case 1 -> setCurrentPage(new FilesInArchivePage(fileService));
            case 2 -> setCurrentPage(new UpdateFilePage(fileService));
            case 3 -> setCurrentPage(new RemoveFilePage(fileService));
            case 4 -> setCurrentPage(new AddFilePage(fileService));

            case 5 -> switchMenu(MenusEnum.FileArchiveMenu);
            case 6 -> switchMenu(MenusEnum.UserMenu);
        }
    }
}
