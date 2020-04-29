package com.yzt.manager.stuinfo.controller;


import com.yzt.manager.stuinfo.mapperimpl.StuAndTeacInfoMapperImple;
import com.yzt.manager.stuinfo.pojo.StuInfo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {

  @Autowired
  private StuAndTeacInfoMapperImple stuAndTeacInfoMapperImple;

  @GetMapping("/t")
  @ResponseBody
  public String test(StuInfo stuInfo) {
    System.out.println(stuInfo);
    return "test";
  }

  @GetMapping("/qs")
  @ResponseBody
  public List<StuInfo> query(){
   return stuAndTeacInfoMapperImple.selectStu(null);
  }

}
