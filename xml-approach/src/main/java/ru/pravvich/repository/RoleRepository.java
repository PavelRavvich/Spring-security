package ru.pravvich.repository;

import org.springframework.data.repository.CrudRepository;
import ru.pravvich.model.Role;

import java.util.List;

/**
 * Author : Pavel Ravvich.
 * Created : 16.08.17.
 */
public interface RoleRepository extends CrudRepository<Role, Integer> {
    List<Role> findByUsername(String username);
}
