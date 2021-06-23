package com.portaldeestagios.api.selectionprocess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SelectionProcessRepository extends JpaRepository<SelectionProcessEntity, Long> {


  @Query("select sp \n" +
          "from SelectionProcessEntity sp  \n" +
          "join sp.studentList sl \n" +
          "join sl.applicationUser au \n" +
          "where sp.id = :id")
  Optional<SelectionProcessEntity> findByIdCustom(Long id);
}
