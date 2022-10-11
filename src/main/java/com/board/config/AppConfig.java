package com.board.config;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;

@Configuration
@ComponentScan(
		basePackages = {"com.board"},
		excludeFilters = @Filter(
                type = FilterType.ANNOTATION,
                classes = {Controller.class}
        )
)
@MapperScan(basePackages = {"com.board.mapper"})
@EnableTransactionManagement
public class AppConfig{
	

	@Bean
	public DataSource dataSource() {
		   DriverManagerDataSource dataSource = new DriverManagerDataSource();
		   
		   dataSource.setDriverClassName("org.h2.Driver");
		   dataSource.setUsername("sa");
		   dataSource.setPassword("");
		   dataSource.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
	       
	       ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
	       databasePopulator.addScript(new ClassPathResource("ddl/schema.sql"));
	       databasePopulator.addScript(new ClassPathResource("ddl/data.sql"));
	       
	       databasePopulator.execute(dataSource);
	       
		   return dataSource;
	}


	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource datasource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		
		sqlSessionFactory.setDataSource(datasource);
		sqlSessionFactory.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:/mybatis-config.xml"));
		sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mappers/**/*Mapper.xml"));
		return sqlSessionFactory.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
	  return new SqlSessionTemplate(sqlSessionFactory);
	}
	
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }
    
}
