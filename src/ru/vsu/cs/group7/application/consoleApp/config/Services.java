package ru.vsu.cs.group7.application.consoleApp.config;

import ru.vsu.cs.group7.model.File;
import ru.vsu.cs.group7.model.FileArchive;
import ru.vsu.cs.group7.model.User;
import ru.vsu.cs.group7.service.FileArchiveService;
import ru.vsu.cs.group7.service.FileService;
import ru.vsu.cs.group7.service.Service;
import ru.vsu.cs.group7.service.UserService;
import ru.vsu.cs.group7.storage.inMemoryStorage.FakeFileArchiveStorage;
import ru.vsu.cs.group7.storage.inMemoryStorage.FakeFileStorage;
import ru.vsu.cs.group7.storage.inMemoryStorage.FakeUserStorage;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Services {

    private final Service userService;
    private final Service fileService;
    private final Service fileArchiveService;

    public Services(ApplicationContext context) {
        List<User> usersList = Arrays.asList(
                new User(UUID.fromString("13671c26-16f5-4bc2-978c-3dc6a5544b0b"), "user", "usr1"),
                new User(UUID.fromString("d150f135-f4b9-4b50-9018-51a1ed5982bf"), "admin", "pass", User.RoleEnum.Admin),
                new User(UUID.fromString("6bed28b0-902d-49f6-a322-b63b8ec5e6a7"), "Test", "123")
        );

        List<FileArchive> fileArchives = Arrays.asList(
                new FileArchive(UUID.fromString("954e1f1f-c048-47a4-bd26-8b722191c8af"), "test1", usersList.get(0)),
                new FileArchive(UUID.fromString("6a84eb54-bcee-4a13-8dc6-93f7c14d009f"), "test2", usersList.get(0)),
                new FileArchive(UUID.fromString("db1503b6-0e2c-4c71-99f7-6f848b8ac0f5"), "test3", usersList.get(0)),
                new FileArchive(UUID.fromString("82703e60-9eeb-49b1-8adb-c63b3afc5dc9"), "archive1", usersList.get(1)),
                new FileArchive(UUID.fromString("56bde9dc-5289-4fec-8a11-c42cd12896b3"), "archive2", usersList.get(1)),
                new FileArchive(UUID.fromString("2fa03ef5-a65c-4bbd-9636-4416cdb24139"), "archive3", usersList.get(1)),
                new FileArchive(UUID.fromString("ee6ad082-cc83-4a45-8133-0c60a7225d64"), "archive4", usersList.get(1)),
                new FileArchive(UUID.fromString("6b2cc1ed-83c5-4fd6-b14f-cd93936bf982"), "_new_archive_", usersList.get(2))
        );

        List<File> files = Arrays.asList(
                new File("test11", fileArchives.get(0)),
                new File("test12", fileArchives.get(0)),
                new File("test13", fileArchives.get(0)),
                new File("file2", fileArchives.get(1)),
                new File("file3", fileArchives.get(1)),
                new File("file4", fileArchives.get(2)),
                new File("file5", fileArchives.get(2)),
                new File("file6", fileArchives.get(3)),
                new File("file7", fileArchives.get(3)),
                new File("file8", fileArchives.get(4)),
                new File("file9", fileArchives.get(5)),
                new File("file10", fileArchives.get(6))
        );

        FakeFileArchiveStorage fileArchiveStorage = new FakeFileArchiveStorage(fileArchives);
        FakeUserStorage userStorage = new FakeUserStorage(usersList);
        FakeFileStorage fileStorage = new FakeFileStorage(files);

        this.userService = new UserService(userStorage, fileArchiveStorage, fileStorage, context);
        this.fileService = new FileService(fileStorage, fileArchiveStorage, context);
        this.fileArchiveService = new FileArchiveService(fileArchiveStorage, fileStorage, context);
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
