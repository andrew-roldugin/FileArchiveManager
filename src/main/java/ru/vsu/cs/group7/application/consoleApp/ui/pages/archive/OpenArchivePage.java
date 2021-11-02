package ru.vsu.cs.group7.application.consoleApp.ui.pages.archive;

import ru.vsu.cs.group7.application.consoleApp.Controller;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.MenusEnum;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.fabric.FileArchiveMenu;
import ru.vsu.cs.group7.exception.ActionCancelled;
import ru.vsu.cs.group7.exception.ApplicationException;
import ru.vsu.cs.group7.model.FileArchive;
import ru.vsu.cs.group7.service.FileArchiveService;

public class OpenArchivePage extends ArchivesPages {
    public OpenArchivePage(FileArchiveService archiveService) {
        super("", archiveService);
    }

    @Override
    public void openPage() throws ApplicationException, ActionCancelled {
        System.out.print("Введите id архива: ");
        String input = readUserInput();
        try {
            Long id = Long.parseLong(input);
            FileArchive archive = getFileArchiveService().getOneById(id);
            Controller.getInstance().getContext().setCurrentArchive(archive);
            backToMenu(MenusEnum.CurrentArchiveMenu, getIsWait());
        }catch (NumberFormatException ex){
            System.out.println(ex.getMessage());
        }
    }
}
