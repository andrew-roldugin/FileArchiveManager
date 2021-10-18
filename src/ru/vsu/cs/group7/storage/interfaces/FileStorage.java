package ru.vsu.cs.group7.storage.interfaces;

import ru.vsu.cs.group7.model.File;
import ru.vsu.cs.group7.storage.interfaces.Storage;

import java.util.List;

public interface FileStorage extends Storage<File> {
    List<File> getAllFilesInFileArchive(Long fileArchiveId);
    void removeAllByArchiveId(Long archiveId);
}
