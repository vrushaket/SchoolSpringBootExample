package com.example.school.student;

import com.example.school.schoolClass.SchoolClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    StudentRepository repository;

    public Student addNewStudent(Student student) {
        return repository.save(student);
    }

    public Long deleteStudent(Long id) {
        Optional<Student> studentOptional = repository.findById(id);
        studentOptional.orElseThrow(() -> new ResourceNotFoundException("student not found for id " + id));
        repository.deleteById(id);
        return id;
    }

    public Student retrieveSpecificStudent(Long id) {
        Optional<Student> studentOptional = repository.findById(id);
        studentOptional.orElseThrow(() -> new ResourceNotFoundException("student not found for id " + id));
        return studentOptional.get();
    }

    public List<Student> retrieveAllStudents() {
        List<Student> students = repository.findAll();
        if(students.isEmpty()) return null;
        return students;
    }

    public void updateStudent(Long id, Student student) {
        repository.deleteById(id);
        repository.save(student);
    }

    public List<Student> retrieveAllStudentsByName(String name) {
        return repository.findByName(name);
    }

    public List<Student> retrieveAllStudentsByClassId(Long classId) {
        return repository.findByClassId(classId);
    }

    public List<Student> retrieveAllStudentsByAddress(String address) {
        return repository.findByAddress(address);
    }

    public List<Student> retrieveAllStudentsWhereNameStartWith(String letter) {
        return repository.getAllStudentsWhereNameStartWith(letter);
    }

    public SchoolClass retrieveStudentClass(Long studentId) {
        return repository.getStudentClass(studentId);
    }

//    ObjectMapper mapper = new ObjectMapper();
//      return mapper.writeValueAsString(object);
}
