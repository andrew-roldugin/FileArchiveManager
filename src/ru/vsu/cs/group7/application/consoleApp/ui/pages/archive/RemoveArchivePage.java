package ru.vsu.cs.group7.application.consoleApp.ui.pages.archive;

import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.MenusEnum;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.fabric.FileArchiveMenu;
import ru.vsu.cs.group7.application.consoleApp.ui.pages.Page;
import ru.vsu.cs.group7.exception.ApplicationException;

import java.util.UUID;

public class RemoveArchivePage extends ArchivesPages {
    public RemoveArchivePage(FileArchiveMenu fileArchiveMenu) {
        super(fileArchiveMenu, "==================================== Удаление архива ====================================");
    }

    @Override
    public void openPage() throws ApplicationException {
        System.out.print("Введите id архива: ");
        String input = getScanner().next();
        try {
            UUID id = UUID.fromString(input);
            getFileArchiveService().removeById(id);
            backToMenu(getParentMenu(), MenusEnum.FileArchiveMenu, getIsWait());
        }catch (IllegalArgumentException ex){
            System.out.println(ex.getMessage());
        }
    }
}
