package com.kit.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	/**
	 * 根据格式获取当前日期
	 * @return
	 */
	public static String getCurrentDay(String p) {
		SimpleDateFormat sdf = new SimpleDateFormat(p);
		String day = sdf.format(new Date());
		return day;
	}
	
	/**
	 * 获取几天之后的日期
	 * @param num
	 * @return
	 */
	public static String getDayAfterNum(Integer num, String p) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, num);
		SimpleDateFormat sdf = new SimpleDateFormat(p);
		String day = sdf.format(calendar.getTime());
		return day;
	}

    /**
     *
     * @param num
     * @param p
     * @return
     */
    public static String getDayNum(Integer num, String p) {
        Calendar calendar = Calendar.getInstance();
        //设置开始时间为周一
        calendar.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
        //上周几的时间，如果num为-1则为上周日，num为-7则为上周一
        calendar.add(Calendar.DATE, num);
        SimpleDateFormat sdf = new SimpleDateFormat(p);
        String day = sdf.format(calendar.getTime());
        return day;
    }

}
