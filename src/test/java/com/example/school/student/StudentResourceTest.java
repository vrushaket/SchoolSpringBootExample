package com.example.school.student;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@WebMvcTest(controllers = StudentResource.class)
public class StudentResourceTest {

    private static String GENERIC_URL = "http://localhost:8000/students";
    private static String SPECIFIC_STUDENT_URL = "http://localhost:8000/students/1";
    private static String SPECIFIC_STUDENT_404_URL = "http://localhost:8000/students/11";

    @MockBean
    StudentServiceImpl studentService;

    @Autowired
    private MockMvc mockMvc;

    @Captor
    ArgumentCaptor<Long> studentIdArgumentCaptor;

    @Test
    void retrieveAllStudents_BasicScenarioTest() throws Exception {
        Student student1 = new Student(1l,"Vrushaket","Pune",1l);
        List<Student> studentList= new ArrayList<>();
        studentList.add(student1);
        Mockito.when(studentService.retrieveAllStudents()).thenReturn(studentList);

        String expectedResponse = """
                [{"id":1,"name":"Vrushaket","address":"Pune","classId":1}]
                """;
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                                            .get(GENERIC_URL)
                                            .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(200,result.getResponse().getStatus());
        JSONAssert.assertEquals(expectedResponse,result.getResponse().getContentAsString(),false);
    }

    @Test
    void retrieveSpecificStudent_404ScenarioTest() throws Exception {

        Long studentId = 11l;
        Mockito.when(studentService.retrieveSpecificStudent(studentId)).thenReturn(null);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(SPECIFIC_STUDENT_404_URL)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(404,result.getResponse().getStatus());
        //ArgumentCaptor studentIdArgumentCaptor = ArgumentCaptor.forClass(Student.class);
        verify(studentService).retrieveSpecificStudent(studentIdArgumentCaptor.capture());
        Assertions.assertEquals(studentIdArgumentCaptor.getValue(),studentId);
    }
    @Test
    void retrieveSpecificStudent_BasicScenarioTest() throws Exception {
        Student student1 = new Student(1l,"Vrushaket","Pune",1l);
        Long studentId = 1l;
        Mockito.when(studentService.retrieveSpecificStudent(studentId)).thenReturn(student1);
        String expectedResponse = """
                {"id":1,"name":"Vrushaket","address":"Pune","classId":1}
                """;

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                                            .get(SPECIFIC_STUDENT_URL)
                                            .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(200,result.getResponse().getStatus());
        JSONAssert.assertEquals(expectedResponse,result.getResponse().getContentAsString(),false);
        verify(studentService,atMostOnce()).retrieveSpecificStudent(studentId);
    }

    @Test
    void addNewStudent_BasicScenarioTest() throws Exception{
        Student student = new Student(2L,"Vrushaket","Pune",1L);
        String responseBody = """
                 {"id":1,"name":"Vrushaket","address":"Pune","classId":1}
                """;
        Mockito.when(studentService.addNewStudent(any())).thenReturn(student);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                                            .post(GENERIC_URL)
                                            .accept(MediaType.APPLICATION_JSON)
                                            .content(responseBody)
                                            .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(201,result.getResponse().getStatus());
        String location = result.getResponse().getHeader("location");
        assertTrue(location.contains("students/1"));
    }

    @Test
    void updateStudent_BasicScenarioTest() throws Exception{
        Long studentIdToBeUpdated = 1L;
        Student student1 = new Student(1l,"Vrushaket","Pune",2l);
        String responseBody = """
                 {"id":1,"name":"Vrushaket","address":"Pune","classId":2}
                """;
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                                            .put(SPECIFIC_STUDENT_URL)
                                            .accept(MediaType.APPLICATION_JSON)
                                            .content(responseBody)
                                            .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(204,result.getResponse().getStatus());
        verify(studentService).updateStudent(studentIdToBeUpdated,student1);
    }

    @Test
    void deleteStudent_BasicScenarioTest() throws Exception{
        Long studentIdToBeDeleted = 1L;
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                                            .delete(SPECIFIC_STUDENT_URL)
                                            .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(204,result.getResponse().getStatus());
        verify(studentService).deleteStudent(studentIdToBeDeleted);
    }
}
