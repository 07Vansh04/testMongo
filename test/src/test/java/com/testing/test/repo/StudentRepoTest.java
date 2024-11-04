package com.testing.test.repo;

import com.testing.test.model.Students;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
;

@DataMongoTest
class



StudentRepoTest {
    @Autowired
    private StudentRepo studentRepo;
    private Students students;

    @BeforeEach
    void setUp() {
        students = new Students("1", "vansh", "newyork", "ips");
        studentRepo.save(students);
    }

    @AfterEach
    void tearDown() {
        studentRepo.deleteAll();
    }
    @Test
    public void testFindById_Found() {
        Optional<Students> foundStudent = studentRepo.findById("1");
        assertThat(foundStudent).isPresent();
        assertThat(foundStudent.get().getName()).isEqualTo(students.getName());
    }

    @Test
    void testFindById_NotFound() {
        Optional<Students> foundStudent = studentRepo.findById("7");
        assertThat(foundStudent.isEmpty()).isTrue();
    }


}