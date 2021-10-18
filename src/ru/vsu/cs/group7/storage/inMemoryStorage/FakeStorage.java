package ru.vsu.cs.group7.storage.inMemoryStorage;

import ru.vsu.cs.group7.exception.NotFoundException;
import ru.vsu.cs.group7.model.Entity;
import ru.vsu.cs.group7.storage.interfaces.Storage;

import java.util.*;
import java.util.function.Consumer;

public abstract class FakeStorage<T extends Entity> implements Storage<T> {

    protected List<T> storage;

    public FakeStorage(List<T> storage) {
        this.storage = new LinkedList<>(storage);
    }

    @Override
    public T save(T item) {
        if (item.getId() == null) {
            item.setId(UniqueLongIdGenerator.generate());
            storage.add(item);
        }
        return item;
    }

    @Override
    public Optional<T> getOneById(Long id) {
        return storage.stream()
                .filter(item -> item.getId().equals(id))
                .findAny();
    }

    @Override
    public Collection<T> getAll() {
        return Collections.unmodifiableCollection(storage);
    }

    @Override
    public T removeById(Long id) {
        final Entity[] t = new Entity[]{null};
        storage.stream()
                .filter(item -> item.getId().equals(id))
                .findAny()
                .ifPresent(item -> {
                            t[0] = item;
                            storage.remove(item);
                        }
                );
        return (T) t[0];
    }

    protected T updateById(Long id, Consumer<T> action) {
        Optional<T> oneById = getOneById(id);
        oneById.ifPresentOrElse(action, NotFoundException::new);
        return oneById.get();
    }
}
