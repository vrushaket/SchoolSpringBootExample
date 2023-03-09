package com.example.school.student;

import com.example.school.schoolClass.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long> {

    List<Student> findByName(String name);
    List<Student> findByClassId(Long classId);
    List<Student> findByAddress(String address);

    @Query("SELECT s FROM Student s WHERE s.name LIKE :letter%")
    List<Student> getAllStudentsWhereNameStartWith(@Param("letter") String letter);

    @Query("""
           SELECT sc 
           FROM SchoolClass sc INNER JOIN Student st 
           ON st.classId = sc.id 
           WHERE st.id=:studentId
           """)
    SchoolClass getStudentClass(@Param("studentId") Long studentId);
}
