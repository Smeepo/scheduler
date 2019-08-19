package de.patricklass.scheduler.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Group {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String groupName;

    @Column
    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> users = new ArrayList<>();

    private List<Invitation> invitations = new ArrayList<>();

    public List<Invitation> getInvitations() {
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

    public List<User> getUsers() {
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
