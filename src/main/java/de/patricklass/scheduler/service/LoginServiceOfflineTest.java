package de.patricklass.scheduler.service;

import de.patricklass.scheduler.model.User;
import org.springframework.stereotype.Component;

import javax.security.auth.login.CredentialException;
import java.util.Objects;

@Component
public class LoginServiceOfflineTest implements LoginService {

    private User hansUser;

    /**
     * Username of currently authenticated User
     */
    private String userName;

    /**
     * Password of currently authenticated User
     */
    private String password;

    /**
     * Currently authenticated User
     */
    private User user;

    public LoginServiceOfflineTest(){
        this.hansUser = new User("hans");
        hansUser.setAdmin(true);
    }

    @Override
    public User login(String username, String password) throws CredentialException {
        if ("hans".equals(username) && Objects.nonNull(password)){
            user = hansUser;
            return hansUser;
        } else {
            throw new CredentialException();
        }
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public User getAuthenticatedUser() {
        return user;
    }

}
