package com.fuguo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * 此版本非常规，周一为一周的第一天。
 * @author Administrator
 *
 */
public class DateUtilWeekChina {
	
	
	/**
	 * 描述：获取此date日期的这周的第一天CHINA（星期一为第一天）
	 * @param date
	 * @return Date
	 */
	public   Date   getWeek_Of_FirstDay(Date   date)   {   
		Calendar   cal   =   Calendar.getInstance();
		cal.setTime(date); 
		int w = cal.get(Calendar.DAY_OF_WEEK);//获取是星期几；1：星期天，2：星期一。...
		int xingqiji = w-1;
		if(xingqiji==0){
			//对应这1-7
			xingqiji=7;//对应中国的日期1：星期一2：星期二
		}
		cal.add(Calendar.DATE,   (xingqiji-1)   *   (-1));  
		
		  
        return   cal.getTime();  
	}
	
	
	
	
	/**
	 * 描述：获取当前周的第一天CHINA（星期一为第一天）
	 * @param 
	 * @return Date
	 */
	public   Date   getThisWeek_Of_FirstDay()   {   
	    return   getWeek_Of_FirstDay(new   Date());   
	}

	/**
	 * 描述：获取当前周的最后一天（星期一为第一天）
	 * @param 
	 * @return Date
	 */
	public   Date   getThisWeek_Of_LastDay()   {   
        return   getWeek_Of_LastDay(new   Date());   
	} 
	
	/**
	 * 描述：获取此date日期的这周的最后一天（星期一为第一天）
	 * @param date
	 * @return Date
	 */
	public   Date   getWeek_Of_LastDay(Date   date)   {   
		Calendar   cal   =   Calendar.getInstance();
		cal.setTime(date); 
		int w = cal.get(Calendar.DAY_OF_WEEK);//获取是星期几；1：星期天，2：星期一。...
		int xingqiji = w-1;
		if(xingqiji==0){
			//对应这1-7
			xingqiji=7;
		}
		cal.add(Calendar.DATE,   7-xingqiji);  
        return   cal.getTime();   
	}
	/**
	 * 描述：获取此date日期的上周的第一天（星期一为第一天）
	 * @param date
	 * @return Date
	 */
	public   Date   getUpWeek_Of_FirstDay(Date   date)   {   
		Calendar   cal   =   Calendar.getInstance();
		cal.setTime(date); 
		int w = cal.get(Calendar.DAY_OF_WEEK);//获取是星期几；1：星期天，2：星期一。...
		int xingqiji = w-1;
		if(xingqiji==0){
			//对应这1-7
			xingqiji=7;
		}
		cal.add(Calendar.DATE,   (xingqiji-1)   *   (-1)-7);  
		
		  
        return   cal.getTime();   
	} 
	/**
	 * 描述：获取此date日期的上周的最后一天（星期一为第一天）
	 * @param date
	 * @return Date
	 */
	public   Date   getUpWeek_Of_LastDay(Date   date)   {   
		Calendar   cal   =   Calendar.getInstance();
		cal.setTime(date); 
		int w = cal.get(Calendar.DAY_OF_WEEK);//获取是星期几；1：星期天，2：星期一。...
		int xingqiji = w-1;
		if(xingqiji==0){
			//对应这1-7
			xingqiji=7;
		}
		cal.add(Calendar.DATE,   7-xingqiji-7);  
        return   cal.getTime();   
	}
	

	/**
	 * 描述：获取此date日期的下周的第一天（星期一为第一天）
	 * @param date
	 * @return Date
	 */
	public   Date   getNextWeek_Of_FirstDay(Date   date)   {   
		Calendar   cal   =   Calendar.getInstance();
		cal.setTime(date); 
		int w = cal.get(Calendar.DAY_OF_WEEK);//获取是星期几；1：星期天，2：星期一。...
		int xingqiji = w-1;
		if(xingqiji==0){
			//对应这1-7
			xingqiji=7;
		}
		cal.add(Calendar.DATE,   (xingqiji-1)   *   (-1)+7);  
		
		  
        return   cal.getTime();   
	}
	/**
	 * 描述：获取此date日期的下周的最后一天（星期一为第一天）
	 * @param date
	 * @return Date
	 */
	public   Date   getNextWeek_Of_LastDay(Date   date)   {   
		Calendar   cal   =   Calendar.getInstance();
		cal.setTime(date); 
		int w = cal.get(Calendar.DAY_OF_WEEK);//获取是星期几；1：星期天，2：星期一。...
		int xingqiji = w-1;
		if(xingqiji==0){
			//对应这1-7
			xingqiji=7;
		}
		cal.add(Calendar.DATE,   7-xingqiji + 7);  
        return   cal.getTime();
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
		DateUtilWeekChina   t   =   new   DateUtilWeekChina();   
        Date   d1   =   t.getNextWeek_Of_FirstDay(dateExample);
       
        Date   d2   =   t.getNextWeek_Of_LastDay(dateExample);
        String s1 = sdf.format(d1);
        String s2 = sdf.format(d2);
      
        
        System.out.println(dateExampleStr+"这xia周的第1天的日期为： "+s1);
        System.out.println(dateExampleStr+"这xia周的最后1天的日期为： "+s2);
       
        
        
	}
}
