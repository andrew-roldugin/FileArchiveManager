package ru.vsu.cs.group7.model;

import java.util.Date;
import java.util.UUID;

public class File implements Storeable {

    private final UUID id;
    private String name;
    private final Integer size;
    private String extension;
    private final Date appendTime;
    private final FileArchive fileArchive;

    public File(UUID id, FileArchive fileArchive, String name, Integer size, String extension, Date appendTime) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.extension = extension;
        this.appendTime = appendTime;
        this.fileArchive = fileArchive;
    }

//    public File(String name, Integer size) {
//        this(UUID.randomUUID(), name, size, name.split("\\.")[1], new Date());
//    }


    public File(String name, FileArchive fileArchive) {
        this(UUID.randomUUID(), fileArchive, name, 0, name.split("\\.")[1], new Date());
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && !name.isBlank()) {
            this.name = name;
            this.extension = name.split("\\.")[1];
        }
    }

    public Integer getSize() {
        return size;
    }

    public String getExtension() {
        return extension;
    }

    public Date getAppendTime() {
        return appendTime;
    }

    public FileArchive getFileArchive() {
        return fileArchive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        File file = (File) o;

        if (!id.equals(file.id)) return false;
        if (!name.equals(file.name)) return false;
//        if (!size.equals(file.size)) return false;
        return extension.equals(file.extension);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
//        result = 31 * result + size.hashCode();
        result = 31 * result + extension.hashCode();
        return result;
    }
}
