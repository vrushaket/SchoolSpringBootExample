package com.example.school.schoolClass;

import com.example.school.subject.Subject;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SchoolClass {

    @Id
    @NotNull
    @Column(name = "class_id")
    private Long id;
    private Long classroomId;

    @Column(name = "teacher_id")
    private Long teacherId;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "class_id")
    private List<Subject> subjects;
}
