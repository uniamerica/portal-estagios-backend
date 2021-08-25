package com.portaldeestagios.api.selectionprocess;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.portaldeestagios.api.exception.NegocioException;
import com.portaldeestagios.api.student.Student;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static com.portaldeestagios.api.selectionprocess.SelectionProcessStatusEnum.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "selection_process")
public class SelectionProcessEntity {

  @EqualsAndHashCode.Include
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String companyName;
  private String title;

  @Enumerated(EnumType.STRING)
  private SelectionProcessStatusEnum status;

  @ManyToMany
  @JoinTable(
          name = "selection_process_student",
          joinColumns = {@JoinColumn(name = "selection_process_id")},
          inverseJoinColumns = {@JoinColumn(name = "student_id")}
  )
  Set<Student> studentList = new HashSet<>();

  public SelectionProcessEntity(long l, String dr, SelectionProcessStatusEnum aberto, HashSet<Student> students) {

  }

  public void open() {
    setStatus(ABERTO);
  }
  public void analysis() {
    setStatus(ANALISE);
  }
  public void selection() {
    setStatus(SELECAO);
  }
  public void finish() {
    setStatus(FINALIZADO);
  }

  private void setStatus (SelectionProcessStatusEnum novoStatus) {
    if(getStatus().naoPodeAlterarPara(novoStatus)) {
      throw new NegocioException(
              "Status do pedido "
                      + getId()
                      + " não pode ser alterado de "
                      + getStatus().getDescription() + " para "
                      + novoStatus.getDescription());
    }
    this.status = novoStatus;
  }
}
