package com.cooksy.repository;

import com.cooksy.model.User;
import com.cooksy.model.UserType;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJdbcTest
class UserRepositoryTest {
    UserType customer = new UserType(1L, "CUSTOMER");
    UserType admin = new UserType(2L, "ADMINISTRATOR");

    User user1 = new User(1L, "Adam", "Adamowski", "example@gmail.com", "example1234", "", customer);
    User user2 = new User(2L, "Artur", "Dobry", "example@gmail.com", "example1234", "", admin);
    User user3 = new User(3L, "Adam", "Sławny", "example@gmail.com", "example1234", "", customer);
    User user4 = new User(4L, "Bob", "Dylan", "example@gmail.com", "example1234", "", admin);
    User user5 = new User(5L, "Barbara", "Fiszek", "example@gmail.com", "example1234", "", admin);
    User user6 = new User(6L, "Alicja", "Kowniniak", "example@gmail.com", "example1234", "", customer);
    User user7 = new User(7L, "Bartłomiej", "Fiszek", "example@gmail.com", "example1234", "", customer);
    User user8 = new User(8L, "Anastazja", "Bakcyl", "example@gmail.com", "example1234", "", customer);
    User user9 = new User(9L, "Antoni", "Donat", "example@gmail.com", "example1234", "", admin);
    User user10 = new User(10L, "Beata", "Chorman", "example@gmail.com", "example1234", "", customer);

    @Autowired
    private UserRepository userRepository;

    @Test
    void getSortedUserByFirstName() {
        userRepository.getSortedUserByFirstName();

    }

    @Test
    void getSortedUserByLastName() {
    }

    @Test
    void getSortedUserByUserType() {
    }
}