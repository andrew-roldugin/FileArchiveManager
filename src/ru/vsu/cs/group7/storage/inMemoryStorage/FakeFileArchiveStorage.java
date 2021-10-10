package ru.vsu.cs.group7.storage.inMemoryStorage;

import ru.vsu.cs.group7.model.FileArchive;
import ru.vsu.cs.group7.model.User;

import java.util.*;
import java.util.stream.Collectors;

public class FakeFileArchiveStorage extends FakeStorage<FileArchive> implements FileArchiveStorage {

    public FakeFileArchiveStorage(Collection<FileArchive> storage) {
        super(storage);
    }

    public FakeFileArchiveStorage() {
        super(new HashSet<>());
        storage.addAll(Arrays.asList(
                new FileArchive(UUID.fromString("954e1f1f-c048-47a4-bd26-8b722191c8af"), "test1", new User("Test", "123")),
                new FileArchive(UUID.fromString("6a84eb54-bcee-4a13-8dc6-93f7c14d009f"), "test2", new User("Test", "123")),
                new FileArchive(UUID.fromString("db1503b6-0e2c-4c71-99f7-6f848b8ac0f5"), "test3", new User("Test", "123"))
        ));
    }

    public void removeAllByUserId(UUID userId) {
        storage = storage.stream()
                .filter(fileArchive -> !fileArchive.getOwner().getId().equals(userId))
                .collect(Collectors.toSet());
    }

    @Override
    public Optional<FileArchive> getOneByName(String fileArchiveName) {
        return storage.stream()
                .filter(fileArchive -> fileArchive.getName().equals(fileArchiveName))
                .findFirst();
    }

    @Override
    public void updateById(FileArchive newData) {
        updateById(newData, fileArchive -> {
            fileArchive.setName(newData.getName());
            fileArchive.setUpdateTime(new Date());
        });
    }
}
