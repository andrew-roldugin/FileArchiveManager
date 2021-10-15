package ru.vsu.cs.group7.application.consoleApp.ui.pages.file;

import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.MenusEnum;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.fabric.FileMenu;
import ru.vsu.cs.group7.exception.ActionCancelled;
import ru.vsu.cs.group7.exception.ApplicationException;
import ru.vsu.cs.group7.model.File;

import java.util.List;
import java.util.UUID;

public class FilesInArchivePage extends FilesPages {

    public FilesInArchivePage(FileMenu fileMenu) {
        super(fileMenu, "========================== Информация обо всех файлах в архиве ==========================");
        this.isWait = true;
    }

    @Override
    public void openPage() throws ApplicationException, ActionCancelled {
        System.out.print("Введите id архива: ");
        String input = readUserInput();
        try {
            Long id = Long.parseLong(input);
            List<File> files = getFileService().getAllFilesInArchiveById(id);
            renderTable(new String[]{"ID", "Название", "Дата добавления"}, files);
            backToMenu(getParentMenu(), MenusEnum.FileMenu, getIsWait());
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
