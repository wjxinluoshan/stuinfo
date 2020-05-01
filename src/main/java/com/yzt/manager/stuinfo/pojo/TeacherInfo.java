package com.yzt.manager.stuinfo.pojo;

public class TeacherInfo {

  private Integer id=null;
  private String teacherNumber = null;
  private String teacherClass=null;
  private String name = null;
  private String password = null;

  private Integer college = null;
  private Integer depart = null;
  private String phone = null;
  private String inSchoolYear=null;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getInSchoolYear() {
    return inSchoolYear;
  }

  public void setInSchoolYear(String inSchoolYear) {
    this.inSchoolYear = inSchoolYear;
  }

  public String getTeacherClass() {
    return teacherClass;
  }

  public void setTeacherClass(String teacherClass) {
    this.teacherClass = teacherClass;
  }

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

  @Override
  public String toString() {
    return "TeacherInfo{" +
        "id=" + id +
        ", teacherNumber='" + teacherNumber + '\'' +
        ", teacherClass='" + teacherClass + '\'' +
        ", name='" + name + '\'' +
        ", password='" + password + '\'' +
        ", college=" + college +
        ", depart=" + depart +
        ", phone='" + phone + '\'' +
        ", inSchoolYear='" + inSchoolYear + '\'' +
        '}';
  }
}
