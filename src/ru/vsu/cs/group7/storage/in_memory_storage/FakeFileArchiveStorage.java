package ru.vsu.cs.group7.storage.in_memory_storage;

import ru.vsu.cs.group7.model.FileArchive;
import ru.vsu.cs.group7.model.User;

import java.util.*;
import java.util.stream.Collectors;

public class FakeFileArchiveStorage extends FakeStorage<FileArchive> implements FileArchiveStorage{

    public FakeFileArchiveStorage(Collection<FileArchive> storage) {
        super(storage);
    }

    public FakeFileArchiveStorage() {
        super(new HashSet<>());
//        UserService userService = new UserService();
        storage.addAll(Arrays.asList(
                new FileArchive("test1", new User("Test", "123")),
                new FileArchive("test2", new User("Test", "123")),
                new FileArchive("test3", new User("Test", "123"))
        ));
    }

    public void removeAllByUserId(UUID userId){
        storage = storage.stream()
                .filter(fileArchive -> !fileArchive.getOwner().getId().equals(userId))
                .collect(Collectors.toSet());
    }

    @Override
    public void updateById(FileArchive newData) {
        updateById(newData, fileArchive -> {
            fileArchive.setName(newData.getName());
            fileArchive.setUpdateTime(new Date());
        });
    }
}
