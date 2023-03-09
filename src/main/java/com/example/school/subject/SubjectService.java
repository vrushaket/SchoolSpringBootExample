package com.example.school.subject;


import java.util.List;

public interface SubjectService {
    void addNewSubject(Subject subject);
    void deleteSubject(Long id);
    void updateSubject(Long id, Subject subject);
    Subject retrieveSpecificSubject(Long id);
    List<Subject> retrieveAllSubjects();
}
