package com.yzt.manager.stuinfo.mapper;

import com.yzt.manager.stuinfo.pojo.CollegeInfo;
import com.yzt.manager.stuinfo.pojo.DepartInfo;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
  List<CollegeInfo> selectCollege(Integer id, String college);

  @Update("update tb_college set college=#{newCollege} where college=#{college}")
  Integer updateCollege(String college, String newCollege);

  @Delete("delete from tb_college where college=#{college}")
  Integer delCollege(String college);

  /**
   * 系
   */
  @Insert("insert into tb_department values(#{collegeId},#{depart})")
  Integer insertDepart(Integer collegeId, String depart);

  @Select({
      "<script>",
      "select * from tb_department",
      "<where>",
      "<if test='collegeId != null'>collegeId=#{collegeId}</if>",
      "<if test='college != null'> or depart=#{depart}</if>",
      "</where>",
      "</script>"
  })
  List<DepartInfo> selectDepart(Integer collegeId, String depart);

  @Update({
      "<script>",
      "update tb_department",
      "<set>",
      "<if test='newDepart!=null'>depart=#{newDepart},</if>",
      "<if test='newCollegeId!=null'>collegeId=#{newCollegeId},</if>",
      "</set>",
      "where depart=#{depart}",
      "</script>"
  })
  Integer updateDepart(String depart, String newDepart, Integer newCollegeId);

  @Delete("delete from tb_department where depart=@{depart}")
  Integer deleteDepart(String depart);
}
