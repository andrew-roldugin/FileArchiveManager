package ru.vsu.cs.group7.storage.JDBCStorage.mappers;

import ru.vsu.cs.group7.model.FileArchive;
import ru.vsu.cs.group7.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FileArchiveMapper implements Mapper<FileArchive> {

    public FileArchive mapToJavaObject(ResultSet resultSet) throws SQLException {
        return new FileArchive(
                resultSet.getLong("archive.id"),
                resultSet.getString("archive.name"),
                resultSet.getDate("archive.update_time").toLocalDate(),
                resultSet.getDate("archive.create_time").toLocalDate(),
                new User(
                        resultSet.getLong("user.id"),
                        resultSet.getString("user.login"),
                        resultSet.getString("user.password"),
                        User.RoleEnum.valueOf(resultSet.getString("user.role"))
                )
        );
    }
}
