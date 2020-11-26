package com.cooksy.repository;

import com.cooksy.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query("SELECT u FROM User u order by u.firstName")
    List<User> getSortedUserByFirstName();

    @Query("SELECT u FROM User u order by u.lastName")
    List<User> getSortedUserByLastName();

    @Query("SELECT u FROM User u order by u.userType")
    List<User> getSortedUserByUserType();
}
