package ru.pravvich.repository;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.pravvich.model.Role;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleRepositoryTest {

    private final ClassPathXmlApplicationContext context =
            new ClassPathXmlApplicationContext("spring-data-context.xml");

    private RoleRepository repository = context.getBean(RoleRepository.class);

    /**
     * WARNING WORK ONLY WITH :
     * INSERT INTO user_roles (username, role) VALUES ('jack', 'ROLE_USER');
     * INSERT INTO user_roles (username, role) VALUES ('jack', 'ROLE_ADMIN');
     */
    @Test
    public void whenUserHaveTwoRolesThenGetBothRoles() {
        final List<Role> result = repository.findByUsername("jack");

        assertThat(result.size(), is(2));
        assertTrue(result.get(0).getRole().equals("ROLE_USER"));
        assertTrue(result.get(1).getRole().equals("ROLE_ADMIN"));
    }
}