package ru.pravvich.repository;

import org.springframework.data.repository.CrudRepository;
import ru.pravvich.model.User;

public interface Repository extends CrudRepository<User, Integer> {
}
