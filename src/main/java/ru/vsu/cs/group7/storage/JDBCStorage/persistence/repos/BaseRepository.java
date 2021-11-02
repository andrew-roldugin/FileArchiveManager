package ru.vsu.cs.group7.storage.JDBCStorage.persistence.repos;

import org.antlr.v4.runtime.misc.Pair;
import ru.vsu.cs.group7.model.Entity;
import ru.vsu.cs.group7.storage.JDBCStorage.mappers.MapperFabric;
import ru.vsu.cs.group7.storage.JDBCStorage.persistence.ConnectionManager;
import ru.vsu.cs.group7.storage.JDBCStorage.persistence.Extractor;
import ru.vsu.cs.group7.storage.JDBCStorage.persistence.MySQLQueryGenerator;
import ru.vsu.cs.group7.storage.interfaces.Storage;

import java.lang.reflect.ParameterizedType;
import java.sql.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public abstract class BaseRepository<T extends Entity, ID> implements Storage<T, ID> {

    private final ConnectionManager connectionManager = ConnectionManager.getInstance();
    private final Extractor<T> extractor = new Extractor<>();

    public T save(T item) throws NoSuchFieldException, IllegalAccessException, SQLException {
        String sql = MySQLQueryGenerator.insert(item.getClass());
        try (
                PreparedStatement preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(sql);
        ) {
            MapperFabric.getMapper(getCurrentClass()).fromJavaObjectToSQL(preparedStatement, item);
            preparedStatement.execute();
            return item;
        }
    }

    protected List<T> getAllBy(Pair<String, Object> selectBy) {
        return executeSelect(MySQLQueryGenerator.select(getCurrentClass(), selectBy), true, extractor);
    }

    @Override
    public Optional<T> getOneById(ID id) {
        List<T> res = executeSelect(id, extractor);
        return Optional.ofNullable(res.isEmpty() ? null : res.get(0));
    }

    @Override
    public Collection<T> getAll() {
        return executeSelect(null, extractor);
    }

    private List<T> executeSelect(ID id, Extractor<T> extractor) {
        return executeSelect(MySQLQueryGenerator.select(getCurrentClass(), id), id == null, extractor);
    }

    protected List<T> executeSelect(String query, boolean extractAll, Extractor<T> extractor) {
        try (
                Connection connection = connectionManager.getConnection();
                Statement statement = connection.createStatement()
        ) {
            ResultSet resultSet = statement.executeQuery(query);
            return extractor.extract(resultSet, extractAll, MapperFabric.getMapper(getCurrentClass()));
        } catch (SQLException e) {
            System.out.println("Unable to get data: " + e.getMessage());
        }
        return Collections.emptyList();
    }

    @Override
    public void removeById(ID id) throws SQLException {
        try (
                Connection connection = connectionManager.getConnection();
                Statement statement = connection.createStatement()
        ) {
            final String sql = MySQLQueryGenerator.remove(getCurrentClass(), id);
            statement.executeUpdate(sql);
        }
    }

    @Override
    public T updateById(ID id, T newData) throws SQLException, NoSuchFieldException, IllegalAccessException {
        String sql = MySQLQueryGenerator.update(newData, id);
        try (
                Statement statement = ConnectionManager.getInstance().getConnection().createStatement();
        ) {
            statement.executeUpdate(sql);
        }
        return executeSelect(id, getExtractor()).get(0);
    }

    private Class<T> getCurrentClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public Extractor<T> getExtractor() {
        return extractor;
    }

    public ConnectionManager getConnectionManager() {
        return connectionManager;
    }
}
