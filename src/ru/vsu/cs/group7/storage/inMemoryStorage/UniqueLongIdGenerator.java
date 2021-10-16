package ru.vsu.cs.group7.storage.inMemoryStorage;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

public class UniqueLongIdGenerator {
    private static final AtomicLong atomicCounter = new AtomicLong(1);

    public static Long generate(){
        return atomicCounter.incrementAndGet() + new Date().getTime();
    }
}
