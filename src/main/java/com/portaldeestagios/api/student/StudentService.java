package com.portaldeestagios.api.student;

import com.portaldeestagios.api.dtos.assembler.student.StudentDtoDisassembler;
import com.portaldeestagios.api.dtos.inputDto.student.StudentInput;
import com.portaldeestagios.api.exception.NegocioException;
import com.portaldeestagios.api.exception.UserNotFoundException;
import com.portaldeestagios.api.user.ApplicationUser;
import com.portaldeestagios.api.user.ApplicationUserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class StudentService {

  private final ApplicationUserRepository applicationUserRepository;
  private final StudentRepository studentRepository;
  private final StudentDtoDisassembler studentDtoDisassembler;

  @Autowired
  public StudentService(StudentRepository studentRepository, ApplicationUserRepository applicationUserRepository, StudentDtoDisassembler studentDtoDisassembler) {
    this.studentRepository = studentRepository;
    this.applicationUserRepository = applicationUserRepository;
    this.studentDtoDisassembler = studentDtoDisassembler;
  }

  @Transactional
  public Student update(StudentInput studentInput, String email) {

    Student oldStudent = findByEmail(email);
    studentDtoDisassembler.copyToEntity(studentInput, oldStudent);

    return studentRepository.save(oldStudent);
  }

  public Student findByEmail(String email) {
    return studentRepository.findByApplicationUserEmail(email)
            .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
  }

  public Student findByIdOrFail(Long studentId) {
    return studentRepository.findById(studentId)
            .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
  }
}
