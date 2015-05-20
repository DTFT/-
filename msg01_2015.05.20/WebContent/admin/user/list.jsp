<%@page import="com.msg.model.Pager"%>
<%@page import="org.apache.coyote.RequestGroupInfo"%>
<%@page import="com.msg.model.User"%>
<%@page import="java.util.List"%>
<%@page import="com.msg.dao.DAOFactory"%>
<%@page import="com.msg.dao.IUserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%
	int pageSize=5;
	int pageIndex=1;
	try{
	pageIndex=Integer.parseInt(request.getParameter("pageIndex"));
	}catch(NumberFormatException e){
	}
	String con=request.getParameter("con");
	if(con==null) con="";
	IUserDao userDao = DAOFactory.getUserDao();
	Pager<User> pages=userDao.list(con, pageSize, pageIndex);
	//System.out.println(pageSize);
	//System.out.println(pageIndex);
	List<User> users = pages.getDatas();
	User u=(User)session.getAttribute("loginUser");
%>

</head>
<body>
	
<jsp:include page="inc.jsp">
	<jsp:param value="列表" name="op"/>
</jsp:include>
	<table align="center" border="1" width="800">
	<tr><td colspan="7">
	<form action="list.jsp">
		输入用户名或昵称：<input type="text" name="con"/><input type="submit" value="查询">
	</form>
	</td>
	</tr>
		<tr>
			<td>用户id</td>
			<td>用户名</td>
			<td>密码</td>
			<td>用户昵称</td>
			<td>用户状态</td>
			<td>用户类型</td>
			<td>操作</td>
		</tr>
		<%
			for (User u1 : users) {
		%>
		<tr>
			<td><%=u1.getId()%></td>
			<td><%=u1.getUsername()%></td>
			<td><%=u1.getPassword()%></td>
			<td><%=u1.getNickname()%></td>
			<td><%
				if(u1.getStatus()==0){
				%>
					启用&nbsp;
					<%
				if(u.getType()==1){
				%>
				<a href="setStatus.jsp?id=<%=u1.getId() %>">停用</a>
				<%
				}
				%>
				<% 
				}else{
					%>
					<span style='color:red'>停用</span>&nbsp;
					<%
				if(u.getType()==1){
				%>
					<a href="setStatus.jsp?id=<%=u1.getId() %>">启用</a>
					<%
				}
				%>
				 <% 
				}
				%>
				</td>
			<td><%
			if(u1.getType()==0){
			%>
				普通用户&nbsp;
				<%
				if(u.getType()==1){
				%>
				<a href="setType.jsp?id=<%=u1.getId() %>">设置管理员</a>
				<%
				}
				%>
			<% 
			}else{
				%>
				管理员&nbsp;
				<%
				if(u.getType()==1){
				%>
				<a href="setType.jsp?id=<%=u1.getId() %>">取消管理员</a>
				<%
				}
				%>
			<% 
			}
				%>
			</td>
			<td>
			<%
				if(u.getType()==1){
				%>
			<a href="delete.jsp?id=<%=u1.getId()%>">删除</a> &nbsp
		
			<a href="updateInput.jsp?id=<%=u1.getId()%>">更新</a>
				<%
				}
				%>
		</tr>
		<%
			}
		%>


	</table>

</body>
</html>