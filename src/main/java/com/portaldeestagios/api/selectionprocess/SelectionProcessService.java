package com.portaldeestagios.api.selectionprocess;

import com.portaldeestagios.api.student.Student;
import com.portaldeestagios.api.student.StudentRepository;
import com.portaldeestagios.api.user.ApplicationUser;
import com.portaldeestagios.api.user.ApplicationUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SelectionProcessService {

  private final StudentRepository studentRepository;
  private final SelectionProcessRepository selectionProcessRepository;

  public SelectionProcessEntity register(Long selectionProcessId, String email) {

    SelectionProcessEntity selectionProcess = find(selectionProcessId);

    if(selectionProcess.getStatus().equals(SelectionProcessStatusEnum.ABERTO)) {
      Student student = studentRepository.findByApplicationUserEmail(email)
              .orElseThrow(() -> new IllegalStateException("Cliente não encontrado"));
      selectionProcess.studentList.add(student);
      return selectionProcessRepository.save(selectionProcess);
    }
    return null;
  }

  public SelectionProcessEntity leave(Long selectionProcessId, String email) {

    Student student = studentRepository.findByApplicationUserEmail(email)
            .orElseThrow(() -> new IllegalStateException("Cliente não encontrado"));

    SelectionProcessEntity selectionProcess = selectionProcessRepository.findById(selectionProcessId)
            .orElseThrow(() -> new IllegalStateException("Cliente não encontrado"));

    selectionProcess.studentList.remove(student);

    return selectionProcessRepository.save(selectionProcess);
  }

  public SelectionProcessEntity open(Long selectionProcessId) {
    SelectionProcessEntity selectionProcess = find(selectionProcessId);
    if(selectionProcess.getStatus().equals(SelectionProcessStatusEnum.PENDENTE)) {
      selectionProcess.setStatus(SelectionProcessStatusEnum.ABERTO);
      return selectionProcessRepository.save(selectionProcess);
    }
    return null;

  }

  private SelectionProcessEntity find(Long selectionProcessId) {
    return selectionProcessRepository.findById(selectionProcessId)
            .orElseThrow(() -> new IllegalStateException("Ordem de serviço não encontrada"));
  }
}
