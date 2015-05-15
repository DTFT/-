package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharEncodingFilter implements Filter {

		private String encoding;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("before filter");
		System.out.println(encoding);
		request.setCharacterEncoding(encoding);
		chain.doFilter(request, response);
		System.out.println("end filter");

	}

	@Override
	public void init(FilterConfig cfg) throws ServletException {
		// TODO Auto-generated method stub
		String e=cfg.getInitParameter("encoding");
		if(e==null||"".equals(e.trim())){
			encoding="UTF-8";
		}else{
			encoding=e;
		}
	}

}
