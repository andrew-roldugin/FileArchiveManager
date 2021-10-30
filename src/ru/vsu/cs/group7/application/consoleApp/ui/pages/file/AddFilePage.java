package ru.vsu.cs.group7.application.consoleApp.ui.pages.file;

import ru.vsu.cs.group7.application.consoleApp.Controller;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.MenusEnum;
import ru.vsu.cs.group7.exception.ActionCancelled;
import ru.vsu.cs.group7.exception.ApplicationException;
import ru.vsu.cs.group7.model.FileArchive;
import ru.vsu.cs.group7.service.FileService;

import java.util.Arrays;
import java.util.List;

public class AddFilePage extends FilesPages {

    public AddFilePage(FileService fileService) {
        super("================================ Добавление файла в архив ===============================", fileService);
    }

    @Override
    public void openPage() throws ApplicationException, ActionCancelled {
        FileArchive currentArchive = Controller.getInstance().getContext().getCurrentArchive();
        if (currentArchive != null) {
            getScanner().nextLine();
            System.out.print("Введите имена файлов через пробел: ");
            String listOfFiles = getScanner().nextLine();
            if (listOfFiles.equals(":q"))
                throw new ActionCancelled();
            List<String> names = Arrays.stream(listOfFiles.split(" ")).toList();

            getFileService().addNewFiles(currentArchive.getId(), names);
            backToMenu(MenusEnum.CurrentArchiveMenu, getIsWait());
            return;
        }

        System.out.print("Введите id архива: ");
        String input = readUserInput();
        getScanner().nextLine();
        System.out.print("Теперь введите имена файлов через пробел: ");
        String listOfFiles = getScanner().nextLine();
        if (listOfFiles.equals(":q"))
            throw new ActionCancelled();
        List<String> names = Arrays.stream(listOfFiles.split(" ")).toList();
        try {
            getFileService().addNewFiles(Long.parseLong(input), names);
            backToMenu(MenusEnum.FileMenu, getIsWait());
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
