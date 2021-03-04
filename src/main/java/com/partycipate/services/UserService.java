package com.partycipate.services;

import com.partycipate.models.Survey;
import com.partycipate.models.User;

public class UserService {

    public static User createUser(String name, String email, String password){
        User user = new User.Builder().username(name).email(email).password(password).build();
        //insert the data in the db with methods from the db_service
        return user;

    }
    public static User getUser(int id){
        //get user from db with id
        //obviously id and name need to be inserted from db.
        // data needs to be fetched with the db_service
        // like Survey.Builder().id(data.id).name(data.name).build();
        User user = new User.Builder().id(id).username("user").email("mail@DB").password("password").build();
        return user;
    }
}
