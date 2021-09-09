package com.portaldeestagios.api.selectionprocess;

import com.portaldeestagios.api.email.SendEmailService;
import com.portaldeestagios.api.exception.SelectionProcessNotFoundException;
import com.portaldeestagios.api.student.StudentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SelectionProcessServiceTest {
  @InjectMocks
  private SelectionProcessService selectionProcessService;

  @Mock
  private SelectionProcessRepository selectionProcessRepository;

  public SelectionProcessEntity selectionProcessEntityFactory(){
    SelectionProcessEntity selectionProcessEntity = SelectionProcessEntity.builder()
            .id(1L)
            .status(SelectionProcessStatusEnum.ABERTO)
            .build();

    return selectionProcessEntity;
  }


  @Test
  @DisplayName("must find one selection process by given id")
  void shouldFindOneSelectionProcessById(){
    SelectionProcessEntity selectionProcessEntity = selectionProcessEntityFactory();
    Long id = 1L;

    when(selectionProcessRepository.findByIdCustom(id)).thenReturn(Optional.of(selectionProcessEntity));

    assertThat(selectionProcessService.find(1L).equals(selectionProcessEntity));
    assertThat(selectionProcessService.find(1L)).isNotNull();
  }

  @Test
  @DisplayName("must throw an exception when selection process not found")
  void shouldThrowExceptionWhenFindOneSelectionProcessById(){
    Long id = 1L;

    when(selectionProcessRepository.findByIdCustom(id)).thenThrow(new SelectionProcessNotFoundException(id));

    assertThrows(SelectionProcessNotFoundException.class, ()-> selectionProcessService.find(id));
  }



}

