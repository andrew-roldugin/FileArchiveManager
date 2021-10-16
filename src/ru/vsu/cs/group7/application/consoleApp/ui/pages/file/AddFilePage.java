package ru.vsu.cs.group7.application.consoleApp.ui.pages.file;

import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.BaseMenu;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.MenusEnum;
import ru.vsu.cs.group7.exception.ActionCancelled;
import ru.vsu.cs.group7.exception.ApplicationException;

import java.util.Arrays;
import java.util.List;

public class AddFilePage extends FilesPages {

    public AddFilePage(BaseMenu parentMenu) {
        super(parentMenu, "================================ Добавление файла в архив ===============================");
    }

    @Override
    public void openPage() throws ApplicationException, ActionCancelled {
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
            backToMenu(getParentMenu(), MenusEnum.FileMenu, getIsWait());
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
