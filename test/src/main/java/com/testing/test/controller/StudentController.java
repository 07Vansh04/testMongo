package com.testing.test.controller;


import com.testing.test.model.UserDto;
import com.testing.test.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController

public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/create")
    public ResponseEntity<UserDto> createStudent(@RequestBody UserDto userDto) {
        UserDto savedUserDto = this.studentService.saveStudent(userDto);
        return ResponseEntity.ok(savedUserDto);
    }

    @GetMapping("/students")
    public ResponseEntity<?> getAllStudent() {
        return ResponseEntity.ok(studentService.allStudents());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable String id) {
        try {
            String hi="";
            String bi="";

            String response = studentService.deleteStudentById(id);
            return ResponseEntity.ok(response);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while deleting the student.");
        }

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable String id, @RequestBody UserDto userDto) {
        try {
            UserDto savedUserDto = studentService.studentUpdateById(id, userDto);
            return ResponseEntity.ok(savedUserDto);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating the student.");
        }
    }



}



