package com.partycipate.Partycipate.service;

import com.partycipate.Partycipate.model.MCAnswerContent;
import com.partycipate.Partycipate.model.Survey;
import com.partycipate.Partycipate.repository.McAnswerContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
public class McAnswerContentService {
    @Autowired
    private McAnswerContentRepository mcAnswerContentRepository;

    public @ResponseBody Iterable<MCAnswerContent> getAllMcAnswerContentByAnswerId(int id){
        return mcAnswerContentRepository.findAllByAnswer_Id(id);
    }


}
