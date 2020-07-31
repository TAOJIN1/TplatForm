/**
 * Copyright 2018 人人开源 http://www.renren.io
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package utils;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtils {
	/** 时间格式(yyyy-MM-dd) */
	public final static String DATE_PATTERN = "yyyy-MM-dd";
	/** 时间格式(yyyy-MM-dd HH:mm:ss) */
	public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     * @param date  日期
     * @return  返回yyyy-MM-dd格式日期
     */
	public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     * @param date  日期
     * @param pattern  格式，如：DateUtils.DATE_TIME_PATTERN
     * @return  返回yyyy-MM-dd格式日期
     */
    public static String format(Date date, String pattern) {
        if(date != null){
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    /**
     * 字符串转换成日期
     * @param strDate 日期字符串
     * @param pattern 日期的格式，如：DateUtils.DATE_TIME_PATTERN
     */
    public static Date stringToDate(String strDate, String pattern) {
        if (StringUtils.isBlank(strDate)){
            return null;
        }

        DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
        return fmt.parseLocalDateTime(strDate).toDate();
    }
    public static Date dateToDate(Date date, String pattern) {
    	if (date==null){
    		return null;
    	}
    	String strDate = format(date,pattern);
    	DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
    	return fmt.parseLocalDateTime(strDate).toDate();
    }

    /**
     * 根据周数，获取开始日期、结束日期
     * @param week  周期  0本周，-1上周，-2上上周，1下周，2下下周
     * @return  返回date[0]开始日期、date[1]结束日期
     */
    public static Date[] getWeekStartAndEnd(int week) {
        DateTime dateTime = new DateTime();
        LocalDate date = new LocalDate(dateTime.plusWeeks(week));

        date = date.dayOfWeek().withMinimumValue();
        Date beginDate = date.toDate();
        Date endDate = date.plusDays(6).toDate();
        Calendar c = Calendar.getInstance();
    	c.setTime(endDate);
    	c = endTwoThree(c);
        return new Date[]{beginDate, c.getTime()};
    }

    /**
     * 对日期的【秒】进行加/减
     *
     * @param date 日期
     * @param seconds 秒数，负数为减
     * @return 加/减几秒后的日期
     */
    public static Date addDateSeconds(Date date, int seconds) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusSeconds(seconds).toDate();
    }

    /**
     * 对日期的【分钟】进行加/减
     *
     * @param date 日期
     * @param minutes 分钟数，负数为减
     * @return 加/减几分钟后的日期
     */
    public static Date addDateMinutes(Date date, int minutes) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusMinutes(minutes).toDate();
    }

    /**
     * 对日期的【小时】进行加/减
     *
     * @param date 日期
     * @param hours 小时数，负数为减
     * @return 加/减几小时后的日期
     */
    public static Date addDateHours(Date date, int hours) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusHours(hours).toDate();
    }

    /**
     * 对日期的【天】进行加/减
     *
     * @param date 日期
     * @param days 天数，负数为减
     * @return 加/减几天后的日期
     */
    public static Date addDateDays(Date date, int days) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusDays(days).toDate();
    }

    /**
     * 对日期的【周】进行加/减
     *
     * @param date 日期
     * @param weeks 周数，负数为减
     * @return 加/减几周后的日期
     */
    public static Date addDateWeeks(Date date, int weeks) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusWeeks(weeks).toDate();
    }

    /**
     * 对日期的【月】进行加/减
     *
     * @param date 日期
     * @param months 月数，负数为减
     * @return 加/减几月后的日期
     */
    public static Date addDateMonths(Date date, int months) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusMonths(months).toDate();
    }

    /**
     * 对日期的【年】进行加/减
     *
     * @param date 日期
     * @param years 年数，负数为减
     * @return 加/减几年后的日期
     */
    public static Date addDateYears(Date date, int years) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusYears(years).toDate();
    }
    /**
     * 不考虑时分秒，对日期做减法，返回天数
     * @param date1
     * @param date2
     * @return date1-date2
     */
    public static int compareToDays(Date date1,Date date2)
    {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
       int day1= cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);
        
        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if(year1 != year2)   //不同年
        {
            int timeDistance = 0 ;
            for(int i = year2 ; i < year1 ; i ++)
            {
                if(i%4==0 && i%100!=0 || i%400==0)    //闰年            
                {
                    timeDistance += 366;
                }
                else    //不是闰年
                {
                    timeDistance += 365;
                }
            }
            return timeDistance + (day1-day2) ;
        }
        else    //同一年
        {
            return day1-day2;
        }
    }
    /**
     * 自定义日期拆分
     * 格式是"F2019-05-21T2019-05-29"
     * @param date
     * @return List<Date> ， list.get(0)=startDate ,list.get(1)=endDate
     */
    public static List<Date> fromDateToDate(String date) {
    	if(date==null) {
    		return null;
    	}
    	List<Date> list = new ArrayList<Date>();
    	Date startDate,endDate = null;
    	startDate = stringToDate(date.substring(1, date.indexOf("T", 0)),DateUtils.DATE_PATTERN);
    	endDate = stringToDate(date.substring(date.indexOf("T", 0)+1, date.length()),DateUtils.DATE_PATTERN);
    	Calendar c = Calendar.getInstance();
    	c.setTime(endDate);
    	c = endTwoThree(c);
    	list.add(startDate);
		list.add(c.getTime());
    	return list;
    }
    
    /**
     * 获取本月第一天和最后一天的年月日  
     * @return List<Date>,list.get(0)是startDate，list.get(1)是endDate
     */
    public static List<Date> thisMonth() {
    	List<Date> list = new ArrayList<Date>();
    	Date startDate,endDate = null;
    	//获取当前月第一天：
        Calendar c = Calendar.getInstance();    
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
        c = startZero(c);
        startDate = c.getTime();
        //获取当前月最后一天
        Calendar ca = Calendar.getInstance();    
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH)); 
        ca = endTwoThree(ca);
        endDate = ca.getTime();
        list.add(startDate);
		list.add(endDate);
    	return list;
    }
    /**
     * 获取最近指定天数的起始、终止日期，常用于查询条件“近一周”、“近两周”等
     * 以当前日期为终止日期endDate，往前数num天作为起始日期startDate，
     * @param num(整数)
     * @return List<Date>,list.get(0)是startDate，list.get(1)是endDate
     */
    public static List<Date> nearDays(String num) {
    	if(num == null) {
    		return null;
    	}
    	List<Date> list = new ArrayList<Date>();
    	Date startDate,endDate = null;
    	Calendar calender = Calendar.getInstance();
    	calender = endTwoThree(calender);
    	endDate = calender.getTime();
		calender.add(Calendar.DATE, Integer.parseInt("-"+num));
		calender = startZero(calender);
		startDate = calender.getTime();
		list.add(startDate);
		list.add(endDate);
    	return list;
    }
    /**
     * 通用查询条件处理
     * @param param 查询本周时param=101，本月时param=102；近一周，近两周等固定天数时param=天数；自定义时间，param格式为"Fdate1Tdate2"
     * @return List<Date>;  list.get(0)是startDate，list.get(1)是endDate
     */
    public static List<Date> queryDateProcess(String param){
    	if(param == null) {
    		return null;
    	}
    	List<Date> list = new ArrayList<Date>();
		if(param.startsWith("F")) {
			list = fromDateToDate(param);
		}else if(param.equals("101")){//本周应完成
			list = Arrays.asList(getWeekStartAndEnd(0));
		}else if(param.equals("102")) {//本月应完成
			list = thisMonth();
		}else{
			list = nearDays(param);
		}
    	return list;
    }
    /**
     * 设置时间从零点零分零秒开始
     * @param Calendar
     * @return
     */
    public static Calendar startZero(Calendar c) {
    	if(c==null) {
    		return null;
    	}
        c.set(Calendar.HOUR_OF_DAY, 0);//
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c;
    }
    /**
     * 设置时间23点59分59秒结束
     * @param Calendar
     * @return
     */
    public static Calendar endTwoThree(Calendar c) {
    	if(c==null) {
    		return null;
    	}
        c.set(Calendar.HOUR_OF_DAY, 23);//
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 999);
        return c;
    }
    /**
	 * 获取某年第一天日期
	 * @param year 年份
	 * @return Date
	 */
	public static Date getYearFirst(int year){
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		Date currYearFirst = calendar.getTime();
		return currYearFirst;
	}

    
    /**
	 * 获取某年最后一天日期
	 * @param year 年份
	 * @return Date
	 */
	public static Date getYearLast(int year){
		SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN);
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
		Date currYearLast = calendar.getTime();
		
		return currYearLast;
	}

    public static void main(String[] args) {
    	Calendar date = Calendar.getInstance();
    	Integer year = Integer.valueOf(date.get(Calendar.YEAR));
    	Date yearLast = getYearLast(year);
	}
}
