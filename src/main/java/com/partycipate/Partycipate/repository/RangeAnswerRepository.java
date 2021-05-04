package com.partycipate.Partycipate.repository;


import com.partycipate.Partycipate.model.Answer;
import com.partycipate.Partycipate.model.RangeAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

@Repository
public interface RangeAnswerRepository extends JpaRepository<RangeAnswer, Integer> {
    //getAnswerBySurveyId -> ElementId
    @Query(value = "SELECT * FROM answer WHERE survey_element_id = :element_id", nativeQuery = true)
    public Set<Answer> getAnswersByElementId(@Param("element_id")int element_id);

    @Query(value= "SELECT Count(participant_id) from answer Where survey_element_id=:survey_element_id GROUP by survey_element_id", nativeQuery = true)
    public Optional<Integer> getCountParticipants(@Param("survey_element_id") int survey_element_id);

    //get all survey_element_ids where there are answers
    @Query(value = "SELECT DISTINCT survey_element_id FROM answer", nativeQuery = true)
    Set<Integer> getDistinctElementIds();

//    getAnswerCount for
    @Query(value = "SELECT * FROM answer WHERE survey_element_id = :element_id AND date BETWEEN :start AND :end", nativeQuery = true)
    Set<Answer> getAnswersByDateAndElement(@Param("start") Date start, @Param("end") Date end, @Param("element_id") int element_id);

}

