<%@page import="com.msg.model.User"%>
<%@page import="com.msg.dao.DAOFactory"%>
<%@page import="com.msg.dao.IUserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/inc/adminCheck.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	int id=Integer.parseInt(request.getParameter("id"));
	IUserDao ud=DAOFactory.getUserDao();
	User u=ud.load(id);
	if(u.getStatus()==0){
		u.setStatus(1);
	}else{
		u.setStatus(0);
	}
	ud.update(u);
	response.sendRedirect("list.jsp");
%>
</body>
</html>