package com.example.school.schoolClass;

import com.example.school.subject.Subject;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class SchoolClassCommandLineRunner implements CommandLineRunner {

    @Autowired
    SchoolClassRepository repository;

    @Override
    public void run(String... args) throws Exception {
        Subject subject1 = new Subject(1l,"Java");
        Subject subject2 = new Subject(2l,"Python");
        Subject subject3 = new Subject(3l,"JavaScript");
        Subject subject4 = new Subject(4l,"Ruby");

        List<Subject> subjectList = new ArrayList<>();
        subjectList.add(subject1);
        subjectList.add(subject2);

        List<Subject> subjectList2 = new ArrayList<>();
        subjectList2.add(subject3);
        subjectList2.add(subject4);

        List<Subject> subjectList3 = new ArrayList<>();
        subjectList3.add(subject1);
        subjectList3.add(subject2);
        subjectList3.add(subject3);
        subjectList3.add(subject4);

        SchoolClass schoolClass = new SchoolClass(1l,200l,400l,subjectList);
        SchoolClass schoolClass1 = new SchoolClass(2l,200l,400l,subjectList3);
        SchoolClass schoolClass2 = new SchoolClass(3l,210l,401l,subjectList2);
        repository.save(schoolClass);
        repository.save(schoolClass1);
        repository.save(schoolClass2);
    }
}
