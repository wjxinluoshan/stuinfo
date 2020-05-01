package com.yzt.manager.stuinfo.mapperimpl;

import com.yzt.manager.stuinfo.mapper.LoginMapper;
import com.yzt.manager.stuinfo.pojo.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginMapperImple implements LoginMapper {

  @Autowired
  private LoginMapper loginMapper;

  @Override
  public Integer login(String userNumber, String password, String isTeacher) {
    return loginMapper.login(userNumber, password, isTeacher);
  }

  @Override
  public Integer adminLogin(String accountName, String password) {
    return loginMapper.adminLogin(accountName, password);
  }

  public Integer login(Login login) {
    //得到登录时的标志位
    String mark = login.getMark();
    //判断当卡登录者得身份
    switch (mark) {
      //学生
      case "0":
        return login(login.getUserNumber(), login.getPassword(), null);
      //老师
      case "1":
        return login(login.getUserNumber(), login.getPassword(), mark);
      //admin
      case "2":
        return adminLogin(login.getUserNumber(), login.getPassword());
    }
    return null;
  }
}

