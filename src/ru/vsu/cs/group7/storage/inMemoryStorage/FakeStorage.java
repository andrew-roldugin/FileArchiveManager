package ru.vsu.cs.group7.storage.inMemoryStorage;

import ru.vsu.cs.group7.exception.NotFoundException;
import ru.vsu.cs.group7.model.Entity;
import ru.vsu.cs.group7.storage.interfaces.Storage;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public abstract class FakeStorage<T extends Entity> implements Storage<T> {

    protected List<T> storage;

    public FakeStorage(List<T> storage) {
        this.storage = new LinkedList<>(storage);
    }

    @Override
    public T save(T item) {
        item.setId(UniqueLongIdGenerator.generate());
        storage.add(item);
        return item;
    }

    @Override
    public Optional<T> getOneById(Long id) {
        return storage.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst();
    }

    @Override
    public Collection<T> getAll() {
        return Collections.unmodifiableCollection(storage);
    }

    @Override
    public void removeById(Long id) {
        storage = storage.stream()
                .filter(item -> !item.getId().equals(id))
                .collect(Collectors.toList());
    }

    protected T updateById(T newData, Consumer<T> action) {
        Long id = newData.getId();
        Optional<T> oneById = getOneById(id);
        if (oneById.isPresent()) {
            action.accept(oneById.get());
            return oneById.get();
        }
        return null;
    }
}
