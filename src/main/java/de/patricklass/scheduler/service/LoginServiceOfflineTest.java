package de.patricklass.scheduler.service;

import de.patricklass.scheduler.model.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialException;
import java.util.Objects;

/**
 * Implementation of {@link LoginService} for Offline / Testing use.
 * @author Patrick Laß
 */
@Service("loginService-offline")
public class LoginServiceOfflineTest implements LoginService {

    private User hansUser;

    /**
     * Username of currently authenticated User
     */
    private String userName;

    /**
     * Currently authenticated User
     */
    private User user;

    public LoginServiceOfflineTest(){
        this.hansUser = new User("hans");
        hansUser.setAdmin(true);
    }

    /**
     * Returns {@link #hansUser} if the submitted {@code userName} equals "hans"
     * and the submitted {@code password} isn't null. Otherwise throws {@code CredentialException}
     * The returned hansUser is an admin
     *
     * @param   userName    should be hans for the function to return
     * @param   password    should not be null for the function to return
     * @return  hansUser, an admin
     * @throws  CredentialException if {@code userName} isnt "hans" or the {@code password} is null,
     *                              {@code CredentialException} is thrown
     */
    @Override
    public User login(String userName, String password) throws CredentialException {
        if ("hans".equals(userName) && Objects.nonNull(password)){
            this.userName = userName;
            this.user = hansUser;
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
    public User getAuthenticatedUser() {
        return user;
    }

}
