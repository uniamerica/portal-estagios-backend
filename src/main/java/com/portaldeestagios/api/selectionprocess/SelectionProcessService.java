package com.portaldeestagios.api.selectionprocess;

import com.portaldeestagios.api.email.SendEmailService;
import com.portaldeestagios.api.exception.SelectionProcessNotFoundException;
import com.portaldeestagios.api.student.Student;
import com.portaldeestagios.api.student.StudentRepository;
import com.portaldeestagios.api.student.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.portaldeestagios.api.email.SendEmailService.Mensagem;

@Service
@AllArgsConstructor
public class SelectionProcessService {

  private final StudentRepository studentRepository;
  private final StudentService studentService;
  private final SelectionProcessRepository selectionProcessRepository;
  private final SendEmailService sendEmail;

  @Transactional
  public SelectionProcessEntity register(Long selectionProcessId, String email) {

    SelectionProcessEntity selectionProcess = find(selectionProcessId);

    if(selectionProcess.getStatus().equals(SelectionProcessStatusEnum.ABERTO)) {
      Student student = studentService.findByEmail(email);
      student.checkStudentIsEmpty();
      boolean studentRegistered = selectionProcess.getStudentList()
              .stream()
              .anyMatch(x -> x.getId().equals(student.getId()));
      if(studentRegistered) {
        throw new DataIntegrityViolationException("Usuário já registrado");
      }
      selectionProcess.studentList.add(student);

      var mensagem = Mensagem.builder()
              .assunto(student.getFirstName() + " sua inscrição foi confirmada!")
              .corpo("A sua inscrição no processo seletivo "
                      + selectionProcess.getTitle() + "</strong> foi confirmada com sucesso!")
              .destinatario(student.getApplicationUser().getEmail())
              .build();
     // sendEmail.send(mensagem);

      return selectionProcessRepository.save(selectionProcess);
    }
    return null;
  }

  @Transactional
  public SelectionProcessEntity leave(Long selectionProcessId, String email) {

    Student student = studentRepository.findByApplicationUserEmail(email)
            .orElseThrow(() -> new IllegalStateException("Cliente não encontrado"));

    SelectionProcessEntity selectionProcess = selectionProcessRepository.findById(selectionProcessId)
            .orElseThrow(() -> new IllegalStateException("Cliente não encontrado"));

    selectionProcess.studentList.remove(student);

    return selectionProcessRepository.save(selectionProcess);
  }

  private SelectionProcessEntity find(Long selectionProcessId) {
    return selectionProcessRepository.findByIdCustom(selectionProcessId)
            .orElseThrow(() -> new SelectionProcessNotFoundException(selectionProcessId));
  }

  public SelectionProcessEntity findOrFail(Long selectionProcessId) {
    return selectionProcessRepository.findById(selectionProcessId)
            .orElseThrow(() -> new SelectionProcessNotFoundException(selectionProcessId));
  }
}
