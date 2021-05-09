package com.partycipate.Partycipate.repository;


import com.partycipate.Partycipate.model.Participant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParticipantRepository extends CrudRepository<Participant, Integer> {

    @Query(value= "SELECT Count(participant_id) from answer", nativeQuery = true)
    public Optional<Integer> getCountParticipants();


    public Participant getParticipantByCookie(String cookie);
    /**
     * <authors>
     *   <author> Giovanni Carlucci - giovannicarlucci9@yahoo.de</author>
     * </authors>
     * */
    @Query(value = "SELECT next_val FROM partycipate.participant_sequence", nativeQuery = true)
    Integer getNextValue();

}

