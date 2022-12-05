package com.admaru.util;

public class StrUtil {
	
	public static String nvl(String a, String b) {
		if ("".equals(a) || a == null) {
			return b;
		} else {
			return a;
		}
	}
	
	public static String nvlZero(int a, String b) {
		if (a == 0) {
			return b;
		} else {
			return a+"";
		}
	}
	
	public static String singleQuoteAndComma(String[] strArray) {
	    String in = "";
	    for (int i = 0; i < strArray.length ; i++) {
	        in += "'" + strArray[i].trim() + "'";
	        if (i != strArray.length - 1) {
	            in += ",";
	        }
	    }
	    return in;
	}
		
	public static String make2digit(String a) {
		if (a.length() == 1) {
			return "0" + a;
		} else {
			return a;
		}
	}
	
	public static int int2int(int a, int b) {
		if (a == 0) {
			return b;
		} else {
			return a;
		}
	}
	
	public static float float2float(float a, float b) {
		if (a == 0.0) {
			return b;
		} else {
			return a;
		}
	}
}