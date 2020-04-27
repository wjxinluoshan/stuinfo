package com.yzt.manager.stuinfo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {

  @Select({"<script>",
      "select id from ",
      "<if test='isTeacher == null'> tb_stu </if>",
      "<if test='isTeacher != null'> tb_teacher </if>",
      "where userNumber=#{userNumber} and password=#{password}",
      "</script>"})
  String login(String userNumber, String password, String isTeacher);
}
