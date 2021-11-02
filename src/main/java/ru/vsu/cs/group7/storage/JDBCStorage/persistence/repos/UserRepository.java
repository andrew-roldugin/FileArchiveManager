package ru.vsu.cs.group7.storage.JDBCStorage.persistence.repos;

import org.antlr.v4.runtime.misc.Pair;
import ru.vsu.cs.group7.model.User;
import ru.vsu.cs.group7.storage.interfaces.UserStorage;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserRepository extends BaseRepository<User, Long> implements UserStorage<Long> {

    @Override
    public Optional<User> getOneByLogin(String login) throws SQLException {
        final List<User> res = getAllBy(new Pair<>("user.login", String.format("'%s'", login)));
        return Optional.ofNullable(res.isEmpty() ? null : res.get(0));
//        final List<User> extract;
//        try (
//                Connection connection = getConnectionManager().getConnection();
//                Statement statement = connection.createStatement()
//        ) {
//            final ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM user WHERE login='%s'", login));
//            extract = getExtractor().extract(resultSet, false, MapperFabric.getMapper(User.class));
//        }
//        return Optional.ofNullable(extract.get(0));
    }
}
