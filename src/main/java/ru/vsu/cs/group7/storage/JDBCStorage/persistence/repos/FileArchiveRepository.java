package ru.vsu.cs.group7.storage.JDBCStorage.persistence.repos;

import ru.vsu.cs.group7.model.FileArchive;
import ru.vsu.cs.group7.storage.JDBCStorage.persistence.Pair;
import ru.vsu.cs.group7.storage.interfaces.FileArchiveStorage;

import java.sql.SQLException;
import java.util.List;

public class FileArchiveRepository extends BaseRepository<FileArchive, Long> implements FileArchiveStorage<Long> {

    @Override
    public List<FileArchive> getAllArchivesByUserId(Long userId) throws SQLException {
        return getAllBy(new Pair<>("archive.owner", userId));
//        try (
//                Connection connection = getConnectionManager().getConnection();
//                Statement statement = connection.createStatement()
//        ) {
//            final ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM archive" +
//                                                                             " join user on user.id = archive.owner" +
//                                                                             " WHERE owner=%s", userId));
//            return getExtractor().extract(resultSet, true, MapperFabric.getMapper(FileArchive.class));
//        }
    }
}
