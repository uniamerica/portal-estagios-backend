package com.portaldeestagios.api.student;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.portaldeestagios.api.selectionprocess.SelectionProcessEntity;
import com.portaldeestagios.api.user.ApplicationUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String firstName;
  private String lastName;
  private Byte age;

  @OneToOne
  private ApplicationUser applicationUser;

  @ManyToMany(mappedBy = "studentList", fetch = FetchType.EAGER)
  private Set<SelectionProcessEntity> selectionProcessEntityList = new HashSet<>();

}
