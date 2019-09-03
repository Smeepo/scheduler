package de.patricklass.scheduler.model;

import javax.persistence.*;
import java.util.HashSet;
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

    @OneToMany(fetch = FetchType.EAGER)
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

        return getId() == group.getId();
    }

    @Override
    public int hashCode() {
        return getId();
    }
}
