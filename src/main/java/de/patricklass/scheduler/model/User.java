package de.patricklass.scheduler.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String userName;

    @Column
    @ManyToMany
    private List<Group> groups = new ArrayList<>();

    @Column
    private boolean isAdmin;

    private User() {}

    public User(String userName) {
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (getId() != user.getId()) return false;
        if (isAdmin() != user.isAdmin()) return false;
        if (!getUserName().equals(user.getUserName())) return false;
        return getGroups().equals(user.getGroups());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getUserName().hashCode();
        result = 31 * result + getGroups().hashCode();
        result = 31 * result + (isAdmin() ? 1 : 0);
        return result;
    }
}
