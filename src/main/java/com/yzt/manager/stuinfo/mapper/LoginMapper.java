package com.yzt.manager.stuinfo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {

  @Select({"<script>",
      "select ",
      "<if test='isTeacher == null'> stuNumber </if>",
      "<if test='isTeacher != null'> teacherNumber </if>",
      " from ",
      "<if test='isTeacher == null'> tb_stu </if>",
      "<if test='isTeacher != null'> tb_teacher </if>",
      "where <if test='isTeacher != null'> teacherNumber </if> <if test='isTeacher == null'> stuNumber </if>=#{userNumber} and password=#{password}",
      "</script>"})
  String login(String userNumber, String password, String isTeacher);

  @Select("select id from tb_admin where accountName=#{accountName} and password=#{password}")
  String adminLogin(String accountName, String password);
}
