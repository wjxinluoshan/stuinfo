package com.yzt.manager.stuinfo;

import com.yzt.manager.stuinfo.mapperimpl.SchoolInfoMapperImple;
import com.yzt.manager.stuinfo.mapperimpl.StuAndTeacInfoMapperImple;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class StuinfoApplicationTests {

  @Autowired
  private StuAndTeacInfoMapperImple stuAndTeacInfoMapperImple;

  @Autowired
  private SchoolInfoMapperImple schoolInfoMapperImple;

  @Test
  void contextLoads() {
    System.out.println(stuAndTeacInfoMapperImple.selectStu().get(0));
  }

  @Test
  void test2() throws Exception {
    schoolInfoMapperImple.insertCollegeAndDepart(null, null, null);
  }
}
