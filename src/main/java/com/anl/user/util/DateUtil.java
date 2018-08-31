package com.anl.user.util;

import org.joda.time.DateTimeUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {

	public static long OFFSET = 0;

	public static final String DATE_FORMAT_FULL = "yyyy-MM-dd HH:mm:ss";

	public static final String DATE_FORMAT_SHORT = "yyyy-MM-dd";

	public static final String DATE_FORMAT_COMPACT = "yyyyMMdd";

	public static final String DATE_FORMAT_MONTH = "yyyyMM";

	public static final String DATE_FORMAT_COMPACTFULL = "yyyyMMddHHmmss";


	public static final String DATE_FORMAT_FULL_MSEL = "yyyyMMddHHmmssSSSS";
	public static final String DATE_FORMAT_FULL_MSE = "yyyyMMddHHmmssSSS";

	private static void setOffset() {
		DateTimeUtils.setCurrentMillisOffset(OFFSET);
	}

	public static Date getCurrentDate() {
		setOffset();
		Date currentDate = new Date(DateTimeUtils.currentTimeMillis());
		return currentDate;
	}

	/**
	 * 得到当前日期
	 * 
	 * @return String 当前日期 yyyyMMdd HH:mm:ss格式
	 * @author kevin
	 */
	public static String getCurDateTime() {
		DateFormat sdf = new SimpleDateFormat(DATE_FORMAT_FULL);
		return sdf.format(getCurrentDate());
	}

	public static String getCurDateTime(String format) {
		try {
			DateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(getCurrentDate());
		} catch (Exception e) {
			return getCurDateTime(); // 如果无法转化，则返回默认格式的时间。
		}
	}

	/**
	 * 获取当天开始的时间
	 * 
	 * @return
	 */
	public static Date getTodayStartTime() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getCurrentDate());
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static Date getFristDayOfMonth() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getCurrentDate());
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static Date afterNDaysDate(Date date, int day){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		// add方法中的第二个参数n中，正数表示该日期后n天，负数表示该日期的前n天
		calendar.add(Calendar.DATE, day);
		Date date1 = calendar.getTime();
		return date1;
	}
	public static Date afterNSecondsDate(int seconds) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getCurrentDate());
		cal.add(Calendar.SECOND, seconds);
		return cal.getTime();
	}
	public static Date afterNSecondsDate(Date date, int time) {
		Date dt = null;
		try {
			Calendar rightNow = Calendar.getInstance();
			rightNow.setTime(date);
			rightNow.add(Calendar.SECOND, time);
			dt = rightNow.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dt;
	}
	public static Date getLastDayOfMonth(){
		Calendar cal = Calendar.getInstance();
		cal.setTime(getCurrentDate());
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static Date getLastDayOfBeforeMonth(){
		Calendar cal = Calendar.getInstance();
		cal.setTime(getCurrentDate());
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static Date getFirstDayOfBeforeMonth() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getCurrentDate());
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static Date secondDay(){
		Calendar cal = Calendar.getInstance();
		cal.setTime(getTodayStartTime());
		cal.add(Calendar.DAY_OF_YEAR, 1);
		return cal.getTime();
	}

	public static Date afterNMonthDate(int month) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getCurrentDate());
		cal.add(Calendar.MONTH, month);
		return cal.getTime();
	}

	// 日期转成字符串
	public static String dateToString(Date date, String format) {
		String str = null;
		SimpleDateFormat df = new SimpleDateFormat(format);
		// df = DateFormat.getDateInstance(DateFormat.MEDIUM);
		str = df.format(date);
		return str;
	}
	
	/**
	 * 得到时间戳
	 *
	 * @return String 当前日期时间戳(yyyyMMddHHmmssSSS)
	 * @author kevin
	 */
	public static String getTimeStamp1() {
		try {
			Calendar now = Calendar.getInstance(TimeZone.getDefault());
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_FULL_MSE);
			sdf.setTimeZone(TimeZone.getDefault());
			return (sdf.format(now.getTime()));
		} catch (Exception e) {
			return getCurDateTime(); // 如果无法转化，则返回默认格式的时间。
		}
	}

	public static Date string2Date(String date) throws Exception{
		SimpleDateFormat formatter2  = new SimpleDateFormat("yyyyMMdd");
		return formatter2.parse(date);
	}

	public static int getDaysOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	public static boolean isEarlyMonth(){
		Calendar c = Calendar.getInstance();
		int today = c.get(c.DAY_OF_MONTH);
		if(today ==1){
			return true;
		}else{
			return false;
		}
	}

	public static boolean isLastDayOfMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(afterNDaysDate(new Date(), 1));
		if (calendar.get(Calendar.DAY_OF_MONTH) == 1) {
			return true;
		}
		return false;
	}

	// 比较两个字符串格式日期大小,带格式的日期
	public static boolean isBefore(String strdat1, String strdat2, String format) {
		try {
			Date dat1 = parseStringToDate(strdat1, format);
			Date dat2 = parseStringToDate(strdat2, format);
			return dat1.before(dat2);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 字符串转日期
	 *
	 * @return Date
	 * @author kevin
	 */
	public static Date parseStringToDate(String thedate, String format) {
		DateFormat sdf = new SimpleDateFormat(format);
		Date dd1 = null;
		try {
			dd1 = sdf.parse(thedate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dd1;
	}
	public static Date getFirstDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		return cal.getTime();
	}

	public static Date getLastDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MINUTE, 59);
		return calendar.getTime();
	}

	public static void main(String args[]) throws Exception {
		long a=System.currentTimeMillis();
		Date n=DateUtil.afterNDaysDate(new Date(),2);
		Date last=DateUtil.getLastDayOfMonth(n);
		System.out.println(DateUtil.isBefore(dateToString(n,DATE_FORMAT_COMPACT),dateToString(last,DATE_FORMAT_COMPACT),DATE_FORMAT_COMPACT));

	}

}