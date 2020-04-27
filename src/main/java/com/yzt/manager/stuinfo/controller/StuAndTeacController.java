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

@Controller
@RequestMapping("/sctc")
public class StuAndTeacController {

  @Autowired
  private FileTool fileTool;

  @Autowired
  private SchoolInfoMapperImple schoolInfoMapperImple;

  @PostMapping("/isot")
  @ResponseBody
  public String inputStudentOrTeacher(
      @RequestParam(value = "stus", required = false) StuInfo[] stuInfos,
      @RequestParam(value = "teachers", required = false) TeacherInfo[] teacherInfos,
      @RequestParam(value = "file", required = false) MultipartFile multipartFile)
      throws IOException {
    if (multipartFile == null) {

    } else {
      List<List<List<String>>> listList = fileTool.readStudentOrTeacherExcel(multipartFile);

    }
    return "1";
  }
}
