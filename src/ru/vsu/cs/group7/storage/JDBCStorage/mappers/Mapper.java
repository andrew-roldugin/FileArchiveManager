package ru.vsu.cs.group7.storage.JDBCStorage.mappers;

import ru.vsu.cs.group7.model.Entity;
import ru.vsu.cs.group7.model.File;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface Mapper<T extends Entity> {
    PreparedStatement fromObjToQuery(T obj) throws SQLException, NoSuchFieldException;
    T toObj(ResultSet resultSet);
}
