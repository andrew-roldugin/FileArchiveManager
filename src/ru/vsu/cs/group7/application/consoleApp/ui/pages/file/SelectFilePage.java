package ru.vsu.cs.group7.application.consoleApp.ui.pages.file;

import ru.vsu.cs.group7.application.consoleApp.Controller;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.MenusEnum;
import ru.vsu.cs.group7.exception.ActionCancelled;
import ru.vsu.cs.group7.exception.ApplicationException;
import ru.vsu.cs.group7.model.File;
import ru.vsu.cs.group7.service.FileService;

public class SelectFilePage extends FilesPages {

    public SelectFilePage(FileService fileService) {
        super("", fileService);
    }

    @Override
    public void openPage() throws ApplicationException, ActionCancelled {
        System.out.print("Введите id файла: ");
        String input = readUserInput();
        try {
            Long id = Long.parseLong(input);
            File file = getFileService().getFileById(id);
            Controller.getContext().setCurrentFile(file);
            backToMenu(MenusEnum.CurrentFileMenu, getIsWait());
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
