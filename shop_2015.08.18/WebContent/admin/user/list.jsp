<%@page import="com.shop.model.User"%>
<%@page import="java.util.List"%>
<%@page import="com.shop.dao.DAOFactory"%>
<%@page import="com.shop.dao.IUserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%
	IUserDao userDao = DAOFactory.getUserDao();
	List<User> users = userDao.list();
%>

</head>
<body>
<jsp:include page="inc.jsp"/>
	<table align="center" border="1" width="800">
		<tr>
			<td>用户表示</td>
			<td>用户名</td>
			<td>密码</td>
			<td>用户昵称</td>
			<td>操作</td>
		</tr>
		<%
			for (User u : users) {
		%>
		<tr>
			<td><%=u.getId()%></td>
			<td><%=u.getUsername()%></td>
			<td><%=u.getPassword()%></td>
			<td><%=u.getNickname()%></td>
			<td><a href="delete.jsp?id=<%=u.getId()%>">删除</a>&nbsp<a
				href="">更新</a>
		</tr>
		<%
			}
		%>


	</table>

</body>
</html>