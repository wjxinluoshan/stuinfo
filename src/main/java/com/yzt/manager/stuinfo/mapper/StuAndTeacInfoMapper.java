package com.yzt.manager.stuinfo.mapper;


import com.yzt.manager.stuinfo.pojo.StuInfo;
import com.yzt.manager.stuinfo.pojo.StuInfoCondition;
import com.yzt.manager.stuinfo.pojo.TeacherInfo;
import com.yzt.manager.stuinfo.pojo.TeacherInfoCondition;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface StuAndTeacInfoMapper {

  /*
   * 学生信息
   */
  @Insert({
      "<script>",
      "insert into tb_stu(",
      "<trim prefix='' prefixOverrides=','>",
      "<if test='stuNumber !=null'>stuNumber</if>",
      "<if test='stuClass !=null'>,stuClass</if>",
      "<if test='name !=null'>,name</if>",
      "<if test='password !=null'>,password</if>",
      "<if test='college !=null'>,college</if>",
      "<if test='depart !=null'>,depart</if>",
      "<if test='phone !=null'>,phone</if>",
      "<if test='inSchoolYear !=null'>,inSchoolYear</if>",
      "</trim>",
      ") values(",
      "<trim prefix='' prefixOverrides=','>",
      "<if test='stuNumber !=null'>#{stuNumber}</if>",
      "<if test='stuClass !=null'>,#{stuClass}</if>",
      "<if test='name !=null'>,#{name}</if>",
      "<if test='password !=null'>,#{password}</if>",
      "<if test='college !=null'>,#{college}</if>",
      "<if test='depart !=null'>,#{depart}</if>",
      "<if test='phone !=null'>,#{phone}</if>",
      "<if test='inSchoolYear !=null'>,#{inSchoolYear}</if>",
      "</trim>",
      ")",
      "</script>"
  })
  Integer insertStu(StuInfo stuInfo);

  @Select({
      "<script>",
      "select * from tb_stu",
      "<where>",
      "<if test='stuNumber!=null'>stuNumber=#{stuNumber}</if>",
      "<if test='stuClass!=null'> and stuClass=#{stuClass}</if>",
      "<if test='college!=null'> and college=#{college}</if>",
      "<if test='depart!=null'> and depart=#{depart}</if>",
      "<if test='inSchoolYear!=null'> and inSchoolYear=#{inSchoolYear}</if>",
      "</where>",
      "</script>"
  })
  List<StuInfo> selectStu(StuInfoCondition stuInfoCondition);


  @Update({
      "<script>",
      "update tb_stu",
      "<trim prefix='set' prefixOverrides=','>",
      "<if test='stuClass !=null'>stuClass=#{stuClass}</if>",
      "<if test='name !=null'>,name=#{name}</if>",
      "<if test='password !=null'>,password=#{password}</if>",
      "<if test='college !=null'>,college=#{college}</if>",
      "<if test='depart !=null'>,depart=#{depart}</if>",
      "<if test='phone !=null'>,phone=#{phone}</if>",
      "</trim>",
      " where stuNumber=#{stuNumber}",
      "</script>"
  })
  Integer updateStuInfo(StuInfo stuInfo);


  @Delete({
      "<script>",
      "delete from tb_stu",
      "<where>",
      "<if test='stuNumber != null'>stuNumber=#{stuNumber}</if>",
      "<if test='inSchoolYear!=null'>and inSchoolYear=#{inSchoolYear}</if>"
      , "</where>"
      , "</script>"
  })
  Integer deleteStuInfo(String stuNumber, String inSchoolYear);

  /**
   * 老师信息
   */
  @Insert({
      "<script>",
      "insert into tb_teacher(",
      "<trim prefix='' prefixOverrides=','>",
      "<if test='teacherNumber !=null'>teacherNumber</if>",
      "<if test='name !=null'>,name</if>",
      "<if test='password !=null'>,password</if>",
      "<if test='college !=null'>,college</if>",
      "<if test='depart !=null'>,depart</if>",
      "<if test='phone !=null'>,phone</if>",
      "<if test='inSchoolYear !=null'>,inSchoolYear</if>",
      "</trim>",
      ") values(",
      "<trim prefix='' prefixOverrides=','>",
      "<if test='teacherNumber !=null'>{teacherNumber}</if>",
      "<if test='name !=null'>,#{name}</if>",
      "<if test='password !=null'>,#{password}</if>",
      "<if test='college !=null'>,#{college}</if>",
      "<if test='depart !=null'>,#{depart}</if>",
      "<if test='phone !=null'>,#{phone}</if>",
      "<if test='inSchoolYear !=null'>,#{inSchoolYear}</if>",
      "</trim>",
      ")",
      "</script>"
  })
  Integer insertTeacher(TeacherInfo teacherInfo);

  @Select({
      "<script>",
      "select * from tb_teacher",
      "<where>",
      "<if test='teacherNumber!=null'>teacherNumber=#{teacherNumber}</if>",
      "<if test='college!=null'> and college=#{college}</if>",
      "<if test='depart!=null'> and depart=#{depart}</if>",
      "</where>",
      "</script>"
  })
  List<TeacherInfo> selectTeacher(TeacherInfoCondition teacherInfoCondition);

  @Update({
      "<script>",
      "update tb_teacher",
      "<trim prefix='set' prefixOverrides=','>",
      "<if test='name !=null'>,name=#{name}</if>",
      "<if test='password !=null'>,password=#{password}</if>",
      "<if test='college !=null'>,college=#{college}</if>",
      "<if test='depart !=null'>,depart=#{depart}</if>",
      "<if test='phone !=null'>,phone=#{phone}</if>",
      "</trim>",
      " where teacherNumber=#{teacherNumber}",
      "</script>"
  })
  Integer updateTeacherInfo(TeacherInfo teacherInfo);

  @Delete({
      "<script>",
      "delete from tb_teacher",
      "<where>",
      "<if test='teacherNumber != null'>teacherNumber=#{teacherNumber}</if>",
      "</where>",
      "</script>"
  })
  Integer deleteTeacherInfo(String teacherNumber);
}
