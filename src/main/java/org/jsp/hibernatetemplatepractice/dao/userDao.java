package org.jsp.hibernatetemplatepractice.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.jsp.hibernatetemplatepractice.dto.User;
import org.springframework.orm.hibernate5.HibernateTemplate;

public class userDao {
	private HibernateTemplate hibernateTemplate;
	@Transactional
	public User saveUser(User user) {
		hibernateTemplate.save(user);
		return user;
	}
	@Transactional
	public User updateUser(User user) {
		User dbUser=hibernateTemplate.get(User.class,user.getId());
		if(dbUser!=null) {
			dbUser.setEmail(user.getEmail());
			dbUser.setPhone(user.getPhone());
			dbUser.setName(user.getName());
			dbUser.setPassword(user.getPassword());
			hibernateTemplate.update(dbUser);
			return user;
		}
		return null;
	}
	public User findUserById(int id) {
	User user=	hibernateTemplate.get(User.class,id);
	if(user!=null) {
		return user;
	}
	return null;
		
	}
	@Transactional
	public boolean delete(int id) {
		User user =findUserById(id);
		if(user!=null) {
			hibernateTemplate.delete(user);
			return true;
		}
		return false;
	}
	public User verifyUser(long phone,String password) {
		List<User> users=(List<User>) hibernateTemplate.find("select u from User u where u.phone=?0 and u.password=?1",phone,password);
		if(users.isEmpty()) 
			return null;
		return users.get(0);
		
	}
	public User verifyUser(String email,String password) {
		List<User> users=(List<User>) hibernateTemplate.find("select u from User u where u.email=?0 and u.password=?1",email,password);
		if(users.isEmpty()) 
			return null;
		return users.get(0);
		
	}
	public User verifyUser(int id,String password) {
		List<User> users=(List<User>) hibernateTemplate.find("select u from User u where u.id=?0 and u.password=?1",id,password);
		if(users.isEmpty()) 
			return null;
		return users.get(0);	
	}
	public List<User> findUserByName(String name) {
		List<User> users=(List<User>) hibernateTemplate.find("select u from User u where u.name=?0",name);
		if(users.isEmpty())
			return null;
		    return users;	
	}
	public User findUserByEmail(String email) {
		List<User> users=(List<User>) hibernateTemplate.find("select u from User u where u.email=?0",email);
		if(users.isEmpty())
			return null;
		    return users.get(0);	
	}
	public User findUserByPhone(long phone) {
		List<User> users=(List<User>) hibernateTemplate.find("select u from User u where u.phone=?0",phone);
		if(users.isEmpty())
			return null;
		    return users.get(0);	
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

}
