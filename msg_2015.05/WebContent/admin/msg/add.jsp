<%@page import="java.util.Date"%>
<%@page import="com.msg.model.Message"%>
<%@page import="com.msg.dao.DAOFactory"%>
<%@page import="com.msg.dao.IMessageDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%

	IMessageDAO msgDao=DAOFactory.getMessageDao();
	String title=request.getParameter("title"); 
	String content=request.getParameter("content");
	int userId=Integer.parseInt(request.getParameter("userId"));
	
	Message msg=new Message();
	msg.setContent(content);
	msg.setTitle(title);
	msg.setPostDate(new Date());
	msgDao.add(msg, userId);
	
	response.sendRedirect(request.getContextPath()+"/msg/list.jsp");

 %>