package com.example.school.schoolClass;

import com.example.school.subject.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SchoolClassRepository extends JpaRepository<SchoolClass, Long> {

    @Query("select sc.subjects from SchoolClass sc where sc.id = :id")
    List<Subject> findAllSubjectsByClassId(@Param("id") Long id);
}
