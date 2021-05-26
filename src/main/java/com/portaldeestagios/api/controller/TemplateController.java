package com.portaldeestagios.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TemplateController {

  @GetMapping("login")
  public String getLoginView(Model model){
    return "login";
  }

  @GetMapping("courses")
  public String getCourses() {
    return "courses";
  }
}
