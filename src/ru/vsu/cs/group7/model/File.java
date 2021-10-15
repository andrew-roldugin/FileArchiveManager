package ru.vsu.cs.group7.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.UUID;

public class File extends Entity {

    private String name;
    private final LocalDate appendTime;
    private final FileArchive fileArchive;

    public File(Long id, FileArchive fileArchive, String name, LocalDate appendTime) {
        super(id);
        this.name = name;
        this.appendTime = appendTime;
        this.fileArchive = fileArchive;
    }

    public File(String name, FileArchive fileArchive) {
        this(null, fileArchive, name, LocalDate.now());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && !name.isBlank())
            this.name = name;
    }

    public LocalDate getAppendTime() {
        return appendTime;
    }

    public FileArchive getFileArchive() {
        return fileArchive;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(getId()).append("\t");
        sb.append(name).append('\t');
        sb.append(appendTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))).append('\t');
        return sb.toString();
    }
}
