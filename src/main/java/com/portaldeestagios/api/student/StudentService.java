package com.portaldeestagios.api.student;

import com.portaldeestagios.api.user.ApplicationUser;
import com.portaldeestagios.api.user.ApplicationUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class StudentService {

  private final ApplicationUserRepository applicationUserRepository;
  private final StudentRepository studentRepository;

  @Transactional
  public Student save(Student student, String email) {

    ApplicationUser user = applicationUserRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalStateException("Cliente não encontrado"));

    student.setApplicationUser(user);

    return studentRepository.save(student);
  }

  public Student findByToken(String email) {
    return studentRepository.findByApplicationUserEmail(email)
            .orElseThrow(() -> new IllegalStateException("Cliente não encontrado"));
  }
}
