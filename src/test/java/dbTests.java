import org.junit.Test;
import ru.vsu.cs.group7.model.Entity;
import ru.vsu.cs.group7.model.File;
import ru.vsu.cs.group7.model.FileArchive;
import ru.vsu.cs.group7.model.User;
import ru.vsu.cs.group7.storage.JDBCStorage.persistence.ConnectionManager;
import ru.vsu.cs.group7.storage.JDBCStorage.persistence.MySQLQueryGenerator;
import ru.vsu.cs.group7.storage.JDBCStorage.annotations.ForeignKey;
import ru.vsu.cs.group7.storage.JDBCStorage.annotations.Table;
import ru.vsu.cs.group7.storage.JDBCStorage.mappers.FileMapper;
import ru.vsu.cs.group7.storage.JDBCStorage.mappers.MapperFabric;

import java.lang.reflect.Field;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class dbTests {

    @Test
    public void test1() {
        try (
                Connection connection = ConnectionManager.getInstance().getConnection();
                Statement statement = connection.createStatement()
        ) {
//            ResultSet resultSet = statement.executeQuery("SELECT fa.id as archive_id, fa.name, fa.update_time, fa.create_time, fa.owner, f.id, f.name, f.append_time "
//                                                         + " FROM file f, filearchive fa "
//                                                         + " WHERE f.id = 1");
            ResultSet resultSet1 = statement.executeQuery("SELECT * "
                                                          + " FROM file f"
                                                          + " join archive fa on f.file_archive_id=fa.id"
                                                          + " join user u on u.id = fa.owner"
                                                          + " WHERE fa.id = 1");
            while (resultSet1.next()) {
                File file = new FileMapper().mapToJavaObject(resultSet1);
//                long id = resultSet1.getLong("fa.id");
                System.out.println(file);
            }
            System.out.println();

        } catch (SQLException e) {
            System.out.println("Unable to get data: " + e.getMessage());
        }


    }

    @Test
    public void updateTest() {
        User newData = new User(1L, "user_upd", "usr1_upd", User.RoleEnum.Admin);
//        String sql = MySQLQueryGenerator.insert(User.class);
//        try (
//                PreparedStatement preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(sql);
//        ) {
//            try {
//                MapperFabric.getMapper(User.class).fromJavaObjectToSQL(preparedStatement, user);
//            } catch (NoSuchFieldException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//            if (preparedStatement.executeUpdate() > 0)
//                System.out.println("Успешно");
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }

//        String sql = MySQLQueryGenerator.select(newData.getClass(), 1L);
//        try (
//                Statement statement = ConnectionManager.getInstance().getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
//        ) {
//            final ResultSet resultSet = statement.executeQuery(sql);
//
//            final List<Field> collect = Arrays.stream(newData.getClass().getDeclaredFields())
//                    .filter(field -> field.isAnnotationPresent(Column.class))
//                    .peek(field -> field.setAccessible(true))
//                    .collect(Collectors.toList());
//            resultSet.moveToInsertRow();
//            for (var f : collect) {
//                 Object x = f.get(newData);
//                if (x.getClass().isEnum())
//                    x = x.toString();
//                resultSet.updateObject(f.getName(), x);
//            }
//            statement.executeUpdate(sql);
////            resultSet.();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }


        String sql = MySQLQueryGenerator.update(newData, 1L);
        try (
                Statement statement = ConnectionManager.getInstance().getConnection().createStatement();
        ) {
            if (statement.executeUpdate(sql) > 0)
                System.out.println("Успешно");
            ;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Test
    public void insertTest() {
        User user = new User("insert_test", "insert_test");
        File file = new File(6L, new FileArchive(1L, "123", user), "insert_file_test_upd", LocalDate.now());
        String sql = MySQLQueryGenerator.insert(file.getClass());
        try (
                PreparedStatement preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(sql);
        ) {
            try {
                MapperFabric.getMapper(file.getClass()).fromJavaObjectToSQL(preparedStatement, file);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (preparedStatement.executeUpdate() > 0)
                System.out.println("Успешно");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    @Test
    public void fetchFileFromDBTest() {
//        String sql = "SELECT * FROM file" +
//                     "    join archive on archive.id=file.file_archive_id" +
//                     "    join user on user.id=archive.owner WHERE file.id=3";
        String sql = scanEntity(File.class);
        try (
                final Statement statement = ConnectionManager.getInstance().getConnection().createStatement();
        ) {
            final ResultSet resultSet = statement.executeQuery(sql);
            List<Entity> list = new ArrayList<>();
            while (resultSet.next())
                list.add(MapperFabric.getMapper(File.class).mapToJavaObject(resultSet));
            System.out.println();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    public void fetchUserFromDBTest() {
        Class<User> cls = User.class;
        String sql = scanEntity(cls);
        try (
                final Statement statement = ConnectionManager.getInstance().getConnection().createStatement();
        ) {
            final ResultSet resultSet = statement.executeQuery(sql);
            List<Entity> list = new ArrayList<>();
            while (resultSet.next())
                list.add(MapperFabric.getMapper(cls).mapToJavaObject(resultSet));
            System.out.println();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    public void fetchArchiveFromDBTest() {
        Class<? extends Entity> cls = FileArchive.class;
        String sql = scanEntity(cls);
        try (
                final Statement statement = ConnectionManager.getInstance().getConnection().createStatement();
        ) {
            final ResultSet resultSet = statement.executeQuery(sql);
            List<Entity> list = new ArrayList<>();
            while (resultSet.next())
                list.add(MapperFabric.getMapper(cls).mapToJavaObject(resultSet));
            System.out.println();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    public void testScanner() {
        String s = scanEntity(File.class);
        System.out.println(s);
        s = scanEntity(FileArchive.class);
        System.out.println(s);
    }

    private static <T extends Entity> String scanEntity(Class clazz) {

        class Cls {
            static <T extends Entity> StringBuilder scanEntityImpl(Class clazz, StringBuilder sb) {
                final Queue<Field> foreignKeys = Arrays.stream(clazz.getDeclaredFields())
                        .filter(field -> field.isAnnotationPresent(ForeignKey.class))
                        .collect(Collectors.toCollection(ArrayDeque::new));

                if (foreignKeys.size() > 0) {
                    final Field fk = foreignKeys.poll();
                    final ForeignKey declaredAnnotation = fk.getDeclaredAnnotation(ForeignKey.class);
                    sb
                            .append("JOIN ")
                            .append(getTableName(fk.getType())).append(" ON ")
                            .append(getTableName(fk.getType())).append(".").append(declaredAnnotation.linkTo())
                            .append("=")
                            .append(getTableName(clazz)).append(".").append(declaredAnnotation.name())
                            .append(" ");

                    scanEntityImpl(fk.getType(), sb);
                }
                return sb;
            }

        }
        return Cls.scanEntityImpl(clazz, new StringBuilder("SELECT * FROM ").append(getTableName(clazz)).append(" "))
                .append("WHERE ").append(getTableName(clazz)).append(".").append("id=").append(3).toString();
    }

    private static <T extends Entity> String getTableName(Class<?> clazz) {
        return clazz.getDeclaredAnnotation(Table.class).name().toLowerCase();
    }

}
