package com.example.school.teacher;

import com.example.school.schoolClass.SchoolClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class TeacherResource {

    @Autowired
    TeacherServiceImpl teacherService;

    @RequestMapping("/teachers")
    public List<Teacher> retrieveAllTeachers(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "classId", required = false) Long classId,
            @RequestParam(value = "address", required = false) String address) {
        List<Teacher> teachers;
        if (name != null) {
            teachers = teacherService.retrieveAllTeachersByName(name);
        } else if (address != null) {
            teachers = teacherService.retrieveAllTeachersByAddress(address);
        }
        else {
            teachers = teacherService.retrieveAllTeachers();
        }
        if (teachers == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        if (teachers.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return teachers;
    }

    @RequestMapping(value = "/teachers/{teacherId}", method = RequestMethod.GET)
    public Teacher retrieveSpecificTeacher(@PathVariable Long teacherId){
        Teacher student = teacherService.retrieveSpecificTeacher(teacherId);
        return student;
    }

    @RequestMapping(value = "/teachers", method = RequestMethod.POST)
    public ResponseEntity<Object> addNewTeacher(@RequestBody Teacher student){
        teacherService.addNewTeacher(student);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{teacherId}").buildAndExpand(student.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @RequestMapping(value = "/teachers/{teacherId}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateTeacher(@PathVariable Long teacherId, @RequestBody Teacher student){
        teacherService.updateTeacher(teacherId,student);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/teachers/{teacherId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteTeacher(@PathVariable Long teacherId){
        teacherService.deleteTeacher(teacherId);
        return ResponseEntity.noContent().build();
    }
    @RequestMapping(value = "/teachers/{teacherId}/classes", method = RequestMethod.GET)
    List<SchoolClass> retrieveClassesOfTeacherById(@PathVariable Long teacherId){
        return teacherService.retrieveClassesOfTeacherById(teacherId);
    }
}
