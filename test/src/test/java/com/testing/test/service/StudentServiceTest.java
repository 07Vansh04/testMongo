package com.testing.test.service;

import com.testing.test.mapper.UserMapper;
import com.testing.test.model.Students;
import com.testing.test.model.UserDto;
import com.testing.test.repo.StudentRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @Mock
    private StudentRepo studentRepo;

    @InjectMocks
    private StudentService studentService;
    private AutoCloseable autoCloseable;


    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);

    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
        ;
    }


    @Test
    void saveStudent_ShouldSaveAndReturnStudent() {
        UserDto userDto = new UserDto("7", "goku", "wuhan", "tokyuuniversity");
        Students students = UserMapper.mapToUser(userDto);
        when(studentRepo.save(any(Students.class))).thenReturn(students);

        UserDto result = studentService.saveStudent(userDto);
        assertNotNull(result);
        assertEquals("goku", result.getName());
    }

    @Test
    void allStudents() {


    }

    @Test
    void deleteStudentById_ShouldDeleteStudent_WhenStudentExists() {
        // Arrange
        String studentId = "1";
        Students student = new Students(studentId, "Alice", "New York", "MIT");
        when(studentRepo.findById(studentId)).thenReturn(Optional.of(student));

        // Act
        String result = studentService.deleteStudentById(studentId);

        // Assert
        assertEquals(" this 1 student has been deleted sucessfully", result);
        verify(studentRepo, times(1)).deleteById(studentId);
    }

    @Test
    void studentUpdateById() {
    }
}