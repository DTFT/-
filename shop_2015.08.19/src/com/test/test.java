package com.test;

import com.shop.dao.DAOFactory;
import com.shop.dao.IUserDao;
import com.shop.model.User;
import com.util.DBUtil;

public class test {
 public static void  main(String [] args){
	 
	 
	 
	 DBUtil.getConnection();
	 IUserDao userDao=DAOFactory.getUserDao();
		User user=new User();
		user=userDao.load(5);
		user.setNickname("abc");
		user.setUsername("asdasd");
		user.setPassword("123456");
		user.setType(1);
		user.setStatus(1);
       
		userDao.update(user);
 }
}
