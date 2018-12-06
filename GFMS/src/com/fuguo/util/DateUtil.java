package com.fuguo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * 2.0    此版本非常规，周一为一周的第一天。
 * @author Administrator
 *
 */
public class DateUtil {
	/**
	 * 描述：根据日期获得是星期几的函数
	 * @param dt
	 * @return
	 */
	public static String getWeekOfDate(Date dt) {
		String[] weekDays = { "(日)", "(一)", "(二)", "(三)", "(四)", "(五)", "(六)" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);

		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return weekDays[w];

	}
	
	/**
	 * 描述：获取此date日期的这周的第一天（星期日为第一天）
	 * @param date
	 * @return Date
	 */
	public   Date   getWeek_Of_FirstDay(Date   date)   {   
        Calendar   cal   =   Calendar.getInstance();   
        cal.setTime(date);   
        int   dayOfWeek   =   cal.get(Calendar.DAY_OF_WEEK);   
        cal.add(Calendar.DATE,   (dayOfWeek   -   1)   *   (-1));   
        return   cal.getTime();   
	}
	
	/**
	 * *描述：获取是第几周
	 * @param 
	 * @return int
	 */
	public static int getweekNum(Date date){
		 Calendar   cal   =   Calendar.getInstance();   
	        cal.setTime(date);   
	        int   weekNum   =   cal.get(Calendar.WEEK_OF_YEAR);
	        return weekNum;
	}
	
	
	/**
	 * 描述：获取当前周的第一天（星期日为第一天）
	 * @param 
	 * @return Date
	 */
	public   Date   getThisWeek_Of_FirstDay()   {   
	    return   getWeek_Of_FirstDay(new   Date());   
	}

	/**
	 * 描述：获取当前周的最后一天（星期日为第一天）
	 * @param 
	 * @return Date
	 */
	public   Date   getThisWeek_Of_LastDay()   {   
        return   getWeek_Of_LastDay(new   Date());   
	} 
	
	/**
	 * 描述：获取此date日期的这周的最后一天（星期日为第一天）
	 * @param date
	 * @return Date
	 */
	public   Date   getWeek_Of_LastDay(Date   date)   {   
        Calendar   cal   =   Calendar.getInstance();   
        cal.setTime(date);   
        int   dayOfWeek   =   cal.get(Calendar.DAY_OF_WEEK);   
        cal.add(Calendar.DATE,   (dayOfWeek   -   1)   *   (-1)+6);   
        return   cal.getTime();   
	}
	/**
	 * 描述：获取此date日期的上周的第一天（星期日为第一天）
	 * @param date
	 * @return Date
	 */
	public   Date   getUpWeek_Of_FirstDay(Date   date)   {   
        Calendar   cal   =   Calendar.getInstance();   
        cal.setTime(date);   
        int   dayOfWeek   =   cal.get(Calendar.DAY_OF_WEEK);   
        cal.add(Calendar.DATE,   (dayOfWeek   -   1)   *   (-1)-1-6);   
        return   cal.getTime();   
	} 
	/**
	 * 描述：获取此date日期的上周的最后一天（星期日为第一天）
	 * @param date
	 * @return Date
	 */
	public   Date   getUpWeek_Of_LastDay(Date   date)   {   
        Calendar   cal   =   Calendar.getInstance();   
        cal.setTime(date);   
        int   dayOfWeek   =   cal.get(Calendar.DAY_OF_WEEK);   
        cal.add(Calendar.DATE,   (dayOfWeek   -   1)   *   (-1)-1);   
        return   cal.getTime();   
	}
	

	/**
	 * 描述：获取此date日期的下周的第一天（星期日为第一天）
	 * @param date
	 * @return Date
	 */
	public   Date   getNextWeek_Of_FirstDay(Date   date)   {   
        Calendar   cal   =   Calendar.getInstance();   
        cal.setTime(date);   
        int   dayOfWeek   =   cal.get(Calendar.DAY_OF_WEEK);   
        cal.add(Calendar.DATE,   (dayOfWeek   -   1)   *   (-1)+6+1);   
        return   cal.getTime();   
	}
	/**
	 * 描述：获取此date日期的下周的最后一天（星期日为第一天）
	 * @param date
	 * @return Date
	 */
	public   Date   getNextWeek_Of_LastDay(Date   date)   {   
        Calendar   cal   =   Calendar.getInstance();   
        cal.setTime(date);   
        int   dayOfWeek   =   cal.get(Calendar.DAY_OF_WEEK);   
        cal.add(Calendar.DATE,   (dayOfWeek   -   1)   *   (-1)+7+6);   
        return   cal.getTime();   
	}
	/**
	 * 描述：获取当前月的第一天的日期
	 * @param date
	 * @return Date
	 */
	public   Date   getThisMonthFirtDay()   {   
        return   getMonthFirtDay(new   Date());   
	}
	/**
	 * 描述：获取此date月的第一天的日期
	 * @param date
	 * @return Date
	 */
	public   Date   getMonthFirtDay(Date   date)   {   
        Calendar   cal   =   Calendar.getInstance();   
        cal.setTime(date);      
        cal.add(Calendar.DATE,-cal.get(Calendar.DATE)+1);   
        return   cal.getTime();   
	}
	
	/**
	 * 描述：获取当年的第n个月的第一天的日期
	 * @param int
	 * @return Date
	 */
	public   Date   getMonthFirtDay_Of_This_Year(int month)   {   
        Calendar   cal   =   Calendar.getInstance();        
        cal.add(Calendar.MONTH,-cal.get(Calendar.MONTH)+(month-1)); 
        cal.add(Calendar.DATE,-cal.get(Calendar.DATE)+1);
        return   cal.getTime();   
	}
	
	
	
	
	/**
	 * 描述：获取某年的第n个月的第一天的日期 //新增
	 * @param int int
	 * @return Date
	 */
	public   Date   getMonthFirtDay_Of_Which_Year(int year,int month)   {   
        Calendar   cal   =   Calendar.getInstance(); 
        //设定年份
        cal.add(Calendar.YEAR,-cal.get(Calendar.YEAR)+(year));
        cal.add(Calendar.MONTH,-cal.get(Calendar.MONTH)+(month-1)); 
        cal.add(Calendar.DATE,-cal.get(Calendar.DATE)+1);
        return   cal.getTime();   
	}
	
	
	/**
	 * 描述：获取当年的第n个月的最后一天的日期
	 * @param int
	 * @return Date
	 */
	public   Date   getMonthLastDay_Of_This_Year(int month)   {   
	    Calendar   cal   =   Calendar.getInstance();        
	    cal.add(Calendar.MONTH,-cal.get(Calendar.MONTH)+(month-1)+1); //先加上一个月       
	    cal.add(Calendar.DATE,-cal.get(Calendar.DATE)+1-1);//获取新的月份的第一天 - 1==先前的这个月的最后一天
	    return   cal.getTime();   
	}

	/**
	 * 描述：获取某的第n个月的最后一天的日期//新增
	 * @param int int
	 * @return Date
	 */
	public   Date   getMonthLastDay_Of_Which_Year(int year,int month)   {   
        Calendar   cal   =   Calendar.getInstance();  
        //设定年份
        cal.add(Calendar.YEAR,-cal.get(Calendar.YEAR)+(year));
        cal.add(Calendar.MONTH,-cal.get(Calendar.MONTH)+(month-1)+1); //先加上一个月       
        cal.add(Calendar.DATE,-cal.get(Calendar.DATE)+1-1);//获取新的月份的第一天 - 1==先前的这个月的最后一天
        return   cal.getTime();   
	}
	
	
	
	/**
	 * 描述：获取当前月的最后一天的日期
	 * @param date
	 * @return Date
	 */
	public   Date   getThisMonthLastDay()   {   
        return   getMonthLastDay(new   Date());   
	}
	/**
	 * 描述：获取此date月的最后一天的日期
	 * @param date
	 * @return Date
	 */
	public   Date   getMonthLastDay(Date   date)   {   
        Calendar   cal   =   Calendar.getInstance();   
        cal.setTime(date);      
        cal.add(Calendar.MONTH,+1);//先加上一个月       
        cal.add(Calendar.DATE,-cal.get(Calendar.DATE)+1-1);//获取新的月份的第一天 - 1==先前的这个月的最后一天
        return   cal.getTime();   
	}
	/**
	 * 描述：获取当前日期的前三天的日期
	 * @param date
	 * @return Date
	 */
	public   Date   getThisBeforeThreeDay()   {   
	    return   getBeforeThreeDay(new   Date());   
	}

	/**
	 * 描述：获取此date日期的前三天的日期
	 * @param date
	 * @return Date
	 */
	public   Date   getBeforeThreeDay(Date   date)   {   
	    Calendar   cal   =   Calendar.getInstance();   
	    cal.setTime(date);      
	    cal.add(Calendar.DATE,   3   *   (-1));   
	    return   cal.getTime();   
	}
	
	/**
	 * 描述：获取此date日期的前n天的日期
	 * @param date
	 * @return Date
	 */
	public  static Date   getBeforeNDay(Date   date,int N)   {   
	    Calendar   cal   =   Calendar.getInstance();   
	    cal.setTime(date);      
	    cal.add(Calendar.DATE,   N   *   (-1));   
	    return   cal.getTime();   
	}
	
	
	/**
	 * 描述：获取此date日期的前n月的日期
	 * @param date
	 * @return Date
	 */
	public  static Date   getBeforeNMonth(Date   date,int N)   {   
	    Calendar   cal   =   Calendar.getInstance();   
	    cal.setTime(date);      
	    cal.add(Calendar.MONTH,   N   *   (-1));   
	    return   cal.getTime();   
	}
	
	
	/**
	 * 描述：获取此date日期的后n月的日期
	 * @param date
	 * @return Date
	 */
	public  static Date   getAfertNMonth(Date   date,int N)   {   
	    Calendar   cal   =   Calendar.getInstance();   
	    cal.setTime(date);      
	    cal.add(Calendar.MONTH,   N);   
	    return   cal.getTime();   
	}

	/**
	 * 描述：获取此date日期的后7天的日期
	 * @param date
	 * @return Date
	 */
	public static  Date   getAfter7Day(Date   date)   {   
        Calendar   cal   =   Calendar.getInstance();   
        cal.setTime(date);      
        cal.add(Calendar.DATE,   7);   
        return   cal.getTime();   
	}
	public static  Date   getAfterNDay(Date   date,int n)   {   
        Calendar   cal   =   Calendar.getInstance();   
        cal.setTime(date);      
        cal.add(Calendar.DATE,   n);   
        return   cal.getTime();   
	}
	
	/**
	  * 获取两个日期中间的工作日
	  * @param starttime
	  * @param endtime
	  * @return
	  */
	 public static int getDateDiff(Date  dateFrom, Date dateTo){
	  
	  int workdays = 0;
	  Calendar cal = null;
	     while(dateFrom.before(dateTo)|| dateFrom.equals(dateTo)){
	         cal = Calendar.getInstance();
	         //设置日期
	        cal.setTime(dateFrom);
	       
	         workdays++;
	      
	        //日期加1
	        cal.add(Calendar.DAY_OF_MONTH,1);
	        dateFrom = cal.getTime();
	    }
	     return workdays;
	 }
	 
	 
	 
	 /**
		 * 描述：获取2个日期之间的月份数目；
		 * @param int int
		 * @return Date
		 */
		public  static int   getStringMonthOf_TwoDates(int year,int month,int year2,int month2)   {   
	        
			int yearNumbs=year2-year;
			
			int monthNumbs = month2-month;
			
			int counts=yearNumbs*12+monthNumbs+1;
			
	        return   counts;   
		}
	 
	public static void main(String[] args) {
		//System.out.println(Calendar.getInstance().get(Calendar.DATE));
		String dateExampleStr="2012-11-29";
		SimpleDateFormat   sdf   =   new   SimpleDateFormat("yyyy-MM-dd");   
		Date   dateExample=null;
		try {
			dateExample   =   sdf.parse(dateExampleStr);
		} catch (ParseException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		DateUtil   t   =   new   DateUtil();   
        Date   d1   =   t.getWeek_Of_FirstDay(dateExample);
        Date   d5   =   t.getWeek_Of_LastDay(dateExample);
        Date   d2   =   t.getBeforeThreeDay(dateExample);
        Date   d3   =   t.getMonthFirtDay(dateExample);
        Date   d4   =   t.getMonthLastDay(dateExample);
        
        
        String s1 = sdf.format(d1);
        String s2 = sdf.format(d2);
        
       
        
        String s3 = sdf.format(d3);
        String s4 = sdf.format(d4);
        String s5 = sdf.format(d5);
        
        System.out.println(s1 +"和"+s2 +"日期天数为"+getDateDiff(d1,d2));
        System.out.println(Calendar.DAY_OF_WEEK);
        System.out.println(dateExampleStr+"这周的第一天的日期为： "+s1);
        System.out.println(dateExampleStr+"这周的最后一天的日期为： "+s5);
        System.out.println(dateExampleStr+"的前三天的日期为： "+s2);
        System.out.println(dateExampleStr+"的月的第一天的日期为： "+s3);
        System.out.println(dateExampleStr+"的月的最后一天的日期为： "+s4);
        int month = 2;
        System.out.println("这一年的第"+month+"个月的第一天的日期是："+(t.getMonthFirtDay_Of_This_Year(month)).toLocaleString());
        System.out.println("这一年的第"+month+"个月的最后一天的日期是："+(t.getMonthLastDay_Of_This_Year(month)).toLocaleString());
        //测试 year month 的方法
        int year =2008;
        month=5;
        System.out.println(year+"年的第"+month+"个月的第一天的日期是："+(t.getMonthFirtDay_Of_Which_Year(year,month)).toLocaleString());
        System.out.println(year+"年的第"+month+"个月的最后一天的日期是："+(t.getMonthLastDay_Of_Which_Year(year,month)).toLocaleString());
        
        System.out.println(t.getBeforeNMonth(new Date(),2).toLocaleString());
        
        System.out.println(t.getweekNum(new Date()));
        
        System.out.println(t.getStringMonthOf_TwoDates(2015,1,2015,1));
        
        Date systemDate =new Date();
		int systemYear = systemDate.getYear()+1900;
		int systemMonth =systemDate.getMonth()+1;
		System.out.println(systemYear);
		System.out.println(systemMonth);
		
		System.out.println("-----------------------------------------");
		String dateaaa="2012-11-29";
		String datebbb="2012-11-30";
		
		try {
			Date date1 = sdf.parse(dateaaa);
			Date date2 = sdf.parse(datebbb);
			
			System.out.println(date1 +"和"+date2 +"日期天数为"+getDateDiff(date1,date2));
		} catch (ParseException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
        
		
	}
}
