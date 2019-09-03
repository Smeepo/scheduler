package de.patricklass.scheduler.repository;

import de.patricklass.scheduler.model.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Handles database requests (similar to a DAO) for user credentials
 * @author Patrick Laß
 */
@Repository
public interface UserCredentialsRepository extends JpaRepository<UserCredentials, Long> {
    UserCredentials findByUserName(String userName);
    UserCredentials removeByUserName(String userName);
}
