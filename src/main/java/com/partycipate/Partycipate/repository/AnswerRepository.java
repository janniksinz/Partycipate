package com.partycipate.Partycipate.repository;


import com.partycipate.Partycipate.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    /**
     * <authors>
     *      <author> Andreas Pitsch - wi19165@lehre.dhbw-stuttgart.de</author>
     *      <author> Jarg Heyll - wi19225@lehre.dhbw-stuttgart.de </author></authors>
     * </authors>
     * */
    //getAnswerBySurveyId -> ElementId
    @Query(value = "SELECT * FROM answer WHERE survey_element_id = :element_id", nativeQuery = true)
    public Set<Answer> getAnswersByElementId(@Param("element_id")int element_id);

    /**
     * <authors>
     *      <author> Ines Maurer - inesmaurer@outlook.de</author>
     * </authors>
     * */
    @Query(value= "SELECT Count(participant_id) from answer Where survey_element_id=:survey_element_id GROUP by survey_element_id", nativeQuery = true)
    public Optional<Integer> getCountParticipants(@Param("survey_element_id") int survey_element_id);

    /**
     * <authors>
     *      <author> Ines Maurer - inesmaurer@outlook.de</author>
     * </authors>
     * */
    //get all survey_element_ids where there are answers
    @Query(value = "SELECT DISTINCT survey_element_id FROM answer", nativeQuery = true)
    Set<Integer> getDistinctElementIds();

    /**
     * <authors>
     *      <author> Ines Maurer - inesmaurer@outlook.de</author>
     *      <author> Andreas Pitsch - wi19165@lehre.dhbw-stuttgart.de</author>
     *      <author> Jarg Heyll - wi19225@lehre.dhbw-stuttgart.de </author></authors>
     * </authors>
     * */
//    getAnswerCount for
    @Query(value = "SELECT * FROM answer WHERE survey_element_id = :element_id AND date BETWEEN :start AND :end", nativeQuery = true)
    Set<Answer> getAnswersByDateAndElement(@Param("start") Date start, @Param("end") Date end, @Param("element_id") int element_id);

    @Query(value = "SELECT `answer`.id from answer inner join survey_element se on answer.survey_element_id = se.id inner join survey s on se.survey_id = s.id where survey_id = :survey_id", nativeQuery = true)
    public Set<Integer> findAllBySurveyId(@Param("survey_id") int id);
}

