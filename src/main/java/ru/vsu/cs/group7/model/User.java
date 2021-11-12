package ru.vsu.cs.group7.model;

import ru.vsu.cs.group7.storage.JDBCStorage.annotations.Column;
import ru.vsu.cs.group7.storage.JDBCStorage.annotations.Table;

@Table(name = "User")
public class User extends Entity {

    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private RoleEnum role;

    public User(Long id, String login, String password, RoleEnum role) {
        super(id);
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public User(String login, String password, RoleEnum role) {
        this(null, login, password, role);
    }

    public User(Long id, String login, String password) {
        this(id, login, password, RoleEnum.User);
    }

    public User(String login, String password) {
        this(null, login, password, RoleEnum.User);
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


    @Override
    public String toString() {
        return String.format("%s\t%s\t%s", getId(), login, role);
    }

    public enum RoleEnum {
        User,
        Admin
    }
}
