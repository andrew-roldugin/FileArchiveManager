package ru.vsu.cs.group7.model;


import ru.vsu.cs.group7.storage.JDBCStorage.annotations.Column;
import ru.vsu.cs.group7.storage.JDBCStorage.annotations.ForeignKey;
import ru.vsu.cs.group7.storage.JDBCStorage.annotations.Table;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Table(name = "Archive")
public class FileArchive extends Entity {

    @Column(name = "name")
    private String name;
    @Column(name = "update_time")
    private LocalDate updateTime;
    @Column(name = "create_time")
    private final LocalDate createTime;
    @ForeignKey(name = "owner", linkTo = "id")
    private final User owner;

    public FileArchive(Long id, String name, LocalDate updateTime, LocalDate createTime, User owner) {
        super(id);
        this.name = name;
        this.updateTime = updateTime;
        this.createTime = createTime;
        this.owner = owner;
    }

    public FileArchive(Long id, String name, User owner) {
        this(id, name, LocalDate.now(), LocalDate.now(), owner);
    }

    public FileArchive(String name, User owner) {
        this(null, name, LocalDate.now(), LocalDate.now(), owner);
    }

    public String getName() {
        return name;
    }

    public LocalDate getUpdateTime() {
        return updateTime;
    }

    public LocalDate getCreateTime() {
        return createTime;
    }

    public User getOwner() {
        return owner;
    }

    public void setName(String name) {
        if (!(name == null || name.isBlank()))
            this.name = name;
    }

    public void setUpdateTime(LocalDate updateTime) {
        if (updateTime != null)
            this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return String.format("%s\t%s\t%s\t%s", getId(), name,
                createTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy")),
                updateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
        );
    }

}
