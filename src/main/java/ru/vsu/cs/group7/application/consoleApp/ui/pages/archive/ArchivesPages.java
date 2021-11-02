package ru.vsu.cs.group7.application.consoleApp.ui.pages.archive;

import ru.vsu.cs.group7.application.consoleApp.ui.pages.Page;
import ru.vsu.cs.group7.service.FileArchiveService;

public abstract class ArchivesPages extends Page {

    private final FileArchiveService fileArchiveService;

    public ArchivesPages(String content, FileArchiveService fileArchiveService) {
        super(content);
        this.fileArchiveService = fileArchiveService;
    }

    public FileArchiveService getFileArchiveService() {
        return fileArchiveService;
    }
}
