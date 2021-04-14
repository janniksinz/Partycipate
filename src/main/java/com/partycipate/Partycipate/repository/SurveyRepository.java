package com.partycipate.Partycipate.repository;


import com.partycipate.Partycipate.model.Survey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;
@Repository
public interface SurveyRepository extends CrudRepository<Survey, Integer> {
    Survey findById(int id);

    @Query(value = "SELECT * FROM survey WHERE :user_id = user_id",nativeQuery = true)
    public Set<Survey> getSurveyByUser(@Param("user_id") int id);

    @Query(value = "SELECT id FROM survey Order By id DESC LIMIT 1",nativeQuery = true)
    public int getLastId();
}

