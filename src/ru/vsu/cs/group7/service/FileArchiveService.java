package ru.vsu.cs.group7.service;

import ru.vsu.cs.group7.application.consoleApp.Controller;
import ru.vsu.cs.group7.application.consoleApp.config.ApplicationContext;
import ru.vsu.cs.group7.exception.ApplicationException;
import ru.vsu.cs.group7.exception.NotAllowedExceptions;
import ru.vsu.cs.group7.exception.NotFoundException;
import ru.vsu.cs.group7.exception.UserNotAuthorizedException;
import ru.vsu.cs.group7.model.FileArchive;
import ru.vsu.cs.group7.model.User;
import ru.vsu.cs.group7.storage.inMemoryStorage.FakeFileArchiveStorage;
import ru.vsu.cs.group7.storage.inMemoryStorage.FakeFileStorage;
import ru.vsu.cs.group7.storage.interfaces.FileArchiveStorage;
import ru.vsu.cs.group7.storage.interfaces.FileStorage;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

public class FileArchiveService implements Service {
    private final FileArchiveStorage fileArchiveStorage;
    private final FileStorage fileStorage;
    private final ApplicationContext context;

    public FileArchiveService(FileArchiveStorage fileArchiveStorage, FileStorage fileStorage, ApplicationContext context) {
        this.fileArchiveStorage = fileArchiveStorage;
        this.fileStorage = fileStorage;
        this.context = context;
    }

    public FileArchiveService(ApplicationContext context) {
        this(new FakeFileArchiveStorage(), new FakeFileStorage(), context);
    }

    public FileArchive getOneById(Long id) throws ApplicationException {
        return fileArchiveStorage.getOneById(id).orElseThrow(() -> new NotFoundException("Архив не найден"));
    }

    public Collection<FileArchive> getAllArchives() {
        User user = Controller.getContext().getUser();
        if (user.getRole().equals(User.RoleEnum.Admin))
            return fileArchiveStorage.getAll();
        return fileArchiveStorage.getAllArchivesByUserId(user.getId()).stream().toList();
    }

    public void createArchive(String name) throws UserNotAuthorizedException {
        context.checkLogin();
        User user = context.getUser();
        FileArchive fileArchive = new FileArchive(name, user);
        fileArchiveStorage.save(fileArchive);
    }

    public void removeById(Long fileArchiveId) throws UserNotAuthorizedException, NotFoundException, NotAllowedExceptions {
        context.checkLogin();
        Optional<FileArchive> fileArchiveOptional = fileArchiveStorage.getOneById(fileArchiveId);

        if (fileArchiveOptional.isEmpty())
            throw new NotFoundException("Архив не найден");

        FileArchive fileArchive = fileArchiveOptional.get();
        Long userId = context.getUser().getId();
        if (!(userId.equals(fileArchive.getOwner().getId()) || context.getUser().getRole().equals(User.RoleEnum.Admin)))
            throw new NotAllowedExceptions(userId, fileArchiveId, "удалять архив");

        Long archiveId = fileArchive.getId();
        fileArchiveStorage.removeById(archiveId);
        fileStorage.removeAllByArchiveId(archiveId);
    }

    public FileArchive update(Long fileArchiveId, String newName) throws UserNotAuthorizedException, NotFoundException, NotAllowedExceptions {
        context.checkLogin();

        Optional<FileArchive> fileArchiveOptional = fileArchiveStorage.getOneById(fileArchiveId);
        if (fileArchiveOptional.isEmpty())
            throw new NotFoundException("Архив не найден");

        FileArchive fileArchive = fileArchiveOptional.get();
        Long userId = context.getUser().getId();
        if (!(userId.equals(fileArchive.getOwner().getId()) || context.getUser().getRole().equals(User.RoleEnum.Admin)))
            throw new NotAllowedExceptions();

        fileArchive.setName(newName);

        return fileArchiveStorage.save(fileArchive);
    }
}
