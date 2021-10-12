package ru.vsu.cs.group7.application.consoleApp.ui.pages.archive;

import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.MenusEnum;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.fabric.FileArchiveMenu;
import ru.vsu.cs.group7.application.consoleApp.ui.pages.Page;
import ru.vsu.cs.group7.application.consoleApp.ui.pages.file.FilesInArchivePage;
import ru.vsu.cs.group7.exception.ApplicationException;

import java.util.UUID;

public class CreateArchivePage extends ArchivesPages {
    public CreateArchivePage(FileArchiveMenu fileArchiveMenu) {
        super(fileArchiveMenu, "==================================== Создание архива ====================================");
    }

    @Override
    public void openPage() throws ApplicationException {
        System.out.print("Введите имя архива архива: ");
        String input = getScanner().next();
        getFileArchiveService().createArchive(input);
        backToMenu(getParentMenu(), MenusEnum.FileArchiveMenu, getIsWait());
    }
}
