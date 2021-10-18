package ru.vsu.cs.group7.storage.inMemoryStorage;

import ru.vsu.cs.group7.model.FileArchive;
import ru.vsu.cs.group7.storage.interfaces.FileArchiveStorage;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class FakeFileArchiveStorage extends FakeStorage<FileArchive> implements FileArchiveStorage {

    public FakeFileArchiveStorage(List<FileArchive> storage) {
        super(storage);
    }

    public FakeFileArchiveStorage() {
        super(new LinkedList<>());
    }

    @Override
    public List<FileArchive> getAllArchivesByUserId(Long userId) {
        return storage.stream()
                .filter(fileArchive -> fileArchive.getOwner().getId().equals(userId))
                .collect(Collectors.toList());
    }

    @Override
    public FileArchive updateById(FileArchive newData) {
        return updateById(newData, fileArchive -> {
            fileArchive.setName(newData.getName());
            fileArchive.setUpdateTime(LocalDate.now());
        });
    }
}
