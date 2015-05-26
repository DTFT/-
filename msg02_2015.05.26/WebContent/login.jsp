<%@page import="com.msg.model.MsgException"%>
<%@page import="com.msg.model.User"%>
<%@page import="com.msg.dao.DAOFactory"%>
<%@page import="com.msg.dao.IUserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		try{
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			IUserDao ud=DAOFactory.getUserDao();
			User u=ud.login(username, password);
			
			session.setAttribute("loginUser",u);
			response.sendRedirect(request.getContextPath()+"/admin/user/list.jsp");
			return;
		}catch(MsgException e){
	%>
		<h1 style="color:red">发生错误 ,<%=e.getMessage() %></h1>
	<%	
	}
	%>
</body>
</html>