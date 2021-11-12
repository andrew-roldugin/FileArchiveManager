package ru.vsu.cs.group7.storage.JDBCStorage.mappers;

import ru.vsu.cs.group7.model.Entity;
import ru.vsu.cs.group7.storage.JDBCStorage.persistence.ClassNames;

import java.util.HashMap;
import java.util.Map;

public class MapperFabric {

    private static Map<ClassNames, Mapper> cache = new HashMap<>();

    public static <T extends Entity> Mapper getMapper(Class<T> clazz) {
        final ClassNames className = ClassNames.valueOf(clazz.getSimpleName());
        Mapper mapper;
        if ((mapper = cache.get(className)) != null)
            return mapper;

        mapper = switch (className) {
            case File -> new FileMapper();
            case User -> new UserMapper();
            case FileArchive -> new FileArchiveMapper();
        };
        cache.put(className, mapper);
        return mapper;
    }
}
