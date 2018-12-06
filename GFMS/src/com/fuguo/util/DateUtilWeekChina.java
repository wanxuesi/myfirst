package com.fuguo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * �˰汾�ǳ��棬��һΪһ�ܵĵ�һ�졣
 * @author Administrator
 *
 */
public class DateUtilWeekChina {
	
	
	/**
	 * ��������ȡ��date���ڵ����ܵĵ�һ��CHINA������һΪ��һ�죩
	 * @param date
	 * @return Date
	 */
	public   Date   getWeek_Of_FirstDay(Date   date)   {   
		Calendar   cal   =   Calendar.getInstance();
		cal.setTime(date); 
		int w = cal.get(Calendar.DAY_OF_WEEK);//��ȡ�����ڼ���1�������죬2������һ��...
		int xingqiji = w-1;
		if(xingqiji==0){
			//��Ӧ��1-7
			xingqiji=7;//��Ӧ�й�������1������һ2�����ڶ�
		}
		cal.add(Calendar.DATE,   (xingqiji-1)   *   (-1));  
		
		  
        return   cal.getTime();  
	}
	
	
	
	
	/**
	 * ��������ȡ��ǰ�ܵĵ�һ��CHINA������һΪ��һ�죩
	 * @param 
	 * @return Date
	 */
	public   Date   getThisWeek_Of_FirstDay()   {   
	    return   getWeek_Of_FirstDay(new   Date());   
	}

	/**
	 * ��������ȡ��ǰ�ܵ����һ�죨����һΪ��һ�죩
	 * @param 
	 * @return Date
	 */
	public   Date   getThisWeek_Of_LastDay()   {   
        return   getWeek_Of_LastDay(new   Date());   
	} 
	
	/**
	 * ��������ȡ��date���ڵ����ܵ����һ�죨����һΪ��һ�죩
	 * @param date
	 * @return Date
	 */
	public   Date   getWeek_Of_LastDay(Date   date)   {   
		Calendar   cal   =   Calendar.getInstance();
		cal.setTime(date); 
		int w = cal.get(Calendar.DAY_OF_WEEK);//��ȡ�����ڼ���1�������죬2������һ��...
		int xingqiji = w-1;
		if(xingqiji==0){
			//��Ӧ��1-7
			xingqiji=7;
		}
		cal.add(Calendar.DATE,   7-xingqiji);  
        return   cal.getTime();   
	}
	/**
	 * ��������ȡ��date���ڵ����ܵĵ�һ�죨����һΪ��һ�죩
	 * @param date
	 * @return Date
	 */
	public   Date   getUpWeek_Of_FirstDay(Date   date)   {   
		Calendar   cal   =   Calendar.getInstance();
		cal.setTime(date); 
		int w = cal.get(Calendar.DAY_OF_WEEK);//��ȡ�����ڼ���1�������죬2������һ��...
		int xingqiji = w-1;
		if(xingqiji==0){
			//��Ӧ��1-7
			xingqiji=7;
		}
		cal.add(Calendar.DATE,   (xingqiji-1)   *   (-1)-7);  
		
		  
        return   cal.getTime();   
	} 
	/**
	 * ��������ȡ��date���ڵ����ܵ����һ�죨����һΪ��һ�죩
	 * @param date
	 * @return Date
	 */
	public   Date   getUpWeek_Of_LastDay(Date   date)   {   
		Calendar   cal   =   Calendar.getInstance();
		cal.setTime(date); 
		int w = cal.get(Calendar.DAY_OF_WEEK);//��ȡ�����ڼ���1�������죬2������һ��...
		int xingqiji = w-1;
		if(xingqiji==0){
			//��Ӧ��1-7
			xingqiji=7;
		}
		cal.add(Calendar.DATE,   7-xingqiji-7);  
        return   cal.getTime();   
	}
	

	/**
	 * ��������ȡ��date���ڵ����ܵĵ�һ�죨����һΪ��һ�죩
	 * @param date
	 * @return Date
	 */
	public   Date   getNextWeek_Of_FirstDay(Date   date)   {   
		Calendar   cal   =   Calendar.getInstance();
		cal.setTime(date); 
		int w = cal.get(Calendar.DAY_OF_WEEK);//��ȡ�����ڼ���1�������죬2������һ��...
		int xingqiji = w-1;
		if(xingqiji==0){
			//��Ӧ��1-7
			xingqiji=7;
		}
		cal.add(Calendar.DATE,   (xingqiji-1)   *   (-1)+7);  
		
		  
        return   cal.getTime();   
	}
	/**
	 * ��������ȡ��date���ڵ����ܵ����һ�죨����һΪ��һ�죩
	 * @param date
	 * @return Date
	 */
	public   Date   getNextWeek_Of_LastDay(Date   date)   {   
		Calendar   cal   =   Calendar.getInstance();
		cal.setTime(date); 
		int w = cal.get(Calendar.DAY_OF_WEEK);//��ȡ�����ڼ���1�������죬2������һ��...
		int xingqiji = w-1;
		if(xingqiji==0){
			//��Ӧ��1-7
			xingqiji=7;
		}
		cal.add(Calendar.DATE,   7-xingqiji + 7);  
        return   cal.getTime();
	}
	/**
	 * ��������ȡ��date�µĵ�һ�������
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
	 * ��������ȡ��date�µ����һ�������
	 * @param date
	 * @return Date
	 */
	public   Date   getMonthLastDay(Date   date)   {   
        Calendar   cal   =   Calendar.getInstance();   
        cal.setTime(date);      
        cal.add(Calendar.MONTH,+1);//�ȼ���һ����       
        cal.add(Calendar.DATE,-cal.get(Calendar.DATE)+1-1);//��ȡ�µ��·ݵĵ�һ�� - 1==��ǰ������µ����һ��
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
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		DateUtilWeekChina   t   =   new   DateUtilWeekChina();   
        Date   d1   =   t.getNextWeek_Of_FirstDay(dateExample);
       
        Date   d2   =   t.getNextWeek_Of_LastDay(dateExample);
        String s1 = sdf.format(d1);
        String s2 = sdf.format(d2);
      
        
        System.out.println(dateExampleStr+"��xia�ܵĵ�1�������Ϊ�� "+s1);
        System.out.println(dateExampleStr+"��xia�ܵ����1�������Ϊ�� "+s2);
       
        
        
	}
}
