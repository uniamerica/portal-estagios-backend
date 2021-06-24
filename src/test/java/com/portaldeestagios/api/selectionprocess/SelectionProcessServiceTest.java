package com.portaldeestagios.api.selectionprocess;

import com.portaldeestagios.api.email.SendEmailService;
import com.portaldeestagios.api.exception.SelectionProcessNotFoundException;
import com.portaldeestagios.api.student.Student;
import com.portaldeestagios.api.student.StudentRepository;
import com.portaldeestagios.api.user.ApplicationUser;
import com.portaldeestagios.api.user.ApplicationUserRole;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {SelectionProcessService.class})
@ExtendWith(SpringExtension.class)
public class SelectionProcessServiceTest {
  @MockBean
  private SelectionProcessRepository selectionProcessRepository;

  @Autowired
  private SelectionProcessService selectionProcessService;

  @MockBean
  private SendEmailService sendEmailService;

  @MockBean
  private StudentRepository studentRepository;

  @Test
  public void findOrFailSelectionProcessWillSucceed_WhenSelectionProcessWasFound() {
    SelectionProcessEntity selectionProcessEntity =
            new SelectionProcessEntity(123L, "Dr", SelectionProcessStatusEnum.ABERTO, new HashSet<Student>());

    when(selectionProcessRepository.findById(123L)).thenReturn(Optional.of(selectionProcessEntity));
    assertSame(selectionProcessEntity, selectionProcessService.findOrFail(123L));
    verify(selectionProcessRepository).findById((Long) any());
  }

  @Test
  public void findOrFailWillFail_WhenSelectionProcessNotFound() {
    given(selectionProcessRepository.findById(any())).willReturn(Optional.empty());
    assertThatThrownBy(() -> selectionProcessService.findOrFail(123L))
            .isInstanceOf(SelectionProcessNotFoundException.class);
    verify(selectionProcessRepository).findById(any());
  }
}

