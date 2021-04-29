package com.partycipate.Partycipate.repository;


import com.partycipate.Partycipate.dto.RegionUser;
import com.partycipate.Partycipate.model.Survey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Repository
public interface Survey_ParticipantRepository extends CrudRepository<Survey, Integer> {
    @Query(value= "SELECT new com.partycipate.Partycipate.dto.RegionUser(participant.region AS id, COUNT(*) AS v) FROM `survey` " +
            "INNER JOIN `survey_participant` ON survey.id=survey_participant.survey_id " +
            "INNER JOIN `participant` ON survey_participant.participant_id=participant.id " +
            "WHERE survey.id=:survey_id " +
            "GROUP BY participant.region")
    public List<RegionUser> getParticipantCountPerRegion(@Param("survey_id") int survey_id);
    //Set<String, Integer>

    //Set<Answer> answers = answerRepository.getAnswersByElementId(element_id);
    //            Iterator<Answer> answerIterator= answers.iterator();
}

