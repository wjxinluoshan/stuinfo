package com.yzt.manager.stuinfo.config;

import com.google.gson.Gson;
import com.yzt.manager.stuinfo.pojo.StuInfo;
import com.yzt.manager.stuinfo.pojo.TeacherInfo;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class DisPatcherServletConfig implements WebMvcConfigurer {

  @Override
  public void addFormatters(FormatterRegistry registry) {
    registry.addFormatter(new Formatter<StuInfo>() {
                            @Override
                            public StuInfo parse(String json, Locale locale) throws ParseException {
                              Gson gson = new Gson();
                              StuInfo stuInfo = gson.fromJson(json, StuInfo.class);
                              return stuInfo;
                            }

                            @Override
                            public String print(StuInfo object, Locale locale) {
                              return new Gson().toJson(object);
                            }
                          }
    );

    registry.addFormatter(new Formatter<TeacherInfo>() {
                            @Override
                            public TeacherInfo parse(String json, Locale locale) throws ParseException {
                              Gson gson = new Gson();
                              TeacherInfo teacherInfo = gson.fromJson(json, TeacherInfo.class);
                              return teacherInfo;
                            }

                            @Override
                            public String print(TeacherInfo object, Locale locale) {
                              return new Gson().toJson(object);
                            }
                          }
    );
  }

}
