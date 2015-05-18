package com.shop.dao;

import com.shop.model.User;
import com.util.DBUtil;

public class test {
 public static void  main(String [] args){
	 
	 
	 
	 DBUtil.getConnection();
	 User user=new User();
	 user.setUsername("abc");
	 user.setNickname("abc");
	 user.setPassword("abc");
	 IUserDao userDao=DAOFactory.getUserDao();
	 userDao.add(user);
 }
}
