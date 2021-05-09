package com.partycipate.Partycipate.repository;


import com.partycipate.Partycipate.model.AnswerPossibility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AnswerPossibilityRepository extends JpaRepository<AnswerPossibility, Integer> {
    /**
     * <authors>
     *      <author> Jarg Heyll - wi19225@lehre.dhbw-stuttgart.de</author>
     *      <author> Andreas Pitsch - wi19165@lehre.dhbw-stuttgart.de</author>
     * </authors>
     * */
    @Query(value = "SELECT COUNT(id) FROM answer_possibility WHERE survey_element_id = :element_id", nativeQuery = true)
    int count_answer_possibilities(@Param("element_id")int element_id);

    /**
     * <authors>
     <author> Ines Maurer - inesmaurer@outlook.de</author></author>
     * </authors>
     * */
    @Query(value = "SELECT id FROM answer_possibility Order By id DESC LIMIT 1",nativeQuery = true)
    int getLastId();

    /**
     * <authors>
     *      <author> Ines Maurer - inesmaurer@outlook.de</author>
     * </authors>
     * */
    @Query(value = "SELECT * FROM answer_possibility WHERE survey_element_id = :element_id", nativeQuery = true)
    Set<AnswerPossibility> findByElementId(int element_id);
}

