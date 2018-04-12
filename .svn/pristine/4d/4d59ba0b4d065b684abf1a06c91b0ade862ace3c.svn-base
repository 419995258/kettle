package org.my431.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

public class DateFormater {
    //用来全局控制 上一周，本周，下一周的周数变化    
    private  int weeks = 0;    
    private int MaxDate;//一月最大天数    
    private int MaxYear;//一年最大天数
	public DateFormater() {
	}

	public static String DateToString(Date date, String pattern) {
		String strDateTime = null;
		SimpleDateFormat formater = new SimpleDateFormat(pattern);
		strDateTime = date == null ? null : formater.format(date);
		return strDateTime;
	}

	public static String DateToString(Date date) {
		String _pattern = "yyyy-MM-dd";
		return date == null ? null : DateToString(date, _pattern);
	}

	public static String DateTimeToString(Date date) {
		String _pattern = "yyyy-MM-dd HH:mm:ss";
		return date == null ? null : DateToString(date, _pattern);
	}
	/**格式化到毫秒*/
	public static String DateTimeToMsString(Date date) {
		String _pattern = "yyyy-MM-dd HH:mm:ss:SSS";
		return date == null ? null : DateToString(date, _pattern);
	}
	
	public static Date StringToDate(String str, String pattern) {
		Date dateTime = null;
		try {
			if (str != null && !str.equals("")) {
				SimpleDateFormat formater = new SimpleDateFormat(pattern);
				dateTime = formater.parse(str);
			}
		} catch (Exception ex) {
		}
		return dateTime;
	}

	public static Date StringToDate(String str) {
		String _pattern = "yyyy-MM-dd";
		return StringToDate(str, _pattern);
	}

	public static Date StringToDateTime(String str) {
		String _pattern = "yyyy-MM-dd HH:mm:ss";
		return StringToDate(str, _pattern);
	}

	public static Timestamp StringToDateHMS(String str) throws Exception {
		Timestamp time = null;
		time = Timestamp.valueOf(str);
		return time;
	}

	public static Date YmdToDate(int year, int month, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day);
		return calendar.getTime();
	}

	public static String communityDateToString(Date date) {
		SimpleDateFormat formater = new SimpleDateFormat("MM/dd HH:mm:ss");
		String strDateTime = date == null ? null : formater.format(date);
		return strDateTime;
	}

	public static Date getMaxDateOfDay(Date date) {
		if (date == null) {
			return null;
		} else {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(11, calendar.getActualMaximum(11));
			calendar.set(12, calendar.getActualMaximum(12));
			calendar.set(13, calendar.getActualMaximum(13));
			calendar.set(14, calendar.getActualMaximum(14));
			return calendar.getTime();
		}
	}

	public static Date getMinDateOfDay(Date date) {
		if (date == null) {
			return null;
		} else {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(11, calendar.getActualMinimum(11));
			calendar.set(12, calendar.getActualMinimum(12));
			calendar.set(13, calendar.getActualMinimum(13));
			calendar.set(14, calendar.getActualMinimum(14));
			return calendar.getTime();
		}
	}

	/**
	 * 取指定日后n天，n允许为负数
	 * 
	 * @param date
	 * @param afterDays
	 * @return java.util.Date
	 */
	public static Date getAfterDay(Date date, int afterDays) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, afterDays);
		return cal.getTime();
	}

	// day
	public static int DateDiff(Date date1, Date date2) {
		int i = (int) ((date1.getTime() - date2.getTime()) / 3600 / 24 / 1000);
		return i;
	}
	/**
	 * 
     *类的描述:DateDiffToToday<br>
     *功能描述 ：查询距今天相差几天<br>
     *作者:hyl<br>
     *创建日期:2015-07-08<br>
     *修改人：<br>
     *修改日期：<br>
     *修改原因描述:<br>
	 * @throws ParseException 
	 */
	public static int DateDiffToToday(Date date) throws ParseException {
		int i=0;
		if(date!=null){
			String datebefore=DateToString(date, "yyyy-MM-dd");
			String datenow=DateToString(new Date(), "yyyy-MM-dd");
			Date  resDate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(datebefore+" 00:00:01");
			Date  nowDate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(datenow+" 00:00:01");
			long diff=nowDate.getTime()-resDate.getTime();
			i=(int) (diff/(24*60*60*1000));
		}
		return i;
	}
	// min
	public static int MinDiff(Date date1, Date date2) {
		int i = (int) ((date1.getTime() - date2.getTime()) / 1000 / 60);
		return i;
	}

	// second
	public static int TimeDiff(Date date1, Date date2) {
		int i = (int) ((date1.getTime() - date2.getTime()));
		return i;
	}
	
	/**
	 *  当前年的开始时间，如：2012-01-01 00:00:00
	 * author  90  
	 * on 2015-4-10
	 * @param year 年份
	 * @return
	 */
    public static  Date getYearStartTime(String year) {
    	SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd"); 
    	Calendar c = Calendar.getInstance();
    	Date dateVar=StringToDate(year,"yyyy");
    	c.setTime(dateVar);
        Date now = null;
        try {
            c.set(Calendar.MONTH, 0);
            c.set(Calendar.DATE, 1);
            now = shortSdf.parse(shortSdf.format(c.getTime()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    } 
   
    /**
     * 当前年的结束时间，如2012-12-31 23:59:59
     * author  90  
     * on 2015-4-10
     * @param year
     * @return
     */
    public  static Date getYearEndTime(String year) {
    	SimpleDateFormat longSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
    	SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd"); 
        Calendar c = Calendar.getInstance();
        Date dateVar=StringToDate(year,"yyyy");
        c.setTime(dateVar);
        Date now = null;
        try {
            c.set(Calendar.MONTH, 11);
            c.set(Calendar.DATE, 31);
            now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    } 
	
	
	
	/**   
     * @param args   
     */   
    public static void main(String[] args) {    
    	//System.out.println(DateToString(new Date(), "yyyyMMddHHmmss"));
    	//System.out.println(DateTimeToMsString(new Date()));
    	//System.out.println(DateTimeToMsString(new Date()));
    	//System.out.println(DateTimeToMsString(new Date()));
    	//        System.out.println("获取当天日期:"+tt.getNowTime("yyyy-MM-dd"));    
//        System.out.println("获取本周一日期:"+tt.getMondayOFWeek());    
//        System.out.println("获取本周日的日期~:"+tt.getCurrentWeekday());    
//        System.out.println("获取上周一日期:"+tt.getPreviousWeekday());    
//        System.out.println("获取上周日日期:"+tt.getPreviousWeekSunday());    
//        System.out.println("获取下周一日期:"+tt.getNextMonday());    
//        System.out.println("获取下周日日期:"+tt.getNextSunday());    
//        System.out.println("获得相应周的周六的日期:"+tt.getNowTime("yyyy-MM-dd"));    
//        System.out.println("获取本月第一天日期:"+tt.getFirstDayOfMonth());    
//        System.out.println("获取本月最后一天日期:"+tt.getDefaultDay());    
//        System.out.println("获取上月第一天日期:"+tt.getPreviousMonthFirst());    
//        System.out.println("获取上月最后一天的日期:"+tt.getPreviousMonthEnd());    
//        System.out.println("获取下月第一天日期:"+tt.getNextMonthFirst());    
//        System.out.println("获取下月最后一天日期:"+tt.getNextMonthEnd());    
//        System.out.println("获取本年的第一天日期:"+tt.getCurrentYearFirst());    
//        System.out.println("获取本年最后一天日期:"+tt.getCurrentYearEnd());    
//        System.out.println("获取去年的第一天日期:"+tt.getPreviousYearFirst());    
//        System.out.println("获取去年的最后一天日期:"+tt.getPreviousYearEnd());    
//        System.out.println("获取明年第一天日期:"+tt.getNextYearFirst());    
//        System.out.println("获取明年最后一天日期:"+tt.getNextYearEnd());    
//        System.out.println("获取本季度第一天到最后一天:"+tt.getThisSeasonTime(11));    
//        System.out.println("获取两个日期之间间隔天数2008-12-1~2008-9.29:"+TimeTest.getTwoDay("2008-12-1","2008-9-29"));    
    }    
        
        
    /**   
        * 得到二个日期间的间隔天数   
        */   
    public static String getTwoDay(String sj1, String sj2) {    
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");    
        long day = 0;    
        try {    
         java.util.Date date = myFormatter.parse(sj1);    
         java.util.Date mydate = myFormatter.parse(sj2);    
         day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);    
        } catch (Exception e) {    
         return "";    
        }    
        return day + "";    
    }    
   
   
    /**   
        * 根据一个日期，返回是星期几的字符串   
        *    
        * @param sdate   
        * @return   
        */   
    public static String getWeek(String sdate) {    
        // 再转换为时间    
        Date date = strToDate(sdate);    
        Calendar c = Calendar.getInstance();    
        c.setTime(date);    
        // int hour=c.get(Calendar.DAY_OF_WEEK);    
        // hour中存的就是星期几了，其范围 1~7    
        // 1=星期日 7=星期六，其他类推    
        return new SimpleDateFormat("EEEE").format(c.getTime());    
    }    
   
    /**   
        * 将短时间格式字符串转换为时间 yyyy-MM-dd    
        *    
        * @param strDate   
        * @return   
        */   
    public static Date strToDate(String strDate) {    
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");    
        ParsePosition pos = new ParsePosition(0);    
        Date strtodate = formatter.parse(strDate, pos);    
        return strtodate;    
    }    
   
    /**   
        * 两个时间之间的天数   
        *    
        * @param date1   
        * @param date2   
        * @return   
        */   
    public static long getDays(String date1, String date2) {    
        if (date1 == null || date1.equals(""))    
         return 0;    
        if (date2 == null || date2.equals(""))    
         return 0;    
        // 转换为标准时间    
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");    
        java.util.Date date = null;    
        java.util.Date mydate = null;    
        try {    
         date = myFormatter.parse(date1);    
         mydate = myFormatter.parse(date2);    
        } catch (Exception e) {    
        }    
        long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);    
        return day;    
    }    
   
   
   
        
    // 计算当月最后一天,返回字符串    
    public String getDefaultDay(){      
       String str = "";    
       SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");        
   
       Calendar lastDate = Calendar.getInstance();    
       lastDate.set(Calendar.DATE,1);//设为当前月的1号    
       lastDate.add(Calendar.MONTH,1);//加一个月，变为下月的1号    
       lastDate.add(Calendar.DATE,-1);//减去一天，变为当月最后一天    
           
       str=sdf.format(lastDate.getTime());    
       return str;      
    }    
        
    // 上月第一天    
    public String getPreviousMonthFirst(){      
       String str = "";    
       SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");        
   
       Calendar lastDate = Calendar.getInstance();    
       lastDate.set(Calendar.DATE,1);//设为当前月的1号    
       lastDate.add(Calendar.MONTH,-1);//减一个月，变为下月的1号    
       //lastDate.add(Calendar.DATE,-1);//减去一天，变为当月最后一天    
           
       str=sdf.format(lastDate.getTime());    
       return str;      
    }    
        
    //获取当月第一天    
    public String getFirstDayOfMonth(){      
       String str = "";    
       SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");        
   
       Calendar lastDate = Calendar.getInstance();    
       lastDate.set(Calendar.DATE,1);//设为当前月的1号    
       str=sdf.format(lastDate.getTime());    
       return str;      
    }    
        
    // 获得本周星期日的日期      
    public String getCurrentWeekday() {    
        weeks = 0;    
        int mondayPlus = this.getMondayPlus();    
        GregorianCalendar currentDate = new GregorianCalendar();    
        currentDate.add(GregorianCalendar.DATE, mondayPlus+6);    
        Date monday = currentDate.getTime();    
            
        DateFormat df = DateFormat.getDateInstance();    
        String preMonday = df.format(monday);    
        return preMonday;    
    }    
        
        
    //获取当天时间     
    public String getNowTime(String dateformat){    
        Date   now   =   new   Date();       
        SimpleDateFormat   dateFormat   =   new   SimpleDateFormat(dateformat);//可以方便地修改日期格式       
        String  hehe  = dateFormat.format(now);       
        return hehe;    
    }    
        
    // 获得当前日期与本周日相差的天数    
    private int getMondayPlus() {    
        Calendar cd = Calendar.getInstance();    
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......    
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK)-1;         //因为按中国礼拜一作为第一天所以这里减1    
        if (dayOfWeek == 1) {    
            return 0;    
        } else {    
            return 1 - dayOfWeek;    
        }    
    }    
        
    //获得本周一的日期    
    public String getMondayOFWeek(){    
         weeks = 0;    
         int mondayPlus = this.getMondayPlus();    
         GregorianCalendar currentDate = new GregorianCalendar();    
         currentDate.add(GregorianCalendar.DATE, mondayPlus);    
         Date monday = currentDate.getTime();    
             
         DateFormat df = DateFormat.getDateInstance();    
         String preMonday = df.format(monday);    
         return preMonday;    
    }    
        
  //获得相应周的周六的日期    
    public String getSaturday() {    
        int mondayPlus = this.getMondayPlus();    
        GregorianCalendar currentDate = new GregorianCalendar();    
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks + 6);    
        Date monday = currentDate.getTime();    
        DateFormat df = DateFormat.getDateInstance();    
        String preMonday = df.format(monday);    
        return preMonday;    
    }    
        
 // 获得上周星期日的日期    
    public String getPreviousWeekSunday() {    
        weeks=0;    
        weeks--;    
        int mondayPlus = this.getMondayPlus();    
        GregorianCalendar currentDate = new GregorianCalendar();    
        currentDate.add(GregorianCalendar.DATE, mondayPlus+weeks);    
        Date monday = currentDate.getTime();    
        DateFormat df = DateFormat.getDateInstance();    
        String preMonday = df.format(monday);    
        return preMonday;    
    }    
   
 // 获得上周星期一的日期    
    public String getPreviousWeekday() {    
        weeks--;    
        int mondayPlus = this.getMondayPlus();    
        GregorianCalendar currentDate = new GregorianCalendar();    
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks);    
        Date monday = currentDate.getTime();    
        DateFormat df = DateFormat.getDateInstance();    
        String preMonday = df.format(monday);    
        return preMonday;    
    }    
        
    // 获得下周星期一的日期    
    public String getNextMonday() {    
        weeks++;    
        int mondayPlus = this.getMondayPlus();    
        GregorianCalendar currentDate = new GregorianCalendar();    
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 7);    
        Date monday = currentDate.getTime();    
        DateFormat df = DateFormat.getDateInstance();    
        String preMonday = df.format(monday);    
        return preMonday;    
    }    
        
 // 获得下周星期日的日期    
    public String getNextSunday() {    
            
        int mondayPlus = this.getMondayPlus();    
        GregorianCalendar currentDate = new GregorianCalendar();    
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 7+6);    
        Date monday = currentDate.getTime();    
        DateFormat df = DateFormat.getDateInstance();    
        String preMonday = df.format(monday);    
        return preMonday;    
    }    
        
        
        
    private int getMonthPlus(){    
        Calendar cd = Calendar.getInstance();    
        int monthOfNumber = cd.get(Calendar.DAY_OF_MONTH);    
        cd.set(Calendar.DATE, 1);//把日期设置为当月第一天     
        cd.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天     
        MaxDate=cd.get(Calendar.DATE);     
        if(monthOfNumber == 1){    
            return -MaxDate;    
        }else{    
            return 1-monthOfNumber;    
        }    
    }    
        
  //获得上月最后一天的日期    
    public String getPreviousMonthEnd(){    
        String str = "";    
       SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");        
   
       Calendar lastDate = Calendar.getInstance();    
      lastDate.add(Calendar.MONTH,-1);//减一个月    
      lastDate.set(Calendar.DATE, 1);//把日期设置为当月第一天     
      lastDate.roll(Calendar.DATE, -1);//日期回滚一天，也就是本月最后一天     
       str=sdf.format(lastDate.getTime());    
       return str;      
    }    
        
  //获得下个月第一天的日期    
    public String getNextMonthFirst(){    
        String str = "";    
       SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");        
   
       Calendar lastDate = Calendar.getInstance();    
      lastDate.add(Calendar.MONTH,1);//减一个月    
      lastDate.set(Calendar.DATE, 1);//把日期设置为当月第一天     
       str=sdf.format(lastDate.getTime());    
       return str;      
    }    
        
  //获得下个月最后一天的日期    
    public String getNextMonthEnd(){    
        String str = "";    
       SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");        
   
       Calendar lastDate = Calendar.getInstance();    
      lastDate.add(Calendar.MONTH,1);//加一个月    
      lastDate.set(Calendar.DATE, 1);//把日期设置为当月第一天     
      lastDate.roll(Calendar.DATE, -1);//日期回滚一天，也就是本月最后一天     
       str=sdf.format(lastDate.getTime());    
       return str;      
    }    
        
    //获得明年最后一天的日期    
    public String getNextYearEnd(){    
        String str = "";    
       SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");        
   
      Calendar lastDate = Calendar.getInstance();    
      lastDate.add(Calendar.YEAR,1);//加一个年    
      lastDate.set(Calendar.DAY_OF_YEAR, 1);    
     lastDate.roll(Calendar.DAY_OF_YEAR, -1);    
       str=sdf.format(lastDate.getTime());    
       return str;      
    }    
        
  //获得明年第一天的日期    
    public String getNextYearFirst(){    
        String str = "";    
       SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");        
   
      Calendar lastDate = Calendar.getInstance();    
      lastDate.add(Calendar.YEAR,1);//加一个年    
      lastDate.set(Calendar.DAY_OF_YEAR, 1);    
           str=sdf.format(lastDate.getTime());    
      return str;      
            
    }    
        
  //获得本年有多少天    
    private int getMaxYear(){    
        Calendar cd = Calendar.getInstance();    
        cd.set(Calendar.DAY_OF_YEAR,1);//把日期设为当年第一天    
        cd.roll(Calendar.DAY_OF_YEAR,-1);//把日期回滚一天。    
        int MaxYear = cd.get(Calendar.DAY_OF_YEAR);     
        return MaxYear;    
    }    
        
    private int getYearPlus(){    
        Calendar cd = Calendar.getInstance();    
        int yearOfNumber = cd.get(Calendar.DAY_OF_YEAR);//获得当天是一年中的第几天    
        cd.set(Calendar.DAY_OF_YEAR,1);//把日期设为当年第一天    
        cd.roll(Calendar.DAY_OF_YEAR,-1);//把日期回滚一天。    
        int MaxYear = cd.get(Calendar.DAY_OF_YEAR);    
        if(yearOfNumber == 1){    
            return -MaxYear;    
        }else{    
            return 1-yearOfNumber;    
        }    
    }    
  //获得本年第一天的日期    
    public String getCurrentYearFirst(){    
        int yearPlus = this.getYearPlus();    
        GregorianCalendar currentDate = new GregorianCalendar();    
        currentDate.add(GregorianCalendar.DATE,yearPlus);    
        Date yearDay = currentDate.getTime();    
        DateFormat df = DateFormat.getDateInstance();    
        String preYearDay = df.format(yearDay);    
        return preYearDay;    
    }    
        
        
  //获得本年最后一天的日期 *    
    public String getCurrentYearEnd(){    
        Date date = new Date();    
        SimpleDateFormat   dateFormat   =   new   SimpleDateFormat("yyyy");//可以方便地修改日期格式       
        String  years  = dateFormat.format(date);       
        return years+"-12-31";    
    }    
        
        
  //获得上年第一天的日期 *    
    public String getPreviousYearFirst(){    
        Date date = new Date();    
        SimpleDateFormat   dateFormat   =   new   SimpleDateFormat("yyyy");//可以方便地修改日期格式       
        String  years  = dateFormat.format(date); int years_value = Integer.parseInt(years);      
        years_value--;    
        return years_value+"-1-1";    
    }    
        
  //获得上年最后一天的日期    
    public String getPreviousYearEnd(){    
        weeks--;    
        int yearPlus = this.getYearPlus();    
        GregorianCalendar currentDate = new GregorianCalendar();    
        currentDate.add(GregorianCalendar.DATE,yearPlus+MaxYear*weeks+(MaxYear-1));    
        Date yearDay = currentDate.getTime();    
        DateFormat df = DateFormat.getDateInstance();    
        String preYearDay = df.format(yearDay);    
        getThisSeasonTime(11);    
        return preYearDay;    
    }    
        
  //获得本季度    
    public String getThisSeasonTime(int month){    
        int array[][] = {{1,2,3},{4,5,6},{7,8,9},{10,11,12}};    
        int season = 1;    
        if(month>=1&&month<=3){    
            season = 1;    
        }    
        if(month>=4&&month<=6){    
            season = 2;    
        }    
        if(month>=7&&month<=9){    
            season = 3;    
        }    
        if(month>=10&&month<=12){    
            season = 4;    
        }    
        int start_month = array[season-1][0];    
        int end_month = array[season-1][2];    
            
        Date date = new Date();    
        SimpleDateFormat   dateFormat   =   new   SimpleDateFormat("yyyy");//可以方便地修改日期格式       
        String  years  = dateFormat.format(date);       
        int years_value = Integer.parseInt(years);    
            
        int start_days =1;//years+"-"+String.valueOf(start_month)+"-1";//getLastDayOfMonth(years_value,start_month);    
        int end_days = getLastDayOfMonth(years_value,end_month);    
        String seasonDate = years_value+"-"+start_month+"-"+start_days+";"+years_value+"-"+end_month+"-"+end_days;    
        return seasonDate;    
            
    }    
        
    /**   
     * 获取某年某月的最后一天   
     * @param year 年   
     * @param month 月   
     * @return 最后一天   
     */   
   private int getLastDayOfMonth(int year, int month) {    
         if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8   
                   || month == 10 || month == 12) {    
               return 31;    
         }    
         if (month == 4 || month == 6 || month == 9 || month == 11) {    
               return 30;    
         }    
         if (month == 2) {    
               if (isLeapYear(year)) {    
                   return 29;    
               } else {    
                   return 28;    
               }    
         }    
         return 0;    
   }    
   /**   
    * 是否闰年   
    * @param year 年   
    * @return    
    */   
  public boolean isLeapYear(int year) {    
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);    
  }  

	public static Date TimestampToDate(String str){
		String _pattern = "yyyy-MM-dd";
		SimpleDateFormat formater = new SimpleDateFormat(_pattern);
		Timestamp time = new Timestamp(Long.valueOf(str));
		return StringToDate(formater.format(time),_pattern);
	} 
	
	  // 计算当月最后一天,返回字符串    
	  public String getLastDay(int year,int month,int day){      
	     String str = "";    
	     SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");        
	 
	     Calendar lastDate = new GregorianCalendar(year, month, day,0,0,0); 
	     lastDate.set(Calendar.DATE,1);//设为当前月的1号    
	     lastDate.add(Calendar.MONTH,1);//加一个月，变为下月的1号    
	     lastDate.add(Calendar.DATE,-1);//减去一天，变为当月最后一天    
	         
	     str=sdf.format(lastDate.getTime());    
	     return str;      
	  } 
	  
	  /** 
	     * 设置时间 
	     * @param year 
	     * @param month 
	     * @param date 
	     * @return 
	     */  
	    public static Calendar setCalendar(int year,int month,int date){  
	        Calendar cl = Calendar.getInstance();  
	        cl.set(year, month-1, date);  
	        return cl;  
	    }  
	    
	    
	    /** 
	     * 获取当前时间的前一天时间 
	     * @param cl 
	     * @return 
	     */  
	    public static Calendar getBeforeDay(Calendar cl){  
	        //使用roll方法进行向前回滚  
	        //cl.roll(Calendar.DATE, -1);  
	        //使用set方法直接进行设置  
	        int day = cl.get(Calendar.DATE);  
	        cl.set(Calendar.DATE, day-1);  
	        return cl;  
	    }  
	    /** 
	     * 获取系统时间的前一天时间 
	     * @param cl 
	     * @return 
	     */  
	    public static String getBeforeDay(){  
	        Calendar calendar=Calendar.getInstance();
	        calendar.add(Calendar.DATE, -1);
	        return DateToString(calendar.getTime());  
	    }   
	    /** 
	     * 获取当前时间的后一天时间 
	     * @param cl 
	     * @return 
	     */  
	    public static Calendar getAfterDay(Calendar cl){  
	        //使用roll方法进行回滚到后一天的时间  
	        //cl.roll(Calendar.DATE, 1);  
	        //使用set方法直接设置时间值  
	        int day = cl.get(Calendar.DATE);  
	        cl.set(Calendar.DATE, day+1);  
	        return cl;  
	    } 
	    /** 
	     * 打印时间 
	     * @param cl 
	     */  
	    public static void printCalendar(Calendar cl){  
	        int year = cl.get(Calendar.YEAR);  
	        int month = cl.get(Calendar.MONTH)+1;  
	        int day = cl.get(Calendar.DATE);  
	        System.out.println(year+"-"+month+"-"+day);  
	    }

	// 由出生日期获得年龄
	public static int getAge(String birthDayStr){
		int age = 0;
		if (StringUtils.isNotBlank(birthDayStr)) {//a
			Date birthDay = StringToDate(birthDayStr);
			Calendar cal = Calendar.getInstance();
			
			if (cal.before(birthDay)) {
				throw new IllegalArgumentException(
						"The birthDay is before Now.It's unbelievable!");
			}
			int yearNow = cal.get(Calendar.YEAR);
			int monthNow = cal.get(Calendar.MONTH);
			int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
			cal.setTime(birthDay);
			
			int yearBirth = cal.get(Calendar.YEAR);
			int monthBirth = cal.get(Calendar.MONTH);
			int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
			
			age = yearNow - yearBirth;
			
			if (monthNow <= monthBirth) {
				if (monthNow == monthBirth) {
					if (dayOfMonthNow < dayOfMonthBirth)
						age--;
				} else {
					age--;
				}
			}
		}//a
		return age;
	} 
	/**
	 * 
	* @Description: 获得一个范围日期里面的每一个月份 
	* @author hyl     
	* @throws ParseException 
	* @date 2017-8-23 上午11:37:55  
	 */
	public static List<String> getDatesFromSomeDate(String datestr) throws ParseException {
		Date d1 = new SimpleDateFormat("yyyy-MM").parse(datestr);//定义起始日期
		Date d2 = new Date();//定义起始日期
		Calendar dd = Calendar.getInstance();//定义日期实例
		dd.setTime(d1);//设置日期起始时间
		List<String> list = new ArrayList<String>();
		while(dd.getTime().before(d2)){//判断是否到结束日期
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			String str = sdf.format(dd.getTime());
			System.out.println(str);//输出日期结果
			list.add(str);
			dd.add(Calendar.MONTH, 1);//进行当前日期月份加1
		}
		//list = list.subList(0, list.size()-1);
		return list;
	}
	/**
	 * 
	* @Description: 获取两个年之间的年
	* @author hyl     
	* @throws ParseException 
	* @date 2017-8-23 上午11:37:55  
	 */
	public static List<String> getYearsFromSomeYear(String startYear,String endYear){
		List<String> list = new ArrayList<String>();
		if (StringUtils.isBlank(startYear) || StringUtils.isBlank(endYear) ) {
			return null;
		}else {
			if (MMap.isnullInt(endYear)<MMap.isnullInt(startYear)) {
				return null;
			}
			for (int i = 0; i <= MMap.isnullInt(endYear)-MMap.isnullInt(startYear); i++) {
				list.add((MMap.isnullInt(startYear)+i)+"");
			}
		}
		//list = list.subList(0, list.size()-1);
		return list;
	}
	/**
	 * 
	* @Description: 获得一个范围日期里面的每一个年份 
	* @author hyl     
	* @throws ParseException 
	* @date 2017-8-23 上午11:37:55  
	 */
	public static List<String> getDatesFromSomeDateYear(String datestr) throws ParseException {
		Date d1 = new SimpleDateFormat("yyyy").parse(datestr);//定义起始日期
		Date d2 = new Date();//定义起始日期
		Calendar dd = Calendar.getInstance();//定义日期实例
		dd.setTime(d1);//设置日期起始时间
		List<String> list = new ArrayList<String>();
		while(dd.getTime().before(d2)){//判断是否到结束日期
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			String str = sdf.format(dd.getTime());
			System.out.println(str);//输出日期结果
			list.add(str);
			dd.add(Calendar.YEAR, 1);//进行当前日期月份加1
		}
		//list = list.subList(0, list.size()-1);
		return list;
	}
}