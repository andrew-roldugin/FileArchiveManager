package ru.vsu.cs.group7.application.consoleApp.ui.pages.file;

import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.MenusEnum;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.fabric.FileMenu;
import ru.vsu.cs.group7.exception.ActionCancelled;
import ru.vsu.cs.group7.exception.ApplicationException;

import java.util.UUID;

public class UpdateFilePage extends FilesPages {

    public UpdateFilePage(FileMenu fileMenu) {
        super(fileMenu, "=================================== Обновление файла ====================================");
    }

    @Override
    public void openPage() throws ApplicationException, ActionCancelled {
        System.out.print("Введите id файла: ");
        String input = readUserInput();
        System.out.print("Теперь введите новое имя файла: ");
        String newName = readUserInput();

        try {
            Long fileId = Long.parseLong(input);
            getFileService().updateById(fileId, newName);
            backToMenu(getParentMenu(), MenusEnum.FileMenu, getIsWait());
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
