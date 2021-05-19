package com.portaldeestagios.api.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {

  Optional<ApplicationUser> findByEmail(String email);

  @Transactional
  @Modifying
  @Query("UPDATE ApplicationUser a " +
          "SET a.isEnabled = TRUE WHERE a.email = ?1")
  int enableAppUser(String email);

}
