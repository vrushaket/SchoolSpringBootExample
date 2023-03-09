package com.example.school.schoolClass;

import com.example.school.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SchoolClassServiceImpl implements SchoolClassService{

    @Autowired
    SchoolClassRepository repository;

    public void addNewSchoolClass(SchoolClass schoolClass) {
        repository.save(schoolClass);
    }

    public void deleteSchoolClass(Long id) {
        repository.deleteById(id);
    }

    public void updateSchoolClass(Long id, SchoolClass schoolClass) {
        repository.deleteById(id);
        repository.save(schoolClass);
    }

    public SchoolClass retrieveSpecificSchoolClass(Long id) {
        return repository.findById(id).get();
    }

    public List<SchoolClass> retrieveAllSchoolClass() {
        return repository.findAll();
    }

    public List<Subject> retrieveAllSubjectsOfSchoolClass(Long id) {
        return repository.findAllSubjectsByClassId(id);
    }
}
