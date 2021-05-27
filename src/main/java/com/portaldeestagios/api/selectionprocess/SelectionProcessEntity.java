package com.portaldeestagios.api.selectionprocess;

import com.portaldeestagios.api.student.Student;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class SelectionProcessEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
          name = "selection_process_student",
          joinColumns = {@JoinColumn(name = "selection_process_id")},
          inverseJoinColumns = {@JoinColumn(name = "student_id")}
  )
  private Set<Student> studentList = new HashSet<>();
}
