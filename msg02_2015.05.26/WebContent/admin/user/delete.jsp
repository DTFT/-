<%@page import="com.msg.model.MsgException"%>
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
	try{
	int id=Integer.parseInt(request.getParameter("id"));
   	IUserDao userDao=DAOFactory.getUserDao();
	userDao.delete(id);
	response.sendRedirect("list.jsp");
	}catch(MsgException e){
%>
	<h1 style="color:red">发生错误 ,<%=e.getMessage() %></h1>
<%
	}
%>

</body>
</html>