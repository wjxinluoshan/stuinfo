package com.yzt.manager.stuinfo;


import com.yzt.manager.stuinfo.mapperimpl.SchoolInfoMapperImple;
import java.io.File;
import java.io.FileNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.ResourceUtils;
import org.springframework.web.context.WebApplicationContext;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class StuinfoApplicationTests {

  @Autowired
  private WebApplicationContext context;

  @Autowired
  private SchoolInfoMapperImple schoolInfoMapperImple;


  private MockMvc mvc;

  @BeforeEach
  public void setUp() {
    // 构造MockMvc
    mvc = MockMvcBuilders.webAppContextSetup(context).build();

  }

  @Test
  public void test1() throws Exception {
    mvc.perform(
        MockMvcRequestBuilders.get("/test/t")
            .param("stuNumber", "1223345"));
  }

  @Test
  public void queryCollege(){
    System.out.println(schoolInfoMapperImple.selectCollege(null, null));
  }

  @Test
  public void queryStudent() throws Exception {
  mvc.perform(
        MockMvcRequestBuilders.get("/test/qs"));
  }
@Test
  public void testFile() throws FileNotFoundException {

  System.out.println(ResourceUtils.getFile("classpath:\\com\\yzt\\manager\\stuinfo\\StuinfoApplicationTests.class").getPath());

//  if(!file.exists())
//    file.mkdirs();
//  System.out.println(file.getPath());

}
}
