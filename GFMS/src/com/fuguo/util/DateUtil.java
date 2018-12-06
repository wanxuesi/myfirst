package com.fuguo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * 2.0    �˰汾�ǳ��棬��һΪһ�ܵĵ�һ�졣
 * @author Administrator
 *
 */
public class DateUtil {
	/**
	 * �������������ڻ�������ڼ��ĺ���
	 * @param dt
	 * @return
	 */
	public static String getWeekOfDate(Date dt) {
		String[] weekDays = { "(��)", "(һ)", "(��)", "(��)", "(��)", "(��)", "(��)" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);

		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return weekDays[w];

	}
	
	/**
	 * ��������ȡ��date���ڵ����ܵĵ�һ�죨������Ϊ��һ�죩
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
	 * *��������ȡ�ǵڼ���
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
	 * ��������ȡ��ǰ�ܵĵ�һ�죨������Ϊ��һ�죩
	 * @param 
	 * @return Date
	 */
	public   Date   getThisWeek_Of_FirstDay()   {   
	    return   getWeek_Of_FirstDay(new   Date());   
	}

	/**
	 * ��������ȡ��ǰ�ܵ����һ�죨������Ϊ��һ�죩
	 * @param 
	 * @return Date
	 */
	public   Date   getThisWeek_Of_LastDay()   {   
        return   getWeek_Of_LastDay(new   Date());   
	} 
	
	/**
	 * ��������ȡ��date���ڵ����ܵ����һ�죨������Ϊ��һ�죩
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
	 * ��������ȡ��date���ڵ����ܵĵ�һ�죨������Ϊ��һ�죩
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
	 * ��������ȡ��date���ڵ����ܵ����һ�죨������Ϊ��һ�죩
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
	 * ��������ȡ��date���ڵ����ܵĵ�һ�죨������Ϊ��һ�죩
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
	 * ��������ȡ��date���ڵ����ܵ����һ�죨������Ϊ��һ�죩
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
	 * ��������ȡ��ǰ�µĵ�һ�������
	 * @param date
	 * @return Date
	 */
	public   Date   getThisMonthFirtDay()   {   
        return   getMonthFirtDay(new   Date());   
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
	 * ��������ȡ����ĵ�n���µĵ�һ�������
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
	 * ��������ȡĳ��ĵ�n���µĵ�һ������� //����
	 * @param int int
	 * @return Date
	 */
	public   Date   getMonthFirtDay_Of_Which_Year(int year,int month)   {   
        Calendar   cal   =   Calendar.getInstance(); 
        //�趨���
        cal.add(Calendar.YEAR,-cal.get(Calendar.YEAR)+(year));
        cal.add(Calendar.MONTH,-cal.get(Calendar.MONTH)+(month-1)); 
        cal.add(Calendar.DATE,-cal.get(Calendar.DATE)+1);
        return   cal.getTime();   
	}
	
	
	/**
	 * ��������ȡ����ĵ�n���µ����һ�������
	 * @param int
	 * @return Date
	 */
	public   Date   getMonthLastDay_Of_This_Year(int month)   {   
	    Calendar   cal   =   Calendar.getInstance();        
	    cal.add(Calendar.MONTH,-cal.get(Calendar.MONTH)+(month-1)+1); //�ȼ���һ����       
	    cal.add(Calendar.DATE,-cal.get(Calendar.DATE)+1-1);//��ȡ�µ��·ݵĵ�һ�� - 1==��ǰ������µ����һ��
	    return   cal.getTime();   
	}

	/**
	 * ��������ȡĳ�ĵ�n���µ����һ�������//����
	 * @param int int
	 * @return Date
	 */
	public   Date   getMonthLastDay_Of_Which_Year(int year,int month)   {   
        Calendar   cal   =   Calendar.getInstance();  
        //�趨���
        cal.add(Calendar.YEAR,-cal.get(Calendar.YEAR)+(year));
        cal.add(Calendar.MONTH,-cal.get(Calendar.MONTH)+(month-1)+1); //�ȼ���һ����       
        cal.add(Calendar.DATE,-cal.get(Calendar.DATE)+1-1);//��ȡ�µ��·ݵĵ�һ�� - 1==��ǰ������µ����һ��
        return   cal.getTime();   
	}
	
	
	
	/**
	 * ��������ȡ��ǰ�µ����һ�������
	 * @param date
	 * @return Date
	 */
	public   Date   getThisMonthLastDay()   {   
        return   getMonthLastDay(new   Date());   
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
	/**
	 * ��������ȡ��ǰ���ڵ�ǰ���������
	 * @param date
	 * @return Date
	 */
	public   Date   getThisBeforeThreeDay()   {   
	    return   getBeforeThreeDay(new   Date());   
	}

	/**
	 * ��������ȡ��date���ڵ�ǰ���������
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
	 * ��������ȡ��date���ڵ�ǰn�������
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
	 * ��������ȡ��date���ڵ�ǰn�µ�����
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
	 * ��������ȡ��date���ڵĺ�n�µ�����
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
	 * ��������ȡ��date���ڵĺ�7�������
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
	  * ��ȡ���������м�Ĺ�����
	  * @param starttime
	  * @param endtime
	  * @return
	  */
	 public static int getDateDiff(Date  dateFrom, Date dateTo){
	  
	  int workdays = 0;
	  Calendar cal = null;
	     while(dateFrom.before(dateTo)|| dateFrom.equals(dateTo)){
	         cal = Calendar.getInstance();
	         //��������
	        cal.setTime(dateFrom);
	       
	         workdays++;
	      
	        //���ڼ�1
	        cal.add(Calendar.DAY_OF_MONTH,1);
	        dateFrom = cal.getTime();
	    }
	     return workdays;
	 }
	 
	 
	 
	 /**
		 * ��������ȡ2������֮����·���Ŀ��
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
			// TODO �Զ����� catch ��
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
        
        System.out.println(s1 +"��"+s2 +"��������Ϊ"+getDateDiff(d1,d2));
        System.out.println(Calendar.DAY_OF_WEEK);
        System.out.println(dateExampleStr+"���ܵĵ�һ�������Ϊ�� "+s1);
        System.out.println(dateExampleStr+"���ܵ����һ�������Ϊ�� "+s5);
        System.out.println(dateExampleStr+"��ǰ���������Ϊ�� "+s2);
        System.out.println(dateExampleStr+"���µĵ�һ�������Ϊ�� "+s3);
        System.out.println(dateExampleStr+"���µ����һ�������Ϊ�� "+s4);
        int month = 2;
        System.out.println("��һ��ĵ�"+month+"���µĵ�һ��������ǣ�"+(t.getMonthFirtDay_Of_This_Year(month)).toLocaleString());
        System.out.println("��һ��ĵ�"+month+"���µ����һ��������ǣ�"+(t.getMonthLastDay_Of_This_Year(month)).toLocaleString());
        //���� year month �ķ���
        int year =2008;
        month=5;
        System.out.println(year+"��ĵ�"+month+"���µĵ�һ��������ǣ�"+(t.getMonthFirtDay_Of_Which_Year(year,month)).toLocaleString());
        System.out.println(year+"��ĵ�"+month+"���µ����һ��������ǣ�"+(t.getMonthLastDay_Of_Which_Year(year,month)).toLocaleString());
        
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
			
			System.out.println(date1 +"��"+date2 +"��������Ϊ"+getDateDiff(date1,date2));
		} catch (ParseException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
        
		
	}
}
