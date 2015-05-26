<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager"%>
    <%
    	int items=Integer.parseInt(request.getParameter(("items")));
       String params=request.getParameter("params");
       if(params==null){
    	   params="";
       }
       String [] ps=params.split(",");
    %>
<pg:pager maxPageItems="5"
					items="<%=items%>"  export="curPage=pageNumber">
			<%
				for(String p:ps){
			%>
			<pg:param name="<%=p %>"/>
			<% 
				}
			%>
					<pg:last>
						共<%=items %>条记录，共<%=pageNumber %>页，当前第<%=curPage %>页
					</pg:last>
					
					<pg:first>
					<a href="<%=pageUrl%>">首页</a>
				</pg:first> 
				<pg:prev>
					<a href="<%=pageUrl%>">上一页</a>
				</pg:prev> 
				<pg:pages>
			<%
				if(curPage==pageNumber){
			%>
					[<%=pageNumber %>]
			<%
				}else{
			%>
					<a href="<%=pageUrl %>"><%=pageNumber %></a>
			<%
			}
			%>		
				</pg:pages> 
				<pg:next>
					<a href="<%=pageUrl%>">下一页</a>
				</pg:next> 
				<pg:last>
					<a href="<%=pageUrl%>">尾页</a>
				</pg:last></td>
				</pg:pager> 