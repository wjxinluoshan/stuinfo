package com.yzt.manager.stuinfo.pojo;

public class CollegeInfo {

  private Integer id = null;

  private String college = null;

  @Override
  public String toString() {
    return "CollegeInfo{" +
        "id=" + id +
        ", college='" + college + '\'' +
        '}';
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getCollege() {
    return college;
  }

  public void setCollege(String college) {
    this.college = college;
  }
}
