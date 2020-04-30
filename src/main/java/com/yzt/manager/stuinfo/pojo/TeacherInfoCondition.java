package com.yzt.manager.stuinfo.pojo;

public class TeacherInfoCondition {

  /**
   * stuNumber : stuClass : college : 0 depart : 1
   */

  private String teacherNumber=null;
  private String teacherClass=null;
  private Integer college=null;
  private Integer depart=null;

  public String getTeacherClass() {
    return teacherClass;
  }

  public void setTeacherClass(String teacherClass) {
    this.teacherClass = teacherClass;
  }

  public String getTeacherNumber() {
    return teacherNumber;
  }

  public void setTeacherNumber(String teacherNumber) {
    this.teacherNumber = teacherNumber;
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
}
