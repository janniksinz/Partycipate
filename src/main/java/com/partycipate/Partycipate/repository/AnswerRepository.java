package com.partycipate.Partycipate.repository;


import com.partycipate.Partycipate.model.Answer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AnswerRepository extends CrudRepository<Answer, Integer> {
    //getAnswerBySurveyId -> ElementId
    @Query(value = "SELECT * FROM answer WHERE survey_element_id = :element_id", nativeQuery = true)
    public Set<Answer> getAnswersByElementId(@Param("element_id")int element_id);

    @Query(value= "SELECT Count(participant_id) from answer Where survey_element_id=:survey_element_id GROUP by survey_element_id", nativeQuery = true)
    public int getCountParticipants(@Param("survey_element_id") int survey_element_id);
}

