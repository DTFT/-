<%@page import="com.msg.model.User"%>
<%@page import="com.msg.model.Comment"%>
<%@page import="com.msg.dao.DAOFactory"%>
<%@page import="com.msg.dao.ICommentDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	int userId=((User)session.getAttribute("loginUser")).getId();
	int msgId=Integer.parseInt(request.getParameter("msgId"));
	String content=request.getParameter("content");
	ICommentDAO commentDao=DAOFactory.getCommentDao();
	Comment comment=new Comment();
	comment.setContent(content);
	commentDao.add(comment, userId, msgId);
	response.sendRedirect(request.getContextPath()+"/msg/show.jsp?id="+msgId);
%>