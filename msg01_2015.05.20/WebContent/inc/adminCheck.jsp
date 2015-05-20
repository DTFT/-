<%@page import="com.msg.model.MsgException"%>
<%@page import="com.msg.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	User lu=(User)session.getAttribute("loginUser");
	if(lu.getType()!=1){
		throw new MsgException("没有权限操作该功能");
	}
 %>