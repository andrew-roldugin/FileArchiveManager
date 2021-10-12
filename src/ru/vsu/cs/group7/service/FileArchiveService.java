package ru.vsu.cs.group7.service;

import ru.vsu.cs.group7.exception.NotAllowedExceptions;
import ru.vsu.cs.group7.exception.NotFoundException;
import ru.vsu.cs.group7.exception.UserNotAuthorizedException;
import ru.vsu.cs.group7.model.FileArchive;
import ru.vsu.cs.group7.model.User;
import ru.vsu.cs.group7.application.consoleApp.config.ApplicationContext;
import ru.vsu.cs.group7.storage.inMemoryStorage.FakeFileArchiveStorage;
import ru.vsu.cs.group7.storage.inMemoryStorage.FakeFileStorage;
import ru.vsu.cs.group7.storage.inMemoryStorage.FileArchiveStorage;
import ru.vsu.cs.group7.storage.inMemoryStorage.FileStorage;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

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

    public Collection<FileArchive> getAllArchives() {
        User user = getApplicationContext().getUser();
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

    public void removeById(UUID fileArchiveId) throws UserNotAuthorizedException, NotFoundException, NotAllowedExceptions {
        context.checkLogin();
        Optional<FileArchive> fileArchiveOptional = fileArchiveStorage.getOneById(fileArchiveId);

        if (fileArchiveOptional.isEmpty()) {
            throw new NotFoundException("Архив не найден");
        }
        FileArchive fileArchive = fileArchiveOptional.get();
        UUID userId = context.getUser().getId();
        if (!userId.equals(fileArchive.getOwner().getId()))
            throw new NotAllowedExceptions(userId, fileArchiveId, "удалять архив");

        UUID archiveId = fileArchive.getId();
        fileArchiveStorage.removeById(archiveId);
        fileStorage.removeAllByArchiveId(archiveId);
    }

    public void removeAll(UUID userId) throws UserNotAuthorizedException {
        context.checkLogin();
        fileArchiveStorage.removeAllByUserId(userId);
    }

    public void update(UUID fileArchiveId, String newName) throws UserNotAuthorizedException, NotFoundException, NotAllowedExceptions {
        context.checkLogin();

        Optional<FileArchive> fileArchiveOptional = fileArchiveStorage.getOneById(fileArchiveId);
        if (fileArchiveOptional.isEmpty()) {
            throw new NotFoundException("Архив не найден");
        }
        FileArchive fileArchive = fileArchiveOptional.get();
        UUID userId = context.getUser().getId();
        if (!userId.equals(fileArchive.getOwner().getId()))
            throw new NotAllowedExceptions();

        fileArchive.setName(newName);

        fileArchiveStorage.updateById(fileArchive);
    }

    @Override
    public ApplicationContext getApplicationContext() {
        return context;
    }
}
