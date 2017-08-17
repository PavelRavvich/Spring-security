package ru.pravvich.repository;

import org.springframework.data.repository.CrudRepository;
import ru.pravvich.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findDistinctFirstByUsernameAndPassword(String username, String password);
}

