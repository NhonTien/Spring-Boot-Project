package com.training.dao.impl;


import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:config/datasource.properties")
@ComponentScan("com.training.*")
public class HibernateConfig {

  private Environment environment;

  @Autowired
  public void setEnvironment(Environment environment) {
    this.environment = environment;
  }

  @Bean(name="entityManagerFactory")
  public LocalSessionFactoryBean sessionFactory() {
    LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
    sessionFactory.setDataSource(dataSource());
    sessionFactory.setPackagesToScan(new String[] { "com.training.*" });
    sessionFactory.setHibernateProperties(properties());
    return sessionFactory;
  }

  @Bean
  public DataSource dataSource() {
    BasicDataSource dataSource = new BasicDataSource();
    dataSource.setDriverClassName(environment.getRequiredProperty("spring.datasource.driver-class-name"));
    dataSource.setUrl(environment.getRequiredProperty("spring.datasource.url"));
    dataSource.setUsername(environment.getRequiredProperty("spring.datasource.username"));
    dataSource.setPassword(environment.getRequiredProperty("spring.datasource.password"));
    return dataSource;
  }

  private Properties properties() {
    Properties hibernateProperties = new Properties();
    hibernateProperties.setProperty("spring.jpa.properties.hibernate.dialect", environment.getRequiredProperty("spring.jpa.properties.hibernate.dialect"));
    return hibernateProperties;
  }
}
