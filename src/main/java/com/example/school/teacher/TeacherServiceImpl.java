package com.example.school.teacher;

import com.example.school.schoolClass.SchoolClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService{

    @Autowired
    TeacherRepository repository;

    public void addNewTeacher(Teacher teacher) {
        repository.save(teacher);
    }

    public void deleteTeacher(Long id) {
        repository.deleteById(id);
    }

    public void updateTeacher(Long id, Teacher teacher) {
        repository.deleteById(id);
        repository.save(teacher);
    }

    public Teacher retrieveSpecificTeacher(Long id) {
        return repository.findById(id).get();
    }

    public List<Teacher> retrieveAllTeachers() {
        return repository.findAll();
    }

    public List<Teacher> retrieveAllTeachersByName(String name) {
        return repository.findByName(name);
    }

    public List<Teacher> retrieveAllTeachersByAddress(String address) {
        return repository.findByAddress(address);
    }

    public List<SchoolClass> retrieveClassesOfTeacherById(Long id){
        return repository.retrieveClassesOfTeacherById(id);
    }
}
