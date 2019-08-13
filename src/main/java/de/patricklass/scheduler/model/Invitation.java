package de.patricklass.scheduler.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Invitation {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private LocalDate date;

    @Column
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
}
