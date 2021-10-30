package ru.vsu.cs.group7.application.consoleApp.ui.pages.file;

import ru.vsu.cs.group7.application.consoleApp.Controller;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.MenusEnum;
import ru.vsu.cs.group7.exception.ActionCancelled;
import ru.vsu.cs.group7.model.File;
import ru.vsu.cs.group7.service.FileService;

public class RemoveFilePage extends FilesPages {

    public RemoveFilePage(FileService fileService) {
        super("==================================== Удаление файла =====================================", fileService);
    }

    @Override
    public void openPage() throws ActionCancelled {
        File currentFile = Controller.getInstance().getContext().getCurrentFile();
        if (currentFile != null) {
            getFileService().removeFileById(currentFile.getId());
            Controller.getInstance().getContext().setCurrentFile(null);
            backToMenu(MenusEnum.FileMenu, getIsWait());
            return;
        }

        System.out.print("Введите id файла: ");
        String input = readUserInput();

        try {
            Long fileId = Long.parseLong(input);
            getFileService().removeFileById(fileId);
            backToMenu(MenusEnum.FileMenu, getIsWait());
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
