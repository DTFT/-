package com.msg.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.msg.model.SystemContext;

public class SystemContextFilter implements Filter{
	int pageSize;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		try {
			int pageOffset=0;
			int pageSize=10;
			try {
				pageOffset=Integer.parseInt(req.getParameter("pager.offset"));			
			} catch (Exception e) {
			}
			SystemContext.setPageOffset(pageOffset);
			SystemContext.setPageSize(pageSize);
			chain.doFilter(req, resp);
		} finally{
			SystemContext.removepageOffset();;
			SystemContext.removePageSize();
		}
	}

	@Override
	public void init(FilterConfig cfg) throws ServletException {
		// TODO Auto-generated method stub
		try {
			pageSize=Integer.parseInt(cfg.getInitParameter("pageSize"));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			pageSize=5;
		}
	}

}
