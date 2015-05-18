package com.util;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

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
		return false;
	}
}
