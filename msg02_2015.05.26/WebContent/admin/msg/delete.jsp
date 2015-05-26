<%@page import="com.util.ValidateUtil"%>
<%@page import="com.msg.dao.DAOFactory"%>
<%@page import="com.msg.dao.IMessageDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%
	
	int id=Integer.parseInt(request.getParameter("id"));
	IMessageDAO  msgDao=DAOFactory.getMessageDao();
	
	boolean flag=ValidateUtil.checkAdminOrSelf(session,msgDao.load(id).getUserId());
	if(!flag){
	%>
	<h2 style="color:red">你没有权限删除该留言</h2>
	<% 	
	}else{
	msgDao.delete(id);
	response.sendRedirect(request.getContextPath()+"/msg/list.jsp");
	}
%>
