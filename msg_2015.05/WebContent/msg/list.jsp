<%@page import="com.msg.dao.IUserDao"%>
<%@page import="com.msg.dao.UserDao"%>
<%@page import="com.util.MsgUtil"%>
<%@page import="com.msg.model.Message"%>
<%@page import="com.msg.model.Pager"%>
<%@page import="com.msg.dao.DAOFactory"%>
<%@page import="com.msg.dao.IMessageDAO"%>
<%@page import="com.msg.dao.MessageDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%
	IMessageDAO msgDAO=DAOFactory.getMessageDao();
	IUserDao userDao=DAOFactory.getUserDao();
	Pager<Message> pages=msgDAO.list();
	int totalRecord=pages.getTotalRecord();
%>
</head>
<body>
<jsp:include page="/msg/inc.jsp">
	<jsp:param value="列表" name="op"/>
</jsp:include>

	<table align="center" width="900" border="1">
	<tr>
	<td>标题</td><td>发布时间</td><td>发布人</td><td>评论数量</td>
	</tr>
	<%
		for(Message msg:pages.getDatas()){
	%>
		<tr>
		<td><a href="show.jsp?id=<%=msg.getId()%>"><%=msg.getTitle() %></a></td>
		<td><%=MsgUtil.formatDate(msg.getPostDate()) %></td>
		<td><%=userDao.load(msg.getUserId()).getNickname() %></td>
		<td><%=msgDAO.getCommentCount(msg.getId()) %></td>
		</tr>
	<%
		}
	%>
	<tr>
	<td colspan="4">
		<jsp:include page="/inc/page.jsp">
			<jsp:param value="<%=totalRecord %>" name="items"/>
		</jsp:include>
		</td>
	</tr>
	</table>
</body>
</html>




