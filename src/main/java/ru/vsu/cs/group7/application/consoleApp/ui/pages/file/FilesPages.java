package ru.vsu.cs.group7.application.consoleApp.ui.pages.file;

import ru.vsu.cs.group7.application.consoleApp.ui.pages.Page;
import ru.vsu.cs.group7.service.FileService;

public abstract class FilesPages extends Page {

    private final FileService fileService;

    public FilesPages(String header, FileService fileService) {
        super(header);
        this.fileService = fileService;
    }

    public FileService getFileService() {
        return fileService;
    }
}
