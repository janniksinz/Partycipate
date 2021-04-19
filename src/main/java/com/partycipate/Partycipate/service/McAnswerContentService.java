package com.partycipate.Partycipate.service;

import com.partycipate.Partycipate.model.MCAnswerContent;
import com.partycipate.Partycipate.repository.McAnswerContentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
public class McAnswerContentService {
    private static final Logger log = LoggerFactory.getLogger(McAnswerContentService.class);

    @Autowired
    private McAnswerContentRepository mcAnswerContentRepository;

    public @ResponseBody Iterable<MCAnswerContent> getAllMcAnswerContentByAnswerId(int id){
        return mcAnswerContentRepository.findAllByAnswer_Id(id);
    }


}
