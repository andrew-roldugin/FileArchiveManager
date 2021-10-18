package ru.vsu.cs.group7.application.consoleApp.ui.pages.archive;

import ru.vsu.cs.group7.application.consoleApp.Controller;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.MenusEnum;
import ru.vsu.cs.group7.exception.ActionCancelled;
import ru.vsu.cs.group7.exception.ApplicationException;
import ru.vsu.cs.group7.model.FileArchive;
import ru.vsu.cs.group7.service.FileArchiveService;

public class RemoveArchivePage extends ArchivesPages {
    public RemoveArchivePage(FileArchiveService archiveService) {
        super("==================================== Удаление архива ====================================", archiveService);
    }

    @Override
    public void openPage() throws ApplicationException, ActionCancelled {
        FileArchive currentArchive = Controller.getContext().getCurrentArchive();
        if (currentArchive != null) {
            getFileArchiveService().removeById(currentArchive.getId());
            Controller.getContext().setCurrentArchive(null);
            backToMenu(MenusEnum.FileArchiveMenu, getIsWait());
            return;
        }
        System.out.print("Введите id архива: ");
        String input = readUserInput();
        try {
            Long id = Long.parseLong(input);
            getFileArchiveService().removeById(id);
            backToMenu(MenusEnum.FileArchiveMenu, getIsWait());
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
