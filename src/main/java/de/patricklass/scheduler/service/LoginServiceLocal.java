package de.patricklass.scheduler.service;

import de.patricklass.scheduler.model.User;
import de.patricklass.scheduler.model.UserCredentials;
import de.patricklass.scheduler.repository.UserCredentialsRepository;
import de.patricklass.scheduler.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialException;

@Service("loginService-local")
public class LoginServiceLocal implements LoginService {

    private User authenticatedUser;

    private final UserCredentialsRepository userCredentialsRepository;
    private UserRepository userRepository;

    public LoginServiceLocal(UserCredentialsRepository userCredentialsRepository, UserRepository userRepository) {
        this.userCredentialsRepository = userCredentialsRepository;
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) throws CredentialException {
        UserCredentials credentials = userCredentialsRepository.findByUserName(username);
        if (BCrypt.checkpw(password, credentials.getPasswordEncrypted())){
            User user = userRepository.findByUserName(username);
            this.authenticatedUser = user;
            return user;
        } else {
            throw new CredentialException("Username or Password incorrect");
        }
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
