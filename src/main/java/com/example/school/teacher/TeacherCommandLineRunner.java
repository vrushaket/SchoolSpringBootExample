package com.example.school.teacher;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class TeacherCommandLineRunner implements CommandLineRunner {

    @Autowired
    TeacherRepository repository;

    @Override
    public void run(String... args) throws Exception {

        List<Long> classes = new ArrayList<>();
        classes.add(1l);
        classes.add(2l);

        Teacher teacher1 = new Teacher(400l,"Roy","Pune",classes);
        Teacher teacher2 = new Teacher(401l,"Sameer","Mumbai",classes);
        repository.save(teacher1);
        repository.save(teacher2);
        System.out.println(repository.retrieveClassesOfTeacherById(400l));
    }
}
