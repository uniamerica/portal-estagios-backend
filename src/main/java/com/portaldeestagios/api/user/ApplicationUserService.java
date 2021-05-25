package com.portaldeestagios.api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService implements UserDetailsService {
  private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
  private final ApplicationUserRepository applicationUserRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  //private final ConfirmationTokenService confirmationTokenService;


  @Autowired
  public ApplicationUserService(ApplicationUserRepository applicationUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder, MessageSource messageSource) {
    this.applicationUserRepository = applicationUserRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  @Value("${spring.security.user.name}")
  private String adminUserName;

  @Value("${spring.security.user.password}")
  private String adminPassword;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//    if(email.equals(adminUserName)) {
//      return ApplicationUser.builder()
//              .email(adminUserName)
//              .password(bCryptPasswordEncoder.encode(adminPassword))
//              .applicationUserRole(ApplicationUserRole.ROLE_ADMIN)
//              .isAccountNonExpired(true)
//              .isCredentialsNonExpired(true)
//              .isEnabled(true)
//              .isAccountNonLocked(true)
//              .build();
//    }

    return applicationUserRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("Bad Credentials"));
  }

  public String signUpUser(ApplicationUser applicationUser) {
    boolean userExists = applicationUserRepository.findByEmail(applicationUser.getEmail()).isPresent();

    if (userExists) {
      throw new IllegalStateException("Email already taken");
    }

    String encodedPassword = bCryptPasswordEncoder.encode(applicationUser.getPassword());

    applicationUser.setPassword(encodedPassword);

    applicationUserRepository.save(applicationUser);

//    String token = UUID.randomUUID().toString();
//    ConfirmationToken confirmationToken = new ConfirmationToken(
//            token,
//            LocalDateTime.now(),
//            LocalDateTime.now().plusMinutes(1),
//            appUser
//    );
//
//    confirmationTokenService.saveConfirmationToken(confirmationToken);


    return null;
  }

  public int enableAppUser(String email) {
    return applicationUserRepository.enableAppUser(email);
  }
}


