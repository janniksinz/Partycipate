package com.partycipate.Partycipate.repository;


import com.partycipate.Partycipate.model.AuthToken;
import com.partycipate.Partycipate.model.Survey;
import com.partycipate.Partycipate.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthTokenRepository extends CrudRepository<AuthToken, Integer> {

    @Query(value= "Select * From user Where user_id = (Select user_id From auth_token Where token = :authToken)", nativeQuery = true )
    User getUserByAuthToken(AuthToken authToken);
}

