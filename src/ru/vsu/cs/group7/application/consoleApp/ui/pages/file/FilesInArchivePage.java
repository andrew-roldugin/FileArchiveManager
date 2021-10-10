package ru.vsu.cs.group7.application.consoleApp.ui.pages.file;

import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.MenusEnum;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.fabric.FileMenu;
import ru.vsu.cs.group7.exception.ApplicationException;
import ru.vsu.cs.group7.model.File;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.UUID;

public class FilesInArchivePage extends FilesPages {
    public FilesInArchivePage(FileMenu fileMenu) {
        super(fileMenu, "========================== Информация обо всех файлах в архиве ==========================");
        this.isWait = true;
    }

    @Override
    public void openPage() throws ApplicationException {
        System.out.println("Введите id или имя архива");
        String input = getScanner().next();
        Optional<File> files;

        try {
            UUID id = UUID.fromString(input);
            files = getFileService().getAllFilesInArchiveById(id);
            print(files);
        } catch (IllegalArgumentException ex) {
            files = getFileService().getAllFilesInArchiveByArchiveName(input);
            print(files);
        } finally {
            backToMenu(getParentMenu(), MenusEnum.FileMenu, getIsWait());
        }
    }

    private void print(Optional<File> files) {
        StringBuilder sb = new StringBuilder();
        sb.append("Уникальный id\tНазвание\tДата добавления");
        files.ifPresent(file -> {
            sb.append(file.toString()).append("\n");
        });
        System.out.println(sb);
    }
}
