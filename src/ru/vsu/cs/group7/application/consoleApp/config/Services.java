package ru.vsu.cs.group7.application.consoleApp.config;

import ru.vsu.cs.group7.service.FileArchiveService;
import ru.vsu.cs.group7.service.FileService;
import ru.vsu.cs.group7.service.Service;
import ru.vsu.cs.group7.service.UserService;

public class Services {

    private final Service userService;
    private final Service fileService;
    private final Service fileArchiveService;

    public Services(ApplicationStorage applicationStorage) {
        this.userService = new UserService(applicationStorage);
        this.fileService = new FileService(applicationStorage);
        this.fileArchiveService = new FileArchiveService(applicationStorage);
    }

    public Service getUserService() {
        return userService;
    }

    public Service getFileService() {
        return fileService;
    }

    public Service getFileArchiveService() {
        return fileArchiveService;
    }
}
