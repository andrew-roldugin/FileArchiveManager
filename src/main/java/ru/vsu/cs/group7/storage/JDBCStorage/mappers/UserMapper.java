package ru.vsu.cs.group7.storage.JDBCStorage.mappers;

import ru.vsu.cs.group7.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements Mapper<User> {
    public User mapToJavaObject(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getLong("user.id"),
                resultSet.getString("user.login"),
                resultSet.getString("user.password"),
                User.RoleEnum.valueOf(resultSet.getString("user.role"))
        );
    }
}
