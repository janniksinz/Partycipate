package com.partycipate.Partycipate.repository;


import com.partycipate.Partycipate.model.Survey;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveyRepository extends CrudRepository<Survey, Integer> {

    //@Query(value = "select * from survey Where id = 1", nativeQuery = true)
    //Survey findSurveyById(int id);


    //@Query(value ="Insert Into survey VALUES (?,?,?,?,?,?)", nativeQuery = true)
    //String createSurvey(int id, String cookie, String creation_date, String title, int user_id);

    //List<Survey> findSurveyByUserId (Long user_id);
}

