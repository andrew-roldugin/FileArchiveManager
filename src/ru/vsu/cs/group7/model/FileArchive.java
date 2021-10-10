package ru.vsu.cs.group7.model;

import java.util.*;

public class FileArchive implements Storeable {

    private final UUID id;
    private String name;
    private Date updateTime;
    private final Date createTime;
    private final User owner;
//    private final Set<File> files;

    public FileArchive(UUID id, String name, Date updateTime, Date createTime, User owner) {
        this.id = id;
        this.name = name;
        this.updateTime = updateTime;
        this.createTime = createTime;
        this.owner = owner;
//        this.files = new HashSet<>();
    }

    public FileArchive(UUID id, String name, User owner) {
        this(id, name, new Date(), new Date(), owner);
    }

    public FileArchive(String name, User owner) {
        this(UUID.randomUUID(), name, new Date(), new Date(), owner);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public User getOwner() {
        return owner;
    }

    public void setName(String name) {
        if (name != null && !name.isBlank())
            this.name = name;
    }

    public void setUpdateTime(Date updateTime) {
        if (updateTime != null)
            this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return String.format("%s\t%s\t%s\t%s", id, name, createTime, updateTime);
    }

    //    public Set<File> getFiles() {
//        return Collections.unmodifiableSet(files);
//    }
//
//    public boolean addFile(File f){
//        return files.add(f);
//    }
//
//    public boolean removeFile(File f){
//        return files.remove(f);
//    }
//
//    public int getTotalCountFiles(){
//        return files.size();
//    }
//
//    public void addAll(Collection<File> files){
//        this.files.addAll(files);
//    }
//
//    public boolean isEmpty(){
//        return files.isEmpty();
//    }
}
