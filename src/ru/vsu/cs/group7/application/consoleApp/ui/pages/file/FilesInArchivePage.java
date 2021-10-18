package ru.vsu.cs.group7.application.consoleApp.ui.pages.file;

import ru.vsu.cs.group7.application.consoleApp.Controller;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.BaseMenu;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.MenusEnum;
import ru.vsu.cs.group7.application.consoleApp.ui.pages.archive.OpenArchivePage;
import ru.vsu.cs.group7.exception.ActionCancelled;
import ru.vsu.cs.group7.exception.ApplicationException;
import ru.vsu.cs.group7.exception.UserNotAuthorizedException;
import ru.vsu.cs.group7.model.File;
import ru.vsu.cs.group7.model.FileArchive;
import ru.vsu.cs.group7.service.FileService;

import java.util.List;

public class FilesInArchivePage extends FilesPages {

    public FilesInArchivePage(FileService fileService) {
        super("========================== Информация обо всех файлах в архиве ==========================", fileService);
        this.isWait = true;
    }

    @Override
    public void openPage() throws ApplicationException, ActionCancelled {
        FileArchive currentArchive = Controller.getContext().getCurrentArchive();
        if (currentArchive == null) {
            System.out.print("Введите id архива: ");
            String input = readUserInput();
            try {
                print(Long.parseLong(input));
            } catch (NumberFormatException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            print(currentArchive.getId());
        }
    }

    private void print(Long currentArchive) throws UserNotAuthorizedException {
        List<File> files = getFileService().getAllFilesInArchiveById(currentArchive);
        printTable(new String[]{"ID", "Название", "Дата добавления"}, files);
        System.out.println("Хотите выбрать конкретный файл для работы? (y/n)");
        String next = getScanner().next();
        if (next.equals("y"))
            BaseMenu.switchMenu(MenusEnum.FileMenu).setCurrentPage(new SelectFilePage(getFileService()));
        else
            Controller.getMenuManager().getCurrentMenu().setCurrentPage(null);
//            BaseMenu.switchMenu(MenusEnum.FileMenu);
    }
}
