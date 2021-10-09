package ru.vsu.cs.group7.storage.inMemoryStorage;

import ru.vsu.cs.group7.model.FileArchive;
import ru.vsu.cs.group7.storage.Storage;

import java.util.UUID;

public interface FileArchiveStorage extends Storage<FileArchive> {
    void removeAllByUserId(UUID userId);
}