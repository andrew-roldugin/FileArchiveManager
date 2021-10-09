package ru.vsu.cs.group7.storage.in_memory_storage;

import ru.vsu.cs.group7.model.File;
import ru.vsu.cs.group7.storage.Storage;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface FileStorage extends Storage<File> {
    Optional<File> getFileByName(String fileName);
    Optional<File> getFilesInFileArchive(UUID fileArchiveId);
    void fromCollection(Collection<File> files);
}
