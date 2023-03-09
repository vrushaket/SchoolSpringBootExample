package com.example.school.subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    SubjectRepository repository;

    public void addNewSubject(Subject subject) {
        repository.save(subject);
    }

    public void deleteSubject(Long id) {
        repository.deleteById(id);
    }

    public void updateSubject(Long id, Subject subject) {
        repository.deleteById(id);
        repository.save(subject);
    }

    public Subject retrieveSpecificSubject(Long id) {
        return repository.findById(id).get();
    }

    public List<Subject> retrieveAllSubjects() {
        return repository.findAll();
    }
}
