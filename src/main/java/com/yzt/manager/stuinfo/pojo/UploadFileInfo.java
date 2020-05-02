package com.yzt.manager.stuinfo.pojo;

import com.yzt.manager.stuinfo.tool.FileTool;
import org.springframework.web.multipart.MultipartFile;

public class UploadFileInfo {

  private MultipartFile[] multipartFiles = null;
  /**
   * 上传类型 0：获奖文件 1：个人简介 2：成绩 3：实习经历
   */
  private Integer stuUploadType = null;

  /**
   * 上传类型 0：获奖文件 1：个人简介 2：教学计划 3：年度总结
   */
  private Integer teacherUploadType=null;

  private Integer id = null;

  /**
   * 0：老师
   * 1：学生
   */
  private Integer isTeacher=0;

  public Integer getTeacherUploadType() {
    return teacherUploadType;
  }

  public void setTeacherUploadType(Integer teacherUploadType) {
    this.teacherUploadType = teacherUploadType;
  }

  public Integer getIsTeacher() {
    return isTeacher;
  }

  public void setIsTeacher(Integer isTeacher) {
    this.isTeacher = isTeacher;
  }

  public Integer getStuUploadType() {
    return stuUploadType;
  }

  public void setStuUploadType(Integer stuUploadType) {
    this.stuUploadType = stuUploadType;
  }

  public MultipartFile[] getMultipartFiles() {
    return multipartFiles;
  }

  public void setMultipartFiles(MultipartFile[] multipartFiles) {
    this.multipartFiles = multipartFiles;
  }



  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
}
