package de.patricklass.scheduler.repository;

import de.patricklass.scheduler.model.Group;
import de.patricklass.scheduler.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
