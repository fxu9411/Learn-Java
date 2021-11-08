package com.itranswarp.learnjava;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.itranswarp.learnjava.service.User;
import com.itranswarp.learnjava.service.UserService;

public class Main {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
		UserService userService = context.getBean(UserService.class);
		User user = userService.login("bob@example.com", "password");
		User anotheruser = userService.register("franklolxu@gmail.com", "123218", "Frank");
		
		System.out.println(user.getName());
		System.out.println(anotheruser.getEmail());
	}
}
