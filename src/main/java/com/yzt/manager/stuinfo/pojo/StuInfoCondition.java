package com.yzt.manager.stuinfo.pojo;

public class StuInfoCondition {

  /**
   * stuNumber :
   * stuClass :
   * college : 0
   * depart : 1
   */
  private Integer id=null;
  private String stuNumber=null;
  private String stuClass=null;
  private Integer college=null;
  private Integer depart=null;
  private String inSchoolYear;

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

  public String getStuNumber() {
    return stuNumber;
  }

  public void setStuNumber(String stuNumber) {
    this.stuNumber = stuNumber;
  }

  public String getStuClass() {
    return stuClass;
  }

  public void setStuClass(String stuClass) {
    this.stuClass = stuClass;
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
