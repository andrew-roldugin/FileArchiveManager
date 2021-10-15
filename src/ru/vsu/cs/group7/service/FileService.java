package ru.vsu.cs.group7.service;

import ru.vsu.cs.group7.application.consoleApp.config.ApplicationContext;
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

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class FileService implements Service {

    private final FileStorage fileStorage;
    private final FileArchiveStorage fileArchiveStorage;
    private final ApplicationContext context;

    public FileService(FileStorage fileStorage, FileArchiveStorage fileArchiveStorage, ApplicationContext context) {
        this.fileStorage = fileStorage;
        this.fileArchiveStorage = fileArchiveStorage;
        this.context = context;
    }

    public FileService(ApplicationContext context) {
        this(new FakeFileStorage(), new FakeFileArchiveStorage(), context);
    }

    public void addNewFiles(Long fileArchiveId, Collection<String> names) throws UserNotAuthorizedException, NotFoundException, NotAllowedExceptions {
        context.checkLogin();
        Optional<FileArchive> fileArchiveOptional = fileArchiveStorage.getOneById(fileArchiveId);

        addNew(names, fileArchiveOptional);
    }

    private void addNew(Collection<String> names, Optional<FileArchive> fileArchiveOptional) throws NotFoundException, NotAllowedExceptions {

        if (fileArchiveOptional.isEmpty())
            throw new NotFoundException("Архив не найден");
        FileArchive fileArchive = fileArchiveOptional.get();
        User owner = fileArchive.getOwner();
        if (!owner.getId().equals(context.getUser().getId()) && !owner.getRole().equals(User.RoleEnum.Admin))
            throw new NotAllowedExceptions();

        names.stream()
                .map(name -> new File(name, fileArchive))
                .collect(Collectors.toList())
                .forEach(fileStorage::save);
    }

    public Optional<File> getFileById(Long id) throws UserNotAuthorizedException {
        context.checkLogin();

        return fileStorage.getOneById(id);
    }

    public List<File> getAllFilesInArchiveById(Long fileArchiveId) throws UserNotAuthorizedException {
        context.checkLogin();

        return fileStorage.getAllFilesInFileArchive(fileArchiveId).stream().toList();
    }

    public void updateById(Long fileId, String newName) throws NotFoundException, UserNotAuthorizedException {
        context.checkLogin();

        Optional<File> oneById = fileStorage.getOneById(fileId);
        if (oneById.isEmpty())
            throw new NotFoundException("Файл с таким id не найден и не может быть обновлен");
        oneById.ifPresent(file -> {
            file.setName(newName);
            fileStorage.updateById(file);
        });
    }

    public void removeFileById(Long fileId) {
        fileStorage.removeById(fileId);
    }

    @Override
    public ApplicationContext getApplicationContext() {
        return context;
    }
}
