package com.example.school.subject;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject,Long> {


}
// select * from SchoolClass sc INNER JOIN Subject sb on sc.id = sub.Subjects_Class_Id;
// ID  	NAME  	SUBJECTS_CLASS_ID
//CLASS_ID  	CLASSROOM_ID  	TEACHER_ID
// List<Subject> getAllSubjectOfClass(Long id);