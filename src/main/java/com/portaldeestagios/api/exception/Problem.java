package com.portaldeestagios.api.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class Problem {
  private final OffsetDateTime timestamp;
  private final Integer status;
  private final String type;
  private final String title;
  private final String detail;
  private final String userMessage;
  private final List<Object> objects;

  @Getter
  @Builder
  public static class Object {
    private final String name;
    private final String userMessage;
  }
}
