package de.patricklass.scheduler.service;

import de.patricklass.scheduler.model.User;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialException;

/**
 * Interface defines LoginServices
 * @author Patrick Laß
 */
public interface LoginService {

    User login(String username, String password) throws CredentialException;

    /**
     * Should return the username of the currently authenticated User
     * @return username of currently authenticated User
     */
    String getUserName();

    /**
     * Should return the currently authenticated User
     * @return currently authenticated User
     */
    User getAuthenticatedUser();
}
