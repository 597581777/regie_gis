package com.icss.regiegis.common.util;

public class LogUtil {
	public static void error(Exception ex) {
		ex.printStackTrace();
	}
	public static void info(String info) {
		System.out.println(info);
	}
}
