package com.yzt.manager.stuinfo.pojo;

public class DepartInfo {
 private Integer collegeId=null;

  @Override
  public String toString() {
    return "DepartInfo{" +
        "collegeId=" + collegeId +
        ", depart='" + depart + '\'' +
        '}';
  }

  private String depart =null;

  public Integer getCollegeId() {
    return collegeId;
  }

  public void setCollegeId(Integer collegeId) {
    this.collegeId = collegeId;
  }

  public String getDepart() {
    return depart;
  }

  public void setDepart(String depart) {
    this.depart = depart;
  }
}
