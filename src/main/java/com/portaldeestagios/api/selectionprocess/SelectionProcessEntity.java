package com.portaldeestagios.api.selectionprocess;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.portaldeestagios.api.student.Student;
import com.portaldeestagios.api.user.ApplicationUserRole;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.sql.Select;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "selection_process")
public class SelectionProcessEntity {

  @EqualsAndHashCode.Include
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;

  @JsonIgnore
  @Enumerated(EnumType.STRING)
  private SelectionProcessStatusEnum status = SelectionProcessStatusEnum.PENDENTE;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
          name = "selection_process_student",
          joinColumns = {@JoinColumn(name = "selection_process_id")},
          inverseJoinColumns = {@JoinColumn(name = "student_id")}
  )
  Set<Student> studentList = new HashSet<>();
}
