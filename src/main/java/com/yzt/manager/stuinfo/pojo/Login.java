package com.yzt.manager.stuinfo.pojo;

public class Login {

  private String userNumber=null;
  private String password=null;

  //标识当前登录者的身份：
  // 0:学生   1：老师  2：admin（管理员）
  private String mark=null;

  public String getUserNumber() {
    return userNumber;
  }

  public void setUserNumber(String userNumber) {
    this.userNumber = userNumber;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getMark() {
    return mark;
  }

  public void setMark(String mark) {
    this.mark = mark;
  }
}
