package com.shop.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharFilter implements Filter {

	private String encoding;
	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding(encoding);
		chain.doFilter(req, resp);
	}

	public void init(FilterConfig cfg) throws ServletException {
		// TODO Auto-generated method stub
		encoding=cfg.getInitParameter("encoding");
		if(encoding==null||"".equals(encoding)){
			encoding="UTF-8";
		}
	}

}
