package com.util;

import java.io.IOException;
import java.util.Properties;
//µ¥ÀýÄ£Ê½
public class PropertiesUtil {
	private static Properties jdbcProp;

	public static Properties getJdbcProp() {
		try {
			if (jdbcProp == null) {
				jdbcProp = new Properties();
				jdbcProp.load(DBUtil.class.getClassLoader()
						.getResourceAsStream("jdbc.properties"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jdbcProp;
	}
}
