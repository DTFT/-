
<%@page import="com.util.ValidateUtil"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.msg.model.MsgException"%>
<%@page import="com.msg.dao.DAOFactory"%>
<%@page import="com.msg.dao.IUserDao"%>
<%@page import="com.msg.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/inc/adminCheck.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	int id=Integer.parseInt(request.getParameter("id"));
	String password=request.getParameter("password");
	String nickname=request.getParameter("nickname");
	boolean validate=
	ValidateUtil.validateNull(request, new String [ ]{"password","nickname"});
	
	if(!validate){
%>
		<jsp:forward page="updateInput.jsp" />
<%
	}
	
	IUserDao userDao=DAOFactory.getUserDao();
	User user=userDao.load(id);
	user.setNickname(nickname);
	user.setPassword(password);
	try{
		userDao.update(user);
		response.sendRedirect("list.jsp"); 
		return;
	}catch(MsgException e){
%>
	<h1 style="color:red">发生错误 ,<%=e.getMessage() %></h1>
<%
	}
 %>