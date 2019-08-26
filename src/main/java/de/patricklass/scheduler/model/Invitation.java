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

    @ElementCollection(fetch = FetchType.EAGER)
    private Map<User, InvitationStatus> statusMap;

    public Invitation() { }

    public Invitation(LocalDate date, Group group){
        this.date = date;
        statusMap = new HashMap<>();
        group.getUsers().forEach(user -> statusMap.put(user, InvitationStatus.NOT_ANSWERED));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Invitation)) return false;

        Invitation that = (Invitation) o;

        if (id != that.id) return false;
        return date.equals(that.date);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + date.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Invitation{" +
                "id=" + id +
                ", date=" + date +
                ", statusMap=" + statusMap +
                '}';
    }


    // Getter & Setter
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

    public Map<User, InvitationStatus> getStatusMap() {
        return statusMap;
    }

    public void setStatusMap(Map<User, InvitationStatus> statusMap) {
        this.statusMap = statusMap;
    }
}
