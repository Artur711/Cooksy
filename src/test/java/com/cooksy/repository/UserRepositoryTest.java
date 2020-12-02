package com.cooksy.repository;

import com.cooksy.model.User;
import com.cooksy.model.UserType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertAll;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
    private UserType customer = new UserType(1L, "CUSTOMER");
    private UserType admin = new UserType(2L, "ADMINISTRATOR");

    private User user1 = new User(1L, "Adam", "Adamowski", "", "", "", customer);
    private User user2 = new User(2L, "Artur", "Dobry", "", "", "", admin);
    private User user3 = new User(3L, "Adam", "Sławny", "", "", "", customer);
    private User user4 = new User(4L, "Bob", "Dylan", "", "", "", admin);
    private User user5 = new User(5L, "Barbara", "Fiszek", "", "", "", admin);
    private User user6 = new User(6L, "Alicja", "Kowniniak", "", "", "", customer);
    private User user7 = new User(7L, "Bartłomiej", "Fiszek", "", "", "", customer);
    private User user8 = new User(8L, "Anastazja", "Bakcyl", "", "", "", customer);
    private User user9 = new User(9L, "Antoni", "Donat", "", "", "", admin);
    private User user10 = new User(10L, "Beata", "Chorman", "", "", "", customer);

    @Autowired
    private UserRepository userRepository;

    @Test
    public void should_get_sorted_user_by_first_name() {
        // given:
        List<User> usersSortedByFirstName = new ArrayList<>(Arrays.asList(user3, user1, user6, user8, user9, user2, user5, user7, user10, user4));

        // when:
        List<User> usersSorted = userRepository.getSortedUserByFirstName();

        // then:
        assertAll(() -> assertEquals(usersSorted.get(0).getFirstName(), usersSortedByFirstName.get(0).getFirstName()),
                () -> assertEquals(usersSorted.get(1).getFirstName(), usersSortedByFirstName.get(1).getFirstName()),
                () -> assertEquals(usersSorted.get(2).getFirstName(), usersSortedByFirstName.get(2).getFirstName()),
                () -> assertEquals(usersSorted.get(3).getFirstName(), usersSortedByFirstName.get(3).getFirstName()),
                () -> assertEquals(usersSorted.get(4).getFirstName(), usersSortedByFirstName.get(4).getFirstName()),
                () -> assertEquals(usersSorted.get(5).getFirstName(), usersSortedByFirstName.get(5).getFirstName()),
                () -> assertEquals(usersSorted.get(6).getFirstName(), usersSortedByFirstName.get(6).getFirstName()),
                () -> assertEquals(usersSorted.get(7).getFirstName(), usersSortedByFirstName.get(7).getFirstName()),
                () -> assertEquals(usersSorted.get(8).getFirstName(), usersSortedByFirstName.get(8).getFirstName()),
                () -> assertEquals(usersSorted.get(9).getFirstName(), usersSortedByFirstName.get(9).getFirstName()));
    }

    @Test
    public void should_get_sorted_user_by_last_name() {
        // given:
        List<User> usersSortedByLastName = new ArrayList<>(Arrays.asList(user1, user8, user10, user2, user9, user4, user7, user5, user6, user3));

        // when:
        List<User> usersSorted = userRepository.getSortedUserByLastName();

        // then:
        assertAll(() -> assertEquals(usersSorted.get(0).getLastName(), usersSortedByLastName.get(0).getLastName()),
                () -> assertEquals(usersSorted.get(1).getLastName(), usersSortedByLastName.get(1).getLastName()),
                () -> assertEquals(usersSorted.get(2).getLastName(), usersSortedByLastName.get(2).getLastName()),
                () -> assertEquals(usersSorted.get(3).getLastName(), usersSortedByLastName.get(3).getLastName()),
                () -> assertEquals(usersSorted.get(4).getLastName(), usersSortedByLastName.get(4).getLastName()),
                () -> assertEquals(usersSorted.get(5).getLastName(), usersSortedByLastName.get(5).getLastName()),
                () -> assertEquals(usersSorted.get(6).getLastName(), usersSortedByLastName.get(6).getLastName()),
                () -> assertEquals(usersSorted.get(7).getLastName(), usersSortedByLastName.get(7).getLastName()),
                () -> assertEquals(usersSorted.get(8).getLastName(), usersSortedByLastName.get(8).getLastName()),
                () -> assertEquals(usersSorted.get(9).getLastName(), usersSortedByLastName.get(9).getLastName()));
    }

    @Test
    public void should_get_sorted_user_by_user_type() {
        // given:
        List<User> usersSortedByUserType = new ArrayList<>(Arrays.asList(user2, user9, user5, user4, user6, user7, user8, user1, user10, user3));

        // when:
        List<User> usersSorted = userRepository.getSortedUserByUserType();

        // then:
        assertAll(() -> assertEquals(usersSorted.get(0).getUserType(), usersSortedByUserType.get(0).getUserType()),
                () -> assertEquals(usersSorted.get(1).getUserType(), usersSortedByUserType.get(1).getUserType()),
                () -> assertEquals(usersSorted.get(2).getUserType(), usersSortedByUserType.get(2).getUserType()),
                () -> assertEquals(usersSorted.get(3).getUserType(), usersSortedByUserType.get(3).getUserType()),
                () -> assertEquals(usersSorted.get(4).getUserType(), usersSortedByUserType.get(4).getUserType()),
                () -> assertEquals(usersSorted.get(5).getUserType(), usersSortedByUserType.get(5).getUserType()),
                () -> assertEquals(usersSorted.get(6).getUserType(), usersSortedByUserType.get(6).getUserType()),
                () -> assertEquals(usersSorted.get(7).getUserType(), usersSortedByUserType.get(7).getUserType()),
                () -> assertEquals(usersSorted.get(8).getUserType(), usersSortedByUserType.get(8).getUserType()),
                () -> assertEquals(usersSorted.get(9).getUserType(), usersSortedByUserType.get(9).getUserType()),
                () -> assertEquals(10, usersSortedByUserType.size()));
    }

    @Test
    public void should_find_by_user_type() {
        // given:
        List<User> usersByTypeFirst = new ArrayList<>(Arrays.asList(user1, user3, user6, user7, user8, user10));

        //when:
        List<User> usersFoundByType = userRepository.findByUserType(customer);

        // then:
        assertAll(() -> assertEquals(6, usersFoundByType.size()),
                () -> assertEquals(usersByTypeFirst.get(0).getUserType().getUserTypeId(),
                        usersFoundByType.get(0).getUserType().getUserTypeId()),
                () -> assertEquals(usersByTypeFirst.get(1).getUserType().getUserTypeId(),
                        usersFoundByType.get(1).getUserType().getUserTypeId()),
                () -> assertEquals(usersByTypeFirst.get(2).getUserType().getUserTypeId(),
                        usersFoundByType.get(2).getUserType().getUserTypeId()),
                () -> assertEquals(usersByTypeFirst.get(3).getUserType().getUserTypeId(),
                        usersFoundByType.get(3).getUserType().getUserTypeId()),
                () -> assertEquals(usersByTypeFirst.get(4).getUserType().getUserTypeId(),
                        usersFoundByType.get(4).getUserType().getUserTypeId()),
                () -> assertEquals(usersByTypeFirst.get(5).getUserType().getUserTypeId(),
                        usersFoundByType.get(5).getUserType().getUserTypeId()));
    }

    @Before
    public void prepareDB () {
        List<User> users = new ArrayList<>(Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10));
        userRepository.saveAll(users);
    }
}