package com.partycipate.Partycipate.repository;


import com.partycipate.Partycipate.model.Survey;
import com.partycipate.Partycipate.model.SurveyElement;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Set;

@Repository
public interface SurveyElementRepository extends CrudRepository<SurveyElement, Integer> {

    @Query(value=" Select * from survey_element Where survey_id =:survey_id", nativeQuery = true)
    public Set<SurveyElement> findAllBySurveyId(@Param("survey_id") int survey_id);

    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query (value = "INSERT INTO `survey_element` ( `id`, `may_skip`, `position`, `question`, `type`, `survey_id`) VALUES ( :id, :may_skip, :position,  :question,  :type,  :survey_id); ", nativeQuery = true)
    public void saveSurveyElement(@Param("id") int id, @Param("may_skip") Boolean may_skip,@Param("position")int position, @Param("question")String question, @Param("type")String type, @Param("survey_id") int survey_id);

    @Query(value = "SELECT id FROM survey_element Order By id DESC LIMIT 1",nativeQuery = true)
    public int getLastId();
}

