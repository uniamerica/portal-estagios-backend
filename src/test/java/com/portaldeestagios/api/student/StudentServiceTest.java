package com.portaldeestagios.api.student;

import com.portaldeestagios.api.dtos.assembler.student.StudentDtoDisassembler;
import com.portaldeestagios.api.dtos.inputDto.student.StudentInput;
import com.portaldeestagios.api.exception.UserNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@DisplayName("Student Service test")
@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    private Student student;

    @InjectMocks
    private StudentService studentService;

    @InjectMocks
    private StudentDtoDisassembler studentDtoDisassembler;

    @Mock
    private StudentRepository studentRepository;

    @Test
    @DisplayName("must find a student when a given email is provided")
    void mustFindStudentByEmailSuccess(){
        Student student = StudentFactory.studentWithUser();
        when(studentRepository.findByApplicationUserEmail(student.getApplicationUser().getEmail()))
                .thenReturn(Optional.ofNullable(student));

        Student student1 = studentService.findByEmail(student.getApplicationUser().getEmail());

        assertThat(student1.getId()).isEqualTo(student.getId());
        verify(studentRepository, times(1)).findByApplicationUserEmail(student.getApplicationUser().getEmail());
    }

    @Test
    @DisplayName("must update a student")
    void mustUpdateStudent(){
        Student student = StudentFactory.studentWithUser();
        when(studentRepository.findByApplicationUserEmail(student.getApplicationUser().getEmail()))
                .thenReturn(Optional.ofNullable(student));

        Student oldStudent = studentService.findByEmail(student.getApplicationUser().getEmail());
        StudentInput newStudent = StudentFactory.studentBuilderToBeUpdate();

        Student studentUpdated = StudentFactory.studentBuilderUpdated();
        when(studentRepository.save(oldStudent)).thenReturn(studentUpdated);

        oldStudent.setFirstName(newStudent.getFirstName());
        oldStudent.setLastName(newStudent.getLastName());
        oldStudent.setAge(newStudent.getAge());
        oldStudent.setPhoto(newStudent.getPhoto());

        Student studentSaved = studentRepository.save(oldStudent);

        assertThat(studentSaved.getFirstName()).isEqualTo(newStudent.getFirstName());
        assertThat(studentSaved.getLastName()).isEqualTo(newStudent.getLastName());
        assertThat(studentSaved.getAge()).isEqualTo(newStudent.getAge());
        assertThat(studentSaved.getPhoto()).isEqualTo(newStudent.getPhoto());
        assertThat(studentSaved.getId()).isEqualTo(oldStudent.getId());
    }

    @Test
    @DisplayName("must find a student or fail, when given a given id is provided")
    void mustFindStudentByIdOrFail(){
        Student student = StudentFactory.studentBuildDefault();
        when(studentRepository.findById(student.getId()))
                .thenReturn(Optional.ofNullable(student));
        when(studentRepository.findById(2L))
                .thenThrow(new UserNotFoundException("Usuário não encontrado"));

        Student student1 = studentService.findByIdOrFail(student.getId());

        assertThat(student1.getId()).isEqualTo(student.getId());
        assertThrows(UserNotFoundException.class, () -> studentService.findByIdOrFail(2L));
        verify(studentRepository, times(1)).findById(student.getId());
        verify(studentRepository, times(1)).findById(2L);
    }


}
