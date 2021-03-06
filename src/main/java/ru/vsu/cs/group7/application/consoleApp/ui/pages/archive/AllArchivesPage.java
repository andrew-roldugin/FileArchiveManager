package ru.vsu.cs.group7.application.consoleApp.ui.pages.archive;

import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.BaseMenu;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.MenusEnum;
import ru.vsu.cs.group7.exception.ActionCancelled;
import ru.vsu.cs.group7.model.FileArchive;
import ru.vsu.cs.group7.service.FileArchiveService;

import java.sql.SQLException;
import java.util.Collection;

public class AllArchivesPage extends ArchivesPages {

    public AllArchivesPage(FileArchiveService archiveService) {
        super("=========================== Информация обо всех архивах ===========================", archiveService);
        this.isWait = true;
    }

    @Override
    public void openPage() throws ActionCancelled, SQLException {
        final Collection<FileArchive> allArchives = getFileArchiveService().getAllArchives();
        printTable(new String[]{"ID", "Название", "Дата создания", "Дата обновления"}, allArchives);
        if (!allArchives.isEmpty()) {
            System.out.println("Хотите открыть конкретный архив? (y/n)");
            String next = getScanner().next();
            if (next.equals("y"))
                BaseMenu.switchMenu(MenusEnum.FileArchiveMenu).setCurrentPage(new OpenArchivePage(getFileArchiveService()));
            else
                BaseMenu.switchMenu(MenusEnum.FileArchiveMenu);
        } else {
            System.out.println("Пока нет данных...");
            BaseMenu.switchMenu(MenusEnum.FileArchiveMenu);
        }
    }
}
