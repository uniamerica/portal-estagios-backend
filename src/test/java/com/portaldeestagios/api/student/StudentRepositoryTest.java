package com.portaldeestagios.api.student;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@DisplayName("Student Repository test")
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    @DisplayName("Test if student is create correct")
    void save_PersistAnime_WhenSuccessful(){

        Student student = studentBuilder();
        Student savedStudent = studentRepository.save(student);
        Assertions.assertThat(savedStudent).isNotNull();
        Assertions.assertThat(savedStudent.getId()).isNotNull();
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