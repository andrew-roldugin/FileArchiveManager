package ru.vsu.cs.group7.model;

public abstract class Entity {

    private Long id;

    protected Entity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
