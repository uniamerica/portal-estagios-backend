package com.portaldeestagios.api.student;

import com.portaldeestagios.api.user.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
  Optional<Student> findByApplicationUserEmail(String email);
}
