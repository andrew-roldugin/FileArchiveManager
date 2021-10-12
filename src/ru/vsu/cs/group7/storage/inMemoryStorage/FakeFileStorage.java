package ru.vsu.cs.group7.storage.inMemoryStorage;

import ru.vsu.cs.group7.model.File;

import java.util.*;
import java.util.stream.Collectors;

public class FakeFileStorage extends FakeStorage<File> implements FileStorage {

    public FakeFileStorage(List<File> storage) {
        super(storage);
    }

    public FakeFileStorage() {
        super(new LinkedList<>());
    }

//    public Optional<File> getFileByName(UUID archiveId, String fileName) {
//        return storage.stream()
//                .filter(f -> f.getFileArchive().getId().equals(archiveId) && f.getName().equals(fileName))
//                .findFirst();
//    }

    public List<File> getFilesInFileArchive(UUID fileArchiveId) {
        return storage.stream()
                .filter(f -> f.getFileArchive().getId().equals(fileArchiveId))
                .collect(Collectors.toList());
    }

//    public Optional<File> getFilesInFileArchive(UUID userId, String archiveName) {
//        return storage.stream()
//                .filter(f -> f.getFileArchive().getOwner().getId().equals(userId) && f.getFileArchive().getName().equals(archiveName))
//                .findAny();
//    }

//    @Override
//    public void fromCollection(Collection<File> files) {
//        storage.addAll(files);
//    }


    @Override
    public void removeAllByArchiveId(UUID archiveId) {
        storage = storage.stream()
                .filter(file -> !file.getFileArchive().getId().equals(archiveId))
                .collect(Collectors.toList());
    }

    @Override
    public void updateById(File newData) {
        updateById(newData, file -> {
            file.setName(newData.getName());
        });
    }
}
