package ru.vsu.cs.group7.application.consoleApp.ui.pages.file;

import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.MenusEnum;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.fabric.FileMenu;
import ru.vsu.cs.group7.exception.ActionCancelled;
import ru.vsu.cs.group7.exception.ApplicationException;

import java.util.UUID;

public class RemoveFilePage extends FilesPages {

    public RemoveFilePage(FileMenu fileMenu) {
        super(fileMenu, "==================================== Удаление файла =====================================");
    }

    @Override
    public void openPage() throws ApplicationException, ActionCancelled {

        System.out.print("Введите id файла: ");
        String input = readUserInput();

        try {
            Long fileId = Long.parseLong(input);
            getFileService().removeFileById(fileId);
            backToMenu(getParentMenu(), MenusEnum.FileMenu, getIsWait());
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
