package ru.vsu.cs.group7.storage.interfaces;

import ru.vsu.cs.group7.model.Entity;

import java.util.Collection;
import java.util.Optional;

public interface Storage<T extends Entity, ID> {
    T save(T item);
    Optional<T> getOneById(ID id);
    Collection<T> getAll();
    T updateById(ID id, T newData);
    T removeById(ID id);
}
