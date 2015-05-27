package com.msg.test;

import java.util.Date;
import java.util.List;

import com.msg.dao.DAOFactory;
import com.msg.dao.IMessageDAO;
import com.msg.dao.IUserDao;
import com.msg.model.Message;
import com.msg.model.User;

public class TestInit {

	public static void main(String[] args) {
	IMessageDAO msgDao=DAOFactory.getMessageDao();
	Message msg=msgDao.load(2);
//	System.out.print("id="+msgDao.load(2).getUserId());

		
	}

}
