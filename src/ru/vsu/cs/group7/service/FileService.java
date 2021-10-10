package ru.vsu.cs.group7.service;

import com.sun.source.tree.UnionTypeTree;
import ru.vsu.cs.group7.application.consoleApp.config.ApplicationStorage;
import ru.vsu.cs.group7.exception.NotAllowedExceptions;
import ru.vsu.cs.group7.exception.NotFoundException;
import ru.vsu.cs.group7.exception.UserNotAuthorizedException;
import ru.vsu.cs.group7.model.File;
import ru.vsu.cs.group7.model.FileArchive;
import ru.vsu.cs.group7.model.User;
import ru.vsu.cs.group7.storage.inMemoryStorage.FakeFileArchiveStorage;
import ru.vsu.cs.group7.storage.inMemoryStorage.FakeFileStorage;
import ru.vsu.cs.group7.storage.inMemoryStorage.FileArchiveStorage;
import ru.vsu.cs.group7.storage.inMemoryStorage.FileStorage;

import javax.lang.model.type.UnionType;
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

    public void addNewFiles(UUID fileArchiveId, Collection<String> names) throws UserNotAuthorizedException, NotFoundException, NotAllowedExceptions {
        applicationStorage.checkLogin();
        Optional<FileArchive> fileArchiveOptional = fileArchiveStorage.getOneById(fileArchiveId);

        addNew(names, fileArchiveOptional);
    }

    public void addNewFiles(String fileArchiveName, Collection<String> names) throws UserNotAuthorizedException, NotFoundException, NotAllowedExceptions {
        applicationStorage.checkLogin();

        Optional<FileArchive> fileArchiveOptional = fileArchiveStorage.getOneByName(fileArchiveName);

        addNew(names, fileArchiveOptional);
    }

    private void addNew(Collection<String> names, Optional<FileArchive> fileArchiveOptional) throws NotFoundException, NotAllowedExceptions {
        if (fileArchiveOptional.isEmpty())
            throw new NotFoundException("Архив не найден");
        FileArchive fileArchive = fileArchiveOptional.get();
        User owner = fileArchive.getOwner();
        if (!owner.getId().equals(applicationStorage.getUser().getId()) && !owner.getRole().equals(User.RoleEnum.Admin))
            throw new NotAllowedExceptions(null, null, "");

        names.stream()
                .map(name -> new File(name, fileArchive))
                .collect(Collectors.toSet())
                .forEach(fileStorage::save);
    }

    public Optional<File> getFileById(UUID id) throws UserNotAuthorizedException {
        applicationStorage.checkLogin();

        return fileStorage.getOneById(id);
    }

    public Optional<File> getFileByName(String name) throws UserNotAuthorizedException {
        applicationStorage.checkLogin();

        return fileStorage.getFileByName(name);
    }

    public Optional<File> getAllFilesInArchiveById(UUID fileArchiveId) throws UserNotAuthorizedException {
        applicationStorage.checkLogin();

        Optional<File> filesInFileArchive = fileStorage.getFilesInFileArchive(fileArchiveId);
        return filesInFileArchive;
    }

    public Optional<File> getAllFilesInArchiveByArchiveName(String fileArchiveName) throws UserNotAuthorizedException {
        applicationStorage.checkLogin();

        return fileStorage.getFilesInFileArchive(fileArchiveName);
    }

//    private Optional<File> get(Optional<File> filesInFileArchive){
//        filesInFileArchive.stream().filter()
//    }

    public void updateById(UUID fileId, String newName) {
        Optional<File> oneById = fileStorage.getOneById(fileId);
        oneById.ifPresent(file -> {
            file.setName(newName);
            fileStorage.updateById(file);
        });
    }

    public void removeFileById(UUID fileId) {
        fileStorage.removeById(fileId);
//        fileArchiveStorage.getOneById(fileArchiveId).ifPresent(fileArchive -> {
//            fileArchive.removeFile(f)
//        });
    }
}
