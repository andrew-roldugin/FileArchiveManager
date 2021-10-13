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
            UUID id = UUID.fromString(input);
            List<File> files = getFileService().getAllFilesInArchiveById(id);
            StringBuilder sb = new StringBuilder();
            sb.append("Уникальный id\tНазвание\tДата добавления\n");
            files.forEach(file -> {
                sb.append(file.toString()).append("\n");
            });
            System.out.println(sb);
            backToMenu(getParentMenu(), MenusEnum.FileMenu, getIsWait());
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
