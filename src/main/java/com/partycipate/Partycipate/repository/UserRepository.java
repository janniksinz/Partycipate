package com.partycipate.Partycipate.repository;


import com.partycipate.Partycipate.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    @Query(value = "SELECT password FROM user Where email =:email",nativeQuery = true)
    String getPassword(String email);

    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query(value = "UPDATE user SET password = :password WHERE user.email = :email",nativeQuery = true)
    User changePassword(@Param("password") String password,@Param("email") String email);

    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query(value = "UPDATE user SET email = :email WHERE user.id = :user_id",nativeQuery = true)
    User changeEmail(@Param("user_id") int user_id,@Param("email") String newEmail);
}

