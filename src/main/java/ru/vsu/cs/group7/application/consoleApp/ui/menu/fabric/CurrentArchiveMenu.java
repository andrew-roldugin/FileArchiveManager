package ru.vsu.cs.group7.application.consoleApp.ui.menu.fabric;

import ru.vsu.cs.group7.application.consoleApp.Controller;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.MenusEnum;
import ru.vsu.cs.group7.application.consoleApp.ui.pages.archive.RemoveArchivePage;
import ru.vsu.cs.group7.application.consoleApp.ui.pages.archive.UpdateArchivePage;
import ru.vsu.cs.group7.application.consoleApp.ui.pages.file.AddFilePage;
import ru.vsu.cs.group7.application.consoleApp.ui.pages.file.FilesInArchivePage;
import ru.vsu.cs.group7.service.FileArchiveService;
import ru.vsu.cs.group7.service.FileService;

public class CurrentArchiveMenu extends FileArchiveMenu {

    private final FileService fileService;

    public CurrentArchiveMenu(FileArchiveService fileArchiveService, FileService fileService) {
        super(fileArchiveService);
        this.fileService = fileService;
        String name = Controller.getInstance().getContext().getCurrentArchive().getName();
        this.content = String.format("""
                 _________________________________________________________________________________
                / Открыт архив: %s\\
                |---------------------------------------------------------------------------------|
                | 1) Вывести все файлы в архиве;                                                  |
                | 2) Переименовать архив;                                                         |
                | 3) Удалить архив;																  |
                | 4) Добавить файлы в архив;													  |
                | 5) -> Назад к меню архивы;    												  |
                |               ___________________________________________________               |
                | 0) Завершение работы;                                                           |
                |_________________________________________________________________________________|
                """, name + " ".repeat(66 - name.length()));
    }

    @Override
    public void onSelect(Integer choice) {
        switch (choice) {
            case 0 -> exit();

            case 1 -> {
                setCurrentPage(new FilesInArchivePage(fileService));
            }
            case 2 -> setCurrentPage(new UpdateArchivePage(getArchiveService()));
            case 3 -> setCurrentPage(new RemoveArchivePage(getArchiveService()));
            case 4 -> setCurrentPage(new AddFilePage(fileService));

            case 5 -> {
                Controller.getInstance().getContext().setCurrentArchive(null);
                Controller.getInstance().getContext().setCurrentFile(null);
                switchMenu(MenusEnum.FileArchiveMenu);
            }
        }
    }
}
