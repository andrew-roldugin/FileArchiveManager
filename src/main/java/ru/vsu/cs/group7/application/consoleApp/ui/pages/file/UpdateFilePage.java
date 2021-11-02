package ru.vsu.cs.group7.application.consoleApp.ui.pages.file;

import ru.vsu.cs.group7.application.consoleApp.Controller;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.MenusEnum;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.fabric.FileMenu;
import ru.vsu.cs.group7.exception.ActionCancelled;
import ru.vsu.cs.group7.exception.ApplicationException;
import ru.vsu.cs.group7.model.File;
import ru.vsu.cs.group7.service.FileService;

import java.sql.SQLException;

public class UpdateFilePage extends FilesPages {

    public UpdateFilePage(FileService fileService) {
        super("=================================== Обновление файла ====================================", fileService);
    }

    @Override
    public void openPage() throws ApplicationException, ActionCancelled, SQLException, NoSuchFieldException, IllegalAccessException {
        File currentFile = Controller.getInstance().getContext().getCurrentFile();
        if (currentFile != null) {
            System.out.print("Введите новое имя файла: ");
            String newName = readUserInput();

            File file = getFileService().updateById(currentFile.getId(), newName);
            Controller.getInstance().getContext().setCurrentFile(file);
            backToMenu(MenusEnum.CurrentFileMenu, getIsWait());
            return;
        }

        System.out.print("Введите id файла: ");
        String input = readUserInput();
        System.out.print("Теперь введите новое имя файла: ");
        String newName = readUserInput();

        try {
            Long fileId = Long.parseLong(input);
            getFileService().updateById(fileId, newName);
            backToMenu(MenusEnum.FileMenu, getIsWait());
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
