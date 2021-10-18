package ru.vsu.cs.group7.storage.interfaces;

import ru.vsu.cs.group7.model.Entity;

import java.util.Collection;
import java.util.Optional;

public interface Storage<T extends Entity> {
    T save(T item);
    Optional<T> getOneById(Long id);
    Collection<T> getAll();
    T updateById(Long id, T newData);
    T removeById(Long id);
}
