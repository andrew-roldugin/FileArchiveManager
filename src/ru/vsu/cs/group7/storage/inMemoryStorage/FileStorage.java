package ru.vsu.cs.group7.storage.inMemoryStorage;

import ru.vsu.cs.group7.model.File;
import ru.vsu.cs.group7.storage.Storage;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FileStorage extends Storage<File> {
//    Optional<File> getFileByName(UUID archiveId, String fileName);
    List<File> getFilesInFileArchive(UUID fileArchiveId);

    void removeAllByArchiveId(UUID archiveId);
//    Optional<File> getFilesInFileArchive(UUID userId, String fileArchiveName);
//    void fromCollection(Collection<File> files);
}
