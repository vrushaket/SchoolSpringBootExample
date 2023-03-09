package com.example.school.subject;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Transactional
public class SubjectCommandLineRunner implements CommandLineRunner {

    @Autowired
    SubjectRepository repository;

    @Override
    public void run(String... args) throws Exception {
        Subject subject1 = new Subject(1l,"Java");
        Subject subject2 = new Subject(2l,"Python");
        Subject subject3 = new Subject(3l,"JavaScript");
        Subject subject4 = new Subject(4l,"Ruby");

        repository.save(subject1);
        repository.save(subject2);
        repository.save(subject3);
        repository.save(subject4);
    }
}
