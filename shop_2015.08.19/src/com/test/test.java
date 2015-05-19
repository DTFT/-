package com.test;

import com.shop.dao.DAOFactory;
import com.shop.dao.IUserDao;
import com.shop.model.User;
import com.util.DBUtil;

public class test {
 public static void  main(String [] args){
	 
	 
	 
	 DBUtil.getConnection();
	 IUserDao userDao=DAOFactory.getUserDao();
		User user=userDao.load(10);

	 user.setNickname("abc");
	 user.setPassword("abc");
	 userDao.update(user);
 }
}
