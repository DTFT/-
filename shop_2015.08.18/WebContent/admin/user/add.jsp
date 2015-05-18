
<%@page import="com.util.ValidateUtil"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.shop.model.ShopException"%>
<%@page import="com.shop.dao.DAOFactory"%>
<%@page import="com.shop.dao.IUserDao"%>
<%@page import="com.shop.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	
	String username=request.getParameter("username");
	String password=request.getParameter("password");
	String nickname=request.getParameter("nickname");
	boolean validate=
	ValidateUtil.validateNull(request, new String [ ]{"username","password","nickname"});
	
	if(!validate){
%>
		<jsp:forward page="addInput.jsp" />
<% 		
	}
	
	User user=new User();
	user.setNickname(nickname);
	user.setPassword(password);
	user.setUsername(username);
	IUserDao userDao=DAOFactory.getUserDao();
	try{
		userDao.add(user);
%>
	添加用户成功！<a href="addInput.jsp">继续添加</a>
	<a href="list.jsp">用户列表</a>
<% 
	}catch(ShopException e){
%>
	<h1 style="color:red">发生错误 ,<%=e.getMessage() %></h1>
<%
	}
 %>