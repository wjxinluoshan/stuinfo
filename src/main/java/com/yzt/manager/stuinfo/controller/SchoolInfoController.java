package com.yzt.manager.stuinfo.controller;

import com.yzt.manager.stuinfo.mapperimpl.SchoolInfoMapperImple;
import com.yzt.manager.stuinfo.pojo.CollegeInfo;
import com.yzt.manager.stuinfo.pojo.DepartInfo;
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

  /**
   * 删除
   */
  //删除学院
  @PostMapping("/delc")
  @ResponseBody
  public String delCollege(String college) {
    schoolInfoMapperImple.delCollege(college);
    return "1";
  }

  //删除系
  @PostMapping("/deld")
  @ResponseBody
  public String delDepart(@RequestParam(required = false) String depart,
      @RequestParam(required = false) Integer collegeId) {
    schoolInfoMapperImple.deleteDepart(depart,collegeId);
    return "1";
  }

  /**
   * 更新
   */
  //更新学院
  @PostMapping("/uc")
  @ResponseBody
  public String updateCollege(String college, String newCollege) {
    schoolInfoMapperImple.updateCollege(college, newCollege);
    return "1";
  }

  //更新系
  @PostMapping("/ud")
  @ResponseBody
  public String updateDepart(String depart, @RequestParam(required = false) String newDepart,
      @RequestParam(required = false) Integer newCollegeId) {
    schoolInfoMapperImple.updateDepart(depart, newDepart, newCollegeId);
    return "1";
  }

  /**
   * 查询
   */
  //查询学院
  @PostMapping("/qcs")
  @ResponseBody
  public List<CollegeInfo> queryColleges(@RequestParam(required = false) Integer id,
      @RequestParam(required = false) String college) {
    return schoolInfoMapperImple.selectCollege(id, college);
  }

  //查询系
  @PostMapping("/qds")
  @ResponseBody
  public List<DepartInfo> queryDeparts(@RequestParam(required = false) Integer collegeId,
      @RequestParam(required = false) String depart) {
    return schoolInfoMapperImple.selectDepart(collegeId, depart);
  }

}
