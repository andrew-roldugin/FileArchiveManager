package ru.vsu.cs.group7.model;

import ru.vsu.cs.group7.storage.JDBCStorage.annotations.Column;
import ru.vsu.cs.group7.storage.JDBCStorage.annotations.ForeignKey;
import ru.vsu.cs.group7.storage.JDBCStorage.annotations.Table;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Table(name = "File")
public class File extends Entity {

    @Column(name = "name")
    private String name;
    @Column(name = "append_time")
    private final LocalDate appendTime;
    @ForeignKey(name = "file_archive_id", linkTo = "id")
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
        if (!(name == null || name.isBlank()))
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
