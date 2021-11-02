package ru.vsu.cs.group7.storage.JDBCStorage.persistence;

import ru.vsu.cs.group7.model.Entity;
import ru.vsu.cs.group7.storage.JDBCStorage.mappers.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Extractor<T extends Entity> {

    public List<T> extract(ResultSet rs, boolean extractAll, Mapper<T> mapper) throws SQLException {
        return extractAll ? extractAll(rs, mapper) : Collections.singletonList(extractOne(rs, mapper));
    }

    private T extractOne(ResultSet rs, Mapper<T> mapper) throws SQLException {
        return rs.next() ? mapper.mapToJavaObject(rs) : null;
    }

    private List<T> extractAll(ResultSet rs, Mapper<T> mapper) throws SQLException {
        List<T> items = new ArrayList<>();
        while (rs.next()) {
            T item = mapper.mapToJavaObject(rs);
            if (item != null)
                items.add(item);
        }
        return items;
    }
}
