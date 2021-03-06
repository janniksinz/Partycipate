package com.partycipate.Partycipate.repository;


import com.partycipate.Partycipate.dto.RegionUser;
import com.partycipate.Partycipate.model.Survey;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.jpa.repository.Modifying;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Repository
public interface Survey_ParticipantRepository extends CrudRepository<Survey, Integer> {
    @Query(value= "SELECT participant.region id, COUNT(*) as `value` FROM `survey` " +
            "INNER JOIN `survey_participant` ON survey.id=survey_participant.survey_id " +
            "INNER JOIN `participant` ON survey_participant.participant_id=participant.id " +
            "WHERE survey.id=:survey_id " +
            "GROUP BY participant.region", nativeQuery = true)
    public List<RegionUser> getParticipantCountPerRegion(@Param("survey_id") int survey_id);

    /**
     * <authors>
     *      <author> Jarg Heyll - wi19225@lehre.dhbw-stuttgart.de</author>
     * </authors>
     * */
    @Query(value= "SELECT participant.region id, COUNT(*) as `value` FROM `survey` " +
            "INNER JOIN `survey_participant` ON survey.id=survey_participant.survey_id " +
            "INNER JOIN `participant` ON survey_participant.participant_id=participant.id " +
            "WHERE survey.id=:survey_id " +
            "GROUP BY participant.region", nativeQuery = true)
    public List<RegionUser> getParticipantCountPerRegionBySurvey_id(@Param("survey_id") int survey_id);

    /**
     * <authors>
     *      <author> Jarg Heyll - wi19225@lehre.dhbw-stuttgart.de</author>
     * </authors>
     * */
    @Query(value= "SELECT participant.region id, COUNT(*) as `value` FROM `survey` " +
            "INNER JOIN `survey_participant` ON survey.id=survey_participant.survey_id " +
            "INNER JOIN `participant` ON survey_participant.participant_id=participant.id " +
            "WHERE survey.user_id=:user_id " +
            "GROUP BY participant.region", nativeQuery = true)
    public List<RegionUser> getParticipantCountPerRegionByUser_id(@Param("user_id") int user_id);

    /**
     * <authors>
     *      <author> Jarg Heyll - wi19225@lehre.dhbw-stuttgart.de</author>
     *      <author> Andreas Pitsch - wi19165@lehre.dhbw-stuttgart.de</author>
     * </authors>
     * */
    @Query(value= "SELECT participant.region id, COUNT(*) as `value` FROM `survey` " +
            "INNER JOIN `survey_participant` ON survey.id=survey_participant.survey_id " +
            "INNER JOIN `participant` ON survey_participant.participant_id=participant.id " +
            "GROUP BY participant.region", nativeQuery = true)
    public List<RegionUser> getTotalParticipantCountPerRegion();

    /**
     * <authors>
     *      *      <author> Jannik Sinz - jannik.sinz@ibm.com </author>
     *      *      <author> Giovanni Carlucci - giovannicarlucci9@yahoo.de</author>
     * </authors>
     * */
    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query(value = "Insert Into survey_participant (survey_id,participant_id) VALUES (:survey_id, :participant_id)", nativeQuery = true)
    public void sendAnswer(@Param("survey_id") int survey_id, @Param("participant_id") int participant_id);
}


