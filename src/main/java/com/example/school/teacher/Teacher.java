package com.example.school.teacher;

import com.example.school.schoolClass.SchoolClass;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {

    @Id
    @Column(name = "teacher_id")
    private Long id;
    private String name;
    private String address;
    private List<Long> classes;
}
