package com.partycipate.Partycipate.repository;


import com.partycipate.Partycipate.model.Survey;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface Survey_ParticipantRepository extends CrudRepository<Survey, Integer> {
    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query(value = "Insert Into survey_participant (survey_id,participant_id) VALUES (:survey_id, :participant_id)", nativeQuery = true)
    public void sendAnswer(@Param("survey_id") int survey_id, @Param("participant_id") int participant_id);
}

