package com.yzt.manager.stuinfo.controller;

import com.yzt.manager.stuinfo.pojo.UploadFileInfo;
import com.yzt.manager.stuinfo.tool.FileTool;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/stfc")
public class StuAndTeacherFileController {

  @Autowired
  private FileTool fileTool;

  /**
   * 上传文件
   *
   * @param uploadFileInfo
   * @return
   * @throws FileNotFoundException
   */
  @PostMapping("/uf")
  @ResponseBody
  public String uploadFile(UploadFileInfo uploadFileInfo) throws IOException {
    if (uploadFileInfo.getIsTeacher() == 0) {
      fileTool.uploadTeacherFile(uploadFileInfo);
    } else {
      fileTool.uploadStuFile(uploadFileInfo);
    }
    return "1";
  }


  @PostMapping("/qfu")
  @ResponseBody
  public List<String> queryFilesUrl(UploadFileInfo uploadFileInfo) throws IOException {
    return fileTool.readFilesUrl(uploadFileInfo);
  }

  /**
   * 删除文件
   * @param id
   * @param isTeacher
   * @param fileType
   * @param url
   * @return
   * @throws FileNotFoundException
   */
  @PostMapping("/df")
  @ResponseBody
  public String delFile(Integer id, Integer isTeacher, Integer fileType, String url)
      throws IOException {
    fileTool.delFilesUrl(id, isTeacher, fileType, url);
    return "1";
  }


}
