package com.fuguo.util;

public class RandomTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO �Զ����ɷ������
		//��ȡ[0--10)�����int������	
		
//		int aaa=0;
//		for(int i=0;i<50;i++){
//			aaa = (int)(0+Math.random()*10 );
//			System.out.println(aaa);
//		}
		
		String fullZqdm="002582";
		
		 //ͨ��startDate �������Ǽ�����ڼ����ȣ�
		 int startYear = 2015;
		 int startMonth = 0;
		 int startJd = (startMonth)/3+1;
		 
		 //ͨ��endDate �������Ǽ�����ڼ����ȣ�
		 int endYear = 2015;
		 int endMonth = 9;
		 int endJd = (endMonth)/3+1;
		 
		 int jdSize=endYear*4+endJd-(startYear*4+startJd)+1;
		 System.out.println(":"+jdSize);
		 //�ж���Ҫѭ���Ĵ�����
		 //ƴװURL��ȡ���ӵ�list��
		 int tmpURLYear;
		 int tmpURLJd;
		 String URL="";
		 for(int i=0;i<jdSize;i++){
			 //ͨ��i����Ҫ��ȡ�ĵ�ǰ���ȣ�
			 tmpURLYear = startYear+ i/4;
			 tmpURLJd  = startJd+i%4;
			 

			 URL = "http://money.finance.sina.com.cn/corp/go.php/vMS_MarketHistory/stockid/"+fullZqdm+".phtml?year="+tmpURLYear+"&jidu="+tmpURLJd;
			 System.out.println(URL);
			 
			 
		 }
	}

}
