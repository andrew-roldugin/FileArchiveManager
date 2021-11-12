package ru.vsu.cs.group7.storage.JDBCStorage.persistence;

import ru.vsu.cs.group7.model.Entity;
import ru.vsu.cs.group7.storage.JDBCStorage.annotations.Column;
import ru.vsu.cs.group7.storage.JDBCStorage.annotations.ForeignKey;
import ru.vsu.cs.group7.storage.JDBCStorage.annotations.Table;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class MySQLQueryGenerator {

    public static <T extends Entity> String insert(Class<T> clazz) {
        List<String>[] parts = new List[2];
        parts[0] = new ArrayList<>();
        parts[1] = new ArrayList<>();

        Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> field.getDeclaredAnnotations().length > 0)
                .map(field -> {
                    Object temp;
                    if ((temp = field.getAnnotation(Column.class)) != null) {
                        return ((Column) temp).name();
                    } else if ((temp = field.getAnnotation(ForeignKey.class)) != null)
                        return ((ForeignKey) temp).name();
                    return null;
                }).forEach(x -> {
            parts[0].add(x);
            parts[1].add("?");
        });

        return String.format(
                "INSERT INTO %s (%s) Values (%s)",
                getTableName(clazz),
                String.join(", ", parts[0]),
                String.join(", ", parts[1])
        );
    }

    private static <T extends Entity> StringBuilder select(Class<T> clazz) {
        class InnerClass {
            private static <T extends Entity> StringBuilder scanEntity(Class<T> clazz, StringBuilder sb) {
                final Queue<Field> foreignKeys = Arrays.stream(clazz.getDeclaredFields())
                        .filter(field -> field.isAnnotationPresent(ForeignKey.class))
                        .collect(Collectors.toCollection(ArrayDeque::new));

                if (foreignKeys.size() > 0) {
                    final Field fk = foreignKeys.poll();
                    final ForeignKey declaredAnnotation = fk.getDeclaredAnnotation(ForeignKey.class);
                    sb
                            .append("JOIN ")
                            .append(getTableName((Class<? extends Entity>) fk.getType()))
                            .append(" ON ")
                            .append(getTableName((Class<? extends Entity>) fk.getType())).append(".").append(declaredAnnotation.linkTo())
                            .append("=")
                            .append(getTableName(clazz)).append(".").append(declaredAnnotation.name())
                            .append(" ");

                    scanEntity((Class<T>) fk.getType(), sb);
                }
                return sb;
            }
        }

        return InnerClass.scanEntity(clazz, new StringBuilder("SELECT * FROM ").append(getTableName(clazz)).append(" "));
    }

    public static <T extends Entity> String select(Class<T> clazz, Pair<String, Object> pair) {
        return select(clazz)
                .append("WHERE ")
                .append(pair.getFirst()).append("=").append(pair.getSecond())
                .toString();
    }

    public static <T extends Entity, ID> String select(Class<T> clazz, ID id) {
        final StringBuilder sb = select(clazz);
        return id != null ? sb
                .append("WHERE ").append(getTableName(clazz)).append(".")
                .append("id=").append(id).toString() : sb.toString();
    }

    public static <T extends Entity, ID> String update(T newData, ID id) {
        String collect = Arrays.stream(newData.getClass().getDeclaredFields())
                .filter(field -> {
                    try {
                        field.setAccessible(true);
                        return field.isAnnotationPresent(Column.class) && field.get(newData) != null;
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    return false;
                })
                .map(field -> {
                    field.setAccessible(true);
                    try {
                        return String.format(" %s='%s' ", field.getAnnotation(Column.class).name(), field.get(newData));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .collect(Collectors.joining(", "));

        return String.format("UPDATE %s SET %s WHERE id=%s", getTableName(newData.getClass()), collect, id);
    }

    public static <T extends Entity, ID> String remove(Class<T> clazz, ID id) {
        return String.format("DELETE FROM %s WHERE id = %s", getTableName(clazz), id);
    }

    private static <T extends Entity> String getTableName(Class<T> clazz) {
        return clazz.getDeclaredAnnotation(Table.class).name().toLowerCase();
    }
}
