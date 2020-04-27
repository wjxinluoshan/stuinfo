package com.yzt.manager.stuinfo.mapper;


import com.yzt.manager.stuinfo.pojo.StuInfo;
import com.yzt.manager.stuinfo.pojo.TeacherInfo;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
      "</trim>",
      ")",
      "</script>"
  })
  Integer insertStu(StuInfo stuInfo);

  @Select({
      "<script>",
      "select * from tb_stu","",
      "</script>"
  })
  List<StuInfo> selectStu(String stuNumber, String stuClass, Integer college, Integer depart);

  /*
   * 系
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
      "</trim>",
      ") values(",
      "<trim prefix='' prefixOverrides=','>",
      "<if test='teacherNumber !=null'>{teacherNumber}</if>",
      "<if test='name !=null'>,#{name}</if>",
      "<if test='password !=null'>,#{password}</if>",
      "<if test='college !=null'>,#{college}</if>",
      "<if test='depart !=null'>,#{depart}</if>",
      "<if test='phone !=null'>,#{phone}</if>",
      "</trim>",
      ")",
      "</script>"
  })
  Integer insertDepart(TeacherInfo teacherInfo);

  List<TeacherInfo> selectTeacher(Integer collegeId, String depart);
}
