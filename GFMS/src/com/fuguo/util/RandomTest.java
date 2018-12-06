package com.fuguo.util;

public class RandomTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自动生成方法存根
		//获取[0--10)的随机int型数；	
		
//		int aaa=0;
//		for(int i=0;i<50;i++){
//			aaa = (int)(0+Math.random()*10 );
//			System.out.println(aaa);
//		}
		
		String fullZqdm="002582";
		
		 //通过startDate 该日期是几几年第几季度；
		 int startYear = 2015;
		 int startMonth = 0;
		 int startJd = (startMonth)/3+1;
		 
		 //通过endDate 该日期是几几年第几季度；
		 int endYear = 2015;
		 int endMonth = 9;
		 int endJd = (endMonth)/3+1;
		 
		 int jdSize=endYear*4+endJd-(startYear*4+startJd)+1;
		 System.out.println(":"+jdSize);
		 //判断需要循环的次数；
		 //拼装URL获取叠加的list；
		 int tmpURLYear;
		 int tmpURLJd;
		 String URL="";
		 for(int i=0;i<jdSize;i++){
			 //通过i计算要获取的当前季度；
			 tmpURLYear = startYear+ i/4;
			 tmpURLJd  = startJd+i%4;
			 

			 URL = "http://money.finance.sina.com.cn/corp/go.php/vMS_MarketHistory/stockid/"+fullZqdm+".phtml?year="+tmpURLYear+"&jidu="+tmpURLJd;
			 System.out.println(URL);
			 
			 
		 }
	}

}
