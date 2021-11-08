package com.itranswarp.learnjava;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.HierarchicalMessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.itranswarp.learnjava.service.User;
import com.itranswarp.learnjava.service.UserService;

import javax.sql.DataSource;

@Configuration
@ComponentScan
public class AppConfig {

	@SuppressWarnings("resource")

	@Bean
	DataSource createDataSource() {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl("jdbc:mysql://localhost:3306/learnjdbc?useUnicode=true&characterEncoding=utf-8");
		config.setUsername("root");
		config.setPassword("123456");
		config.addDataSourceProperty("connectionTimeout", "10000"); // 连接超时：1秒
		config.addDataSourceProperty("idleTimeout", "60000"); // 空闲超时：60秒
		config.addDataSourceProperty("maximumPoolSize", "10"); // 最大连接数：10
		config.addDataSourceProperty("autoCommit", "true"); // 最大连接数：10
		DataSource ds = new HikariDataSource(config);
		return ds;
	}
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		UserService userService = context.getBean(UserService.class);
		User user = userService.login("bob@example.com", "password");
		System.out.println(user.getName());
	}
}
