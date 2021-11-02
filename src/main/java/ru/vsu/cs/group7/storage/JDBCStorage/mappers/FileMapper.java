package ru.vsu.cs.group7.storage.JDBCStorage.mappers;

import ru.vsu.cs.group7.model.File;
import ru.vsu.cs.group7.model.FileArchive;
import ru.vsu.cs.group7.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FileMapper implements Mapper<File> {

    public File mapToJavaObject(ResultSet resultSet) throws SQLException {
        FileArchive fa = new FileArchive(
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
        return new File(
                resultSet.getLong("file.id"),
                fa,
                resultSet.getString("file.name"),
                resultSet.getDate("file.append_time").toLocalDate()
        );
    }
}
