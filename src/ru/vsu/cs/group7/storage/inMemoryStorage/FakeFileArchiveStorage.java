package ru.vsu.cs.group7.storage.inMemoryStorage;

import ru.vsu.cs.group7.model.FileArchive;

import java.util.*;
import java.util.stream.Collectors;

public class FakeFileArchiveStorage extends FakeStorage<FileArchive> implements FileArchiveStorage {

    public FakeFileArchiveStorage(List<FileArchive> storage) {
        super(storage);
    }

    public FakeFileArchiveStorage() {
        super(new LinkedList<>());
    }

    public void removeAllByUserId(UUID userId) {
        storage = storage.stream()
                .filter(fileArchive -> !fileArchive.getOwner().getId().equals(userId))
                .collect(Collectors.toList());
    }

//    @Override
//    public Optional<FileArchive> getOneByName(String fileArchiveName) {
//        return storage.stream()
//                .filter(fileArchive -> fileArchive.getName().equals(fileArchiveName))
//                .findFirst();
//    }

    @Override
    public List<FileArchive> getAllArchivesByUserId(UUID userId) {
        return storage.stream()
                .filter(fileArchive -> fileArchive.getOwner().getId().equals(userId))
                .collect(Collectors.toList());
    }

    @Override
    public void updateById(FileArchive newData) {
        updateById(newData, fileArchive -> {
            fileArchive.setName(newData.getName());
            fileArchive.setUpdateTime(new Date());
        });
    }
}
