package com.portaldeestagios.api.user;

import com.portaldeestagios.api.exception.ResourceNotFoundException;
import com.portaldeestagios.api.exception.SelectionProcessNotFoundException;
import com.portaldeestagios.api.exception.UserNotFoundException;
import com.portaldeestagios.api.student.Student;
import com.portaldeestagios.api.student.StudentRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static com.portaldeestagios.api.user.ApplicationUserRole.ROLE_ADMIN;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ApplicationUserServiceTest {

    @Mock
    private ApplicationUserRepository applicationUserRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private ApplicationUserService applicationUserService;

    @Test
    public void signUp_success(){
        ApplicationUser applicationUser = new ApplicationUser("vinicius.vof@outlook.com", "1234", ROLE_ADMIN);

        Student student = new Student();

        student.setApplicationUser(applicationUser);

        Mockito.when(bCryptPasswordEncoder.encode(applicationUser.getPassword())).thenReturn("1234");
        Mockito.when(applicationUserRepository.save(applicationUser)).thenReturn(applicationUser);
        Mockito.when(studentRepository.save(student)).thenReturn(student);

        applicationUserService.signUpUser(applicationUser);

        verify(bCryptPasswordEncoder, times(1)).encode(applicationUser.getPassword());
        verify(applicationUserRepository, times(1)).save(applicationUser);
        verify(studentRepository, times(1)).save(student);
    }
    @Test
    public void loadUserByUsername_success(){
        ApplicationUser applicationUser = new ApplicationUser("vinicius.vof@outlook.com", "1234", ROLE_ADMIN);

        Mockito.when(applicationUserRepository.findByEmail("vinicius.vof@outlook.com")).thenReturn(Optional.of(applicationUser));

        applicationUserService.loadUserByUsername(applicationUser.getUsername());

        verify(applicationUserRepository, times(1)).findByEmail(applicationUser.getUsername());

    }
    @Test
    public void findOrFail_success(){
        ApplicationUser applicationUser = new ApplicationUser("vinicius.vof@outlook.com", "1234", ROLE_ADMIN);

        Mockito.when(applicationUserRepository.findById(1L)).thenReturn(Optional.of(applicationUser));

        applicationUserService.findOrFail(1L);

        verify(applicationUserRepository, times(1)).findById(1L);
    }
    @Test
    public void findOrFail_fail() throws UserNotFoundException {
        ApplicationUser applicationUser = new ApplicationUser("vinicius.vof@outlook.com", "1234", ROLE_ADMIN);

        Mockito.when(applicationUserRepository.findById(1L)).thenThrow(new UserNotFoundException(1L));

        assertThrows(ResourceNotFoundException.class, () -> applicationUserService.findOrFail(1L));
    }
    @Test
    public void loadUserByUsername_fail() throws UserNotFoundException {
        ApplicationUser applicationUser = new ApplicationUser("vinicius.vof@outlook.com", "1234", ROLE_ADMIN);

        Mockito.when(applicationUserRepository.findByEmail(applicationUser.getUsername())).thenThrow(new UsernameNotFoundException("Bad Credentials"));

        assertThrows(AuthenticationException.class, () -> applicationUserService.loadUserByUsername(applicationUser.getUsername()));
    }
}
