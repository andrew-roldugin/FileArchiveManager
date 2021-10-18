package ru.vsu.cs.group7.application.consoleApp.config;

import ru.vsu.cs.group7.exception.UserNotAuthorizedException;
import ru.vsu.cs.group7.model.File;
import ru.vsu.cs.group7.model.FileArchive;
import ru.vsu.cs.group7.model.User;

public class ApplicationContext {

    private User user;
    private File currentFile;
    private FileArchive currentArchive;

    public void checkLogin() throws UserNotAuthorizedException {
        if (!isLoggedIn()) throw new UserNotAuthorizedException();
    }

    public boolean isLoggedIn() {
        return this.getUser() != null;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public File getCurrentFile() {
        return currentFile;
    }

    public void setCurrentFile(File currentFile) {
        this.currentFile = currentFile;
    }

    public FileArchive getCurrentArchive() {
        return currentArchive;
    }

    public void setCurrentArchive(FileArchive currentArchive) {
        this.currentArchive = currentArchive;
    }
}
