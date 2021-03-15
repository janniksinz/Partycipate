package com.partycipate.Partycipate.repository;


import com.partycipate.Partycipate.model.Survey;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;
@Repository
public interface SurveyRepository extends CrudRepository<Survey, Integer> {
    Survey findById(int id);
    //Survey findAllByUser_id(int id);

    @Query(value = "SELECT * FROM survey WHERE :user_id = user_id",nativeQuery = true)
    public Set<Survey> getSurveyByUser(@Param("user_id") int id);

    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query(value = "DELETE FROM `survey` WHERE `id` = :id", nativeQuery = true)
    void deleteSurveyById(int id);


}

