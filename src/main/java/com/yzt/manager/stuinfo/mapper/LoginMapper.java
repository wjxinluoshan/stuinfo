package com.yzt.manager.stuinfo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {

  @Select({"<script>",
      "select id",
      " from ",
      "<if test='isTeacher == null'> tb_stu </if>",
      "<if test='isTeacher != null'> tb_teacher </if>",
      "where <if test='isTeacher != null'> teacherNumber </if> <if test='isTeacher == null'> stuNumber </if>=#{userNumber} and password=#{password}",
      "</script>"})
  Integer login(String userNumber, String password, String isTeacher);

  @Select("select id from tb_admin where accountName=#{accountName} and password=#{password}")
  Integer adminLogin(String accountName, String password);
}
