package ru.vsu.cs.group7.storage.inMemoryStorage;

import ru.vsu.cs.group7.model.File;
import ru.vsu.cs.group7.storage.interfaces.FileStorage;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class FakeFileStorage extends FakeStorage<File> implements FileStorage {

    public FakeFileStorage(List<File> storage) {
        super(storage);
    }

    public FakeFileStorage() {
        super(new LinkedList<>());
    }

    public List<File> getAllFilesInFileArchive(Long fileArchiveId) {
        return storage.stream()
                .filter(f -> f.getFileArchive().getId().equals(fileArchiveId))
                .collect(Collectors.toList());
    }

    @Override
    public void removeAllByArchiveId(Long archiveId) {
        storage = storage.stream()
                .filter(file -> !file.getFileArchive().getId().equals(archiveId))
                .collect(Collectors.toList());
    }

    @Override
    public File updateById(File newData) {
        return updateById(newData, file -> {
            file.setName(newData.getName());
        });
    }
}
