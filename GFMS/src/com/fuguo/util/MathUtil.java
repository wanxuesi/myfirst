package com.fuguo.util;

import bsw.tools.exception.BSWException;

import com.fuguo.dto.LsjgDTO;

public class MathUtil {

	
	/**
	 * ������ز�����ma���߼۸�
	 * @param 
	 * @return
	 */
	public double[] mathMa(LsjgDTO[] lsjgDTOs,String name,int maNumb){
		double[] myMaDouble=new double[lsjgDTOs.length];
		double oldSubAdjClose=0d;//��¼��һ��nowSubAdjCloseֵ��
		double nowSubAdjClose=0d;
		for(int i=0;i<lsjgDTOs.length;i++){			
			nowSubAdjClose=0d;//����			
			if(i<maNumb-1){
				myMaDouble[i]=0d;
			}else if(i==maNumb-1){
				//����maNumb��΢ѭ������ʾmaNumb�ĸ�����close�ĵ��ӣ�
				//���120���ߵĻ����㷨�ͺ�cup�ˡ��Ժ��Ż��㷨������(ǰһ���ľ�ֵ*ƽ����-��ǰ���һ��ֵ+�����һ��ֵ)/120
				for(int j=0;j<maNumb;j++){
					nowSubAdjClose+=lsjgDTOs[i-j].getAdjclose();
				}
				myMaDouble[i]=nowSubAdjClose/maNumb;
				oldSubAdjClose = nowSubAdjClose;// ��ֵ����oldSubAdjClose
			}else{
				nowSubAdjClose = oldSubAdjClose-lsjgDTOs[i-maNumb].getAdjclose()+lsjgDTOs[i].getAdjclose();
				myMaDouble[i]=nowSubAdjClose/maNumb;
				oldSubAdjClose = nowSubAdjClose;// ��ֵ����oldSubAdjClose
			}						
		}	
		return myMaDouble;
	}

	//����ϵ�����趨 ��ʱ��Ƶķ������Ժ����ơ�
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
			
			if(monthValue[x]>maxDouble){//�ж���䣬����������һ��ֵ���� ֮ǰ����Ǹ����ֵ�������
				maxDouble = monthValue[x];
				maxDoubleIndex = x; //��֮ǰ�Ǹ����ֵ�滻�����������
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
			
			if(monthValue[x]<minDouble){//�ж���䣬����������һ��ֵ���� ֮ǰ����Ǹ����ֵ�������
				minDouble=monthValue[x];
				minDoubleIndex = x; //��֮ǰ�Ǹ����ֵ�滻�����������
			}
		}	
		System.out.println(minDoubleIndex+":"+minDouble);
		return minDoubleIndex;		
	}
	
	public long getLongNumb(String numberStr)throws BSWException{
		numberStr = numberStr.replace(",","");
		if(numberStr.contains("��")){
			numberStr  = numberStr.replace("��","");
			Double number =new Double(numberStr);
			long fullNumberLong  =(long)(number*10000.0d);
			return fullNumberLong;
			
		}else if(numberStr.contains("��")){
			numberStr  = numberStr.replace("��","");
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
	 * ��λ���汣��2Ϊ����
	 * @param longNumber
	 * @return
	 * @throws BSWException
	 */
	public String getStringNumb(long longNumber) throws BSWException{
		if(longNumber<10000){
			return Long.toString(longNumber);
		}else if(longNumber<100000000){
			double n = (double)longNumber/10000;
			//����2λ��
			
			return String.format("%.2f", n)+"��";
		}else {
			double n = (double)longNumber/100000000;
			//����2λ��
			
			return String.format("%.2f", n)+"��";
		}
		
	}
	
	
public static void main(String[] args) throws Exception {
		
		
		String numberStr = "20.3��";
		MathUtil mathUtil = new MathUtil();
		long fullNumberLong = mathUtil.getLongNumb(numberStr);
		System.out.println(numberStr+"����ֵΪ:"+fullNumberLong);
		
	}
	
}

