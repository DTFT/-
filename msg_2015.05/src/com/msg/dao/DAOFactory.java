package com.msg.dao;

public class DAOFactory {
    	public static IUserDao getUserDao(){
    		return new UserDao();
    	}
    	
    	public static IMessageDAO getMessageDao(){
    		return new MessageDao();
    	}
    	
    	public static ICommentDAO getCommentDao(){
    		return new CommentDao();
    	}
}
