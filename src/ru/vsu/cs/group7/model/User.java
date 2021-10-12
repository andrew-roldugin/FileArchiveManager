package ru.vsu.cs.group7.model;

import java.util.UUID;

public class User implements Entity {
    private final UUID id;
    private String login;
    private String password;
    private RoleEnum role;

    public User(UUID id, String login, String password, RoleEnum role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public User(String login, String password, RoleEnum role) {
        this(UUID.randomUUID(), login, password, role);
    }

    public User(UUID id, String login, String password) {
        this(id, login, password, RoleEnum.User);
    }

    public User(String login, String password) {
        this(UUID.randomUUID(), login, password, RoleEnum.User);
    }

    public UUID getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        User user = (User) o;
//
//        if (!id.equals(user.id)) return false;
//        return login.equals(user.login);
//    }
//
//    @Override
//    public int hashCode() {
//        int result = id.hashCode();
//        result = 31 * result + login.hashCode();
//        return result;
//    }

    @Override
    public String toString() {
        return String.format("%s\t%s", id, login);
    }

    public enum RoleEnum{
        User,
        Admin
    }
}
