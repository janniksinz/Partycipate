package com.partycipate.Partycipate.repository;


import com.partycipate.Partycipate.model.Survey;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Set;
@Repository
public interface SurveyRepository extends CrudRepository<Survey, Integer> {
    Survey findById(int id);

    /**
     * <authors>
     *      <author> Giovanni Carlucci - giovannicarlucci9@yahoo.de</author>
     * </authors>
     * */
    @Query(value = "SELECT * FROM survey WHERE :user_id = user_id",nativeQuery = true)
    public Set<Survey> getSurveysByUser(@Param("user_id") int id);

    /**
     * <authors>
     *      <author> Giovanni Carlucci - giovannicarlucci9@yahoo.de</author>
     * </authors>
     * */
    @Query(value = "SELECT DISTINCT id FROM survey WHERE :user_id = user_id", nativeQuery = true)
    Set<Integer> getDistinctSurveyIds(@Param("user_id") int user_id);


    /**
     * <authors>
     *      <author> Giovanni Carlucci - giovannicarlucci9@yahoo.de</author>
     * </authors>
     * */
    @Query(value = "SELECT COUNT(id) FROM survey WHERE user_id=:user_id and id=:id", nativeQuery = true)
    int ownsSurvey(@Param("user_id")int user_id, @Param("id") int survey_id);

    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    public void deleteById(int id);

}


