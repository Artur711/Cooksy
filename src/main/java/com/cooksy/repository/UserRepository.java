package com.cooksy.repository;

import com.cooksy.model.User;
import com.cooksy.model.UserType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query("SELECT u FROM User u order by u.firstName")
    List<User> getSortedUserByFirstName();

    @Query("SELECT u FROM User u order by u.lastName")
    List<User> getSortedUserByLastName();

    @Query("SELECT u FROM User u order by u.userType.userTypeId DESC")
    List<User> getSortedUserByUserType();

    @Query("SELECT u FROM User u where u.userType = :user_type")
    List<User> findByUserType(@Param("user_type") UserType userType);
}
