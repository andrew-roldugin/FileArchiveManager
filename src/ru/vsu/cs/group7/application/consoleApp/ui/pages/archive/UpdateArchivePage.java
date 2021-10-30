package ru.vsu.cs.group7.application.consoleApp.ui.pages.archive;

import ru.vsu.cs.group7.application.consoleApp.Controller;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.MenusEnum;
import ru.vsu.cs.group7.exception.ActionCancelled;
import ru.vsu.cs.group7.exception.ApplicationException;
import ru.vsu.cs.group7.model.FileArchive;
import ru.vsu.cs.group7.service.FileArchiveService;

public class UpdateArchivePage extends ArchivesPages {

    public UpdateArchivePage(FileArchiveService archiveService) {
        super("================================== Обновление архива ====================================", archiveService);
    }

    @Override
    public void openPage() throws ApplicationException, ActionCancelled {
        FileArchive currentArchive = Controller.getInstance().getContext().getCurrentArchive();
        if (currentArchive != null){
            System.out.print("Введите новое имя архива: ");
            String newName = readUserInput();
            FileArchive archive = getFileArchiveService().update(currentArchive.getId(), newName);
            Controller.getInstance().getContext().setCurrentArchive(archive);
            backToMenu(MenusEnum.CurrentArchiveMenu, getIsWait());
            return;
        }

        System.out.print("Введите id архива: ");
        String input = readUserInput();
        System.out.print("Теперь введите новое имя архива: ");
        String newName = readUserInput();

        try {
            Long id = Long.parseLong(input);
            getFileArchiveService().update(id, newName);
            backToMenu(MenusEnum.FileArchiveMenu, getIsWait());
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
