package ru.pravvich.repository;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.pravvich.model.User;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserRepositoryTest {

    private final ClassPathXmlApplicationContext context =
            new ClassPathXmlApplicationContext("spring-data-context.xml");

    private UserRepository repository = context.getBean(UserRepository.class);

    /**
     * Find User by username & password.
     *
     * WARNING!!!
     *
     * Database should contain:
     *
     * INSERT INTO users (id, username, password, enabled) VALUES (DEFAULT ,'peter', 'peter', TRUE);
     * INSERT INTO user_roles (username, role) VALUES ('peter', 'ROLE_USER');
     */
    @Test
    public void whenUserExistInDBThenGetUser() {

        final User result = repository
                .findDistinctFirstByUsernameAndPassword("peter", "peter");

        assertNotNull(result);
        assertThat(result.getUsername(), is("peter"));
        assertThat(result.getPassword(), is("peter"));
    }

    @Test
    public void whenGetUserWhichNotExistThenGetNull() {

        final User result = repository
                .findDistinctFirstByUsernameAndPassword("~?>", "*=/*");

        assertNull(result);
    }
}