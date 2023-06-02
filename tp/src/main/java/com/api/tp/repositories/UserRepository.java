package com.api.tp.repositories;

import com.api.tp.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Override
    List<User> findAll();

    User findByEmail(String email);

}
