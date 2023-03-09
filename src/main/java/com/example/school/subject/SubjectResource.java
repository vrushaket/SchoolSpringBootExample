package com.example.school.subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubjectResource {

    @Autowired
    SubjectServiceImpl subjectService;

}
