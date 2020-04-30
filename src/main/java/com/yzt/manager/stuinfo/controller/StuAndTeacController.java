package com.yzt.manager.stuinfo.controller;


import com.yzt.manager.stuinfo.mapper.StuAndTeacInfoMapper;
import com.yzt.manager.stuinfo.mapperimpl.SchoolInfoMapperImple;
import com.yzt.manager.stuinfo.mapperimpl.StuAndTeacInfoMapperImple;
import com.yzt.manager.stuinfo.pojo.StuInfo;
import com.yzt.manager.stuinfo.pojo.StuInfoCondition;
import com.yzt.manager.stuinfo.pojo.TeacherInfo;
import com.yzt.manager.stuinfo.pojo.TeacherInfoCondition;
import com.yzt.manager.stuinfo.tool.FileTool;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;


/**
 * 学生和老师的信息操作接口
 */
@Controller
@RequestMapping("/sctc")
public class StuAndTeacController {

  @Autowired
  private FileTool fileTool;

  @Autowired
  private StuAndTeacInfoMapperImple stuAndTeacInfoMapperImple;


  /**
   * 插入学生信息
   *
   * @param stuInfos
   * @param teacherInfos
   * @param multipartFile
   * @param isTeacher 0:教师
   * @return
   * @throws IOException
   */
  @PostMapping("/isot")
  @ResponseBody
  public String inputStudentOrTeacher(
      @RequestParam(value = "stus", required = false) StuInfo[] stuInfos,
      @RequestParam(value = "teachers", required = false) TeacherInfo[] teacherInfos,
      @RequestParam(value = "file", required = false) MultipartFile multipartFile, Integer isTeacher)
      throws Exception {
    if (multipartFile == null) {
      stuAndTeacInfoMapperImple.insertStuAndTeacherInfo(stuInfos, teacherInfos, null, isTeacher);
    } else {
      List<List<List<String>>> listList = fileTool.readStudentOrTeacherExcel(multipartFile);
      stuAndTeacInfoMapperImple.insertStuAndTeacherInfo(null, null, listList, isTeacher);
    }
    return "1";
  }

  /**
   * 查询信息
   */
  //查询学生信息
  @PostMapping("/qsis")
  @ResponseBody
  public List<StuInfo> queryStuInfos(StuInfoCondition stuInfoCondition) {
    return stuAndTeacInfoMapperImple.selectStu(stuInfoCondition);
  }

  //查询老师信息t
  @PostMapping("/qis")
  @ResponseBody
  public List<TeacherInfo> queryTeacherInfos(TeacherInfoCondition teacherInfoCondition) {
    return stuAndTeacInfoMapperImple.selectTeacher(teacherInfoCondition);
  }

  /**
   * 修改信息
   */
  //修改学生信息
  @PostMapping("/usi")
  @ResponseBody
  public String updateStuInfo(StuInfo stuInfo) {
    stuAndTeacInfoMapperImple.updateStuInfo(stuInfo);
    return "1";
  }

  //修改老师信息
  @PostMapping("/uti")
  @ResponseBody
  public String updateTeacherInfo(TeacherInfo teacherInfo) {
    stuAndTeacInfoMapperImple.updateTeacherInfo(teacherInfo);
    return "1";
  }

  /**
   * 删除信息
   */

  //删除学生信息
  @PostMapping("/delsi")
  @ResponseBody
  public String delStuInfo(
      @RequestParam(required = false) String stuNumber,
      @RequestParam(required = false) String inSchoolYear
  ) {
    stuAndTeacInfoMapperImple.deleteStuInfo(stuNumber, inSchoolYear);
    return "1";
  }

  //删除老师信息
  @PostMapping("/delti")
  @ResponseBody
  public String delTeacherInfo(@RequestParam(required = false) String teacherNumber) {
    stuAndTeacInfoMapperImple.deleteTeacherInfo(teacherNumber);
    return "1";
  }

}
