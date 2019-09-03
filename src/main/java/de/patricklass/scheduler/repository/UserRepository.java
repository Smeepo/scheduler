package de.patricklass.scheduler.repository;

import de.patricklass.scheduler.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Handles database requests (similar to a DAO) for users
 * @author Patrick Laß
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
}
