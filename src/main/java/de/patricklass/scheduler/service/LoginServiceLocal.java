package de.patricklass.scheduler.service;

import de.patricklass.scheduler.model.User;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialException;

@Service("loginService-local")
public class LoginServiceLocal implements LoginService {

    private User authenticatedUser;

    @Override
    public User login(String username, String password) throws CredentialException {
        return null;
    }

    @Override
    public String getUserName() {
        return this.authenticatedUser.getUserName();
    }

    @Override
    public User getAuthenticatedUser() {
        return this.authenticatedUser;
    }
}
