package com.yzt.manager.stuinfo.controller;

import com.yzt.manager.stuinfo.mapperimpl.LoginMapperImple;
import com.yzt.manager.stuinfo.pojo.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/login")
public class LoginController {

  @Autowired
  private LoginMapperImple loginMapperImple;

  /**
   * 登录
   */
  @PostMapping("/lsort")
  @ResponseBody
  public String login(Login login) {
    return loginMapperImple.login(login);
  }

}
