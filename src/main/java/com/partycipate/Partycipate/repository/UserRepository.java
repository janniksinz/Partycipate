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

    /**
     * <authors>
     *      <author> Ines Maurer - inesmaurer@outlook.de/author>
     *      <author> Andreas Pitsch - wi19165@lehre.dhbw-stuttgart.de</author>
     * </authors>
     * */
    @Query(value = "DELETE FROM user_roles WHERE user_id=:user_id", nativeQuery = true)
    int deleteUserInRoles(@Param("user_id") int userId);

    /**
     * <authors>
     *      <author> Andreas Pitsch - wi19165@lehre.dhbw-stuttgart.de</author>
     * </authors>
     * */

    @Query(value = "SELECT password FROM user Where email =:email",nativeQuery = true)
    String getPassword(String email);

    /**
     * <authors>
     *      <author> Giovanni Carlucci - giovannicarlucci9@yahoo.de</author>
     * </authors>
     * */
    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query(value = "UPDATE user SET password = :password WHERE user.email = :email",nativeQuery = true)
    int changePassword(@Param("password") String password,@Param("email") String email);

    /**
     * <authors>
     *      <author> Ines Maurer - inesmaurer@outlook.de</author>
     * </authors>
     * */
    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query(value = "UPDATE user SET email = :email, name = :name, username = :email WHERE user.id = :user_id",nativeQuery = true)
    int changeUser(@Param("user_id") int user_id, @Param("email") String newEmail, @Param("name") String name);
}

