package com.example.school.schoolClass;

import com.example.school.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class SchoolClassResource {

    @Autowired
    SchoolClassServiceImpl schoolClassService;

    @RequestMapping("/class")
    public List<SchoolClass> retreiveAllClasses(){
        List<SchoolClass> schoolClasses = schoolClassService.retrieveAllSchoolClass();
        return schoolClasses;
    }

    @RequestMapping("/class/{classId}/subjects")
    public List<Subject> retrieveAllSubjectsOfSchoolClass(@PathVariable Long classId){
        List<Subject> subjects = schoolClassService.retrieveAllSubjectsOfSchoolClass(classId);
        return subjects;
    }

    @RequestMapping(value = "/class/{classId}", method = RequestMethod.GET)
    public SchoolClass retrieveSpecificClass(@PathVariable Long classId){
        SchoolClass schoolClass = schoolClassService.retrieveSpecificSchoolClass(classId);
        return schoolClass;
    }

    @RequestMapping(value = "/class", method = RequestMethod.POST)
    public ResponseEntity<Object> addNewSchoolClass(@RequestBody SchoolClass schoolClass){
        schoolClassService.addNewSchoolClass(schoolClass);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{classId}").buildAndExpand(schoolClass.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @RequestMapping(value = "/class/{classId}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateSchoolClass(@PathVariable Long classId, @RequestBody SchoolClass schoolClass){
        schoolClassService.updateSchoolClass(classId,schoolClass);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/class/{classId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteSchoolClass(@PathVariable Long classId){
        schoolClassService.deleteSchoolClass(classId);
        return ResponseEntity.noContent().build();
    }
}
