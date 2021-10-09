package ru.vsu.cs.group7.service;

import ru.vsu.cs.group7.exception.NotAllowedExceptions;
import ru.vsu.cs.group7.exception.UserNotAuthorizedException;
import ru.vsu.cs.group7.model.FileArchive;
import ru.vsu.cs.group7.model.User;
import ru.vsu.cs.group7.application.simple_console_app.ApplicationStorage;
import ru.vsu.cs.group7.storage.in_memory_storage.FakeFileArchiveStorage;
import ru.vsu.cs.group7.storage.in_memory_storage.FileArchiveStorage;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public class FileArchiveService implements Service {
    private final FileArchiveStorage fileArchiveStorage;
    private final ApplicationStorage applicationStorage;

    public FileArchiveService(FileArchiveStorage fileArchiveStorage, ApplicationStorage applicationStorage) {
        this.fileArchiveStorage = fileArchiveStorage;
        this.applicationStorage = applicationStorage;
    }

    public FileArchiveService(ApplicationStorage applicationStorage) {
        this(new FakeFileArchiveStorage(), applicationStorage);
    }

    public Collection<FileArchive> getAllArchives() {
        return fileArchiveStorage.getAll();
    }

    public void createArchive(String name) throws UserNotAuthorizedException {
        applicationStorage.checkLogin();
        User user = applicationStorage.getUser();
        FileArchive fileArchive = new FileArchive(name, user);
        fileArchiveStorage.save(fileArchive);
    }

    public void removeById(UUID fileArchiveId) throws UserNotAuthorizedException {
        applicationStorage.checkLogin();
        Optional<FileArchive> fileArchiveOptional = fileArchiveStorage.getOneById(fileArchiveId);
        fileArchiveOptional.ifPresent(fileArchive -> {
            UUID userId = applicationStorage.getUser().getId();
            if (!userId.equals(fileArchive.getOwner().getId()))
                try {
                    throw new NotAllowedExceptions(userId, fileArchiveId, "удалять архив");
                } catch (NotAllowedExceptions notAllowedExceptions) {
                    notAllowedExceptions.printStackTrace();
                }
            fileArchiveStorage.removeById(fileArchiveId);
        });
    }

    public void removeAll(UUID userId) throws UserNotAuthorizedException {
        applicationStorage.checkLogin();
        fileArchiveStorage.removeAllByUserId(userId);
    }

    public void update(UUID fileArchiveId, String newName) throws UserNotAuthorizedException {
        applicationStorage.checkLogin();

        Optional<FileArchive> fileArchiveOptional = fileArchiveStorage.getOneById(fileArchiveId);
        fileArchiveOptional.ifPresent(fileArchive -> {
            UUID userId = applicationStorage.getUser().getId();
            if (!userId.equals(fileArchive.getOwner().getId()))
                // TODO: 08.10.2021 исключение при обновлении
//                try {
//                    throw new NotAllowedExceptions(userId, fileArchiveId, "изменять архив");
//                } catch (NotAllowedExceptions notAllowedExceptions) {
//                    notAllowedExceptions.printStackTrace();
//                }
            fileArchive.setName(newName);
        });

//        fileArchiveStorage.updateById(new FileArchive(newName, ApplicationStorage.getINSTANCE().getUser()));
    }
}
