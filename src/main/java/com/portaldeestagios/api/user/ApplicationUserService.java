package com.portaldeestagios.api.user;

import com.portaldeestagios.api.exception.UserNotFoundException;
import com.portaldeestagios.api.student.Student;
import com.portaldeestagios.api.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
  //private final ConfirmationTokenService confirmationTokenService;


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
  public void signUpUser(ApplicationUser applicationUser) {

    String encodedPassword = bCryptPasswordEncoder.encode(applicationUser.getPassword());

    applicationUser.setPassword(encodedPassword);

    var student = new Student();
    student.setApplicationUser(applicationUser);
    applicationUserRepository.save(applicationUser);
    studentRepository.save(student);


//    String token = UUID.randomUUID().toString();
//    ConfirmationToken confirmationToken = new ConfirmationToken(
//            token,
//            LocalDateTime.now(),
//            LocalDateTime.now().plusMinutes(1),
//            appUser
//    );
//
//    confirmationTokenService.saveConfirmationToken(confirmationToken);


  }

//  public int enableAppUser(String email) {
//    return applicationUserRepository.enableAppUser(email);
//  }

  public ApplicationUser findOrFail(Long userId) {
    return applicationUserRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException(userId));
  }
}


