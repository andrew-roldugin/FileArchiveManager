package ru.vsu.cs.group7.storage.inMemoryStorage;

import ru.vsu.cs.group7.model.FileArchive;
import ru.vsu.cs.group7.storage.Storage;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FileArchiveStorage extends Storage<FileArchive> {
    void removeAllByUserId(Long userId);
    List<FileArchive> getAllArchivesByUserId(Long id);
}
