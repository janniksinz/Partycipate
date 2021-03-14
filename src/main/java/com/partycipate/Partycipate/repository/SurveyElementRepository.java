package com.partycipate.Partycipate.repository;


import com.partycipate.Partycipate.model.Survey;
import com.partycipate.Partycipate.model.SurveyElement;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SurveyElementRepository extends CrudRepository<Survey, Integer> {

    @Query(value = "SELECT survey_id FROM survey_element WHERE :element_id = id LIMIT 1",nativeQuery = true)
    public Set<SurveyElement> getSurveyElementByElementId(@Param("element_id") int id);


}

