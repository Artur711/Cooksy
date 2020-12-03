package com.cooksy.repository;

import com.cooksy.model.User;
import com.cooksy.model.UserType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertAll;

@RunWith(SpringRunner.class)
@DataJpaTest
@Sql("data-test.sql")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    private List<User> usersSorted;
    private final UserType customer = new UserType(2L, "CUSTOMER");
    private final UserType admin = new UserType(1L, "ADMINISTRATOR");

    @Autowired
    private UserRepository userRepository;

    @Test
    public void should_get_sorted_user_by_first_name() {
        // given:
        usersSorted = userRepository.getSortedUserByFirstName();

        // when, then:
        assertAll(() -> assertEquals("Adam", usersSorted.get(0).getFirstName()),
                () -> assertEquals("Adam", usersSorted.get(1).getFirstName()),
                () -> assertEquals("Alicja", usersSorted.get(2).getFirstName()),
                () -> assertEquals("Anastazja", usersSorted.get(3).getFirstName()),
                () -> assertEquals("Antoni", usersSorted.get(4).getFirstName()),
                () -> assertEquals("Artur", usersSorted.get(5).getFirstName()),
                () -> assertEquals("Barbara", usersSorted.get(6).getFirstName()),
                () -> assertEquals("Bartłomiej", usersSorted.get(7).getFirstName()),
                () -> assertEquals("Beata", usersSorted.get(8).getFirstName()),
                () -> assertEquals("Bob", usersSorted.get(9).getFirstName()),
                () -> assertEquals(10, usersSorted.size()));
    }

    @Test
    public void should_get_sorted_user_by_last_name() {
        // given:
        usersSorted = userRepository.getSortedUserByLastName();

        // when, then:
        assertAll(() -> assertEquals("Adamowski", usersSorted.get(0).getLastName()),
                () -> assertEquals("Bakcyl", usersSorted.get(1).getLastName()),
                () -> assertEquals("Chorman", usersSorted.get(2).getLastName()),
                () -> assertEquals("Dobry", usersSorted.get(3).getLastName()),
                () -> assertEquals("Donat", usersSorted.get(4).getLastName()),
                () -> assertEquals("Dylan", usersSorted.get(5).getLastName()),
                () -> assertEquals("Fiszek", usersSorted.get(6).getLastName()),
                () -> assertEquals("Fiszek", usersSorted.get(7).getLastName()),
                () -> assertEquals("Kowniniak", usersSorted.get(8).getLastName()),
                () -> assertEquals("Sławny", usersSorted.get(9).getLastName()),
                () -> assertEquals(10, usersSorted.size()));
    }

    @Test
    public void should_get_sorted_user_by_user_type() {
        // given:
       usersSorted = userRepository.getSortedUserByUserType();

        // when, then:
        assertAll(() -> assertEquals(admin, usersSorted.get(0).getUserType()),
                () -> assertEquals(admin, usersSorted.get(1).getUserType()),
                () -> assertEquals(admin, usersSorted.get(2).getUserType()),
                () -> assertEquals(admin, usersSorted.get(3).getUserType()),
                () -> assertEquals(customer, usersSorted.get(4).getUserType()),
                () -> assertEquals(customer, usersSorted.get(5).getUserType()),
                () -> assertEquals(customer, usersSorted.get(6).getUserType()),
                () -> assertEquals(customer, usersSorted.get(7).getUserType()),
                () -> assertEquals(customer, usersSorted.get(8).getUserType()),
                () -> assertEquals(customer, usersSorted.get(9).getUserType()),
                () -> assertEquals(10, usersSorted.size()));
    }

    @Test
    public void should_find_by_user_type() {
        // given:
        List<User> usersFoundByType = userRepository.findByUserType(customer);

        // when, then:
        assertAll(() -> assertEquals(6, usersFoundByType.size()),
                () -> assertEquals(customer, usersFoundByType.get(0).getUserType()),
                () -> assertEquals(customer, usersFoundByType.get(1).getUserType()),
                () -> assertEquals(customer, usersFoundByType.get(2).getUserType()),
                () -> assertEquals(customer, usersFoundByType.get(3).getUserType()),
                () -> assertEquals(customer, usersFoundByType.get(4).getUserType()),
                () -> assertEquals(customer, usersFoundByType.get(5).getUserType()));
    }
}