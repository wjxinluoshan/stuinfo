package com.yzt.manager.stuinfo.mapper;


import com.yzt.manager.stuinfo.pojo.StuInfo;
import com.yzt.manager.stuinfo.pojo.StuInfoCondition;
import com.yzt.manager.stuinfo.pojo.TeacherInfo;
import com.yzt.manager.stuinfo.pojo.TeacherInfoCondition;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
      "<if test='id!=null'> and id=#{id}</if>",
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
      "<if test='stuInfo.stuNumber !=null'>stuNumber=#{stuInfo.stuNumber}</if>",
      "<if test='stuInfo.stuClass !=null'>,stuClass=#{stuInfo.stuClass}</if>",
      "<if test='stuInfo.name !=null'>,name=#{stuInfo.name}</if>",
      "<if test='stuInfo.password !=null'>,password=#{stuInfo.password}</if>",
      "<if test='stuInfo.college !=null'>,college=#{stuInfo.college}</if>",
      "<if test='stuInfo.depart !=null'>,depart=#{stuInfo.depart}</if>",
      "<if test='stuInfo.phone !=null'>,phone=#{stuInfo.phone}</if>",
      "<if test='stuInfo.inSchoolYear !=null'>,inSchoolYear=#{stuInfo.inSchoolYear}</if>",
      "</trim>",
      " where stuNumber=#{oldStuNumber}",
      "</script>"
  })
  Integer updateStuInfo(StuInfo stuInfo, String oldStuNumber);


  @Delete({
      "<script>",
      "delete from tb_stu",
      "<where>",
      "<if test='stuNumber != null'>stuNumber=#{stuNumber}</if>",
      "<if test='inSchoolYear!=null'>and inSchoolYear=#{inSchoolYear}</if>",
      "<if test='id!=null'>and id=#{id}</if>"
      , "</where>"
      , "</script>"
  })
  Integer deleteStuInfo(String stuNumber, String inSchoolYear, Integer id);

  @Update("update tb_upload_file_privilege set openStuPrize=#{tag}")
  Integer openStuInfoPrize(Integer tag);

  @Select("select openStuPrize from tb_upload_file_privilege")
  Integer selectStuInfoPrize();

  /**
   * 老师信息
   */
  @Insert({
      "<script>",
      "insert into tb_teacher(",
      "<trim prefix='' prefixOverrides=','>",
      "<if test='teacherNumber !=null'>teacherNumber</if>",
      "<if test='teacherClass !=null'>,teacherClass</if>",
      "<if test='name !=null'>,name</if>",
      "<if test='password !=null'>,password</if>",
      "<if test='college !=null'>,college</if>",
      "<if test='depart !=null'>,depart</if>",
      "<if test='phone !=null'>,phone</if>",
      "<if test='inSchoolYear !=null'>,inSchoolYear</if>",
      "</trim>",
      ") values(",
      "<trim prefix='' prefixOverrides=','>",
      "<if test='teacherNumber !=null'>#{teacherNumber}</if>",
      "<if test='teacherClass !=null'>,#{teacherClass}</if>",
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
      "<if test='id!=null'> and id=#{id}</if>",
      "<if test='teacherClass!=null'> and teacherClass=#{teacherClass}</if>",
      "<if test='college!=null'> and college=#{college}</if>",
      "<if test='depart!=null'> and depart=#{depart}</if>",
      "<if test='inSchoolYear!=null'> and inSchoolYear=#{inSchoolYear}</if>",
      "</where>",
      "</script>"
  })
  List<TeacherInfo> selectTeacher(TeacherInfoCondition teacherInfoCondition);

  @Update({
      "<script>",
      "update tb_teacher",
      "<trim prefix='set' prefixOverrides=','>",
      "<if test='teacherInfo.teacherNumber !=null'>teacherNumber=#{teacherInfo.teacherNumber}</if>",
      "<if test='teacherInfo.teacherClass !=null'>,teacherClass=#{teacherInfo.teacherClass}</if>",
      "<if test='teacherInfo.name !=null'>,name=#{teacherInfo.name}</if>",
      "<if test='teacherInfo.password !=null'>,password=#{teacherInfo.password}</if>",
      "<if test='teacherInfo.college !=null'>,college=#{teacherInfo.college}</if>",
      "<if test='teacherInfo.depart !=null'>,depart=#{teacherInfo.depart}</if>",
      "<if test='teacherInfo.phone !=null'>,phone=#{teacherInfo.phone}</if>",
      "<if test='teacherInfo.inSchoolYear !=null'>,phone=#{teacherInfo.inSchoolYear}</if>",
      "</trim>",
      " where teacherNumber=#{oldTeacherNumber}",
      "</script>"
  })
  Integer updateTeacherInfo(TeacherInfo teacherInfo, String oldTeacherNumber);

  @Delete({
      "<script>",
      "delete from tb_teacher",
      "<where>",
      "<if test='teacherNumber != null'>teacherNumber=#{teacherNumber}</if>",
      "<if test='id != null'>id=#{id}</if>",
      "</where>",
      "</script>"
  })
  Integer deleteTeacherInfo(String teacherNumber, Integer id);

  @Update("update tb_upload_file_privilege set openTeacherPrize=#{tag}")
  Integer openTeacherInfoPrize(Integer tag);

  @Select("select openTeacherPrize from tb_upload_file_privilege")
  Integer selectTeacherInfoPrize();

}
