package ru.vsu.cs.group7.storage.JDBCStorage.persistence.repos;

import ru.vsu.cs.group7.model.File;
import ru.vsu.cs.group7.storage.JDBCStorage.persistence.Pair;
import ru.vsu.cs.group7.storage.interfaces.FileStorage;

import java.sql.SQLException;
import java.util.*;

public class FileRepository extends BaseRepository<File, Long> implements FileStorage<Long> {
    @Override
    public List<File> getAllFilesInFileArchive(Long fileArchiveId) {
        return getAllBy(new Pair<>("archive.id", fileArchiveId));
//        return executeSelect("SELECT * FROM file"
//                             + " join archive on file.file_archive_id = archive.id"
//                             + " join user on user.id = archive.owner"
//                             + " WHERE archive.id = " + fileArchiveId, true, getExtractor());
    }

    @Override
    public void removeAllByArchiveId(Long archiveId) throws SQLException {
        // реализуется базой с помощью ссылок
//        try (
//                Connection connection = getConnectionManager().getConnection();
//                Statement statement = connection.createStatement()
//        ) {
//            statement.executeQuery(String.format("DELETE FROM file WHERE file_archive_id=%s", archiveId));
//        }
    }
}
