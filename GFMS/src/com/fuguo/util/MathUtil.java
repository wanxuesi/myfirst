package com.fuguo.util;

import bsw.tools.exception.BSWException;

import com.fuguo.dto.LsjgDTO;

public class MathUtil {

	
	/**
	 * 返回相关参数的ma均线价格
	 * @param 
	 * @return
	 */
	public double[] mathMa(LsjgDTO[] lsjgDTOs,String name,int maNumb){
		double[] myMaDouble=new double[lsjgDTOs.length];
		double oldSubAdjClose=0d;//记录上一个nowSubAdjClose值；
		double nowSubAdjClose=0d;
		for(int i=0;i<lsjgDTOs.length;i++){			
			nowSubAdjClose=0d;//清零			
			if(i<maNumb-1){
				myMaDouble[i]=0d;
			}else if(i==maNumb-1){
				//做个maNumb的微循环；表示maNumb的个数的close的叠加；
				//如果120均线的话，算法就耗cup了。以后优化算法；即：(前一个的均值*平均数-最前面的一个值+后面的一个值)/120
				for(int j=0;j<maNumb;j++){
					nowSubAdjClose+=lsjgDTOs[i-j].getAdjclose();
				}
				myMaDouble[i]=nowSubAdjClose/maNumb;
				oldSubAdjClose = nowSubAdjClose;// 将值赋给oldSubAdjClose
			}else{
				nowSubAdjClose = oldSubAdjClose-lsjgDTOs[i-maNumb].getAdjclose()+lsjgDTOs[i].getAdjclose();
				myMaDouble[i]=nowSubAdjClose/maNumb;
				oldSubAdjClose = nowSubAdjClose;// 将值赋给oldSubAdjClose
			}						
		}	
		return myMaDouble;
	}

	//减仓系数的设定 临时设计的方法，以后完善。
	public double getJianCangXishu(int maNumb) throws BSWException{
		double k=0d;
		if(maNumb<=20){
			k=0d;
		}else if(maNumb<=30){
			k=0.20d;
		}else if(maNumb<=60){
			k=0.40d;
		}else if(maNumb<=90){
			k=0.55d;
		}else if(maNumb<=120){
			k=0.70d;
		}else if(maNumb<=180){
			k=0.80d;
		}else if(maNumb<=250){
			k=0.90d;
		}else{
			k=0.90d;
		}
		 
		return k;
	}
	
	
	
	public int getMaxDoubleIndex(Double monthValue[]){
		int maxDoubleIndex=0;
		double maxDouble=0;
		
		for(int x=0;x<monthValue.length;x++){
			
			if(monthValue[x]>maxDouble){//判断语句，如果数组的下一个值大于 之前设的那个最大值，则进入
				maxDouble = monthValue[x];
				maxDoubleIndex = x; //把之前那个最大值替换成了现在这个
			}
		}
		System.out.println(maxDoubleIndex+":"+maxDouble);
		return maxDoubleIndex;		
	}
	
	public int getMinDoubleIndex(Double monthValue[]){
		int minDoubleIndex=0;
		double minDouble=0;
		for(int x=0;x<monthValue.length;x++){
			if(minDouble==0d){
				minDouble = monthValue[x];
				minDoubleIndex = x;
			}
				
			//System.out.println(minDoubleIndex+"now:"+minDouble);	
			
			if(monthValue[x]<minDouble){//判断语句，如果数组的下一个值大于 之前设的那个最大值，则进入
				minDouble=monthValue[x];
				minDoubleIndex = x; //把之前那个最大值替换成了现在这个
			}
		}	
		System.out.println(minDoubleIndex+":"+minDouble);
		return minDoubleIndex;		
	}
	
	public long getLongNumb(String numberStr)throws BSWException{
		numberStr = numberStr.replace(",","");
		if(numberStr.contains("万")){
			numberStr  = numberStr.replace("万","");
			Double number =new Double(numberStr);
			long fullNumberLong  =(long)(number*10000.0d);
			return fullNumberLong;
			
		}else if(numberStr.contains("亿")){
			numberStr  = numberStr.replace("亿","");
			Double number =new Double(numberStr);
			long fullNumberLong  =(long)(number*100000000.0d);
			return fullNumberLong;
		}else{
			Double number =new Double(numberStr);
			long fullNumberLong  =(long)(number*1.0d);
			return fullNumberLong;
		}
				
	}
	
	public long getLong10000Numb(String numberStr)throws BSWException{
		numberStr = numberStr.replace(",","");
		
			Double number =new Double(numberStr);
			long fullNumberLong  =(long)(number*10000.0d);
			return fullNumberLong;		
				
	}
	/**
	 * 单位后面保留2为数；
	 * @param longNumber
	 * @return
	 * @throws BSWException
	 */
	public String getStringNumb(long longNumber) throws BSWException{
		if(longNumber<10000){
			return Long.toString(longNumber);
		}else if(longNumber<100000000){
			double n = (double)longNumber/10000;
			//保留2位数
			
			return String.format("%.2f", n)+"万";
		}else {
			double n = (double)longNumber/100000000;
			//保留2位数
			
			return String.format("%.2f", n)+"亿";
		}
		
	}
	
	
public static void main(String[] args) throws Exception {
		
		
		String numberStr = "20.3亿";
		MathUtil mathUtil = new MathUtil();
		long fullNumberLong = mathUtil.getLongNumb(numberStr);
		System.out.println(numberStr+"的数值为:"+fullNumberLong);
		
	}
	
}

