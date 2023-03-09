package com.example.school.teacher;

import com.example.school.schoolClass.SchoolClass;
import com.example.school.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher,Long> {


    List<Teacher> findByName(String name);
    List<Teacher> findByAddress(String address);

    @Query("""
    SELECT sc
    FROM SchoolClass sc INNER Join Teacher tc on sc.teacherId = tc.id where sc.teacherId = :id
    """)
    List<SchoolClass> retrieveClassesOfTeacherById(@Param("id") Long id);
}
