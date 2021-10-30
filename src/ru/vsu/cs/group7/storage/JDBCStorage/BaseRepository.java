package ru.vsu.cs.group7.storage.JDBCStorage;

import com.mysql.cj.MysqlType;
import ru.vsu.cs.group7.model.Entity;
import ru.vsu.cs.group7.model.File;
import ru.vsu.cs.group7.model.User;
import ru.vsu.cs.group7.storage.JDBCStorage.annotations.Field;
import ru.vsu.cs.group7.storage.interfaces.Storage;

import java.lang.reflect.ParameterizedType;
import java.sql.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class BaseRepository<T extends Entity, ID> implements Storage<T, ID> {

    private ConnectionManager connectionManager = ConnectionManager.getInstance();

    protected Extractor<T> extractor = rs -> {
        Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        System.out.println(clazz.getSimpleName());
        System.out.println(m(clazz));
        return null;
    };

    private String m(Class<T> clazz) {
        try {
            Field f = File.class.getDeclaredField("name").getAnnotation(Field.class);
            String name = f.name();
            MysqlType mysqlType = f.targetType();
            System.out.println(MysqlType.getByName("DECIMAL"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        switch (ClassNames.valueOf(clazz.getSimpleName())) {
            case File:
                return "file";
            case User:
                return "user";
            case FileArchive:
                return "archive";
        }
        return null;
    }

    @Override
    public Collection<T> getAll() {
        return executeSelect("SELECT * FROM file", extractor);
    }

    private List<T> executeSelect(String query, Extractor<T> extractor) {
        try (
                Connection connection = connectionManager.getConnection();
                Statement statement = connection.createStatement()
        ) {
            ResultSet resultSet = statement.executeQuery(query);
            return extractor.extract(resultSet);
        } catch (SQLException e) {
            System.out.println("Unable to get data: " + e.getMessage());
        }
        return Collections.emptyList();
    }
}
