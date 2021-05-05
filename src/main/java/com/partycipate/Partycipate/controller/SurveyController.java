package com.partycipate.Partycipate.controller;


import com.partycipate.Partycipate.dto.SendElement;
import com.partycipate.Partycipate.dto.SendSurvey;
import com.partycipate.Partycipate.model.User;
import com.partycipate.Partycipate.repository.SurveyRepository;
import com.partycipate.Partycipate.security.message.response.ResponseMessage;
import com.partycipate.Partycipate.model.Survey;
import com.partycipate.Partycipate.service.SurveyElementService;
import com.partycipate.Partycipate.service.SurveyService;
import com.partycipate.Partycipate.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/survey")
@CrossOrigin(origins = "*")
public class SurveyController {
    private static final Logger log = LoggerFactory.getLogger(SurveyController.class);

    @Autowired
    private SurveyElementService surveyElementService;
    @Autowired
    private SurveyService surveyService;
    @Autowired
    private UserService userService;
    @Autowired
    private SurveyRepository surveyRepository;

//    addSurvey
    @PostMapping(value = "")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<?> addSurvey(@RequestBody SendSurvey sendsurvey){
        User user = userService.getUserByJWT();
        log.info("addSurvey: Inserting edSurvey for user {}", user.getUser_id());
        return new ResponseEntity<>(surveyService.addSurvey(sendsurvey, user).getId(), HttpStatus.OK);
    }

//    getAll
    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<?> getAllSurveys() {
        log.info("getAllSurveys");
        User user = userService.getUserByJWT();
        if (userService.isAdmin()) return new ResponseEntity<>(surveyService.getAllSurveys(), HttpStatus.OK);
        else {
            return new ResponseEntity<>(surveyService.getAllSurveysByUser(user), HttpStatus.OK);
        }
    }

//    getById
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<?> getSurvey(@PathVariable("id") int id){
        log.info("getSurvey: {}", id);
        if (userService.isAdmin() || surveyService.ownsSurvey(id)) {
            return new ResponseEntity<>(surveyService.getSurveyBySurveyId(id), HttpStatus.OK);
        } else return new ResponseEntity<>(new ResponseMessage("Fail -> no Auth to access Survey for this User"), HttpStatus.BAD_REQUEST);
    }

//    deleteById
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<?> deleteSurveybyId(@PathVariable("id") int id) throws EmptyResultDataAccessException {
        log.info("deleteSurvey: Deleting Survey {}", id);
        if (userService.isAdmin() || surveyService.ownsSurvey(id)){
            return new ResponseEntity<>(surveyService.deleteSurveybyId(id), HttpStatus.OK);
        } else return new ResponseEntity<>(new ResponseMessage("Fail -> no Auth to access Survey for this User"), HttpStatus.BAD_REQUEST);
    }

//    addSurveyElement
    @PostMapping("/element")
    public int addSurveyElement(@RequestBody SendElement sendElement){
        return 0;
    }

}
