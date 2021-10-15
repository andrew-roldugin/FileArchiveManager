package ru.vsu.cs.group7.application.consoleApp.ui.pages.archive;

import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.MenusEnum;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.fabric.FileArchiveMenu;
import ru.vsu.cs.group7.exception.ActionCancelled;
import ru.vsu.cs.group7.exception.ApplicationException;

import java.util.UUID;

public class UpdateArchivePage extends ArchivesPages {

    public UpdateArchivePage(FileArchiveMenu fileArchiveMenu) {
        super(fileArchiveMenu, "================================== Обновление архива ====================================");
    }

    @Override
    public void openPage() throws ApplicationException, ActionCancelled {
        System.out.print("Введите id архива: ");
        String input = readUserInput();
        System.out.print("Теперь введите новое имя архива: ");
        String newName = readUserInput();

        try {
            Long id = Long.parseLong(input);
            getFileArchiveService().update(id, newName);
            backToMenu(getParentMenu(), MenusEnum.FileArchiveMenu, getIsWait());
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
