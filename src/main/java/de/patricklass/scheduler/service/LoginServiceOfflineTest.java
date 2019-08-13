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

    /**
     * Returns {@link #hansUser} if the submitted username equals "hans" and the submitted password isn't null.
     * Otherwise throws {@code CredentialException}
     * The returned hansUser is an admin
     *
     * @param   username    should be hans for the function to return
     * @param   password    should not be null for the function to return
     * @return  hansUser, an admin
     * @throws  CredentialException if username isnt "hans" or the password is null,
     *                              {@code CredentialException} is thrown
     */
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
