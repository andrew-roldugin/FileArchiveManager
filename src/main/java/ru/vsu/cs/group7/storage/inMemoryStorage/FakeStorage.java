package ru.vsu.cs.group7.storage.inMemoryStorage;

import ru.vsu.cs.group7.exception.NotFoundException;
import ru.vsu.cs.group7.model.Entity;
import ru.vsu.cs.group7.storage.interfaces.Storage;

import java.util.*;
import java.util.function.Consumer;

public abstract class FakeStorage<T extends Entity, ID> implements Storage<T, ID> {

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
    public Optional<T> getOneById(ID id) {
        return storage.stream()
                .filter(item -> item.getId().equals(id))
                .findAny();
    }

    @Override
    public Collection<T> getAll() {
        return Collections.unmodifiableCollection(storage);
    }

    @Override
    public void removeById(ID id) {
        final Entity[] t = new Entity[]{null};
        storage.stream()
                .filter(item -> item.getId().equals(id))
                .findAny()
                .ifPresent(item -> {
                            t[0] = item;
                            storage.remove(item);
                        }
                );
    }

    protected T updateById(ID id, Consumer<T> action) {
        Optional<T> oneById = getOneById(id);
        oneById.ifPresentOrElse(action, NotFoundException::new);
        return oneById.get();
    }
}
