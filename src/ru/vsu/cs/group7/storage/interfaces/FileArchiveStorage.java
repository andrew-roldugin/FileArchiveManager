package ru.vsu.cs.group7.storage.interfaces;

import ru.vsu.cs.group7.model.FileArchive;

import java.util.List;

public interface FileArchiveStorage<ID> extends Storage<FileArchive, ID> {
    List<FileArchive> getAllArchivesByUserId(ID id);
}
