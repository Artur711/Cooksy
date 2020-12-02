package com.cooksy.repository;

import com.cooksy.model.Favorite;
import com.cooksy.model.Recipe;
import com.cooksy.model.User;
import com.cooksy.model.UserType;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class FavoriteRepositoryTest {
    private UserType customer = new UserType(1L, "CUSTOMER");
    private User user1 = new User(1L, "Adam", "Adamowski", "", "", "", customer);
    private User user2 = new User(3L, "Adam", "Sławny", "", "", "", customer);
    private Recipe recipe1 = new Recipe(1L, 1L, "bla bla", "dasdasdas", "Atos");
    private Recipe recipe2 = new Recipe(2L, 2L,"bla", "dasdasdas", "Atos");
    private Favorite favorite1 = new Favorite(1L, user1, recipe1);
    private Favorite favorite2 = new Favorite(2L, user1, recipe2);
    private Favorite favorite3 = new Favorite(3L, user2, recipe1);
    private Favorite favorite4 = new Favorite(4L, user2, recipe2);

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Test
    void should_find_by_user() {
        // given:
        User user = new User();
        user.setUserId(1L);
        List<Favorite> userFavorites = new ArrayList<>(Arrays.asList(favorite1, favorite2));

        // when:
        List<Favorite> foundFavorites = favoriteRepository.findByUser(user);

        // then:
        assertEquals(userFavorites.get(0).getUser().getUserId(), foundFavorites.get(0).getUser().getUserId());
    }

    @Before
    public void prepareDB () {
        List<Favorite> favorites = new ArrayList<>(Arrays.asList(favorite1, favorite2, favorite3, favorite4));
        favoriteRepository.saveAll(favorites);
    }
}