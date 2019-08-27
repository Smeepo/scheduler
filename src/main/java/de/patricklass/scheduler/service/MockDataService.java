package de.patricklass.scheduler.service;

import de.patricklass.scheduler.model.Group;
import de.patricklass.scheduler.model.Invitation;
import de.patricklass.scheduler.model.User;
import de.patricklass.scheduler.model.UserCredentials;
import de.patricklass.scheduler.repository.GroupRepository;
import de.patricklass.scheduler.repository.InvitationRepository;
import de.patricklass.scheduler.repository.UserCredentialsRepository;
import de.patricklass.scheduler.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class MockDataService {

    private final GroupRepository groupRepository;
    private final InvitationRepository invitationRepository;
    private final UserCredentialsRepository userCredentialsRepository;
    private final UserRepository userRepository;

    public MockDataService(GroupRepository groupRepository,
                           InvitationRepository invitationRepository,
                           UserCredentialsRepository userCredentialsRepository,
                           UserRepository userRepository) {

        this.groupRepository = groupRepository;
        this.invitationRepository = invitationRepository;
        this.userCredentialsRepository = userCredentialsRepository;
        this.userRepository = userRepository;
    }

    public void initRepositoryData() {
        List<User> users = Arrays.asList(
                new User("peter"),
                new User("hans"),
                new User("lisa"),
                new User("winfried"),
                new User("joachim"),
                new User("jennifer"),
                new User("melina"),
                new User("bernd")
                );
        userRepository.saveAll(users);

        List<UserCredentials> credentials = Arrays.asList(
            new UserCredentials("peter", "passwort"),
            new UserCredentials("hans", "passwort"),
            new UserCredentials("lisa", "passwort"),
            new UserCredentials("winfried", "passwort"),
            new UserCredentials("joachim", "passwort"),
            new UserCredentials("jennifer", "passwort"),
            new UserCredentials("melina", "passwort"),
            new UserCredentials("bernd", "passwort")
        );
        userCredentialsRepository.saveAll(credentials);

        Group group1 = new Group("coole Kidz");
        group1.getUsers().addAll(users.subList(0,4));
        Invitation invite1 = new Invitation(LocalDate.now().plusDays(10), group1, "foo", "bar");
        group1.getInvitations().add(invite1);

        // New Invitation for testing purposes
        Invitation invite3 = new Invitation(LocalDate.now().plusDays(10).plusYears(1), group1);
        group1.getInvitations().add(invite3);

        Group group2 = new Group("Die heftigen Heftigen");
        group2.getUsers().addAll(users.subList(4,users.size()));
        Invitation invite2 = new Invitation(LocalDate.now().plusDays(10).plusMonths(2), group2, "boo", "far");
        group2.getUsers().addAll(users.subList(1,2));
        Invitation invite2 = new Invitation(LocalDate.now().plusDays(10).plusMonths(2), group2);
        group2.getInvitations().add(invite2);

        invitationRepository.save(invite1);
        invitationRepository.save(invite2);
        invitationRepository.save(invite3);

        groupRepository.save(group1);
        groupRepository.save(group2);
    }

}
