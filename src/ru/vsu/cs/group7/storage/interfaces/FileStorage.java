package ru.vsu.cs.group7.storage.interfaces;

import ru.vsu.cs.group7.model.File;

import java.util.List;

public interface FileStorage<ID> extends Storage<File, ID> {
    List<File> getAllFilesInFileArchive(Long fileArchiveId);
    void removeAllByArchiveId(Long archiveId);
}
