package com.yzt.manager.stuinfo.mapperimpl;

import com.yzt.manager.stuinfo.mapper.SchoolInfoMapper;
import com.yzt.manager.stuinfo.pojo.CollegeInfo;
import com.yzt.manager.stuinfo.pojo.DepartInfo;
import java.util.List;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SchoolInfoMapperImple implements SchoolInfoMapper {

  @Autowired
  private SqlSessionTemplate sqlSessionTemplate;

  @Autowired
  private SchoolInfoMapper schoolInfoMapper;

  public void insertCollegeAndDepart(String college, String[] departs,
      List<List<List<String>>> lists) throws Exception {
    if (lists == null) {
      int collegeId;
      List<CollegeInfo> list = this.selectCollege(null, college);
      //学院不存在，插入，得到学院id
      if (list.isEmpty()) {
        this.insertCollege(college);
        collegeId = this.selectCollege(null, college).get(0).getId();
      } else {
        collegeId = list.get(0).getId();
      }
      //根据学院id和系，插入数据
      for (String depart : departs) {
        if (this.selectDepart(null, depart).isEmpty()) {
          this.insertDepart(collegeId, depart);
        }
      }
    } else {
      //1.遍历读出各个sheet
      for (List<List<String>> list : lists) {
        //2.遍历读出各个列
        for (List<String> strings : list) {
          boolean isCollege = true;
          int collegeId = 0;
          //3.遍历各个行
          for (String string : strings) {
            //第一行是学院
            if (isCollege) {
              isCollege = false;
              //检查该学院在表中是否存在
              if (this.selectCollege(null, string).isEmpty()) {
                //不存在，插入数据
                this.insertCollege(string);
                collegeId =  this.selectCollege(null, string).get(0).getId();
              }
              continue;
            }
            //检查该系是否存在
            if (this.selectDepart(null, string).isEmpty()) {
              //不存在，插入数据
              this.insertDepart(collegeId, string);
            }
          }
        }
      }
    }
  }


  @Override
  public Integer insertCollege(String college) {
    return schoolInfoMapper.insertCollege(college);
  }


  @Override
  public List<CollegeInfo> selectCollege(Integer id, String college) {
    return schoolInfoMapper.selectCollege(id, college);
  }

  @Override
  public Integer updateCollege(String college, String newCollege) {
    if (newCollege == null || college == null) {
      return null;
    }
    return schoolInfoMapper.updateCollege(college, newCollege);
  }

  @Override
  public Integer delCollege(String college) {
    return delCollege(college);
  }

  @Override
  public Integer insertDepart(Integer collegeId, String depart) {
    return schoolInfoMapper.insertDepart(collegeId, depart);
  }

  @Override
  public List<DepartInfo> selectDepart(Integer collegeId, String depart) {
    return schoolInfoMapper.selectDepart(collegeId, depart);
  }

  @Override
  public Integer updateDepart(String depart, String newDepart, Integer newCollegeId) {
    if ((newDepart == null && newCollegeId == null) || depart == null) {
      return null;
    }
    return schoolInfoMapper.updateDepart(depart, newDepart, newCollegeId);
  }

  @Override
  public Integer deleteDepart(String depart) {
    return schoolInfoMapper.deleteDepart(depart);
  }
}
