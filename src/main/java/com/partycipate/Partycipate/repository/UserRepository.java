package com.partycipate.Partycipate.repository;


import com.partycipate.Partycipate.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    //ToDo check if findByUsername or email works or if it needs to be byId?
    Optional<User> findByUsername(String name);
    Boolean existsByUsername(String name);
    Boolean existsByEmail(String email);
    User findById(int id);
}

