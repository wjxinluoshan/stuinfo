package com.yzt.manager.stuinfo.controller;

import com.yzt.manager.stuinfo.mapperimpl.SchoolInfoMapperImple;
import com.yzt.manager.stuinfo.pojo.StuInfo;
import com.yzt.manager.stuinfo.pojo.TeacherInfo;
import com.yzt.manager.stuinfo.tool.FileTool;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 录入学校信息的
 */
@Controller
@RequestMapping("/sic")
public class SchoolInfoController {

  @Autowired
  private FileTool fileTool;

  @Autowired
  private SchoolInfoMapperImple schoolInfoMapperImple;

  /*
   * 录入学院、院系
   *
   * 学院为必须参数
   */
  @PostMapping("/icandd")
  @ResponseBody
  public String inputCollegeAndDepart(String college,
      @RequestParam(required = false) String[] departs,
      @RequestParam(value = "file", required = false) MultipartFile collegeFile)
      throws Exception {
    //如果传输的是文件
    if (collegeFile != null) {
      List<List<List<String>>> listList = fileTool.readCollegeAndDepartExcel(collegeFile);
      schoolInfoMapperImple.insertCollegeAndDepart(null, null, listList);
    } else {
      schoolInfoMapperImple.insertCollegeAndDepart(college, departs, null);
    }
    return "1";
  }

}
