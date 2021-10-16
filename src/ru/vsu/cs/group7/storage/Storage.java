package ru.vsu.cs.group7.storage;

import ru.vsu.cs.group7.model.Entity;

import java.util.Collection;
import java.util.Optional;

public interface Storage<T extends Entity> {
    void save(T item);
    Optional<T> getOneById(Long id);
    Collection<T> getAll();
    void updateById(T newData);
    void removeById(Long id);
}
