package com.partycipate.Partycipate.repository;


import com.partycipate.Partycipate.model.Survey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerPossibilityRepository extends CrudRepository<Survey, Integer> {

}
