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
 * @描述: 
 * 		  
 * @单位:
 * @邮箱:wanxuesi@163.com
 * @作者:万学思
 * @日期：2008-9-4
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
			//先判断是基金还是股票；
			boolean isJiJin = StockUtil.isJiJin(zqdm);
			//如果是基金(static String gupiaoORjijin(Integer zqdmInteger)），就跳过复权因子，直接将复权因子直接设置为1
			if(isJiJin==true){
				//什么都不干
			}else{
//				应该此处获取复权因子，然后放入到临时中，map<date,double>
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
	            	if(dateStringfq.equals("日期")){
	            		continue;
	            	}
	            	//复权因子 
	            	//System.out.println(dateString);
	            	//datefq = sdf.parse(dateStringfq);
	            	
	            	
	             	element7fq = childenTdsfq.get(7);
	             	fqyzString = element7fq.text();
	             	fqyz = Double.valueOf(fqyzString);
	             	
	            	
	             	mapfq.put(dateStringfq,fqyz);
	             	System.out.println(dateStringfq+"的复权因子"+fqyz+"已放入map中！");
	            	
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
            	if(dateString.equals("日期")){
            		continue;
            	}
            	//日期 开盘价 最高价 收盘价 最低价 交易量(股) 
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
    				//默认的复权值为1；
            		lsjgDTO.setFlag1("standard");
            		lsjgDTO.setFqyz(1d);
            		
    			}else{
//    				通过map中的日期，添加复权因子
                	System.out.println("开始获取键为"+dateString+"的值");
                	Double fqyzDouble = mapfq.get(dateString);
                	if(fqyzDouble==null){
                		lsjgDTO.setFlag1("tmp");
                		lsjgDTO.setFqyz(0d);
                	}else{
                		lsjgDTO.setFlag1("standard");
                    	lsjgDTO.setFqyz(fqyzDouble);
                	}
                	
                	System.out.println("得到键为"+dateString+"的值为："+fqyzDouble);
    			}
            	
            	
            	
            	
            	list.add(lsjgDTO);
            	
            }
            

		} catch (IOException e) {
			throw new BSWException("抓取网页失败"+e.getMessage());
		} catch (ParseException e) {
			throw new BSWException("转换日期格式失败"+e.getMessage());
		}
		
		return list;
	}
	
	
	
	public GpmcDTO getTsxgByUrl(String gubenjiegouFullURL,String xianshoujiejinFullURL,GpmcDTO gpmcDTO,long zszLong, long ltszLong, Date queryDateDate)throws BSWException{
		String zqdm  = gpmcDTO.getZqdm();
		String zqmc  = gpmcDTO.getZqmc();
		StockUtil stockUtil = new StockUtil();
		double dqj = stockUtil.getDqjByZqdm(zqdm);//当前价
		if(dqj<0.1d){
			//市场价为0的，表示未上市的。直接跳过；
			return  null;
		}
		System.out.println(zqmc+"("+zqdm+")抓取开始......");
		double zgbnumGpmc = gpmcDTO.getZgbnum();
		
		long zgbnumGpmcLong = (long)(zgbnumGpmc*10000);//股票名称里面的总股本转换；
		long zszGpmcLong = (long)(zgbnumGpmcLong*dqj);
		if(zszGpmcLong>zszLong){
			System.out.println(zqmc+"("+zqdm+")总市值过大，不符合筛选条件！直接跳过！");
			return  null;
		}
		
		
		
		long dateSxgfNumberALL=0L;
		try {
			
			MathUtil mathUtil = new MathUtil();
			
			Document gbjgDoc = Jsoup.connect(gubenjiegouFullURL).timeout(1000).get();//五秒连不上，再报超时错误；
			
            Element gbjgBody = gbjgDoc.body();//股本结构  第一个页面
           
            
            
            
            
            
//          对gbjgBody进行解析；
            //总股本(历史记录)	20000 万股	
           			
            //流通A股(历史记录)	5000 万股
            //总股本
            Element elementGbjgTable = gbjgBody.getElementById("StockStructureNewTable0");//<table id="StockStructureNewTable0" width="100%">
//          对elementGbjgTable进行解析；
            Element elementGbjgTbody =  elementGbjgTable.child(1); //<tbody>

            
            //第五个<tr><td width='150'>·总股本(历史记录)</a></td><td>20000 万股</td><td>10000 万股</td><td>7500 万股</td></tr>
            //第7个<tr><td width='150'>&nbsp;&nbsp;流通股</td><td></td><td></td><td></td></tr>
            Element elementGbjgTr5 = elementGbjgTbody.child(4);  //
            Element elementGbjgTr7 = elementGbjgTbody.child(6);  //
            
            //从第五个和第六个里面获取--总股本，--流通A股
            
            String dqyltnumStr  = elementGbjgTr7.child(1).text().replace(" ","").replace("股","");//当前已流通股份
            
            
            System.out.println(zqmc+"("+zqdm+")当前已流通股份(包含万)"+dqyltnumStr);
            
            long dqyltnumLong  = mathUtil.getLongNumb(dqyltnumStr);
            
            System.out.println(zqmc+"("+zqdm+")当前已流通股份Long型"+dqyltnumStr);
            
            gpmcDTO.setDqyltnumStr(mathUtil.getStringNumb(dqyltnumLong));
            
            long dqyltszLong = (long)(dqyltnumLong*dqj);
            gpmcDTO.setDqyltszStr(mathUtil.getStringNumb(dqyltszLong));
            
//          判断新股条件--流通市值小于4个亿，date流通市值>流通市值/2  定义为新股；以后该方法抽取出来；
            if(dqyltszLong<=400000000L){
            	//gpmcDTO.setNewFlag("Y");
            	gpmcDTO.setNewFlagInt(1);
            }
            
            
            
            
            System.out.println("当前已流通股份:"+dqyltnumStr);
            
            String zgbStr  = elementGbjgTr5.child(1).text().replace(" ","").replace("股","");//当前总股本；
            
            System.out.println("当前总股本:"+zgbStr);
            long zgbnumLong = mathUtil.getLongNumb(zgbStr);//这个很有用，判断gpmcDTO里面的zgbnum，是否为0,如果为零就更新到DTO里面；
            
            if(zgbnumGpmcLong<0.1d){
            	double zgbnum_wan = Double.parseDouble(zgbStr.replace("万",""));
            	gpmcDTO.setZgbnum(zgbnum_wan);
            	GpmcBO gBO = new GpmcBO();
            	gBO.update(gpmcDTO);
            }
            
            gpmcDTO.setZgbStr(mathUtil.getStringNumb(zgbnumLong));
            
            
            
            
            
            
            
            
            
            
            

            HttpUnitOptions.setScriptingEnabled(false);//屏蔽所有的css，javascript；
    		
            ;
			webTable = wc.getResponse ( new GetMethodWebRequest(xianshoujiejinFullURL) ).getTables()[0];//限售解禁表
			int rows  = webTable.getRowCount();//如果行数>1,表示有解禁股；
            
            //限售解禁页面的解析

           
            
            if(rows<=1){
            	System.out.println("该公司暂无限售解禁!直接对总市值进行判断！");
            	
            	//zgbnumLong  对总股本进行判断，如果总市值还小于限定的流通市值；肯定符合条件的。
                //直接将后面的参数复制过来即可；
            	if(zszGpmcLong>ltszLong){
        			return  null;
        		}
            	//如果≤的话，也算符合条件
//            	将相关数据set到gpmcDTO里；
             	gpmcDTO.setDateSxgfNumberStr(mathUtil.getStringNumb(dateSxgfNumberALL));
            	
            	
            	//此处应该还要计算总市值，判断是否符合条件；暂缓；比如30亿的总盘子，全流通，也还是很不错的。
            	//return list;//原路返回；
            }else {
            	//对webTable进行解析；

                
                String dateTitleString;
                
                String dateSxgfNumString="";
                
                long dateSxgfNumber;
                
                Date dateTitle=new Date();
               
                SimpleDateFormat   sdf   =   new   SimpleDateFormat("yyyy-MM-dd");
                          
                for(int j=0;j<rows;j++){
                	
                	
                	dateTitleString = webTable.getCellAsText(j,2);
        			dateSxgfNumString = webTable.getCellAsText(j,3);
        			
                	if(dateTitleString.equals("解禁日期")){
                		continue;
                	}
                	//解禁时间 解禁数量(股) 
                	//System.out.println(dateTitleString);
                	dateTitle = sdf.parse(dateTitleString);//逻辑运算的时候用；
                	
                	
                	
                	//gpmcDTO.setDate(date);
                 	
                 	
                 	//需要调用算法，对dateSxgfNumString里的亿，万进行逻辑解析，转换为long型；
                 	dateSxgfNumber = mathUtil.getLong10000Numb(dateSxgfNumString.trim());//该日期前还受限股份数量；扩大1万倍
                 	System.out.println(dateSxgfNumString+":"+dateSxgfNumber);
                 	
                 	if(dateTitle.getTime()>=queryDateDate.getTime()){
                		//说明还是受限股；需要叠加
                 		dateSxgfNumberALL+=dateSxgfNumber;
                	}
                	//将相关数据set到gpmcDTO里；
                 	gpmcDTO.setDateSxgfNumberStr(mathUtil.getStringNumb(dateSxgfNumberALL));
                	
                }               
            }
           
            
            
            
            
    		gpmcDTO.setScj(dqj);
            
            long zszLongNow = (long)(zgbnumLong*dqj);
            
            gpmcDTO.setZszStr(mathUtil.getStringNumb(zszLongNow));
            
            long dateLtgfNumber=zgbnumLong-dateSxgfNumberALL;//该query日期前的流通股本；
            gpmcDTO.setDateLtgfNumberStr(mathUtil.getStringNumb(dateLtgfNumber));
            
            long dateLtgfMoney = (long)(dateLtgfNumber*dqj);//该query日期前的流通市值；
            gpmcDTO.setDateLtszStr(mathUtil.getStringNumb(dateLtgfMoney));
            
            
            if(dateLtgfMoney>ltszLong ||zszLongNow>zszLong){
            	System.out.println(zqmc+"("+zqdm+")抓取结束！不符合筛选条件！");
            	return null;
            }else {
            	
            	long dateYqjjszLong = dateLtgfMoney-dqyltszLong;
            	if(dateYqjjszLong<0){
            		dateYqjjszLong=0;
            	}
            	if(dateYqjjszLong==0){
            		gpmcDTO.setNewFlagInt(-1);//点赞
            	}
            	
            	
            	gpmcDTO.setDateYqjjszStr(mathUtil.getStringNumb(dateYqjjszLong));
            	
            	System.out.println(zqmc+"("+zqdm+")抓取结束！符合筛选条件^*^");
            	
//              判断新股条件--流通市值小于3.5个亿，date流通市值>流通市值/2  定义为新股；以后该方法抽取出来；
            	System.out.println(zqmc+"("+zqdm+")Date预期解禁市值："+dateYqjjszLong);
            	long dateYqjjszLong2bei = dateYqjjszLong+dateYqjjszLong;
            	//System.out.println(zqmc+"("+zqdm+") 2倍的Date预期解禁市值："+dateYqjjszLong2bei);
            	System.out.println(zqmc+"("+zqdm+") 当前流通市值："+dqyltszLong);
            	
                if(dqyltszLong<dateYqjjszLong2bei){
                	System.out.println(zqmc+"("+zqdm+") 符合要求：当前流通市值<2倍的Date预期解禁市值，为新股！");
                	//gpmcDTO.setNewFlag("Y");
                	gpmcDTO.setNewFlagInt(1);
                	
                }
            }

            
            
		} catch (ParseException e) {
			throw new BSWException("转换日期格式失败"+e.getMessage());
		} catch (Exception e) {
			System.out.println(zqmc+"("+zqdm+")抓取失败！放弃该股票抓取，继续抓取中......");
			return null;//异常抓取，放弃本次抓取，继续抓取；
			//throw new BSWException("抓取网页失败"+e.getMessage());
		}
		
		return gpmcDTO;
	}

	
	public GpmcDTO getTsxgByUrl(String gubenjiegouFullURL,String xianshoujiejinFullURL,GpmcDTO gpmcDTO)throws BSWException{
		
		MathUtil mathUtil = new MathUtil();
		String zqdm  = gpmcDTO.getZqdm();
		String zqmc  = gpmcDTO.getZqmc();
		StockUtil stockUtil = new StockUtil();
		double dqj = stockUtil.getDqjByZqdm(zqdm);//当前价
		if(dqj<0.1d){
			//市场价为0的，表示未上市的。直接跳过；
			return  null;
		}
		System.out.println(zqmc+"("+zqdm+")流通市值相关信息抓取开始......");
		
		
		
		long dateSxgfNumberALL=0L;
		try {
			
			
			
			Document gbjgDoc = Jsoup.connect(gubenjiegouFullURL).timeout(5000).get();//五秒连不上，再报超时错误；
			
            Element gbjgBody = gbjgDoc.body();//股本结构  第一个页面
           
            
            
//          对gbjgBody进行解析；
            //总股本(历史记录)	20000 万股	
           			
            //流通A股(历史记录)	5000 万股
            //总股本
            Element elementGbjgTable = gbjgBody.getElementById("StockStructureNewTable0");//<table id="StockStructureNewTable0" width="100%">
//          对elementGbjgTable进行解析；
            Element elementGbjgTbody =  elementGbjgTable.child(1); //<tbody>

            
            //第五个<tr><td width='150'>·总股本(历史记录)</a></td><td>20000 万股</td><td>10000 万股</td><td>7500 万股</td></tr>
            //第7个<tr><td width='150'>&nbsp;&nbsp;流通股</td><td></td><td></td><td></td></tr>
            Element elementGbjgTr5 = elementGbjgTbody.child(4);  //
            Element elementGbjgTr7 = elementGbjgTbody.child(6);  //
            
            //从第五个和第六个里面获取--总股本，--流通A股
            
            
            
            String zgbStr  = elementGbjgTr5.child(1).text().replace(" ","").replace("股","");//当前总股本；
            gpmcDTO.setZgbStr(zgbStr);//含万/亿单位
            System.out.println("抓取得到当前总股本（万/亿）:"+zgbStr);
            
            long zgbnumLong = mathUtil.getLongNumb(zgbStr);//这个很有用，判断gpmcDTO里面的zgbnum，是否为0,如果为零就更新到DTO里面；
            
            long zszLong = (long)(zgbnumLong*dqj);//总市值：元
            
            String zszStr = mathUtil.getStringNumb(zszLong);//单位为万、亿
            gpmcDTO.setZszStr(zszStr);
            
            System.out.println(zqmc+"("+zqdm+")总市值："+zszStr+" .....");
    		
            
//            当前流通股数  单位：万股
            String dqyltnumStr  = elementGbjgTr7.child(1).text().replace(" ","").replace("股","");//当前已流通股份
            
            
            System.out.println(zqmc+"("+zqdm+")当前已流通股份(包含万)"+dqyltnumStr);
            
            long dqyltnumLong  = mathUtil.getLongNumb(dqyltnumStr);
            
           //System.out.println(zqmc+"("+zqdm+")当前已流通股份Long型"+dqyltnumLong);
            
            gpmcDTO.setDqyltnumStr(dqyltnumStr);//含万/亿单位
            
            long dqyltszLong = (long)(dqyltnumLong*dqj);
            
            //System.out.println(zqmc+"("+zqdm+")当前已流通市值（元）："+dqyltszLong+" （此处为 抓取页面获得的数值）");
    		
            String dqyltszStr =mathUtil.getStringNumb(dqyltszLong);
            
            gpmcDTO.setDqyltszStr(dqyltszStr);
            
            System.out.println(zqmc+"("+zqdm+")当前已流通市值（万/亿）："+dqyltszStr+" （程序换算后的值)");
    		       
            
            //该方法这边用不到，因为不需要存入数据库；
//            long zgbnumLong = mathUtil.getLongNumb(zgbStr);//这个很有用，判断gpmcDTO里面的zgbnum，是否为0,如果为零就更新到DTO里面；
//                       
//            gpmcDTO.setZgbStr(mathUtil.getStringNumb(zgbnumLong));
            gpmcDTO.setZgbStr(zgbStr);
            

            HttpUnitOptions.setScriptingEnabled(false);//屏蔽所有的css，javascript；
    		
            ;
			webTable = wc.getResponse ( new GetMethodWebRequest(xianshoujiejinFullURL) ).getTables()[0];//限售解禁表
			int rows  = webTable.getRowCount();//如果行数>1,表示有解禁股；
            
            //限售解禁页面的解析

           
            
            if(rows<=1){
            	System.out.println("该公司暂无限售解禁!");
            	
            }else {
            	//对webTable进行解析；

                
                String dateTitleString;
                
                String dateSxgfNumString="";
                
                long dateSxgfNumber;
                
                Date dateTitle=new Date();
               
                SimpleDateFormat   sdf   =   new   SimpleDateFormat("yyyy-MM-dd");
                          
                for(int j=0;j<rows;j++){
                	
                	
                	dateTitleString = webTable.getCellAsText(j,2);
        			dateSxgfNumString = webTable.getCellAsText(j,3);
        			
                	if(dateTitleString.equals("解禁日期")){
                		continue;
                	}
                	//解禁时间 解禁数量(股) 
                	System.out.println("解禁日期（String）:"+dateTitleString);
                	System.out.println("解禁数量 万/亿（String）:"+dateSxgfNumString);
                	//dateTitle = sdf.parse(dateTitleString);//逻辑运算的时候用；
                	
                	
                	
                	//gpmcDTO.setDate(date);
                 	
                 	
                 	//需要调用算法，对dateSxgfNumString里的亿，万进行逻辑解析，转换为long型；
                 	//dateSxgfNumber = mathUtil.getLong10000Numb(dateSxgfNumString.trim());//该日期前还受限股份数量；扩大1万倍
                 	
                 	//System.out.println("解禁日期（Date）:"+dateTitle);
                	//System.out.println("解禁数量 股（long）:"+dateSxgfNumber);
                	
                	
                 	System.out.println("放入jiejinMessageMap中......");                	
                    //放入jiejinMessageMap中；
                 	gpmcDTO.getJiejinMessageMap().put(j+"、"+dateTitleString,dateSxgfNumString+"万股");
                	
                }               
            }
           
            
            
            
            
    		gpmcDTO.setScj(dqj);
            
        	
        	System.out.println(zqmc+"("+zqdm+")抓取结束！^*^");
        	

        

            
            
		}  catch (Exception e) {
			System.out.println(zqmc+"("+zqdm+")抓取失败！");
			return null;//异常抓取，放弃本次抓取，继续抓取；
			//throw new BSWException("抓取网页失败"+e.getMessage());
		}
		
		return gpmcDTO;
	}

	
	public List<GpmcDTO> getListGpmcsByUrl(String urlStr)throws BSWException{
		//需要判断是1股票还是0基金；
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
//        1    <div class="sltit"> 上海股票 </div>
//            
//        2    <ul>
	//            <li><a target="_blank" href="http://quote.eastmoney.com/sh166105.html">信达增利(166105)</a></li>
	//            .....
	            
	//            <li><a target="_blank" href="http://quote.eastmoney.com/sh900957.html">凌云B股(900957)</a></li>
//            </ul>
//            
              
//        3    <div class="sltit"><a name="sz"/>深圳股票</div>
//            
	//    4    <ul>
	//            <li><a target="_blank" href="http://quote.eastmoney.com/sz000001.html">平安银行(000001)</a></li>
	//            .....
	//         </ul>

            Elements childen = quotesearchDiv.children(); //共有5个Element
            
            Element elementULShanghai =  childen.get(2);
            Elements elementULShanghaiChildenLis  = elementULShanghai.children();//上海股票中的所有<Li>
            //需要迭代解析了。。。
            Iterator itChildenLi = elementULShanghaiChildenLis.iterator(); 
            Element elementLi;
            String msg="";
            String zqdm = "";
            String zqmc = "";
            for(int j=0;j<elementULShanghaiChildenLis.size();j++){
            	gpmcDTO = new GpmcDTO();
            	gpmcDTO.setFlag1("上海A股");//证券所
            	
            	
            	
            	
            	elementLi = (Element)itChildenLi.next();
            	
            	msg  = elementLi.child(0).text();
            	
            	//System.out.println(msg);
            	zqmc=msg.substring(0,msg.indexOf("("));
    			zqdm=msg.substring(msg.indexOf("(")+1,msg.indexOf(")"));

            	gpmcDTO.setZqdm(zqdm);
            	gpmcDTO.setZqmc(zqmc);
            	
            	//格式化一下；应该是去掉空格和前面的0，极度简化存入数据库；
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
            
            Elements elementULShenzhenChildenLis  = elementULShenzhen.children();//深圳股票中的所有<Li>
            //需要迭代解析了。。。
            itChildenLi = elementULShenzhenChildenLis.iterator(); 
            
            for(int j=0;j<elementULShenzhenChildenLis.size();j++){
            	gpmcDTO = new GpmcDTO();
            	gpmcDTO.setFlag1("深圳A股");//证券所
            	
            	
            	
            	
            	elementLi = (Element)itChildenLi.next();
            	
            	msg  = elementLi.child(0).text();
            	
            	System.out.println(msg);
            	zqmc=msg.substring(0,msg.indexOf("("));
    			zqdm=msg.substring(msg.indexOf("(")+1,msg.indexOf(")"));

            	gpmcDTO.setZqdm(zqdm);
            	gpmcDTO.setZqmc(zqmc);
            	
            	//格式化一下；应该是去掉空格和前面的0，极度简化存入数据库；
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
			throw new BSWException("抓取网页失败"+e.getMessage());
		}
		
		return list;
	}
	public static void main(String[] args) throws BSWException{
		// TODO 自动生成方法存根
//		JsoupParser jParser=new JsoupParser();
//		jParser.readDocumentByUrl("http://money.finance.sina.com.cn/corp/go.php/vMS_MarketHistory/stockid/002582.phtml?year=2015&jidu=3");
//        
	}

}
