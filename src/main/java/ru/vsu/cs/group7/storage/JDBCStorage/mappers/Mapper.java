package ru.vsu.cs.group7.storage.JDBCStorage.mappers;

import ru.vsu.cs.group7.model.Entity;
import ru.vsu.cs.group7.storage.JDBCStorage.annotations.Column;
import ru.vsu.cs.group7.storage.JDBCStorage.annotations.ForeignKey;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface Mapper<T extends Entity> {

    default void fromJavaObjectToSQL(PreparedStatement preparedStatement, T object) throws SQLException, NoSuchFieldException, IllegalAccessException {
        Field[] fields = object.getClass().getDeclaredFields();
        int counter = 0;
        for (Field field : fields) {
            Annotation[] declaredAnnotations = field.getDeclaredAnnotations();
            for (Annotation annotation : declaredAnnotations) {
                Object v = null;
                if (annotation.annotationType().equals(Column.class)) {
                    field.setAccessible(true);
                    v = field.get(object);
                    if (v.getClass().isEnum())
                        v = v.toString();
                } else if (annotation.annotationType().equals(ForeignKey.class)) {
                    Class<?> superclass = field.getType().getSuperclass();
                    String ref = ((ForeignKey) annotation).linkTo();
                    Field field1 = superclass.getDeclaredField(ref);
                    field1.setAccessible(true);
                    field.setAccessible(true);
                    v = field1.get(field.get(object));
                }
                if (v != null)
                    preparedStatement.setObject(++counter, v);
            }
        }
    }

    T mapToJavaObject(ResultSet resultSet) throws SQLException;
}
