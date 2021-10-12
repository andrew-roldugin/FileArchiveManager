package ru.vsu.cs.group7.application.consoleApp.ui.menu.fabric;

import ru.vsu.cs.group7.application.consoleApp.Controller;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.BaseMenu;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.MenusEnum;
import ru.vsu.cs.group7.application.consoleApp.ui.pages.file.AddFilePage;
import ru.vsu.cs.group7.application.consoleApp.ui.pages.file.FilesInArchivePage;
import ru.vsu.cs.group7.application.consoleApp.ui.pages.file.RemoveFilePage;
import ru.vsu.cs.group7.application.consoleApp.ui.pages.file.UpdateFilePage;
import ru.vsu.cs.group7.service.Service;

public class FileMenu extends BaseMenu {
    public FileMenu(Controller controller, Service fileService) {
        super(controller, fileService);
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
        switch (choice){
            case 0 -> exit();

            case 1 -> setCurrentPage(new FilesInArchivePage(this));
            case 2 -> setCurrentPage(new UpdateFilePage(this));
            case 3 -> setCurrentPage(new RemoveFilePage(this));
            case 4 -> setCurrentPage(new AddFilePage(this));
            case 5 -> switchMenu(MenusEnum.FileArchiveMenu); //getController().getMenuManager().switchMenu(MenusEnum.FileArchiveMenu, getController()); //getCurrentPage().backToMenu(this, MenusEnum.FileArchiveMenu, false);
            case 6 -> switchMenu(MenusEnum.UserMenu); //getController().getMenuManager().switchMenu(MenusEnum.UserMenu, getController()); //getCurrentPage().backToMenu(this, MenusEnum.UserMenu, false);
        }
    }
}
