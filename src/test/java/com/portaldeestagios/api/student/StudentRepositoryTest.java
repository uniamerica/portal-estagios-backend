package com.portaldeestagios.api.student;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@DisplayName("Student Repository test")
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    @DisplayName("Test if student is persisted correct")
    void save_PersistStudent_WhenSuccessful(){

        Student student = studentBuilder();
        Student savedStudent = this.studentRepository.save(student);

        Assertions.assertThat(savedStudent).isNotNull();
        Assertions.assertThat(savedStudent.getId()).isNotNull();
        Assertions.assertThat(savedStudent.getFirstName().equals(student.getFirstName()));
    }


    @Test
    @DisplayName("Test if student is updated correct")
    void save_UpdatedStudent_WhenSuccessful(){

        Student student = studentBuilder();
        Student savedStudent = this.studentRepository.save(student);
        savedStudent.setFirstName("Alexandre");
        Student updatedStudent = this.studentRepository.save(student);

        Assertions.assertThat(savedStudent).isNotNull();
        Assertions.assertThat(savedStudent.getId()).isNotNull();
        Assertions.assertThat(updatedStudent.getFirstName().equals(savedStudent.getFirstName()));
    }

    @Test
    @DisplayName("Test if student is deleted correct")
    void delete_PersistStudent_WhenSuccessful(){

        Student student = studentBuilder();
        Student savedStudent = this.studentRepository.save(student);
        this.studentRepository.delete(savedStudent);
        Optional<Student> studentOptional = this.studentRepository.findById(savedStudent.getId());

        Assertions.assertThat(studentOptional.isEmpty()).isTrue();
    }


    private Student studentBuilder(){
        return Student.builder()
                .id(1L)
                .firstName("Bruno")
                .lastName("Segato")
                .age((byte) 26)
                .build();

    }

}