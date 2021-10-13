package ru.vsu.cs.group7.application.consoleApp.ui.pages.archive;

import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.MenusEnum;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.fabric.FileArchiveMenu;
import ru.vsu.cs.group7.exception.ActionCancelled;
import ru.vsu.cs.group7.exception.ApplicationException;

public class AllArchivesPage extends ArchivesPages {

    public AllArchivesPage(FileArchiveMenu parentMenu) {
        super(parentMenu, "=========================== Информация обо всех архивах ===========================");
        this.isWait = true;
    }

    @Override
    public void openPage() throws ApplicationException, ActionCancelled {
        StringBuilder sb = new StringBuilder();
        sb.append("id\tname\tupdateTime\tcreateTime\n");
        getFileArchiveService().getAllArchives().forEach(fileArchive -> {
            sb.append(fileArchive.toString()).append("\n");
        });
        System.out.println(sb);
        backToMenu(getParentMenu(), MenusEnum.FileArchiveMenu, getIsWait());
    }
}
