package com.testing.test.mapper;


import com.testing.test.model.Students;
import com.testing.test.model.UserDto;

public class UserMapper {
    public static UserDto mapToUserDto(Students students){
UserDto userDto= new UserDto(students.getId(), students.getName(), students.getCity(), students.getCollege());
 return userDto;
    }

    public static Students mapToUser(UserDto userDto){
        Students students = new  Students(userDto.getId(), userDto.getName(), userDto.getCity(), userDto.getCollege());
        return students;
    }
}
