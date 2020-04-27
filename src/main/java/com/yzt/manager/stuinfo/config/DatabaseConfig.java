package com.yzt.manager.stuinfo.config;

import com.yzt.manager.stuinfo.mapper.SchoolInfoMapper;
import com.yzt.manager.stuinfo.mapper.StuAndTeacInfoMapper;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DatabaseConfig {

  @ConfigurationProperties(prefix = "spring.datasource")
  @Bean
  public DataSource dataSource() {
    return new DriverManagerDataSource();
  }

  @Bean("ssf")
  public SqlSessionFactory sqlSessionFactory(DataSource dataSource)
      throws Exception {
    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(dataSource);
    sqlSessionFactoryBean.getObject().getConfiguration()
        .addMappers("com.yzt.manager.stuinfo.mapper");
    return sqlSessionFactoryBean.getObject();
  }

  @Bean
  public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
    return new SqlSessionTemplate(sqlSessionFactory);
  }

  @Bean
  public SchoolInfoMapper schoolInfoMapper(SqlSessionTemplate sqlSessionTemplate) {
    return sqlSessionTemplate.getMapper(SchoolInfoMapper.class);
  }

  @Bean
  public StuAndTeacInfoMapper stuAndTeacInfoMapper(SqlSessionTemplate sqlSessionTemplate) {
    return sqlSessionTemplate.getMapper(StuAndTeacInfoMapper.class);
  }

}
