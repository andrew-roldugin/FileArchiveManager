package ru.vsu.cs.group7.application.consoleApp.ui.menu.fabric;

import ru.vsu.cs.group7.application.consoleApp.Controller;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.BaseMenu;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.MenusEnum;
import ru.vsu.cs.group7.application.consoleApp.ui.pages.file.RemoveFilePage;
import ru.vsu.cs.group7.application.consoleApp.ui.pages.file.UpdateFilePage;
import ru.vsu.cs.group7.service.FileService;

public class SelectedFileMenu extends BaseMenu {

    private final FileService fileService;

    public SelectedFileMenu(FileService fileService) {
        this.fileService = fileService;
        String name = Controller.getContext().getCurrentFile().getName();

        this.content = String.format("""
                 _________________________________________________________________________________
                / Открыт файл: %s\\
                |---------------------------------------------------------------------------------|
                | 1) Переименовать файл;                                                          |
                | 2) Удалить файл;																  |
                | 3) -> Назад к меню архивы;    												      |
                |               ___________________________________________________               |
                | 0) Завершение работы;                                                           |
                |_________________________________________________________________________________|
                """, name + " ".repeat(66 - name.length()));
    }

    @Override
    public void onSelect(Integer choice) {
        switch (choice) {
            case 0 -> exit();

            case 1 -> setCurrentPage(new UpdateFilePage(fileService));
            case 2 -> setCurrentPage(new RemoveFilePage(fileService));

            case 3 -> {
                Controller.getContext().setCurrentFile(null);
                Controller.getContext().setCurrentArchive(null);
                switchMenu(MenusEnum.FileArchiveMenu);
            }
        }
    }
}
