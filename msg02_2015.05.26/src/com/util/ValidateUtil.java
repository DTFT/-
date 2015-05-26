package com.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.msg.model.User;

public class ValidateUtil {
	public static boolean validateNull(HttpServletRequest request,
			String[] fields) {
		boolean validate = true;
		Map<String, String> errorMsg = new HashMap<String, String>();
		for (String field : fields) {
			String value = request.getParameter(field);
			if (value == "" || "".equals(value.trim())) {
				validate = false;
				errorMsg.put(field, field + "²»ÄÜÎª¿Õ");
			}
		}
		if (!validate)
			request.setAttribute("errorMsg", errorMsg);
		return validate;
	}
	
	public static String showError(HttpServletRequest request,String field){
		@SuppressWarnings("unchecked")
		Map<String, String> errorMsg = (Map<String,String>)request.getAttribute("errorMsg");
		if(errorMsg==null) return "";
			String msg=errorMsg.get(field);
		if(msg==null) return "";
			return msg;
	}
	
	public static boolean checkAdminOrSelf(HttpSession session,int userId){
		User u=(User)session.getAttribute("loginUser");
		if(u==null) return false;
		if((u.getType())==1) return true;
		if(u.getId()==userId) return true;
			return false;
		
	}
	
	
}
