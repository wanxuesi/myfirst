package com.fuguo.util;


import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import bsw.tools.exception.BSWException;

import com.fuguo.bo.ConfigBO;
import com.fuguo.dto.ConfigDTO;
import com.fuguo.dto.GpmcDTO;

public class  StockUtil{

	/**
	 * ����������zqdm��ȡ��ǰ�ۣ�
	 * @param dt
	 * @return
	 */
	public static Double getDqjByZqdm(String zqdm) {
		//��zqdmƴ�ɱ�׼�Ĵ��룻
		double dqj=0.0;
		double tmp=0.0;
		zqdm = getFullZqdmByZqdm(zqdm);
		
		String url = "http://hq.sinajs.cn/list="+zqdm;
		
		 try {
	            URL u = new URL(url);
	            byte[] b = new byte[256];
	            InputStream in = null;
	            ByteArrayOutputStream bo = new ByteArrayOutputStream();
	            
	            try {
	                in = u.openStream();
	                int i;
	                while ((i = in.read(b)) != -1) {
	                    bo.write(b, 0, i);
	                }
	                String result = bo.toString();
	                String[] stocks = result.split(";");
	             
	                String[] datas = stocks[0].split(",");
	                //���ݶ����Լ���Ӧ����
	                
	                
	                //System.out.println(datas[3]);
	               // name = datas[0];
	                tmp = new Double(datas[2]);//�������̼�
	                dqj = new Double(datas[3]);
	                if(dqj==0.0||dqj==0){
	                	dqj = tmp;
	                }
	                bo.reset();
	            } catch (Exception e) {
	                System.out.println(e.getMessage());
	            } finally {
	                if (in != null) {
	                    in.close();
	                }
	            }
	           
	        } catch (Exception ex) {
	            System.out.println(ex.getMessage());
	        }
		
		
		
		return dqj;

	}
	/**
	 * ����������zqdm��ȡ��ǰ�ۣ�
	 * @param dt
	 * @return
	 */
//	public static Map<String,Double> getDqjsByZqdms(GpmcDTO[] gpmcDTOs) {
//		
//		Map<String,Double> map = new HashMap<String,Double>();
//		//��zqdmƴ�ɱ�׼�Ĵ��룻
//		if(gpmcDTOs.length==0){
//			return map;
//		}
//		double dqj=0.0;
//		double tmp=0.0;
//		String zqdm="";
//		String fullZqdm="";
//		StringBuffer sb = new StringBuffer();
//		for(int i=0;i<gpmcDTOs.length;i++){
//			zqdm  = gpmcDTOs[i].getZqdm();
//			fullZqdm = getFullZqdmByZqdm(zqdm);//��ȡ�����Ķ��ƿ��õ�֤ȯ����eg��sz002582;sh600000;
//			if(i==0){
//				sb.append(fullZqdm);
//			}else{
//				sb.append(","+fullZqdm);
//			}
//			
//		}	
//		
//		String url = "http://hq.sinajs.cn/list="+sb.toString();
//		//������õ�String���ݣ�Ȼ���������map�У�
//		 try {
//	            URL u = new URL(url);
//	            byte[] b = new byte[256];
//	            InputStream in = null;
//	            ByteArrayOutputStream bo = new ByteArrayOutputStream();
//	            
//	            try {
//	                in = u.openStream();
//	                int i;
//	                while ((i = in.read(b)) != -1) {
//	                    bo.write(b, 0, i);
//	                }
//	                String result = bo.toString();
//	                String[] stocks = result.split(";");
//	             
//	                String[] datas = stocks[0].split(",");
//	                //���ݶ����Լ���Ӧ����
//	                
//	                
//	                //System.out.println(datas[3]);
//	               // name = datas[0];
//	                tmp = new Double(datas[2]);//�������̼�
//	                dqj = new Double(datas[3]);
//	                if(dqj==0.0||dqj==0){
//	                	dqj = tmp;
//	                }
//	                bo.reset();
//	            } catch (Exception e) {
//	                System.out.println(e.getMessage());
//	            } finally {
//	                if (in != null) {
//	                    in.close();
//	                }
//	            }
//	           
//	        } catch (Exception ex) {
//	            System.out.println(ex.getMessage());
//	        }
//		
//		
//		
//		return map;
//
//	}
//	
	public static String  getFullZqdmByZqdm(String zqdm) {
		
		if(zqdm.length()==5){
			zqdm="0"+zqdm;
		}else if(zqdm.length()==4){
			zqdm="00"+zqdm;
		}else if(zqdm.length()==3){
			zqdm="000"+zqdm;
		}else if(zqdm.length()==2){
			zqdm="0000"+zqdm;
		}else if(zqdm.length()==1){
			zqdm="00000"+zqdm;
		}
		
		//��zqdm�����жϣ������6��5��9��ͷ�ģ�����+��sh��
		if(zqdm.charAt(0)=='6'||zqdm.charAt(0)=='5'||zqdm.charAt(0)=='9'){
			zqdm="sh"+zqdm;
		}else{
			zqdm="sz"+zqdm;
		}
	
		return zqdm;

	}
	
	/**
	 * ר�������ж��Ƿ�Ϊ�����ã�
	 * @param zqdm
	 * @return
	 */
	public static boolean isJiJin(String zqdm){
		boolean isJijin=false;
		if(zqdm.length()!=6){
			//isJijin=false;
		}else{
			if(zqdm.charAt(0)=='1' || zqdm.charAt(0)=='5' || (zqdm.charAt(0)=='2' && !zqdm.startsWith("200") ) ){
				isJijin=true;
			}
		}
		
		
		return isJijin;
		
	}
	
	public static String gupiaoORjijin(Integer zqdmInteger){
		
		if((zqdmInteger>0 && zqdmInteger<3000) || (zqdmInteger >300000 && zqdmInteger<302000) || (zqdmInteger>=600000 &&zqdmInteger<605000)){
			return "1";
		}else return "0";
		
		
		
		
	}
	/**
	 * ר����������CSV��ʱ���URL�ã�
	 * @param zqdm
	 * @return
	 */
	public static String  getFullZqdmByZqdm2(String zqdm) {
		
		if(zqdm.length()==5){
			zqdm="0"+zqdm;
		}else if(zqdm.length()==4){
			zqdm="00"+zqdm;
		}else if(zqdm.length()==3){
			zqdm="000"+zqdm;
		}else if(zqdm.length()==2){
			zqdm="0000"+zqdm;
		}else if(zqdm.length()==1){
			zqdm="00000"+zqdm;
		}
		
		//��zqdm�����жϣ������6��ͷ�ģ�����+��sh��
		if(zqdm.charAt(0)=='6'){
			zqdm=zqdm+".ss";
		}else{
			
			zqdm=zqdm+".sz";
		}
	
		return zqdm;

	}
	
	/**
	 * ר������sinaURL�ã�
	 * @param zqdm
	 * @return
	 */
	public static String  getFullZqdmByZqdm3(String zqdm) {
		
		if(zqdm.length()==5){
			zqdm="0"+zqdm;
		}else if(zqdm.length()==4){
			zqdm="00"+zqdm;
		}else if(zqdm.length()==3){
			zqdm="000"+zqdm;
		}else if(zqdm.length()==2){
			zqdm="0000"+zqdm;
		}else if(zqdm.length()==1){
			zqdm="00000"+zqdm;
		}
		
//		//��zqdm�����жϣ������6��ͷ�ģ�����+��sh��
//		if(zqdm.charAt(0)=='6'){
//			zqdm=zqdm+".ss";
//		}else{
//			
//			zqdm=zqdm+".sz";
//		}
	
		return zqdm;

	}
	
	public  double getQsje(String userid,double cjje,String jysmc,String mmflag,boolean isJiJin) throws BSWException{
		ConfigBO cBO=new ConfigBO();
		ConfigDTO m=new ConfigDTO();
		m.setId(userid);
		m  = cBO.query(m);
		//int onoff =m.getOnoff();
        //double confirmbfb =m.getConfirmbfb();
        double yinhuashuibuy =m.getYinhuashuibuy();
        double yinhuashuisell =m.getYinhuashuisell();
       // double onegupiaocangweibfb =m.getOnegupiaocangweibfb();
        //double onejiflcangweibfb =m.getOnejiflcangweibfb();
        double yongjin =m.getYongjin();
        int yongjinmin =m.getYongjinmin();
        double guohufei =m.getGuohufei();
        int guohufeimin =m.getGuohufeimin();
		
        //����Ӷ��
        double idYj=cjje*yongjin;
		if(idYj<yongjinmin){idYj=yongjinmin;};
		//System.out.println(mmflag+"Ӷ��Ϊ��"+idYj );
//		ӡ��˰ 
		double yhsbuy = cjje*yinhuashuibuy;//ӡ��˰��
		double yhssell = cjje*yinhuashuisell;//ӡ��˰��
		
//		�����ѵ��жϣ����˳ɽ�������0.2������1ԪҲ����һԪ��
		double ghf=0;
		if(jysmc.equals("�Ϻ�A��")){
			ghf = cjje*guohufei;
			if(ghf<guohufeimin){ghf=guohufeimin;};
		}else{
			ghf=0;
		}
		//System.out.println(mmflag+"�����ѣ�"+ghf );
		Double qsje=0.0;//ʵ������ʱ�Ļ���������ʵ������ʱ�Ļ�����
		
		if(mmflag.equals("����")){
			
			if(isJiJin==true){
				qsje =cjje+ghf+idYj;
			}else{
				qsje =cjje+ghf+idYj+yhsbuy;
			}
			
			float qsjeFloat=0;
			qsjeFloat=qsje.floatValue()*(-1);
			qsje =new Double(String.format("%.2f", qsjeFloat));
			//System.out.println(String.format("%.2f", qsje));
			//System.out.println(mmflag+"ӡ��˰��"+yhsbuy );
			//System.out.println(mmflag+"�ܻ��ѣ�"+idYj+ghf+ yhsbuy);
		}	

		if(mmflag.equals("����")){
			if(isJiJin==true){
				qsje = cjje-idYj-ghf;
			}else{
				qsje = cjje-idYj-ghf-yhssell;
			}
			//System.out.println(mmflag+"ӡ��˰��"+yhssell );
			//System.out.println(mmflag+"�ܻ��ѣ�"+idYj+ghf+ yhssell);
		}
		
		
		//System.out.println(mmflag+"�ɽ����Ϊ��"+cjje+"������Ϊ��"+qsje);
		return qsje;
	}
	public static void main(String[] args) throws Exception {
		
		//getDqjByZqdm("600547");
		String zqdm = "201153";//200555����B�ɣ�
		boolean  isJiJin = isJiJin(zqdm);
		
		System.out.println(zqdm+"�Ƿ�Ϊ����:"+isJiJin);
		
	}
}
