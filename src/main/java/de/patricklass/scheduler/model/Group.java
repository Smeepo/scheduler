package de.patricklass.scheduler.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Group {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String groupName;

    @Column
    @ManyToMany
    private List<User> user = new ArrayList<>();

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

    public List<User> getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Group)) return false;

        Group group = (Group) o;

        if (getId() != group.getId()) return false;
        if (!getGroupName().equals(group.getGroupName())) return false;
        return getUser().equals(group.getUser());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getGroupName().hashCode();
        result = 31 * result + getUser().hashCode();
        return result;
    }

}
