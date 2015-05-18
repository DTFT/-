<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户添加</title>
</head>
<body>
<jsp:include page="inc.jsp"/>

 	<form action="add.jsp" method="post">
 	<table align="center" width="500" border="1">
 	<tr>
 	<td>用户名称：</td><td><input type="text" name="username"/>
 		<%
 			Map<String,String> errorMsg=(Map<String,String>)request.getAttribute("errorMsg");
 		if(errorMsg!=null){
 				out.println(errorMsg.get("username"));
 		}
 		%>
 	</td>
 	</tr>
 	<tr>
 	<td>用户密码：</td><td><input type="password" name="password"/>
 	<%		
 		if(errorMsg!=null){
 				out.println(errorMsg.get("password"));
 		}
 		%>
 	</td>
 	</tr>
 	<tr>
 	<td>用户昵称：</td><td><input type="text" name="nickname"/>
 	 	<%
 		if(errorMsg!=null){
 				out.println(errorMsg.get("nickname"));
 		}
 		%>
 	</td>
 	</tr>
 	<tr>
 	<td colspan="2">
 		<input type="submit" value="添加用户"/> 
 	</td>
 	</tr>
 	</table> 	 	
 	</form>
</body>
</html>