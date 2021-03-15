package com.partycipate.Partycipate.repository;


import com.partycipate.Partycipate.model.Survey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerPossibilityRepository extends CrudRepository<Survey, Integer> {

    @Query(value = "SELECT COUNT answer_possibility_id FROM answer_possibility WHERE survey_element_id = :element_id", nativeQuery = true)
    public int count_answer_possibilities(@Param("element_id")int element_id);
}

