package com.demo.springrestdocs.controllers;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import com.demo.springrestdocs.dto.HttpResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

  @ResponseStatus(OK)
  @RequestMapping("/success")
  public HttpResponse authenticationSuccess() {
    return HttpResponse.ok("Authorized");
  }

  @ResponseStatus(BAD_REQUEST)
  @RequestMapping(value = "/failure")
  public HttpResponse authenticationFailed() {
    return HttpResponse.error("Wrong credentials");
  }

  @ResponseStatus(UNAUTHORIZED)
  @RequestMapping("/unauthorized")
  public HttpResponse requestUnauthorized() {
    return HttpResponse.error("Not authorized");
  }
}
