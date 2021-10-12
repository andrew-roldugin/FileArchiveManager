package ru.vsu.cs.group7.application.consoleApp.ui.pages.archive;

import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.MenusEnum;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.fabric.FileArchiveMenu;
import ru.vsu.cs.group7.exception.ApplicationException;

import java.util.UUID;

public class UpdateArchivePage extends ArchivesPages{
    public UpdateArchivePage(FileArchiveMenu fileArchiveMenu) {
        super(fileArchiveMenu, "================================== Обновление архива ====================================");

    }

    @Override
    public void openPage() throws ApplicationException {
        System.out.print("Введите id архива: ");
        String input = getScanner().next();
        System.out.print("Теперь введите новое имя архива: ");
        String newName = getScanner().next();

        try {
            UUID id = UUID.fromString(input);
            getFileArchiveService().update(id, newName);
            backToMenu(getParentMenu(), MenusEnum.FileArchiveMenu, getIsWait());
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
