package com.example.school.teacher;


import com.example.school.schoolClass.SchoolClass;

import java.util.List;

public interface TeacherService {

    void addNewTeacher(Teacher Teacher);
    void deleteTeacher(Long id);
    void updateTeacher(Long id, Teacher Teacher);
    Teacher retrieveSpecificTeacher(Long id);
    List<Teacher> retrieveAllTeachers();
    List<Teacher> retrieveAllTeachersByName(String name);
    List<SchoolClass> retrieveClassesOfTeacherById(Long id);

    List<Teacher> retrieveAllTeachersByAddress(String address);

}
