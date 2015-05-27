<%@page import="com.msg.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	User u=(User)session.getAttribute("loginUser");
	
%>
<div style="text-align:right;border-bottom: 1px solid #000;">
<%
	if(u!=null) {
%>
	欢迎[<%=u.getNickname()%>]使用我们的系统
		&nbsp;<a href="<%=request.getContextPath()%>/admin/user/list.jsp">用户管理</a>
	<a href="<%=request.getContextPath()%>/admin/user/updateSelfInput.jsp">修改个人信息</a>
	<a href="<%=request.getContextPath()%>/logout.jsp">退出系统</a>
<% 		
	}
%>
    <a href="<%=request.getContextPath()%>/loginInput.jsp">用户登录</a>
	<a href="<%=request.getContextPath()%>/msg/list.jsp">留言管理</a>
</div>