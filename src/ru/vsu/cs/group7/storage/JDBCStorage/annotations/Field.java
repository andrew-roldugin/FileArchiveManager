package ru.vsu.cs.group7.storage.JDBCStorage.annotations;

import com.mysql.cj.MysqlType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Field {

    String name();

    MysqlType targetType();
}
