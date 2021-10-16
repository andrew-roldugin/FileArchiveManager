package ru.vsu.cs.group7.application.consoleApp.ui.pages.file;

import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.BaseMenu;
import ru.vsu.cs.group7.application.consoleApp.ui.pages.Page;
import ru.vsu.cs.group7.service.FileService;

public abstract class FilesPages extends Page {
    private final FileService fileService;

    public FilesPages(BaseMenu parentMenu, String header) {
        super(parentMenu, header);
        this.fileService = (FileService) parentMenu.getService();
    }

    public FileService getFileService() {
        return fileService;
    }
}
