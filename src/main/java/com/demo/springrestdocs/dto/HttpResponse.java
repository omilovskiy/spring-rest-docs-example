package com.demo.springrestdocs.dto;

import static org.springframework.http.HttpStatus.OK;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HttpResponse {

  private LocalDateTime timestamp;

  @JsonInclude(Include.NON_NULL)
  private String error;

  @JsonInclude(Include.NON_NULL)
  private String message;

  public static HttpResponse ok(String msg) {
    HttpResponse response = new HttpResponse();

    response.setMessage(msg);
    response.setTimestamp(LocalDateTime.now());
    return response;
  }

  public static HttpResponse error(String errorMsg) {
    HttpResponse response = new HttpResponse();

    response.setError(errorMsg);
    response.setTimestamp(LocalDateTime.now());
    return response;
  }
}
