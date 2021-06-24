package com.portaldeestagios.api.student;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.portaldeestagios.api.selectionprocess.SelectionProcessEntity;
import com.portaldeestagios.api.user.ApplicationUser;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Student implements Serializable {

  @Id
  @EqualsAndHashCode.Include
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String firstName;
  private String lastName;
  private Byte age;

  @OneToOne
  private ApplicationUser applicationUser;

  @ManyToMany(mappedBy = "studentList", fetch = FetchType.EAGER)
  private Set<SelectionProcessEntity> selectionProcessEntityList = new HashSet<>();


  @Builder
  public Student(Long id, String firstName, String lastName, Byte age) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
  }

}
