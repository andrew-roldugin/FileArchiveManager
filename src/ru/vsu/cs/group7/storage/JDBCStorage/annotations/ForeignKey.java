package ru.vsu.cs.group7.storage.JDBCStorage.annotations;

import ru.vsu.cs.group7.model.Entity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ForeignKey {
    Table table();
    Field field();
}
