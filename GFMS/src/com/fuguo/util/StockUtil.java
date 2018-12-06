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
	 * 描述：根据zqdm获取当前价；
	 * @param dt
	 * @return
	 */
	public static Double getDqjByZqdm(String zqdm) {
		//将zqdm拼成标准的代码；
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
	                //根据对照自己对应数据
	                
	                
	                //System.out.println(datas[3]);
	               // name = datas[0];
	                tmp = new Double(datas[2]);//昨日收盘价
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
	 * 描述：根据zqdm获取当前价；
	 * @param dt
	 * @return
	 */
//	public static Map<String,Double> getDqjsByZqdms(GpmcDTO[] gpmcDTOs) {
//		
//		Map<String,Double> map = new HashMap<String,Double>();
//		//将zqdm拼成标准的代码；
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
//			fullZqdm = getFullZqdmByZqdm(zqdm);//获取完整的东财可用的证券代码eg：sz002582;sh600000;
//			if(i==0){
//				sb.append(fullZqdm);
//			}else{
//				sb.append(","+fullZqdm);
//			}
//			
//		}	
//		
//		String url = "http://hq.sinajs.cn/list="+sb.toString();
//		//解析获得的String数据；然后解析放入map中，
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
//	                //根据对照自己对应数据
//	                
//	                
//	                //System.out.println(datas[3]);
//	               // name = datas[0];
//	                tmp = new Double(datas[2]);//昨日收盘价
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
		
		//对zqdm进行判断，如果是6，5，9开头的，就是+“sh”
		if(zqdm.charAt(0)=='6'||zqdm.charAt(0)=='5'||zqdm.charAt(0)=='9'){
			zqdm="sh"+zqdm;
		}else{
			zqdm="sz"+zqdm;
		}
	
		return zqdm;

	}
	
	/**
	 * 专门留作判断是否为基金用；
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
	 * 专门留作下载CSV的时候的URL用；
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
		
		//对zqdm进行判断，如果是6开头的，就是+“sh”
		if(zqdm.charAt(0)=='6'){
			zqdm=zqdm+".ss";
		}else{
			
			zqdm=zqdm+".sz";
		}
	
		return zqdm;

	}
	
	/**
	 * 专门留作sinaURL用；
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
		
//		//对zqdm进行判断，如果是6开头的，就是+“sh”
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
		
        //计算佣金
        double idYj=cjje*yongjin;
		if(idYj<yongjinmin){idYj=yongjinmin;};
		//System.out.println(mmflag+"佣金为："+idYj );
//		印花税 
		double yhsbuy = cjje*yinhuashuibuy;//印花税；
		double yhssell = cjje*yinhuashuisell;//印花税；
		
//		过户费的判断；本人成交金额的万0.2，不满1元也不收一元。
		double ghf=0;
		if(jysmc.equals("上海A股")){
			ghf = cjje*guohufei;
			if(ghf<guohufeimin){ghf=guohufeimin;};
		}else{
			ghf=0;
		}
		//System.out.println(mmflag+"过户费："+ghf );
		Double qsje=0.0;//实际买入时的花销，或者实际卖出时的花销；
		
		if(mmflag.equals("买入")){
			
			if(isJiJin==true){
				qsje =cjje+ghf+idYj;
			}else{
				qsje =cjje+ghf+idYj+yhsbuy;
			}
			
			float qsjeFloat=0;
			qsjeFloat=qsje.floatValue()*(-1);
			qsje =new Double(String.format("%.2f", qsjeFloat));
			//System.out.println(String.format("%.2f", qsje));
			//System.out.println(mmflag+"印花税："+yhsbuy );
			//System.out.println(mmflag+"总花费："+idYj+ghf+ yhsbuy);
		}	

		if(mmflag.equals("卖出")){
			if(isJiJin==true){
				qsje = cjje-idYj-ghf;
			}else{
				qsje = cjje-idYj-ghf-yhssell;
			}
			//System.out.println(mmflag+"印花税："+yhssell );
			//System.out.println(mmflag+"总花费："+idYj+ghf+ yhssell);
		}
		
		
		//System.out.println(mmflag+"成交金额为："+cjje+"清算金额为："+qsje);
		return qsje;
	}
	public static void main(String[] args) throws Exception {
		
		//getDqjByZqdm("600547");
		String zqdm = "201153";//200555就是B股；
		boolean  isJiJin = isJiJin(zqdm);
		
		System.out.println(zqdm+"是否为基金:"+isJiJin);
		
	}
}
