package com.yzt.manager.stuinfo.pojo;

public class TeacherInfo {

  private String teacherNumber = null;
  private String name = null;
  private String password = null;

  private Integer college = null;
  private Integer depart = null;
  private String phone = null;

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getTeacherNumber() {
    return teacherNumber;
  }

  public void setTeacherNumber(String teacherNumber) {
    this.teacherNumber = teacherNumber;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getCollege() {
    return college;
  }

  public void setCollege(Integer college) {
    this.college = college;
  }

  public Integer getDepart() {
    return depart;
  }

  public void setDepart(Integer depart) {
    this.depart = depart;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }
}
