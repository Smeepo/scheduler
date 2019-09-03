package de.patricklass.scheduler.model;

import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Data class for login user credentials
 * @author Patrick Laß
 */

@Entity
@Table
public class UserCredentials {

    @Id
    private String userName;

    @Column
    private String passwordEncrypted;

    public UserCredentials(String userName, String passwordPlain) {
        this.userName = userName;
        this.passwordEncrypted = BCrypt.hashpw(passwordPlain, BCrypt.gensalt());
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPasswordEncrypted(String passwordEncrypted) {
        this.passwordEncrypted = passwordEncrypted;
    }

    public String getUserName() {
        return userName;
    }

    public String getPasswordEncrypted() {
        return passwordEncrypted;
    }

    public UserCredentials() { }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserCredentials)) return false;

        UserCredentials that = (UserCredentials) o;

        if (!getUserName().equals(that.getUserName())) return false;
        return getPasswordEncrypted().equals(that.getPasswordEncrypted());
    }

    @Override
    public int hashCode() {
        int result = getUserName().hashCode();
        result = 31 * result + getPasswordEncrypted().hashCode();
        return result;
    }
}
