package org.jsp.hibernatetemplatepractice.controller;

import java.util.List;
import java.util.Scanner;

import org.jsp.hibernatetemplatepractice.dao.userDao;
import org.jsp.hibernatetemplatepractice.dto.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class userController {
	public static void main(String[] args) {
		ApplicationContext context=new ClassPathXmlApplicationContext("spring-orm.xml");
		userDao dao=context.getBean("userDao",userDao.class);
		boolean flag=true;
		while(flag) {
			System.out.println("1.save user");
			System.out.println("2.Update user");
			System.out.println("3.find user by id");
			System.out.println("4.delete user by id");
			System.out.println("5.Verify user By phone and password");
			System.out.println("6.Verify user By email and password");
			System.out.println("7.Verify user By id and password");
			System.out.println("8.Find user by name\n9.Find user by phone\n10.Find user by email");
			System.out.println("11.Exit");
			Scanner sc=new Scanner(System.in);
			switch(sc.nextInt()) {
			case 1:{
				System.out.println("please enter the name,email,phone,password");
				User user=new User();
				user.setName(sc.next());
				user.setEmail(sc.next());
				user.setPhone(sc.nextLong());
				user.setPassword(sc.next());
				user=dao.saveUser(user);
				System.out.println("uaser saved with id "+user.getId());
				break;
			}
			case 2:{
				System.out.println("please enter the id, name,email,phone,password");
				User user=new User();
				user.setId(sc.nextInt());
				user.setName(sc.next());
				user.setEmail(sc.next());
				user.setPhone(sc.nextLong());
				user.setPassword(sc.next());
				user=dao.updateUser(user);
				if(user!=null) {
					System.out.println("uaser update with id "+user.getId());
				}
				else {
					System.err.println("unable to update");
				}
				break;
			}
			case 3:{
				System.out.println("enter the id to find user");
				User user=dao.findUserById(sc.nextInt());
				if(user!=null) {
					System.out.println(user);
				}else {
					System.err.println("unable to find user id is not present");
				}
				break;
			}
			case 4:{
				System.out.println("enter the user id to delete user");
				if(dao.delete(sc.nextInt())) {
					System.out.println("user is deleted");
				}else {
					System.out.println("unable to delete user");
				}
				break;
			}
			case 5:{
				System.out.println("enter the user phone and password");
				User user=dao.verifyUser(sc.nextLong(),sc.next());
				if(user!=null) {
					System.out.println(user);
				}
				else {
					System.out.println("invalid phone and password");
				}
				break;
			}
			case 6:{
				System.out.println("enter the user email and password");
				User user=dao.verifyUser(sc.next(),sc.next());
				if(user!=null) {
					System.out.println(user);
				}
				else {
					System.out.println("invalid email and password");
				}
				break;
			}
			case 7:{
				System.out.println("enter the user id and password");
				User user=dao.verifyUser(sc.nextInt(),sc.next());
				if(user!=null) {
					System.out.println(user);
				}
				else {
					System.out.println("invalid id and password");
				}
				break;
			}
			case 8:{
				System.out.println("enter the name to find");
				List<User> users=dao.findUserByName(sc.next());
				if(users.size()>0) {
					for(User u:users) {
						System.out.println(u);
					}
				}else {
					System.err.println("not found any user by this name");
				}
				break;
			}
			case 9:{
				System.out.println("enter the phone to find");
				User user=dao.findUserByPhone(sc.nextLong());
				if(user!=null) {
						System.out.println(user);
				}else {
					System.err.println("not found any user by this phone");
				}
				break;
			}
			case 10:{
				System.out.println("enter the email to find");
				User user=dao.findUserByEmail(sc.next());
				if(user!=null) {
						System.out.println(user);
				}else {
					System.err.println("not found any user by this email");
				}
				break;
			}
			case 11:{
				flag=false;
				System.err.println("exiting....");
				break;
			}
			default:{
				System.out.println("chose a valid option");
			}
			}
		}
	}

}
