package com.example.school.student;

import com.example.school.schoolClass.SchoolClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class StudentResource {

    @Autowired
    StudentServiceImpl studentService;

    @RequestMapping("/students")
    public List<Student> retrieveAllStudents(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "classId", required = false) Long classId,
            @RequestParam(value = "address", required = false) String address,
            @RequestParam(value = "nameStartsWith", required = false) String letter) {
        List<Student> students;
        if (name != null) {
            students = studentService.retrieveAllStudentsByName(name);
        } else if (classId != null) {
            students = studentService.retrieveAllStudentsByClassId(classId);
        } else if (address != null) {
            students = studentService.retrieveAllStudentsByAddress(address);
        }
        else if (letter != null) {
            students = studentService.retrieveAllStudentsWhereNameStartWith(letter);
        }else {
            students = studentService.retrieveAllStudents();
        }
        if (students == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        if (students.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return students;
    }

    @RequestMapping(value = "/students/{studentId}", method = RequestMethod.GET)
    public Student retrieveSpecificStudent(@PathVariable Long studentId){
        Student student = studentService.retrieveSpecificStudent(studentId);
        if (student == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return student;
    }

    @RequestMapping(value = "/students", method = RequestMethod.POST)
    public ResponseEntity<Object> addNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{studentId}").buildAndExpand(student.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @RequestMapping(value = "/students/{studentId}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateStudent(@PathVariable Long studentId, @RequestBody Student student){
        studentService.updateStudent(studentId,student);

        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/students/{studentId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteStudent(@PathVariable Long studentId){
        studentService.deleteStudent(studentId);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/students/{studentId}/class", method = RequestMethod.GET)
    public SchoolClass retrieveStudentClass(@PathVariable Long studentId){
        SchoolClass studentClass = studentService.retrieveStudentClass(studentId);
        return studentClass;
    }
//    @RequestMapping(value = "/studentsByName", method = RequestMethod.GET)
//    public List<Student> retrieveAllStudentsByName(@RequestParam("name") String name) {
//        return studentService.retrieveAllStudentsByName(name);
//    }
//
//    @RequestMapping(value = "/studentsByClassId", method = RequestMethod.GET)
//    public List<Student> retrieveAllStudentsByClassId(@RequestParam("classId") Long classId) {
//        return studentService.retrieveAllStudentsByClassId(classId);
//    }
//
//    @RequestMapping(value = "/studentsByAddress", method = RequestMethod.GET)
//    public List<Student> retrieveAllStudentsByAddress(@RequestParam("address") String address) {
//        return studentService.retrieveAllStudentsByAddress(address);
//    }

}
