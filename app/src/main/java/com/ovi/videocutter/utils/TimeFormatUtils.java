package com.ovi.videocutter.utils;

public class TimeFormatUtils {
	
	
	/**
	 * How long does the millisecond value change to a fixed format?
	 * @param mesc
	 * @return
	 */
	public static String formatLongToTimeStr(int mesc) {
		int hour = 0;
		int minute = 0;
		int second = 0;

		int sub=mesc%1000/10;
		second = mesc / 1000;

		if (second > 60) {
			minute = second / 60;
			second = second % 60;
		}
		if (minute > 60) {
			hour = minute / 60;
			minute = minute % 60;
		}
		return (getTwoLength(hour) + ":" + getTwoLength(minute) + ":" + getTwoLength(second)+"."+(sub<10?0+""+sub:sub+""));
	}

	public static String getTwoLength(final int data) {
		if (data < 10) {
			return "0" + data;
		} else {
			return "" + data;
		}
	}
	
	

}
