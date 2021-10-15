package ru.vsu.cs.group7.storage.inMemoryStorage;

import ru.vsu.cs.group7.model.File;
import ru.vsu.cs.group7.storage.Storage;

import java.util.List;
import java.util.UUID;

public interface FileStorage extends Storage<File> {
    List<File> getAllFilesInFileArchive(Long fileArchiveId);
    void removeAllByArchiveId(Long archiveId);
}
