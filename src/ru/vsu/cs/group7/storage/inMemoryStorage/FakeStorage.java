package ru.vsu.cs.group7.storage.inMemoryStorage;

import ru.vsu.cs.group7.model.Storeable;
import ru.vsu.cs.group7.storage.Storage;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public abstract class FakeStorage<T extends Storeable> implements Storage<T> {
    protected Collection<T> storage;

    public FakeStorage(Collection<T> storage) {
        this.storage = storage;
    }

    @Override
    public void save(T... items) {
        storage.addAll(Arrays.asList(items));
    }

    @Override
    public Optional<T> getOneById(UUID id) {
        return storage.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst();
    }

    @Override
    public Collection<T> getAll() {
        return Collections.unmodifiableCollection(storage);
    }

    @Override
    public void removeById(UUID id) {
        storage = storage.stream()
                .filter(item -> !item.getId().equals(id))
                .collect(Collectors.toSet());
//        T value = getOneById(id)
//                .orElseThrow(() -> new NotFoundException(String.format("По id %s ничего не найдено", id.toString())));
//        storage.remove(value);
    }

    protected void updateById(T newData, Consumer<T> action) {
        UUID id = newData.getId();
        getOneById(id).ifPresent(action);
    }
}
