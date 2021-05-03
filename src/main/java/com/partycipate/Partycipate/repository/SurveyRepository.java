package com.partycipate.Partycipate.repository;


import com.partycipate.Partycipate.model.Survey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;
@Repository
public interface SurveyRepository extends CrudRepository<Survey, Integer> {
    Survey findById(int id);

    @Query(value = "SELECT * FROM survey WHERE :user_id = user_id",nativeQuery = true)
    public Set<Survey> getSurveysByUser(@Param("user_id") int id);

    @Query(value = "SELECT DISTINCT id FROM survey WHERE :user_id = user_id", nativeQuery = true)
    Set<Integer> getDistinctSurveyIds(@Param("user_id") int user_id);

//    @Transactional
//    @Modifying

    @Query(value = "SELECT COUNT(id) FROM survey WHERE user_id=: user_id", nativeQuery = true)
    boolean ownsSurvey(@Param("user_id")int user_id);
}
