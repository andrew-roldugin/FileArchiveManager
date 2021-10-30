package ru.vsu.cs.group7.storage.JDBCStorage.mappers;

import com.mysql.cj.MysqlType;
import ru.vsu.cs.group7.model.Entity;
import ru.vsu.cs.group7.model.File;
import ru.vsu.cs.group7.storage.JDBCStorage.ConnectionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;

public class FileMapper implements Mapper<File> {

    @Override
    public PreparedStatement fromObjToQuery(File obj) throws SQLException, NoSuchFieldException {
        String sql = "INSERT INTO file (name, append_time, file_archive_id) Values (?, ?, ?)";
        PreparedStatement preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setObject(1, "name", MysqlType.valueOf(File.class.getDeclaredField("name").getName()));// File.class.getDeclaredField("name").getType());
        preparedStatement.setInt(2, 2);
        return null;
    }

    @Override
    public File toObj(ResultSet resultSet) {

        return null;
    }
}
