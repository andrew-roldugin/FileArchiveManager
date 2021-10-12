package ru.vsu.cs.group7.application.consoleApp.ui.pages.file;

import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.BaseMenu;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.MenusEnum;
import ru.vsu.cs.group7.exception.ApplicationException;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.UUID;

public class AddFilePage extends FilesPages {

    public AddFilePage(BaseMenu parentMenu) {
        super(parentMenu, "================================ Добавление файла в архив ===============================");
    }

    @Override
    public void openPage() throws ApplicationException {
        System.out.print("Введите id архива: ");
        String input = getScanner().next();
        getScanner().nextLine();
        System.out.print("Теперь введите имена файлов через пробел: ");
        String listOfFiles = getScanner().nextLine();

        List<String> names = Arrays.stream(listOfFiles.split(" ")).toList();
        try {
            getFileService().addNewFiles(UUID.fromString(input), names);
            backToMenu(getParentMenu(), MenusEnum.FileMenu, getIsWait());
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }

//        System.out.println("Введите id или имя архива");
//        String input = getScanner().next();
//        System.out.println("Теперь введите имена файлов через пробел");
//        String listOfFiles = getScanner().next();
//
//        List<String> names = Arrays.stream(listOfFiles.split(" ")).toList();
//        try {
//            getFileService().addNewFiles(UUID.fromString(input), names);
//        } catch (InputMismatchException ex) {
//            getFileService().addNewFiles(input, names);
//        } finally {
//            backToMenu(getParentMenu(), MenusEnum.FileMenu, getIsWait());
//        }
    }
}
