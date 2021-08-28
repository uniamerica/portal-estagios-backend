package com.portaldeestagios.api.student;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Student Repository test")
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    @DisplayName("Test if student is persisted correct")
    void save_PersistStudent_WhenSuccessful(){

        Student student = StudentFactory.studentBuilderToBeSaved();
        Student savedStudent = this.studentRepository.save(student);

        assertThat(savedStudent).isNotNull();
        assertThat(savedStudent.getId()).isNotNull();
        assertThat(savedStudent.getFirstName().equals(student.getFirstName()));
    }

    @Test
    @DisplayName("Test if student is updated correct")
    void save_UpdatedStudent_WhenSuccessful(){

        Student student = StudentFactory.studentBuilderToBeSaved();
        Student savedStudent = this.studentRepository.save(student);
        savedStudent.setFirstName("Alexandre");
        Student updatedStudent = this.studentRepository.save(student);

        assertThat(savedStudent).isNotNull();
        assertThat(savedStudent.getId()).isNotNull();
        assertThat(updatedStudent.getFirstName().equals(savedStudent.getFirstName()));
    }

    @Test
    @DisplayName("Test if student is deleted correct")
    void delete_PersistStudent_WhenSuccessful(){

        Student student = StudentFactory.studentBuilderToBeSaved();
        Student savedStudent = this.studentRepository.save(student);
        this.studentRepository.delete(savedStudent);
        Optional<Student> studentOptional = this.studentRepository.findById(savedStudent.getId());

        assertThat(studentOptional.isEmpty()).isTrue();
    }


    @Test
    @DisplayName("Test if student is find by Email correctly")
    void findStudentByApplicationUserEmail(){
        Student student = StudentFactory.studentWithUser();
        this.studentRepository.save(student);
        Optional<Student> searchedStudent = this.studentRepository.findByApplicationUserEmail(student.getApplicationUser().getEmail());

        assertThat(searchedStudent).isNotNull();
        assertThat(searchedStudent.get().getId()).isEqualTo(student.getId());
    }

}