package com.yzt.manager.stuinfo.mapperimpl;

import com.yzt.manager.stuinfo.mapper.StuAndTeacInfoMapper;
import com.yzt.manager.stuinfo.pojo.StuInfo;
import com.yzt.manager.stuinfo.pojo.TeacherInfo;
import java.util.List;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StuAndTeacInfoMapperImple implements StuAndTeacInfoMapper {

  @Autowired
  private StuAndTeacInfoMapper stuAndTeacInfoMapper;

  @Override
  public Integer insertStu(StuInfo stuInfo) {
    return null;
  }


  @Override
  public List<StuInfo> selectStu(String stuNumber, String stuClass, Integer college,
      Integer depart) {
    return null;
  }

  @Override
  public Integer insertDepart(TeacherInfo teacherInfo) {
    return null;
  }

  @Override
  public List<TeacherInfo> selectTeacher(Integer collegeId, String depart) {
    return null;
  }


}
