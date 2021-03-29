package com.partycipate.Partycipate.repository;


import com.partycipate.Partycipate.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    //ToDo check if findByUsername or email works or if it needs to be byId?
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}

