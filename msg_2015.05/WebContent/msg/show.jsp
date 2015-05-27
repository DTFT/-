<%@page import="com.msg.model.User"%>
<%@page import="com.msg.model.Comment"%>
<%@page import="com.msg.model.Pager"%>
<%@page import="com.msg.dao.ICommentDAO"%>
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
    ICommentDAO commentDao=DAOFactory.getCommentDao();
    IUserDao userDao=DAOFactory.getUserDao();
	int id=Integer.parseInt(request.getParameter("id"));
    Pager<Comment> comments=commentDao.list(id);
	Message msg=msgDao.load(id);
	User lu=(User)session.getAttribute("loginUser");
%>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/xhEditor/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/xhEditor/xheditor-1.1.14-zh-cn.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#content").xheditor({
			tools : 'full',
			skin : 'o2007silver',
			emots : {
				msn : {
					name : 'MSN',
					count : 40,
					width : 22,
					height : 22,
					line : 8
				},ipb:{name:'IPB',width:20,height:25,line:8,list:{smile:'微笑',joyful:'开心',laugh:'笑',biglaugh:'大笑',w00t:'欢呼',wub:'欢喜',depres:'沮丧',sad:'悲伤',cry:'哭泣',angry:'生气',devil:'魔鬼',blush:'脸红',kiss:'吻',surprised:'惊讶',wondering:'疑惑',unsure:'不确定',tongue:'吐舌头',cool:'耍酷',blink:'眨眼',whistling:'吹口哨',glare:'轻视',pinch:'捏',sideways:'侧身',sleep:'睡了',sick:'生病',ninja:'忍者',bandit:'强盗',police:'警察',angel:'天使',magician:'魔法师',alien:'外星人',heart:'心动'}},
				pidgin:{name:'Pidgin',width:22,height:25,line:8,list:{smile:'微笑',cute:'可爱',wink:'眨眼',laugh:'大笑',victory:'胜利',sad:'伤心',cry:'哭泣',angry:'生气',shout:'大骂',curse:'诅咒',devil:'魔鬼',blush:'害羞',tongue:'吐舌头',envy:'羡慕',cool:'耍酷',kiss:'吻',shocked:'惊讶',sweat:'汗',sick:'生病',bye:'再见',tired:'累',sleepy:'睡了',question:'疑问',rose:'玫瑰',gift:'礼物',coffee:'咖啡',music:'音乐',soccer:'足球',good:'赞同',bad:'反对',love:'心',brokenheart:'伤心'}}
			}
		});
	});
</script>
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
	
	
	<table width="900" align="center" border="1">
	<%
	for(Comment comment:comments.getDatas()){
	%>
		<tr>
		<td width="400"><%=comment.getContent() %></td>
		<td>发布时间：<%=MsgUtil.formatDate(comment.getPostDate()) %>&nbsp;
				发布人员：<%=userDao.load(comment.getUserId()).getNickname() %>&nbsp;
			<%
			if(ValidateUtil.checkAdminOrSelf(session, comment.getUserId())){
				%>
				<a href="<%=request.getContextPath()%>/admin/comment/delete.jsp?id=<%=comment.getId()%>
				&msgId=<%=msg.getId() %>">删除</a>
				<% 
			}
			%>	
		</td>
		</tr>
	<% 
	}
	%>
	
	<tr><td colspan="2">
	<jsp:include page="/inc/page.jsp">
	<jsp:param value="<%=comments.getTotalRecord() %>" name="items"/>
	<jsp:param value="id" name="params"/>
	</jsp:include>
	</td></tr>
	</table>
	<%
	if(lu!=null){
	%>
	<form action="<%=request.getContextPath()%>/admin/comment/add.jsp" method="post">
	<input type="hidden" name="msgId" value="<%=msg.getId() %>"/>
	<table width="900" align="center" border="1">
	<tr>
	<td>添加回复</td>
	</tr>
	<tr>
	<td><textarea id="content"  name="content" cols="100" rows="10"></textarea></td>
	</tr>
	<tr>
	<td><input type="submit" value="添加回复"/></td>
	</tr>
	</table>
	</form>
	
	<% 
	}
	%>
	
</body>
</html>




