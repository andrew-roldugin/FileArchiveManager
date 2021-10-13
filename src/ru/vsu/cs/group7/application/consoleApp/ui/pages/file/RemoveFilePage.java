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
            UUID fileId = UUID.fromString(input);
            getFileService().removeFileById(fileId);
            backToMenu(getParentMenu(), MenusEnum.FileMenu, getIsWait());
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }

//        System.out.println("Введите id или имя файла");
//        String input = getScanner().next();
//        final UUID[] fileId = {null};
//        try {
//            fileId[0] = UUID.fromString(input);
//        } catch (InputMismatchException ex) {
//            getFileService().getFileByName(getParentMenu().getController().getContext().getCurrentArchive().getId(), input).ifPresent(file -> {
//                fileId[0] = file.getId();
//            });
//        } finally {
//            if (fileId[0] != null)
//                getFileService().removeFileById(fileId[0]);
//            backToMenu(getParentMenu(), MenusEnum.FileMenu, getIsWait());
//        }
    }
}
