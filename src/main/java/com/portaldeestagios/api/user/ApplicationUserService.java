package com.portaldeestagios.api.user;

import com.portaldeestagios.api.exception.UserNotFoundException;
import com.portaldeestagios.api.student.Student;
import com.portaldeestagios.api.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ApplicationUserService implements UserDetailsService {
  private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
  private final ApplicationUserRepository applicationUserRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final StudentRepository studentRepository;


  @Autowired
  public ApplicationUserService(ApplicationUserRepository applicationUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder, StudentRepository studentRepository) {
    this.applicationUserRepository = applicationUserRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    this.studentRepository = studentRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

    return applicationUserRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("Bad Credentials"));
  }

  @Transactional
  public ApplicationUser signUpUser(ApplicationUser applicationUser) {

    String encodedPassword = bCryptPasswordEncoder.encode(applicationUser.getPassword());

    applicationUser.setPassword(encodedPassword);

    var student = new Student();
    student.setApplicationUser(applicationUser);
    applicationUserRepository.save(applicationUser);
    studentRepository.save(student);

    return applicationUser;
  }

  public ApplicationUser findOrFail(Long userId) {
    return applicationUserRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException(userId));
  }
}


