package com.partycipate.Partycipate.service;

import com.partycipate.Partycipate.model.Survey;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ISurveyService {
    List<Survey> findAll();
    Optional<Survey> getSurvey(Long id);
}
