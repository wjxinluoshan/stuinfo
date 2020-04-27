package com.yzt.manager.stuinfo.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SchoolInfoMapper {

  /**
   * 学院
   */
  @Insert("insert into tb_college(college) values(#{college})")
  Integer insertCollege(String college);

  @Select({
      "<script>",
      "select * from tb_college",
      "<where>",
      "<if test='id != null'>id=#{id}</if>",
      "<if test='college != null'> or college=#{college}</if>",
      "</where>",
      "</script>"
  })
  List<Object> selectCollege(Integer id, String college);

  /**
   * 系
   */
  @Insert("insert into tb_department values(#{collegeId},#{depart})")
  Integer insertDepart(Integer collegeId,String depart);

  @Select({
      "<script>",
      "select * from tb_department",
      "<where>",
      "<if test='collegeId != null'>collegeId=#{collegeId}</if>",
      "<if test='college != null'> or depart=#{depart}</if>",
      "</where>",
      "</script>"
  })
  List<Object> selectDepart(Integer collegeId, String depart);

}
