package ru.vsu.cs.group7.model;

import java.util.Date;
import java.util.UUID;

public class File implements Entity {

    private final UUID id;
    private String name;
    private final Date appendTime;
    private final FileArchive fileArchive;

    public File(UUID id, FileArchive fileArchive, String name, Date appendTime) {
        this.id = id;
        this.name = name;
        this.appendTime = appendTime;
        this.fileArchive = fileArchive;
    }

    public File(String name, FileArchive fileArchive) {
        this(UUID.randomUUID(), fileArchive, name, new Date());
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && !name.isBlank())
            this.name = name;
    }

    public Date getAppendTime() {
        return appendTime;
    }

    public FileArchive getFileArchive() {
        return fileArchive;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(id).append("\t");
        sb.append(name).append('\t');
        sb.append(appendTime).append('\t');
        return sb.toString();
    }
}
