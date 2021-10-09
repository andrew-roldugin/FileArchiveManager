package ru.vsu.cs.group7.storage.inMemoryStorage;

import ru.vsu.cs.group7.model.File;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

public class FakeFileStorage extends FakeStorage<File> implements FileStorage {

    public FakeFileStorage(Collection<File> storage) {
        super(storage);
    }

    public FakeFileStorage() {
        super(new HashSet<>());
    }

    public Optional<File> getFileByName(String fileName) {
        return storage.stream()
                .filter(f -> f.getName().equals(fileName))
                .findFirst();
    }

    public Optional<File> getFilesInFileArchive(UUID fileArchiveId) {
        return storage.stream()
                .filter(f -> f.getId().equals(fileArchiveId))
                .findAny();
    }

    @Override
    public void fromCollection(Collection<File> files) {
        storage.addAll(files);
    }

    @Override
    public void updateById(File newData) {
        updateById(newData, file -> {
            file.setName(newData.getName());
        });
    }
}
