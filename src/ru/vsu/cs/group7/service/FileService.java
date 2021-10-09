package ru.vsu.cs.group7.service;

import ru.vsu.cs.group7.application.simple_console_app.ApplicationStorage;
import ru.vsu.cs.group7.exception.UserNotAuthorizedException;
import ru.vsu.cs.group7.model.File;
import ru.vsu.cs.group7.model.FileArchive;
import ru.vsu.cs.group7.storage.in_memory_storage.FakeFileArchiveStorage;
import ru.vsu.cs.group7.storage.in_memory_storage.FakeFileStorage;
import ru.vsu.cs.group7.storage.in_memory_storage.FileArchiveStorage;
import ru.vsu.cs.group7.storage.in_memory_storage.FileStorage;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class FileService implements Service {
    private final FileStorage fileStorage;
    private final FileArchiveStorage fileArchiveStorage;
    private final ApplicationStorage applicationStorage;

    public FileService(FileStorage fileStorage, FileArchiveStorage fileArchiveStorage, ApplicationStorage applicationStorage) {
        this.fileStorage = fileStorage;
        this.fileArchiveStorage = fileArchiveStorage;
        this.applicationStorage = applicationStorage;
    }

    public FileService(ApplicationStorage applicationStorage) {
        this(new FakeFileStorage(), new FakeFileArchiveStorage(), applicationStorage);
    }


    public void create(UUID fileArchiveId, Collection<String> names) throws UserNotAuthorizedException {
        applicationStorage.checkLogin();

        Optional<FileArchive> fileArchiveOptional = fileArchiveStorage.getOneById(fileArchiveId);

        fileArchiveOptional.ifPresent(fileArchive -> {
            Collection<File> c;
            (c = names.stream()
                    .map(name -> new File(name, fileArchive))
                    .collect(Collectors.toSet()))
                    .forEach(fileStorage::save);
//            fileArchive.addAll(c);
        });
    }

    public Optional<File> getAllFilesInArchive(UUID fileArchiveId) throws UserNotAuthorizedException {
        applicationStorage.checkLogin();

        return fileStorage.getFilesInFileArchive(fileArchiveId);
    }

    public void removeFile(UUID fileArchiveId, UUID fileId) {
        fileStorage.removeById(fileId);
//        fileArchiveStorage.getOneById(fileArchiveId).ifPresent(fileArchive -> {
//            fileArchive.removeFile(f)
//        });
    }

}
