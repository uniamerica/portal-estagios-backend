package com.portaldeestagios.api.student;

import com.portaldeestagios.api.exception.NegocioException;
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
@AllArgsConstructor
@Builder
public class Student implements Serializable {

  @Id
  @EqualsAndHashCode.Include
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String firstName;

  private String lastName;

  private Byte age;

  private String photo;

  @OneToOne
  @JoinColumn(unique = true)
  private ApplicationUser applicationUser;

  @ManyToMany(mappedBy = "studentList")
  private Set<SelectionProcessEntity> selectionProcessEntityList = new HashSet<>();


  @Builder
  public Student(Long id, String firstName, String lastName, Byte age) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
  }

  public void checkStudentIsEmpty() {
    if (this.getFirstName() == null || this.getLastName() == null) {
      throw new NegocioException("Complete seu perfil");
    }
  }

}
