<%@page import="com.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<a href="01-2.jsp?username=aaa">01-2.jsp</a>
<%
	User u=new User(12,"abc","cba");
	pageContext.setAttribute("user", u);
%>
<%
	User uu=(User)pageContext.getAttribute("user");
%>
<%=uu.getName() %>
<jsp:forward page="01-2.jsp"></jsp:forward>
</body>
</html>