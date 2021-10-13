package ru.vsu.cs.group7.application.consoleApp.ui.pages.archive;

import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.MenusEnum;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.fabric.FileArchiveMenu;
import ru.vsu.cs.group7.exception.ActionCancelled;
import ru.vsu.cs.group7.exception.ApplicationException;

public class CreateArchivePage extends ArchivesPages {
    public CreateArchivePage(FileArchiveMenu fileArchiveMenu) {
        super(fileArchiveMenu, "==================================== Создание архива ====================================");
    }

    @Override
    public void openPage() throws ApplicationException, ActionCancelled {
        System.out.print("Введите имя архива архива: ");
        String input = readUserInput();
        getFileArchiveService().createArchive(input);
        backToMenu(getParentMenu(), MenusEnum.FileArchiveMenu, getIsWait());
    }
}
