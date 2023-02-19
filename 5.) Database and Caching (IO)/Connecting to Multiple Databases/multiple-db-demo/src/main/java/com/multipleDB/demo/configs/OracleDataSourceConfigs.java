package com.multipleDB.demo.configs;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class OracleDataSourceConfigs {

	@Bean(name="DataSourcePropertiesONE")
	@ConfigurationProperties("spring.myuserone")	
	//@Primary
	public DataSourceProperties firstDataSourceProperties() {
	    return new DataSourceProperties();
	}
	
	@Bean(name="DataSourcePropertiesTWO")
	@ConfigurationProperties("spring.myusertwo")	
	public DataSourceProperties secondDataSourceProperties() {
	    return new DataSourceProperties();
	}
	
	
	
	@Bean(name="DataSourceONE")	
	public DataSource createMyUserOneDataSource(@Qualifier("DataSourcePropertiesONE")DataSourceProperties dataSourceProperties) {
		//return DataSourceBuilder.create().build();
		return dataSourceProperties.initializeDataSourceBuilder().build();	
	}
	
	@Bean(name="DataSourceTWO")
	public DataSource createMyUserTwoDataSource(@Qualifier("DataSourcePropertiesTWO")DataSourceProperties dataSourceProperties) {
		//return DataSourceBuilder.create().build();
		return dataSourceProperties.initializeDataSourceBuilder().build();	
	}
	
	
	
	
	@Bean(name="JdbcTemplateOne")
	@Autowired
	public JdbcTemplate createJdbcTemplate_MyUserOneDB(@Qualifier("DataSourceONE") DataSource myuserone) {
		return new JdbcTemplate(myuserone);
	}
	
	@Bean(name="JdbcTemplateTwo")
	@Autowired
	public JdbcTemplate createJdbcTemplate_MyUserTwoDB(@Qualifier("DataSourceTWO") DataSource myusertwo) {
		return new JdbcTemplate(myusertwo);
	}
}
