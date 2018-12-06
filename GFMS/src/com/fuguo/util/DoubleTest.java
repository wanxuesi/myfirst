package com.fuguo.util;

public class DoubleTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自动生成方法存根
		double ls = 15.33d;
		double bql = 10.94d;
		
		double nowls = 6.61d;
		double nowbql = 2.87d;
		
		double a1 = 0.9d;
		double a2 = 0.95d;
		double n;
		
		for(int i=0;i<35;i++){
			double tmp1 = nowls*Math.pow( a1,  i) ;
			double tmp2 = nowbql*Math.pow( a2,  i) ;
			
			System.out.println(i+":"+tmp1);
			
			System.out.println(i+":"+tmp2);
		}
		

	}

}
