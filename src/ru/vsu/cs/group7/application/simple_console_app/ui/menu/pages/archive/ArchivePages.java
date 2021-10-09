package ru.vsu.cs.group7.application.simple_console_app.ui.menu.pages.archive;

import ru.vsu.cs.group7.application.simple_console_app.ui.menu.pages.Page;
import ru.vsu.cs.group7.application.simple_console_app.ui.menu.common.BaseMenu;
import ru.vsu.cs.group7.service.FileArchiveService;

public abstract class ArchivePages extends Page {
    private final FileArchiveService fileArchiveService;
    public ArchivePages(BaseMenu parentMenu, String content) {
        super(parentMenu, content);
        this.fileArchiveService = (FileArchiveService) parentMenu.getService();
    }

    public FileArchiveService getFileArchiveService() {
        return fileArchiveService;
    }
}
