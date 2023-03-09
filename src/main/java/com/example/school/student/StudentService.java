package com.example.school.student;
import com.example.school.schoolClass.SchoolClass;

import java.util.List;

public interface StudentService {
    Student addNewStudent(Student student);
    Long deleteStudent(Long id);
    void updateStudent(Long id, Student student);
    Student retrieveSpecificStudent(Long id);
    List<Student> retrieveAllStudents();
    List<Student> retrieveAllStudentsByName(String name);
    List<Student> retrieveAllStudentsByClassId(Long id);
    List<Student> retrieveAllStudentsByAddress(String address);
    List<Student> retrieveAllStudentsWhereNameStartWith(String letter);

    SchoolClass retrieveStudentClass(Long studentId);
}
