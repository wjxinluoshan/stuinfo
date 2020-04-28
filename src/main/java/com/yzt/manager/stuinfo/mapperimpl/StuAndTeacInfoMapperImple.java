package com.yzt.manager.stuinfo.mapperimpl;

import com.yzt.manager.stuinfo.mapper.StuAndTeacInfoMapper;
import com.yzt.manager.stuinfo.pojo.StuInfo;
import com.yzt.manager.stuinfo.pojo.StuInfoCondition;
import com.yzt.manager.stuinfo.pojo.TeacherInfo;
import com.yzt.manager.stuinfo.pojo.TeacherInfoCondition;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StuAndTeacInfoMapperImple implements StuAndTeacInfoMapper {

  @Autowired
  private StuAndTeacInfoMapper stuAndTeacInfoMapper;

  @Override
  public Integer deleteStuInfo(String stuNumber, String inSchoolYear) {
    return stuAndTeacInfoMapper.deleteStuInfo(stuNumber, inSchoolYear);
  }

  @Override
  public Integer deleteTeacherInfo(String teacherNumber) {
    return stuAndTeacInfoMapper.deleteTeacherInfo(teacherNumber);
  }

  @Override
  public Integer insertStu(StuInfo stuInfo) {
    return stuAndTeacInfoMapper.insertStu(stuInfo);
  }

  @Override
  public List<StuInfo> selectStu(StuInfoCondition stuInfoCondition) {
    return stuAndTeacInfoMapper.selectStu(stuInfoCondition);
  }

  @Override
  public Integer updateStuInfo(StuInfo stuInfo) {
    return stuAndTeacInfoMapper.updateStuInfo(stuInfo);
  }

  @Override
  public Integer insertTeacher(TeacherInfo teacherInfo) {
    return stuAndTeacInfoMapper.insertTeacher(teacherInfo);
  }

  @Override
  public List<TeacherInfo> selectTeacher(TeacherInfoCondition teacherInfoCondition) {
    return stuAndTeacInfoMapper.selectTeacher(teacherInfoCondition);
  }

  @Override
  public Integer updateTeacherInfo(TeacherInfo teacherInfo) {
    return stuAndTeacInfoMapper.updateTeacherInfo(teacherInfo);
  }

  /**
   * 插入学生和老师的数据
   */
  public void insertStuAndTeacherInfo(StuInfo[] stuInfos,
      TeacherInfo[] teacherInfos,
      List<List<List<String>>> listList, String isTeacher) {
    //文件形式的插入
    if (listList != null) {
      //遍历各个excel的sheet
      for (List<List<String>> lists : listList) {
        //遍历sheet的各个行
        for (List<String> list : lists) {
          //插入老师
          if (Integer.parseInt(isTeacher) == 0) {
            TeacherInfo teacherInfo = new TeacherInfo();
            teacherInfo.setTeacherNumber(list.get(0));
            teacherInfo.setName(list.get(1));
            teacherInfo.setPassword(list.get(2));
            teacherInfo.setCollege(Integer.parseInt(list.get(3)));
            teacherInfo.setDepart(Integer.parseInt(list.get(4)));
            teacherInfo.setPhone(list.get(5));
            //该老师信息尚未输入
            TeacherInfoCondition teacherInfoCondition = new TeacherInfoCondition();
            teacherInfoCondition.setTeacherNumber(teacherInfo.getTeacherNumber());
            if (this.selectTeacher(teacherInfoCondition).isEmpty()) {
              this.insertTeacher(teacherInfo);
            }
          }
          //插入学生
          else {
            StuInfo stuInfo = new StuInfo();
            stuInfo.setStuNumber(list.get(0));
            stuInfo.setStuClass(list.get(1));
            stuInfo.setName(list.get(2));
            stuInfo.setPassword(list.get(3));
            stuInfo.setCollege(Integer.parseInt(list.get(4)));
            stuInfo.setDepart(Integer.parseInt(list.get(5)));
            stuInfo.setPhone(list.get(6));
            stuInfo.setInSchoolYear(list.get(7));
            //该学生信息尚未输入
            StuInfoCondition stuInfoCondition = new StuInfoCondition();
            stuInfoCondition.setStuNumber(stuInfo.getStuNumber());
            if (this.selectStu(stuInfoCondition).isEmpty()) {
              this.insertStu(stuInfo);
            }
          }
        }
      }
    }
    //数据输入形式
    else {
      if (teacherInfos != null) {
        for (TeacherInfo teacherInfo : teacherInfos) {
          TeacherInfoCondition teacherInfoCondition = new TeacherInfoCondition();
          teacherInfoCondition.setTeacherNumber(teacherInfo.getTeacherNumber());
          if (this.selectTeacher(teacherInfoCondition).isEmpty()) {
            this.insertTeacher(teacherInfo);
          }
        }
      }
      if (stuInfos != null) {
        for (StuInfo stuInfo : stuInfos) {
          StuInfoCondition stuInfoCondition = new StuInfoCondition();
          stuInfoCondition.setStuNumber(stuInfo.getStuNumber());
          if (this.selectStu(stuInfoCondition).isEmpty()) {
            this.insertStu(stuInfo);
          }
        }
      }
    }
  }

}
