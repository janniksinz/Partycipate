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

    @Query(value = "Select * from mcanswer_content where answer_id = :answer_id", nativeQuery = true)
    Iterable<MCAnswerContent> findAllByAnswer_Id(@Param("answer_id") int answer_id);
}
