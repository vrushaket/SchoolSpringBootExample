package com.example.school.schoolClass;

import com.example.school.student.Student;
import com.example.school.subject.Subject;

import java.util.List;

public interface SchoolClassService {
    void addNewSchoolClass(SchoolClass schoolClass);
    void deleteSchoolClass(Long id);
    void updateSchoolClass(Long id, SchoolClass schoolClass);
    SchoolClass retrieveSpecificSchoolClass(Long id);
    List<SchoolClass> retrieveAllSchoolClass();
    List<Subject> retrieveAllSubjectsOfSchoolClass(Long id);
}
