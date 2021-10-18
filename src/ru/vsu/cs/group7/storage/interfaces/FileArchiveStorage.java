package ru.vsu.cs.group7.storage.interfaces;

import ru.vsu.cs.group7.model.FileArchive;
import ru.vsu.cs.group7.storage.interfaces.Storage;

import java.util.List;

public interface FileArchiveStorage extends Storage<FileArchive> {
    List<FileArchive> getAllArchivesByUserId(Long id);
}
