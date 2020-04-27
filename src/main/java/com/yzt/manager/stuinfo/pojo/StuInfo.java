package com.yzt.manager.stuinfo.pojo;

public class StuInfo {

  private String stuNumber=null;
  private String stuClass=null;
  private String name=null;
  private String password=null;

  private Integer college=null;
  private Integer depart=null;
  private String phone=null;

  public String getStuClass() {
    return stuClass;
  }

  public void setStuClass(String stuClass) {
    this.stuClass = stuClass;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
  public String getStuNumber() {
    return stuNumber;
  }

  public void setStuNumber(String stuNumber) {
    this.stuNumber = stuNumber;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getCollege() {
    return college;
  }

  public void setCollege(int college) {
    this.college = college;
  }

  public int getDepart() {
    return depart;
  }

  public void setDepart(int depart) {
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
    return "StuInfo{" +
        "stuNumber='" + stuNumber + '\'' +
        ", stuClass='" + stuClass + '\'' +
        ", name='" + name + '\'' +
        ", password='" + password + '\'' +
        ", college=" + college +
        ", depart=" + depart +
        ", phone='" + phone + '\'' +
        '}';
  }
}
