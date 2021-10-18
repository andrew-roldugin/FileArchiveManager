package ru.vsu.cs.group7.application.consoleApp.config;

import ru.vsu.cs.group7.model.File;
import ru.vsu.cs.group7.model.FileArchive;
import ru.vsu.cs.group7.model.User;
import ru.vsu.cs.group7.service.FileArchiveService;
import ru.vsu.cs.group7.service.FileService;
import ru.vsu.cs.group7.service.UserService;
import ru.vsu.cs.group7.storage.inMemoryStorage.FakeFileArchiveStorage;
import ru.vsu.cs.group7.storage.inMemoryStorage.FakeFileStorage;
import ru.vsu.cs.group7.storage.inMemoryStorage.FakeUserStorage;
import ru.vsu.cs.group7.storage.inMemoryStorage.UniqueLongIdGenerator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Services {

    private final UserService userService;
    private final FileService fileService;
    private final FileArchiveService fileArchiveService;

    public Services(ApplicationContext context) {
        List<User> usersList = Stream.of(
                new User("user", "usr1"),
                new User("admin", "pass", User.RoleEnum.Admin),
                new User("Test", "123")
        ).peek(user -> user.setId(UniqueLongIdGenerator.generate())).collect(Collectors.toList());

        List<FileArchive> fileArchives = Stream.of(
                new FileArchive("test1", usersList.get(0)),
                new FileArchive("test2", usersList.get(0)),
                new FileArchive("test3", usersList.get(0)),
                new FileArchive("archive1", usersList.get(1)),
                new FileArchive("archive2", usersList.get(1)),
                new FileArchive("archive3", usersList.get(1)),
                new FileArchive("archive4", usersList.get(1)),
                new FileArchive(1L, "_new_archive_", usersList.get(2))
        ).peek(fileArchive -> fileArchive.setId(UniqueLongIdGenerator.generate())).collect(Collectors.toList());

        List<File> files = Stream.of(
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
        ).peek(file -> file.setId(UniqueLongIdGenerator.generate())).collect(Collectors.toList());

        FakeFileArchiveStorage fileArchiveStorage = new FakeFileArchiveStorage(fileArchives);
        FakeUserStorage userStorage = new FakeUserStorage(usersList);
        FakeFileStorage fileStorage = new FakeFileStorage(files);

        this.userService = new UserService(userStorage, fileArchiveStorage, fileStorage, context);
        this.fileService = new FileService(fileStorage, fileArchiveStorage, context);
        this.fileArchiveService = new FileArchiveService(fileArchiveStorage, fileStorage, context);
    }

    public UserService getUserService() {
        return userService;
    }

    public FileService getFileService() {
        return fileService;
    }

    public FileArchiveService getFileArchiveService() {
        return fileArchiveService;
    }
}
