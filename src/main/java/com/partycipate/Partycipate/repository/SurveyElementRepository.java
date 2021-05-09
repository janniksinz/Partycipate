package com.partycipate.Partycipate.repository;


import com.partycipate.Partycipate.model.SurveyElement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Set;

@Repository
public interface SurveyElementRepository extends JpaRepository<SurveyElement, Integer> {
    /**
     * <authors>
     *      <author> Ines Maurer - inesmaurer@outlook.de</author>
     *      <author> Andreas Pitsch - wi19165@lehre.dhbw-stuttgart.de</author>
     *      <author> Jarg Heyll - wi19225@lehre.dhbw-stuttgart.de </author></authors>
     * </authors>
     * */
    @Query(value=" Select * from survey_element Where survey_id =:survey_id", nativeQuery = true)
    public Set<SurveyElement> findAllBySurveyId(@Param("survey_id") int survey_id);

    /**
     * <authors>
     *      <author> Jannik Sinz - jannik.sinz@ibm.com </author>
     *      <author> Giovanni Carlucci - giovannicarlucci9@yahoo.de</author>
     *      <author> Ines Maurer - inesmaurer@outlook.de</author>
     *      <author> Andreas Pitsch - wi19165@lehre.dhbw-stuttgart.de</author>
     *      <author> Jarg Heyll - wi19225@lehre.dhbw-stuttgart.de </author></authors>
     * </authors>
     * */
    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query (value = "INSERT INTO `survey_element` ( `id`, `may_skip`, `position`, `question`, `type`, `survey_id`) VALUES ( :id, :may_skip, :position,  :question,  :type,  :survey_id); ", nativeQuery = true)
    public void saveSurveyElement(@Param("id") int id, @Param("may_skip") Boolean may_skip,@Param("position")int position, @Param("question")String question, @Param("type")String type, @Param("survey_id") int survey_id);

    /**
     * <authors>
     *      <author> Giovanni Carlucci - giovannicarlucci9@yahoo.de</author>
     * </authors>
     * */
    @Query(value = "SELECT id FROM survey_element Order By id DESC LIMIT 1",nativeQuery = true)
    public int getLastId();

    /**
     * <authors>
     *      <author> Jannik Sinz - jannik.sinz@ibm.com</author>
     * </authors>
     * */
    @Query(value = "SELECT id FROM survey_element WHERE :survey_id = survey_id", nativeQuery = true)
    Set<Integer> getSurveyElementsBySurveyId(@Param("survey_id") int survey_id);
}

