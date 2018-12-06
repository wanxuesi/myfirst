package com.fuguo.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import bsw.tools.exception.BSWException;

import com.fuguo.bo.GpmcBO;
import com.fuguo.dto.GpmcDTO;
import com.fuguo.dto.LsjgDTO;
import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.HttpUnitOptions;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import com.meterware.httpunit.WebTable;

/**
 * 
 * @����: 
 * 		  
 * @��λ:
 * @����:wanxuesi@163.com
 * @����:��ѧ˼
 * @���ڣ�2008-9-4
 */
public class JsoupParser {
	WebConversation wc = new WebConversation();
	
	WebRequest req;
	WebResponse resp ;
	WebTable webTable;
	/**
	 * @param args
	 */
	public List<LsjgDTO> getListLsjgsByUrl(String urlStrFQ,String urlStr,List<LsjgDTO> list,String zqdm,String zqmc)throws BSWException{
		try {
			SimpleDateFormat   sdf   =   new   SimpleDateFormat("yyyy-MM-dd");
			Map<String,Double> mapfq = new HashMap();
			//���ж��ǻ����ǹ�Ʊ��
			boolean isJiJin = StockUtil.isJiJin(zqdm);
			//����ǻ���(static String gupiaoORjijin(Integer zqdmInteger)������������Ȩ���ӣ�ֱ�ӽ���Ȩ����ֱ������Ϊ1
			if(isJiJin==true){
				//ʲô������
			}else{
//				Ӧ�ô˴���ȡ��Ȩ���ӣ�Ȼ����뵽��ʱ�У�map<date,double>
				//String urlStr = "http://money.finance.sina.com.cn/corp/go.php/vMS_FuQuanMarketHistory/stockid/002582.phtml?year=2015&jidu=3";
				
				Document docfq = Jsoup.connect(urlStrFQ).get();
	            Element bodyfq = docfq.body();
	            Element fundHoldSharesTablefq =  bodyfq.getElementById("FundHoldSharesTable");
	            if(fundHoldSharesTablefq==null){
	            	return list;
	            }
	            
	            Elements childenfq = fundHoldSharesTablefq.children();
	            Element lastChildenfq = childenfq.last();
	            Elements childenTrsfq =lastChildenfq.children();
	           
	            Element elementTrfq;
	            Elements childenTdsfq;
	            Element element0fq,element7fq;
	            String dateStringfq;
	            
	            
	            String fqyzString;
	            
	            double fqyz;
	            Date datefq=new Date();
	            
	            
	            //LsjgDTO lsjgDTO;
	            
	            Iterator itChildenTrfq = childenTrsfq.iterator();            
	            for(int j=0;j<childenTrsfq.size();j++){
	            	
	            	elementTrfq = (Element)itChildenTrfq.next();
	            	childenTdsfq = elementTrfq.children();
	            	element0fq = childenTdsfq.get(0);
	            	dateStringfq =element0fq.child(0).child(0).text();
	            	if(dateStringfq.equals("����")){
	            		continue;
	            	}
	            	//��Ȩ���� 
	            	//System.out.println(dateString);
	            	//datefq = sdf.parse(dateStringfq);
	            	
	            	
	             	element7fq = childenTdsfq.get(7);
	             	fqyzString = element7fq.text();
	             	fqyz = Double.valueOf(fqyzString);
	             	
	            	
	             	mapfq.put(dateStringfq,fqyz);
	             	System.out.println(dateStringfq+"�ĸ�Ȩ����"+fqyz+"�ѷ���map�У�");
	            	
	            }
			}
			
			
			
			
			
			//String urlStr = "http://money.finance.sina.com.cn/corp/go.php/vMS_MarketHistory/stockid/002582.phtml?year=2015&jidu=3";
			//http://vip.stock.finance.sina.com.cn/corp/go.php/vMS_FuQuanMarketHistory/stockid/002582.phtml?year=2016&jidu=2
			

			Document doc = Jsoup.connect(urlStr).get();
            Element body = doc.body();
            Element fundHoldSharesTable =  body.getElementById("FundHoldSharesTable");
            if(fundHoldSharesTable==null){
            	return list;
            }
            
            Elements childen = fundHoldSharesTable.children();
            Element lastChilden = childen.last();
            Elements childenTrs =lastChilden.children();
           
            Element elementTr;
            Elements childenTds;
            Element element0,element1,element2,element3,element4,element5;
            String dateString;
            
            String openString;
            String highString;
            String lowString;
            String closeString;
            String volumeString;
            double open;
            double high;
            double low;
            double close;
            double volume;
            Date date=new Date();
            LsjgDTO lsjgDTO;
            //SimpleDateFormat   sdf   =   new   SimpleDateFormat("yyyy-MM-dd");
            Iterator itChildenTr = childenTrs.iterator();            
            for(int j=0;j<childenTrs.size();j++){
            	lsjgDTO = new LsjgDTO();
            	lsjgDTO.setZqdm(zqdm);
            	lsjgDTO.setZqmc(zqmc);
            	elementTr = (Element)itChildenTr.next();
            	childenTds = elementTr.children();
            	element0 = childenTds.get(0);
            	dateString =element0.child(0).child(0).text();
            	if(dateString.equals("����")){
            		continue;
            	}
            	//���� ���̼� ��߼� ���̼� ��ͼ� ������(��) 
            	System.out.println(dateString);
            	date = sdf.parse(dateString);
            	
            	lsjgDTO.setDate(date);
             	element1 = childenTds.get(1);
             	openString = element1.text();
             	open = Double.valueOf(openString);
             	lsjgDTO.setOpen(open);
             	
            	element2 = childenTds.get(2);
            	highString = element2.text();
            	high = Double.valueOf(highString);
            	lsjgDTO.setHigh(high);
            	
            	element3 = childenTds.get(3);
            	closeString = element3.text();
            	close = Double.valueOf(closeString);
            	lsjgDTO.setClose(close);
            	
            	element4 = childenTds.get(4);
            	lowString = element4.text();
            	low = Double.valueOf(lowString);
            	lsjgDTO.setLow(low);
            	
            	element5 = childenTds.get(5);
            	volumeString = element5.text();
            	volume = Double.valueOf(volumeString);
            	lsjgDTO.setVolume(volume);
            	
            	if(isJiJin==true){
    				//Ĭ�ϵĸ�ȨֵΪ1��
            		lsjgDTO.setFlag1("standard");
            		lsjgDTO.setFqyz(1d);
            		
    			}else{
//    				ͨ��map�е����ڣ���Ӹ�Ȩ����
                	System.out.println("��ʼ��ȡ��Ϊ"+dateString+"��ֵ");
                	Double fqyzDouble = mapfq.get(dateString);
                	if(fqyzDouble==null){
                		lsjgDTO.setFlag1("tmp");
                		lsjgDTO.setFqyz(0d);
                	}else{
                		lsjgDTO.setFlag1("standard");
                    	lsjgDTO.setFqyz(fqyzDouble);
                	}
                	
                	System.out.println("�õ���Ϊ"+dateString+"��ֵΪ��"+fqyzDouble);
    			}
            	
            	
            	
            	
            	list.add(lsjgDTO);
            	
            }
            

		} catch (IOException e) {
			throw new BSWException("ץȡ��ҳʧ��"+e.getMessage());
		} catch (ParseException e) {
			throw new BSWException("ת�����ڸ�ʽʧ��"+e.getMessage());
		}
		
		return list;
	}
	
	
	
	public GpmcDTO getTsxgByUrl(String gubenjiegouFullURL,String xianshoujiejinFullURL,GpmcDTO gpmcDTO,long zszLong, long ltszLong, Date queryDateDate)throws BSWException{
		String zqdm  = gpmcDTO.getZqdm();
		String zqmc  = gpmcDTO.getZqmc();
		StockUtil stockUtil = new StockUtil();
		double dqj = stockUtil.getDqjByZqdm(zqdm);//��ǰ��
		if(dqj<0.1d){
			//�г���Ϊ0�ģ���ʾδ���еġ�ֱ��������
			return  null;
		}
		System.out.println(zqmc+"("+zqdm+")ץȡ��ʼ......");
		double zgbnumGpmc = gpmcDTO.getZgbnum();
		
		long zgbnumGpmcLong = (long)(zgbnumGpmc*10000);//��Ʊ����������ܹɱ�ת����
		long zszGpmcLong = (long)(zgbnumGpmcLong*dqj);
		if(zszGpmcLong>zszLong){
			System.out.println(zqmc+"("+zqdm+")����ֵ���󣬲�����ɸѡ������ֱ��������");
			return  null;
		}
		
		
		
		long dateSxgfNumberALL=0L;
		try {
			
			MathUtil mathUtil = new MathUtil();
			
			Document gbjgDoc = Jsoup.connect(gubenjiegouFullURL).timeout(1000).get();//���������ϣ��ٱ���ʱ����
			
            Element gbjgBody = gbjgDoc.body();//�ɱ��ṹ  ��һ��ҳ��
           
            
            
            
            
            
//          ��gbjgBody���н�����
            //�ܹɱ�(��ʷ��¼)	20000 ���	
           			
            //��ͨA��(��ʷ��¼)	5000 ���
            //�ܹɱ�
            Element elementGbjgTable = gbjgBody.getElementById("StockStructureNewTable0");//<table id="StockStructureNewTable0" width="100%">
//          ��elementGbjgTable���н�����
            Element elementGbjgTbody =  elementGbjgTable.child(1); //<tbody>

            
            //�����<tr><td width='150'>���ܹɱ�(��ʷ��¼)</a></td><td>20000 ���</td><td>10000 ���</td><td>7500 ���</td></tr>
            //��7��<tr><td width='150'>&nbsp;&nbsp;��ͨ��</td><td></td><td></td><td></td></tr>
            Element elementGbjgTr5 = elementGbjgTbody.child(4);  //
            Element elementGbjgTr7 = elementGbjgTbody.child(6);  //
            
            //�ӵ�����͵����������ȡ--�ܹɱ���--��ͨA��
            
            String dqyltnumStr  = elementGbjgTr7.child(1).text().replace(" ","").replace("��","");//��ǰ����ͨ�ɷ�
            
            
            System.out.println(zqmc+"("+zqdm+")��ǰ����ͨ�ɷ�(������)"+dqyltnumStr);
            
            long dqyltnumLong  = mathUtil.getLongNumb(dqyltnumStr);
            
            System.out.println(zqmc+"("+zqdm+")��ǰ����ͨ�ɷ�Long��"+dqyltnumStr);
            
            gpmcDTO.setDqyltnumStr(mathUtil.getStringNumb(dqyltnumLong));
            
            long dqyltszLong = (long)(dqyltnumLong*dqj);
            gpmcDTO.setDqyltszStr(mathUtil.getStringNumb(dqyltszLong));
            
//          �ж��¹�����--��ͨ��ֵС��4���ڣ�date��ͨ��ֵ>��ͨ��ֵ/2  ����Ϊ�¹ɣ��Ժ�÷�����ȡ������
            if(dqyltszLong<=400000000L){
            	//gpmcDTO.setNewFlag("Y");
            	gpmcDTO.setNewFlagInt(1);
            }
            
            
            
            
            System.out.println("��ǰ����ͨ�ɷ�:"+dqyltnumStr);
            
            String zgbStr  = elementGbjgTr5.child(1).text().replace(" ","").replace("��","");//��ǰ�ܹɱ���
            
            System.out.println("��ǰ�ܹɱ�:"+zgbStr);
            long zgbnumLong = mathUtil.getLongNumb(zgbStr);//��������ã��ж�gpmcDTO�����zgbnum���Ƿ�Ϊ0,���Ϊ��͸��µ�DTO���棻
            
            if(zgbnumGpmcLong<0.1d){
            	double zgbnum_wan = Double.parseDouble(zgbStr.replace("��",""));
            	gpmcDTO.setZgbnum(zgbnum_wan);
            	GpmcBO gBO = new GpmcBO();
            	gBO.update(gpmcDTO);
            }
            
            gpmcDTO.setZgbStr(mathUtil.getStringNumb(zgbnumLong));
            
            
            
            
            
            
            
            
            
            
            

            HttpUnitOptions.setScriptingEnabled(false);//�������е�css��javascript��
    		
            ;
			webTable = wc.getResponse ( new GetMethodWebRequest(xianshoujiejinFullURL) ).getTables()[0];//���۽����
			int rows  = webTable.getRowCount();//�������>1,��ʾ�н���ɣ�
            
            //���۽��ҳ��Ľ���

           
            
            if(rows<=1){
            	System.out.println("�ù�˾�������۽��!ֱ�Ӷ�����ֵ�����жϣ�");
            	
            	//zgbnumLong  ���ܹɱ������жϣ��������ֵ��С���޶�����ͨ��ֵ���϶����������ġ�
                //ֱ�ӽ�����Ĳ������ƹ������ɣ�
            	if(zszGpmcLong>ltszLong){
        			return  null;
        		}
            	//����ܵĻ���Ҳ���������
//            	���������set��gpmcDTO�
             	gpmcDTO.setDateSxgfNumberStr(mathUtil.getStringNumb(dateSxgfNumberALL));
            	
            	
            	//�˴�Ӧ�û�Ҫ��������ֵ���ж��Ƿ�����������ݻ�������30�ڵ������ӣ�ȫ��ͨ��Ҳ���Ǻܲ���ġ�
            	//return list;//ԭ·���أ�
            }else {
            	//��webTable���н�����

                
                String dateTitleString;
                
                String dateSxgfNumString="";
                
                long dateSxgfNumber;
                
                Date dateTitle=new Date();
               
                SimpleDateFormat   sdf   =   new   SimpleDateFormat("yyyy-MM-dd");
                          
                for(int j=0;j<rows;j++){
                	
                	
                	dateTitleString = webTable.getCellAsText(j,2);
        			dateSxgfNumString = webTable.getCellAsText(j,3);
        			
                	if(dateTitleString.equals("�������")){
                		continue;
                	}
                	//���ʱ�� �������(��) 
                	//System.out.println(dateTitleString);
                	dateTitle = sdf.parse(dateTitleString);//�߼������ʱ���ã�
                	
                	
                	
                	//gpmcDTO.setDate(date);
                 	
                 	
                 	//��Ҫ�����㷨����dateSxgfNumString����ڣ�������߼�������ת��Ϊlong�ͣ�
                 	dateSxgfNumber = mathUtil.getLong10000Numb(dateSxgfNumString.trim());//������ǰ�����޹ɷ�����������1��
                 	System.out.println(dateSxgfNumString+":"+dateSxgfNumber);
                 	
                 	if(dateTitle.getTime()>=queryDateDate.getTime()){
                		//˵���������޹ɣ���Ҫ����
                 		dateSxgfNumberALL+=dateSxgfNumber;
                	}
                	//���������set��gpmcDTO�
                 	gpmcDTO.setDateSxgfNumberStr(mathUtil.getStringNumb(dateSxgfNumberALL));
                	
                }               
            }
           
            
            
            
            
    		gpmcDTO.setScj(dqj);
            
            long zszLongNow = (long)(zgbnumLong*dqj);
            
            gpmcDTO.setZszStr(mathUtil.getStringNumb(zszLongNow));
            
            long dateLtgfNumber=zgbnumLong-dateSxgfNumberALL;//��query����ǰ����ͨ�ɱ���
            gpmcDTO.setDateLtgfNumberStr(mathUtil.getStringNumb(dateLtgfNumber));
            
            long dateLtgfMoney = (long)(dateLtgfNumber*dqj);//��query����ǰ����ͨ��ֵ��
            gpmcDTO.setDateLtszStr(mathUtil.getStringNumb(dateLtgfMoney));
            
            
            if(dateLtgfMoney>ltszLong ||zszLongNow>zszLong){
            	System.out.println(zqmc+"("+zqdm+")ץȡ������������ɸѡ������");
            	return null;
            }else {
            	
            	long dateYqjjszLong = dateLtgfMoney-dqyltszLong;
            	if(dateYqjjszLong<0){
            		dateYqjjszLong=0;
            	}
            	if(dateYqjjszLong==0){
            		gpmcDTO.setNewFlagInt(-1);//����
            	}
            	
            	
            	gpmcDTO.setDateYqjjszStr(mathUtil.getStringNumb(dateYqjjszLong));
            	
            	System.out.println(zqmc+"("+zqdm+")ץȡ����������ɸѡ����^*^");
            	
//              �ж��¹�����--��ͨ��ֵС��3.5���ڣ�date��ͨ��ֵ>��ͨ��ֵ/2  ����Ϊ�¹ɣ��Ժ�÷�����ȡ������
            	System.out.println(zqmc+"("+zqdm+")DateԤ�ڽ����ֵ��"+dateYqjjszLong);
            	long dateYqjjszLong2bei = dateYqjjszLong+dateYqjjszLong;
            	//System.out.println(zqmc+"("+zqdm+") 2����DateԤ�ڽ����ֵ��"+dateYqjjszLong2bei);
            	System.out.println(zqmc+"("+zqdm+") ��ǰ��ͨ��ֵ��"+dqyltszLong);
            	
                if(dqyltszLong<dateYqjjszLong2bei){
                	System.out.println(zqmc+"("+zqdm+") ����Ҫ�󣺵�ǰ��ͨ��ֵ<2����DateԤ�ڽ����ֵ��Ϊ�¹ɣ�");
                	//gpmcDTO.setNewFlag("Y");
                	gpmcDTO.setNewFlagInt(1);
                	
                }
            }

            
            
		} catch (ParseException e) {
			throw new BSWException("ת�����ڸ�ʽʧ��"+e.getMessage());
		} catch (Exception e) {
			System.out.println(zqmc+"("+zqdm+")ץȡʧ�ܣ������ù�Ʊץȡ������ץȡ��......");
			return null;//�쳣ץȡ����������ץȡ������ץȡ��
			//throw new BSWException("ץȡ��ҳʧ��"+e.getMessage());
		}
		
		return gpmcDTO;
	}

	
	public GpmcDTO getTsxgByUrl(String gubenjiegouFullURL,String xianshoujiejinFullURL,GpmcDTO gpmcDTO)throws BSWException{
		
		MathUtil mathUtil = new MathUtil();
		String zqdm  = gpmcDTO.getZqdm();
		String zqmc  = gpmcDTO.getZqmc();
		StockUtil stockUtil = new StockUtil();
		double dqj = stockUtil.getDqjByZqdm(zqdm);//��ǰ��
		if(dqj<0.1d){
			//�г���Ϊ0�ģ���ʾδ���еġ�ֱ��������
			return  null;
		}
		System.out.println(zqmc+"("+zqdm+")��ͨ��ֵ�����Ϣץȡ��ʼ......");
		
		
		
		long dateSxgfNumberALL=0L;
		try {
			
			
			
			Document gbjgDoc = Jsoup.connect(gubenjiegouFullURL).timeout(5000).get();//���������ϣ��ٱ���ʱ����
			
            Element gbjgBody = gbjgDoc.body();//�ɱ��ṹ  ��һ��ҳ��
           
            
            
//          ��gbjgBody���н�����
            //�ܹɱ�(��ʷ��¼)	20000 ���	
           			
            //��ͨA��(��ʷ��¼)	5000 ���
            //�ܹɱ�
            Element elementGbjgTable = gbjgBody.getElementById("StockStructureNewTable0");//<table id="StockStructureNewTable0" width="100%">
//          ��elementGbjgTable���н�����
            Element elementGbjgTbody =  elementGbjgTable.child(1); //<tbody>

            
            //�����<tr><td width='150'>���ܹɱ�(��ʷ��¼)</a></td><td>20000 ���</td><td>10000 ���</td><td>7500 ���</td></tr>
            //��7��<tr><td width='150'>&nbsp;&nbsp;��ͨ��</td><td></td><td></td><td></td></tr>
            Element elementGbjgTr5 = elementGbjgTbody.child(4);  //
            Element elementGbjgTr7 = elementGbjgTbody.child(6);  //
            
            //�ӵ�����͵����������ȡ--�ܹɱ���--��ͨA��
            
            
            
            String zgbStr  = elementGbjgTr5.child(1).text().replace(" ","").replace("��","");//��ǰ�ܹɱ���
            gpmcDTO.setZgbStr(zgbStr);//����/�ڵ�λ
            System.out.println("ץȡ�õ���ǰ�ܹɱ�����/�ڣ�:"+zgbStr);
            
            long zgbnumLong = mathUtil.getLongNumb(zgbStr);//��������ã��ж�gpmcDTO�����zgbnum���Ƿ�Ϊ0,���Ϊ��͸��µ�DTO���棻
            
            long zszLong = (long)(zgbnumLong*dqj);//����ֵ��Ԫ
            
            String zszStr = mathUtil.getStringNumb(zszLong);//��λΪ����
            gpmcDTO.setZszStr(zszStr);
            
            System.out.println(zqmc+"("+zqdm+")����ֵ��"+zszStr+" .....");
    		
            
//            ��ǰ��ͨ����  ��λ�����
            String dqyltnumStr  = elementGbjgTr7.child(1).text().replace(" ","").replace("��","");//��ǰ����ͨ�ɷ�
            
            
            System.out.println(zqmc+"("+zqdm+")��ǰ����ͨ�ɷ�(������)"+dqyltnumStr);
            
            long dqyltnumLong  = mathUtil.getLongNumb(dqyltnumStr);
            
           //System.out.println(zqmc+"("+zqdm+")��ǰ����ͨ�ɷ�Long��"+dqyltnumLong);
            
            gpmcDTO.setDqyltnumStr(dqyltnumStr);//����/�ڵ�λ
            
            long dqyltszLong = (long)(dqyltnumLong*dqj);
            
            //System.out.println(zqmc+"("+zqdm+")��ǰ����ͨ��ֵ��Ԫ����"+dqyltszLong+" ���˴�Ϊ ץȡҳ���õ���ֵ��");
    		
            String dqyltszStr =mathUtil.getStringNumb(dqyltszLong);
            
            gpmcDTO.setDqyltszStr(dqyltszStr);
            
            System.out.println(zqmc+"("+zqdm+")��ǰ����ͨ��ֵ����/�ڣ���"+dqyltszStr+" ����������ֵ)");
    		       
            
            //�÷�������ò�������Ϊ����Ҫ�������ݿ⣻
//            long zgbnumLong = mathUtil.getLongNumb(zgbStr);//��������ã��ж�gpmcDTO�����zgbnum���Ƿ�Ϊ0,���Ϊ��͸��µ�DTO���棻
//                       
//            gpmcDTO.setZgbStr(mathUtil.getStringNumb(zgbnumLong));
            gpmcDTO.setZgbStr(zgbStr);
            

            HttpUnitOptions.setScriptingEnabled(false);//�������е�css��javascript��
    		
            ;
			webTable = wc.getResponse ( new GetMethodWebRequest(xianshoujiejinFullURL) ).getTables()[0];//���۽����
			int rows  = webTable.getRowCount();//�������>1,��ʾ�н���ɣ�
            
            //���۽��ҳ��Ľ���

           
            
            if(rows<=1){
            	System.out.println("�ù�˾�������۽��!");
            	
            }else {
            	//��webTable���н�����

                
                String dateTitleString;
                
                String dateSxgfNumString="";
                
                long dateSxgfNumber;
                
                Date dateTitle=new Date();
               
                SimpleDateFormat   sdf   =   new   SimpleDateFormat("yyyy-MM-dd");
                          
                for(int j=0;j<rows;j++){
                	
                	
                	dateTitleString = webTable.getCellAsText(j,2);
        			dateSxgfNumString = webTable.getCellAsText(j,3);
        			
                	if(dateTitleString.equals("�������")){
                		continue;
                	}
                	//���ʱ�� �������(��) 
                	System.out.println("������ڣ�String��:"+dateTitleString);
                	System.out.println("������� ��/�ڣ�String��:"+dateSxgfNumString);
                	//dateTitle = sdf.parse(dateTitleString);//�߼������ʱ���ã�
                	
                	
                	
                	//gpmcDTO.setDate(date);
                 	
                 	
                 	//��Ҫ�����㷨����dateSxgfNumString����ڣ�������߼�������ת��Ϊlong�ͣ�
                 	//dateSxgfNumber = mathUtil.getLong10000Numb(dateSxgfNumString.trim());//������ǰ�����޹ɷ�����������1��
                 	
                 	//System.out.println("������ڣ�Date��:"+dateTitle);
                	//System.out.println("������� �ɣ�long��:"+dateSxgfNumber);
                	
                	
                 	System.out.println("����jiejinMessageMap��......");                	
                    //����jiejinMessageMap�У�
                 	gpmcDTO.getJiejinMessageMap().put(j+"��"+dateTitleString,dateSxgfNumString+"���");
                	
                }               
            }
           
            
            
            
            
    		gpmcDTO.setScj(dqj);
            
        	
        	System.out.println(zqmc+"("+zqdm+")ץȡ������^*^");
        	

        

            
            
		}  catch (Exception e) {
			System.out.println(zqmc+"("+zqdm+")ץȡʧ�ܣ�");
			return null;//�쳣ץȡ����������ץȡ������ץȡ��
			//throw new BSWException("ץȡ��ҳʧ��"+e.getMessage());
		}
		
		return gpmcDTO;
	}

	
	public List<GpmcDTO> getListGpmcsByUrl(String urlStr)throws BSWException{
		//��Ҫ�ж���1��Ʊ����0����
		StockUtil sUtil = new StockUtil();
		GpmcDTO gpmcDTO=null;
		List<GpmcDTO> list = new ArrayList<GpmcDTO>();
		try {

			Document doc = Jsoup.connect(urlStr).get();
            Element body = doc.body();
       
            
            Element quotesearchDiv =  body.getElementById("quotesearch");
            if(quotesearchDiv==null){
            	return null;
            }
//            <div id="quotesearch">
//        0    <div class="sltito">    </div>
//        1    <div class="sltit"> �Ϻ���Ʊ </div>
//            
//        2    <ul>
	//            <li><a target="_blank" href="http://quote.eastmoney.com/sh166105.html">�Ŵ�����(166105)</a></li>
	//            .....
	            
	//            <li><a target="_blank" href="http://quote.eastmoney.com/sh900957.html">����B��(900957)</a></li>
//            </ul>
//            
              
//        3    <div class="sltit"><a name="sz"/>���ڹ�Ʊ</div>
//            
	//    4    <ul>
	//            <li><a target="_blank" href="http://quote.eastmoney.com/sz000001.html">ƽ������(000001)</a></li>
	//            .....
	//         </ul>

            Elements childen = quotesearchDiv.children(); //����5��Element
            
            Element elementULShanghai =  childen.get(2);
            Elements elementULShanghaiChildenLis  = elementULShanghai.children();//�Ϻ���Ʊ�е�����<Li>
            //��Ҫ���������ˡ�����
            Iterator itChildenLi = elementULShanghaiChildenLis.iterator(); 
            Element elementLi;
            String msg="";
            String zqdm = "";
            String zqmc = "";
            for(int j=0;j<elementULShanghaiChildenLis.size();j++){
            	gpmcDTO = new GpmcDTO();
            	gpmcDTO.setFlag1("�Ϻ�A��");//֤ȯ��
            	
            	
            	
            	
            	elementLi = (Element)itChildenLi.next();
            	
            	msg  = elementLi.child(0).text();
            	
            	//System.out.println(msg);
            	zqmc=msg.substring(0,msg.indexOf("("));
    			zqdm=msg.substring(msg.indexOf("(")+1,msg.indexOf(")"));

            	gpmcDTO.setZqdm(zqdm);
            	gpmcDTO.setZqmc(zqmc);
            	
            	//��ʽ��һ�£�Ӧ����ȥ���ո��ǰ���0�����ȼ򻯴������ݿ⣻
      	      	Integer zqdmInteger = Integer.parseInt(gpmcDTO.getZqdm());
      	      	
      	      	gpmcDTO.setFlag2(sUtil.gupiaoORjijin(zqdmInteger));
      	      	
      		  	zqdm=zqdmInteger.toString();
      		  	gpmcDTO.setZqdm(zqdm);
      			zqmc = gpmcDTO.getZqmc();
      			zqmc = zqmc.replace(" ","");
      			zqmc = zqmc.replace(" ","");
      			gpmcDTO.setZqmc(zqmc);
      			
      			
      			gpmcDTO.setId(zqdm);
            	
            	list.add(gpmcDTO);
            	
            }
            
            
            
            
            Element elementULShenzhen = childen.get(4);
            
            Elements elementULShenzhenChildenLis  = elementULShenzhen.children();//���ڹ�Ʊ�е�����<Li>
            //��Ҫ���������ˡ�����
            itChildenLi = elementULShenzhenChildenLis.iterator(); 
            
            for(int j=0;j<elementULShenzhenChildenLis.size();j++){
            	gpmcDTO = new GpmcDTO();
            	gpmcDTO.setFlag1("����A��");//֤ȯ��
            	
            	
            	
            	
            	elementLi = (Element)itChildenLi.next();
            	
            	msg  = elementLi.child(0).text();
            	
            	System.out.println(msg);
            	zqmc=msg.substring(0,msg.indexOf("("));
    			zqdm=msg.substring(msg.indexOf("(")+1,msg.indexOf(")"));

            	gpmcDTO.setZqdm(zqdm);
            	gpmcDTO.setZqmc(zqmc);
            	
            	//��ʽ��һ�£�Ӧ����ȥ���ո��ǰ���0�����ȼ򻯴������ݿ⣻
      	      	Integer zqdmInteger = Integer.parseInt(gpmcDTO.getZqdm());	
      	      	gpmcDTO.setFlag2(sUtil.gupiaoORjijin(zqdmInteger));
      		  	zqdm=zqdmInteger.toString();
      		  	gpmcDTO.setZqdm(zqdm);
      			zqmc = gpmcDTO.getZqmc();
      			zqmc = zqmc.replace(" ","");
      			zqmc = zqmc.replace(" ","");
      			gpmcDTO.setZqmc(zqmc);
      			
      			
      			gpmcDTO.setId(zqdm);
            	
            	list.add(gpmcDTO);
            	
            }
            

		} catch (IOException e) {
			throw new BSWException("ץȡ��ҳʧ��"+e.getMessage());
		}
		
		return list;
	}
	public static void main(String[] args) throws BSWException{
		// TODO �Զ����ɷ������
//		JsoupParser jParser=new JsoupParser();
//		jParser.readDocumentByUrl("http://money.finance.sina.com.cn/corp/go.php/vMS_MarketHistory/stockid/002582.phtml?year=2015&jidu=3");
//        
	}

}
