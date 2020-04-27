package com.yzt.manager.stuinfo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {

  @GetMapping("/t")
  @ResponseBody
  public String test() {
    return "test";
  }

}
