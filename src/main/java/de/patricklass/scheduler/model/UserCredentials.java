package de.patricklass.scheduler.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class UserCredentials {

    @Id
    private String userName;

    @Column
    private String passwordEncrypted;

}
