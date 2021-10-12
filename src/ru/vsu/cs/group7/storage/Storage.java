package ru.vsu.cs.group7.storage;

import ru.vsu.cs.group7.model.Entity;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface Storage<T extends Entity> {
    void save(T items);
    Optional<T> getOneById(UUID id);
    Collection<T> getAll();
    void updateById(T newData);
    void removeById(UUID id);
}
