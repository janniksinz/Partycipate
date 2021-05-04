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
    Optional<User> findByUsername(String name);
    Boolean existsByUsername(String name);
    Boolean existsByEmail(String email);
    User findById(int id);

    @Query(value = "DELETE FROM user_roles WHERE user_id=:user_id", nativeQuery = true)
    int deleteUserInRoles(@Param("user_id") int userId);

    @Query(value = "SELECT password FROM user Where email =:email",nativeQuery = true)
    String getPassword(String email);

    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query(value = "UPDATE user SET password = :password WHERE user.email = :email",nativeQuery = true)
    int changePassword(@Param("password") String password,@Param("email") String email);

    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query(value = "UPDATE user SET email = :email, name = :name, username = :email WHERE user.id = :user_id",nativeQuery = true)
    int changeUser(@Param("user_id") int user_id, @Param("email") String newEmail, @Param("name") String name);
}

