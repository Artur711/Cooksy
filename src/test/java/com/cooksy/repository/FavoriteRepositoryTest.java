package com.cooksy.repository;

import com.cooksy.model.Favorite;
import com.cooksy.model.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@Sql("data-test.sql")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class FavoriteRepositoryTest {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Test
    void should_find_by_user() {
        // given:
        User user = new User();
        user.setUserId(1L);

        // when:
        List<Favorite> foundFavorites = favoriteRepository.findByUser(user);

        // then:
        assertAll(() -> assertEquals(1, foundFavorites.get(0).getFavoriteId()),
                () -> assertEquals(2, foundFavorites.get(1).getFavoriteId()),
                () -> assertEquals(1, foundFavorites.get(0).getUser().getUserId()),
                () -> assertEquals(1, foundFavorites.get(1).getUser().getUserId()),
                () -> assertEquals(2, foundFavorites.size()));
    }
}
