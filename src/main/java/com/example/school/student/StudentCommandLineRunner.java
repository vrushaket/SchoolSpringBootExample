package com.example.school.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Transactional
public class StudentCommandLineRunner implements CommandLineRunner {

    @Autowired
    StudentRepository repository;

    @Override
    public void run(String... args) throws Exception {
        Student student1 = new Student(1l,"Vrushaket","Pune",1l);
        Student student2 = new Student(2l,"Om","Jalgaon",1l);
        Student student3 = new Student(3l,"Chetan","Mumbai",1l);
        Student student4 = new Student(4l,"Akshay","Pune",1l);

        repository.save(student1);
        repository.save(student2);
        repository.save(student3);
        repository.save(student4);
    }
}
