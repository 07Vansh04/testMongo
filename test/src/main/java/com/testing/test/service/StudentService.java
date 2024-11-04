package com.testing.test.service;

import com.testing.test.mapper.UserMapper;
import com.testing.test.model.Students;
import com.testing.test.model.UserDto;
import com.testing.test.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepo repo;


    public UserDto saveStudent(UserDto userDto) {
        Students students= UserMapper.mapToUser(userDto);
        Students savedStudent=repo.save(students);
        UserDto savedUserDto=UserMapper.mapToUserDto(savedStudent);
        return savedUserDto;
    }

    public List<UserDto> allStudents() {
        List<Students> students= repo.findAll();
        List<UserDto> userDtoList= students.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
        return userDtoList;
    }

    public String deleteStudentById(String id) {
        Optional<Students> student= repo.findById(id);
        if( student.isPresent()) {
            this.repo.deleteById(id);
            return " this " + id + " student has been deleted sucessfully";
        }
        else{
            throw  new NoSuchElementException("Student "+id+ " is not found");
        }
    }



    public UserDto studentUpdateById(String id, UserDto userDto) {
        Optional<Students> existingStudent = repo.findById(id);
        if( existingStudent.isPresent()) {
            Students student = existingStudent.get();
            student.setName(userDto.getName());
            student.setCity(userDto.getCity());
            student.setCollege(userDto.getCollege());
            repo.save(student);
            return  UserMapper.mapToUserDto(student) ;
        }
        else{
            throw  new NoSuchElementException("Student "+id+ " is not found");
        }
    }
}
