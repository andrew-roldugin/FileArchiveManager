package ru.vsu.cs.group7.application.consoleApp.ui.pages.archive;

import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.MenusEnum;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.fabric.FileArchiveMenu;
import ru.vsu.cs.group7.exception.ActionCancelled;
import ru.vsu.cs.group7.exception.ApplicationException;
import ru.vsu.cs.group7.service.FileArchiveService;

public class CreateArchivePage extends ArchivesPages {

    public CreateArchivePage(FileArchiveService archiveService) {
        super("==================================== Создание архива ====================================", archiveService);
    }

    @Override
    public void openPage() throws ApplicationException, ActionCancelled {
        System.out.print("Введите имя архива: ");
        String input = readUserInput();
        getFileArchiveService().createArchive(input);
        backToMenu(MenusEnum.FileArchiveMenu, getIsWait());
    }
}
