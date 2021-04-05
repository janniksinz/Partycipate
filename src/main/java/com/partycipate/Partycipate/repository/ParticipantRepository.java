package com.partycipate.Partycipate.repository;


import com.partycipate.Partycipate.model.Participant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends CrudRepository<Participant, Integer> {

}

