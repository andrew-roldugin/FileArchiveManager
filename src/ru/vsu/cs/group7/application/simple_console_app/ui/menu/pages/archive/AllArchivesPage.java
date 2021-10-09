package ru.vsu.cs.group7.application.simple_console_app.ui.menu.pages.archive;

import ru.vsu.cs.group7.application.simple_console_app.ui.menu.fabric.FileArchiveMenu;

public class AllArchivesPage extends ArchivePages {
    public AllArchivesPage(FileArchiveMenu parentMenu) {
        super(parentMenu, "=========================== Информация обо всех архивах ===========================");
        setAction((input) -> {
            StringBuilder sb = new StringBuilder();
            sb.append("id\tname\tupdateTime\tcreateTime\n");
            getFileArchiveService().getAllArchives().forEach(fileArchive -> {
                sb.append(fileArchive.toString()).append("\n");
            });
//            sb.append("\n");
            System.out.println(sb);
//            Main.setCurrentPage(MainPage.getINSTANCE());
        });
    }
}
