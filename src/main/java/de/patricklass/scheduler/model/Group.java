package de.patricklass.scheduler.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String groupName;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<User> users = new HashSet<>();

    @OneToMany
    private Set<Invitation> invitations = new HashSet<>();

    public Set<Invitation> getInvitations() {
        return invitations;
    }

    private Group() {}

    public Group(String groupName) {
        this.groupName = groupName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Set<User> getUsers() {
        return users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Group)) return false;

        Group group = (Group) o;

        if (getId() != group.getId()) return false;
        if (!getGroupName().equals(group.getGroupName())) return false;
        return getUsers().equals(group.getUsers());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getGroupName().hashCode();
        result = 31 * result + getUsers().hashCode();
        return result;
    }

}
