package com.msg.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.msg.model.User;

public class AdminCheckFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
//		// TODO Auto-generated method stub
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse response2=(HttpServletResponse) response;
		HttpSession session=req.getSession();
		User u=(User)session.getAttribute("loginUser");
		if(u==null){
			response2.sendRedirect(req.getContextPath()+"/loginInput.jsp");
			return;
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig agr0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
