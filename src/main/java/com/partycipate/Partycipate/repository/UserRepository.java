package com.partycipate.Partycipate.repository;


import com.partycipate.Partycipate.model.Survey;
import com.partycipate.Partycipate.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    //getUserByAuthToken -> TokenRepository

    //save (register)

    //findById (login)

    //delete

    //void addUser(String email, String password, String username);
    @Query(value = "select * from user where id =:id", nativeQuery = true)
    User getUserById(int id);


}

