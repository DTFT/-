<%@page import="com.util.ValidateUtil"%>
<%@page import="com.msg.dao.IUserDao"%>
<%@page import="com.util.MsgUtil"%>
<%@page import="com.msg.model.Message"%>
<%@page import="com.msg.dao.DAOFactory"%>
<%@page import="com.msg.dao.IMessageDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>留言显示</title>
<%
	IMessageDAO msgDao=DAOFactory.getMessageDao();
    IUserDao userDao=DAOFactory.getUserDao();
	int id=Integer.parseInt(request.getParameter("id"));
	Message msg=msgDao.load(id);
%>
</head>
<body>
<jsp:include page="/msg/inc.jsp">
	<jsp:param value="显示" name="op"/>
</jsp:include>
	<table width="900" align="center" border="1">
		<tr>
		<td><h3><%=msg.getTitle() %></h3></td>
		</tr>
		
		<tr>
		<td>发表日期：<%=MsgUtil.formatDate(msg.getPostDate()) %>&nbsp;
				发布人员：<%=userDao.load(msg.getUserId()).getNickname() %>
				<%
				if(ValidateUtil.checkAdminOrSelf(session, msg.getUserId())){
					%>
					<a href="<%=request.getContextPath()%>/admin/msg/updateInput.jsp?id=<%=msg.getId() %>">更新</a> &nbsp;&nbsp;
					<a href="<%=request.getContextPath()%>/admin/msg/delete.jsp?id=<%=msg.getId() %>">删除</a>
					<% 
				}
				%>
		</td>
		
		</tr>
		<tr>
		<td>
				<%=msg.getContent() %>
		</td>
		</tr>
	</table>

</body>
</html>




