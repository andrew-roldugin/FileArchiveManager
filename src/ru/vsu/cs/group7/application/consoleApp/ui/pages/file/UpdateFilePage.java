package ru.vsu.cs.group7.application.consoleApp.ui.pages.file;

import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.MenusEnum;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.fabric.FileMenu;
import ru.vsu.cs.group7.exception.ApplicationException;

import java.util.InputMismatchException;
import java.util.UUID;

public class UpdateFilePage extends FilesPages {
    public UpdateFilePage(FileMenu fileMenu) {
        super(fileMenu, "=================================== Обновление файла ====================================");
    }

    @Override
    public void openPage() throws ApplicationException {
        System.out.print("Введите id файла: ");
        String input = getScanner().next();
        System.out.print("Теперь введите новое имя файла: ");
        String newName = getScanner().next();

        try {
            UUID fileId = UUID.fromString(input);
            getFileService().updateById(fileId, newName);
            backToMenu(getParentMenu(), MenusEnum.FileMenu, getIsWait());
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
