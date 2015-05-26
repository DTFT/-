package com.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MsgUtil {
	private final static String dateFormat="yyyy-MM-dd HH:mm";
	private static final SimpleDateFormat sdf=new SimpleDateFormat(dateFormat);
	
	public static String formatDate(Date date){
		return sdf.format(date);
	}
}
