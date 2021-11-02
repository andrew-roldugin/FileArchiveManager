package ru.vsu.cs.group7.storage.JDBCStorage.mappers;

import org.apache.commons.lang3.NotImplementedException;
import ru.vsu.cs.group7.model.Entity;
import ru.vsu.cs.group7.storage.JDBCStorage.persistence.ClassNames;

public class MapperFabric {

    public static <T extends Entity> Mapper getMapper(Class<T> clazz) {
        return switch (ClassNames.valueOf(clazz.getSimpleName())) {
            case File -> new FileMapper();
            case User -> new UserMapper();
            case FileArchive -> new FileArchiveMapper();
        };
    }
}
