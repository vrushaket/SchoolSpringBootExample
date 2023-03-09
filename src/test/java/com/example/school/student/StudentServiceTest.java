package com.example.school.student;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.stubbing.OngoingStubbing;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class StudentServiceTest {

    @Mock
    StudentRepository repository;

    @InjectMocks
    StudentServiceImpl studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void addNewStudent_BasicScenarioTest(){
        Student student = new Student(2L,"Vrushaket","Pune",1000L);
        when(repository.save(student)).thenReturn(student);
        studentService.addNewStudent(student);
        verify(repository, times(1)).save(student);
        ArgumentCaptor<Student> captor = ArgumentCaptor.forClass(Student.class);
        verify(repository).save(captor.capture());
        System.out.println(captor.getValue());
        assertTrue(captor.getValue().getName().equals(student.getName()));
    }

    @Test
    void deleteStudent_BasicScenarioTest() {
        Student student = new Student(1L,"Vrushaket","Jalgaon",1000L);
        Long idToDelete = student.getId();
        when(repository.save(student)).thenReturn(student);
        studentService.addNewStudent(student);
        when(repository.findById(idToDelete)).thenReturn(Optional.of(student));
        studentService.deleteStudent(idToDelete);
        verify(repository, times(1)).deleteById(any());
        //assertTrue(repository.findById(idToDelete).isEmpty());
    }

    @Test
    void retrieveSpecificStudent_BasicScenarioTest() {
        Student student = new Student(1L,"Vrushaket","Jalgaon",1000L);
        Long idToRetreive = 1L;
        when(repository.findById(idToRetreive)).thenReturn(Optional.of(student));
        studentService.retrieveSpecificStudent(idToRetreive);
        verify(repository, times(1)).findById(any());
    }

    @Test
    public void updateStudent_BasicScenarioTest(){
        Long studentId = 1l;
        Student student = new Student(1L,"Vrushaket","Jalgaon",1000L);
        studentService.updateStudent(studentId,student);
        verify(repository, times(1)).deleteById(anyLong());
        verify(repository, times(1)).save(student);
        ArgumentCaptor<Student> captor = ArgumentCaptor.forClass(Student.class);
        verify(repository).save(captor.capture());
        assertTrue(captor.getValue().getName().equals(student.getName()));
    }

}
