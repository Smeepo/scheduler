package de.patricklass.scheduler.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table
public class Invitation {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private LocalDate date;

    @Column
    private String name;

    @Column
    private String description;

    @ElementCollection
    private Map<User, InvitationStatus> statusMap;

    public Invitation() { }

    public Invitation(LocalDate date, Group group){
        this.date = date;
        statusMap = new HashMap<>();
        group.getUsers().forEach(user -> statusMap.put(user, InvitationStatus.NOT_ANSWERED));
    }

    @Override
    public String toString() {
        return "Invitation{" +
                "id=" + id +
                ", date=" + date +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", statusMap=" + statusMap +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Invitation)) return false;

        Invitation that = (Invitation) o;

        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return getId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<User, InvitationStatus> getStatusMap() {
        return statusMap;
    }

    public void setStatusMap(Map<User, InvitationStatus> statusMap) {
        this.statusMap = statusMap;
    }
}
