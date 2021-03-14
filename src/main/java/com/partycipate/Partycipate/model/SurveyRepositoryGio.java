package com.partycipate.Partycipate.model;

import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveyRepositoryGio extends CrudRepository<Survey, Long> {

    //@Query(value = "select * from survey Where id = 1", nativeQuery = true)
    //Survey findSurveyById(int id);


    //@Query(value ="Insert Into survey VALUES (?,?,?,?,?,?)", nativeQuery = true)
    //String createSurvey(int id, String cookie, String creation_date, String title, int user_id);

    //List<Survey> findSurveyByUserId (Long user_id);
}

