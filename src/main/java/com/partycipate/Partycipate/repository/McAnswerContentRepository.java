package com.partycipate.Partycipate.repository;

import com.partycipate.Partycipate.model.Answer;
import com.partycipate.Partycipate.model.MCAnswerContent;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface McAnswerContentRepository extends CrudRepository<MCAnswerContent, Integer> {
    /**
     * <authors>
     *      <author> Jarg Heyll - wi19225@lehre.dhbw-stuttgart.de</author>
     *      <author> Andreas Pitsch - wi19165@lehre.dhbw-stuttgart.de</author>
     * </authors>
     * */
    @Query(value = "Select * from mcanswer_content where answer_id = :answer_id", nativeQuery = true)
    Iterable<MCAnswerContent> findAllByAnswer_Id(@Param("answer_id") int answer_id);

    @Query(value = "SELECT `mcanswer_content`.id from mcanswer_content Inner JOIN answer a on mcanswer_content.answer_id = a.id Inner JOIN answer_possibility ap on mcanswer_content.answer_possibility_id = ap.id Inner Join survey_element se on a.survey_element_id = se.id Inner JOIN survey s on se.survey_id = s.id Where survey_id = :survey_id", nativeQuery = true)
    public Set<Integer> findAllBySurveyId(@Param("survey_id") int id);
}
