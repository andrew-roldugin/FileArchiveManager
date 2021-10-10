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
        System.out.println("Введите id или имя файла");
        String input = getScanner().next();
        System.out.println("Теперь введите новое имя файла");
        String newName = getScanner().next();

        final UUID[] fileId = {null};
        try {
            fileId[0] = UUID.fromString(input);
        } catch (InputMismatchException ex) {
            getFileService().getFileByName(input).ifPresent(file -> {
                fileId[0] = file.getId();
            });
        } finally {
            if (fileId[0] != null)
                getFileService().updateById(fileId[0], newName);
            backToMenu(getParentMenu(), MenusEnum.FileMenu, getIsWait());
        }
    }
}
