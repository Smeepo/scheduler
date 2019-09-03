package de.patricklass.scheduler.repository;

import de.patricklass.scheduler.model.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

;


/**
 * Handles database requests (similar to a DAO) for invitations
 * @author Patrick Laß
 */
@Repository
public interface InvitationRepository extends JpaRepository<Invitation, Long> {
}
