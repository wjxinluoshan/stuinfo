package com.yzt.manager.stuinfo.mapperimpl;

import com.yzt.manager.stuinfo.mapper.LoginMapper;
import com.yzt.manager.stuinfo.pojo.Login;
import org.springframework.stereotype.Service;

@Service
public class LoginMapperImple implements LoginMapper {

  @Override
  public String login(String userNumber, String password, String isTeacher) {
    return null;
  }

  public String login(Login login) {
    //得到登录时的标志位
    String mark = login.getMark();
    //判断当卡登录者得身份
    if (mark.equals("0")) {
      //学生
      return login(login.getUserNumber(), login.getPassword(), null);
    }
    //老师
    return login(login.getUserNumber(), login.getPassword(), mark);
  }
}
