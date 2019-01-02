package com.fuguo.util;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import bsw.tools.exception.BSWException;

import com.fuguo.bo.GpmcBO;
import com.fuguo.bo.LsjgBO;
import com.fuguo.dto.GpmcDTO;
import com.fuguo.dto.LsjgDTO;
/**
 * 将下载完的文件统一保存到数据库工具类
 * @author Administrator
 *
 */
public class CSVUtil {

	/**
	 * @param args
	 */
	private BufferedReader bufferedreader = null;
	 private List list = new ArrayList();
	 
	 public CSVUtil(){

	 }
	 public CSVUtil(String filename) throws IOException{

	       bufferedreader = new BufferedReader(new FileReader(filename));
	       String stemp;
	       while((stemp = bufferedreader.readLine()) != null){
	           list.add(stemp);
	       }
	 }
	 public List getList() throws IOException {

	        return list;
	 }
	 // 得到csv文件的行数
	 public int getRowNum(){

	     return list.size();
	 }

	    //得到csv文件的列数
	 public int getColNum(){

	       if(!list.toString().equals("[]")) {
	      
	         //csv文件中，每列之间的是用','来分隔的
	            if(list.get(0).toString().contains(",")) { 
	                return list.get(0).toString().split(",").length;
	            }else if(list.get(0).toString().trim().length() != 0) {
	                return 1;
	            }else{
	                return 0;
	            }
	       }else{
	            return 0;
	        }
	 }
	 
	    //取得指定行的值
	 public String getRow(int index) {

	     if (this.list.size() != 0)
	      return (String) list.get(index);
	     else                      
	      return null;
	 }

	 //取得指定列的值
	 public String getCol(int index){

	       if (this.getColNum() == 0){
	                return null;
	       }
	      
	       StringBuffer scol = new StringBuffer();
	       String temp = null;
	       int colnum = this.getColNum();
	     
	       if (colnum > 1){
	          for (Iterator it = list.iterator(); it.hasNext();) {
	             temp = it.next().toString();
	             scol = scol.append(temp.split(",")[index] + ",");
	          }
	       }else{
	          for (Iterator it = list.iterator(); it.hasNext();) {
	            temp = it.next().toString();
	            scol = scol.append(temp + ",");
	          }
	       }
	       String str=new String(scol.toString());
	       str = str.substring(0, str.length() - 1);
	       return str;
	 }

	 //取得指定行，指定列的值
	 public String getString(int row, int col) {

	        String temp = null;
	        int colnum = this.getColNum();
	        if(colnum > 1){
	            temp = list.get(row).toString().split(",")[col];
	        }else if(colnum == 1) {
	            temp = list.get(row).toString();
	        }else{
	            temp = null;
	        }
	            return temp;
	 }


	 public void CsvClose() throws IOException {
	     this.bufferedreader.close();
	 }

	 public void run(String zqdm,String filename) throws BSWException, IOException {
	  
		 GpmcBO gBO = new GpmcBO();
		 GpmcDTO gDTO = new GpmcDTO();
		 gDTO.setId(zqdm);
		 gDTO = gBO.query(gDTO);
		 String zqmc = gDTO.getZqmc();
		 if(zqmc==null||zqmc.equals("")){
			 throw new BSWException("通过证券代码获取股票名称失败，请查看（股票名称维护）中，是否已经添加该股票信息！"); 
		 } 
	  CSVUtil cu = new CSVUtil(filename);
	  String dateString;
	  String openString;
	  String highString;
	  String lowString;
	  String closeString;
	  String volumeString;
	  String adjcloseString;
	  Date date=new Date();
	  double open;
	  double high;
	  double low;
	  double close;
	  double volume;
	  double adjclose;
	  LsjgDTO lsjgDTO=null;
	  LsjgBO lsjgBO=new LsjgBO();
	  SimpleDateFormat   sdf   =   new   SimpleDateFormat("yyyy-MM-dd");
	     for(int i=1;i<cu.getRowNum();i++){
	    	 
	    	 //标题行不读；
	           dateString  = cu.getString(i,0);//得到第i行.第1列的数据.
	           try {
				date = sdf.parse(dateString);
			} catch (ParseException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
	           openString  = cu.getString(i,1);//得到第i行.第2列的数据.
	           open = Double.valueOf(openString);
	           highString  = cu.getString(i,2);//得到第i行.第3列的数据.
	           high = Double.valueOf(highString);
	           lowString  = cu.getString(i,3);//得到第i行.第4列的数据.
	           low = Double.valueOf(lowString);
	           closeString = cu.getString(i,4);//得到第i行.第5列的数据.
	           close = Double.valueOf(closeString);
	           volumeString =cu.getString(i,5);//得到第i行.第6列的数据.
	           volume = Double.valueOf(volumeString);
	           adjcloseString =cu.getString(i,6);//得到第i行.第7列的数据.
	           adjclose = Double.valueOf(adjcloseString);
	           
	           //System.out.println("Date:"+dateString+"   收盘价："+closeString);
	           lsjgDTO=new LsjgDTO();
	           //证券名称，证券代码；
	           //lsjgDTO.setId(i+100);
	           lsjgDTO.setZqdm(zqdm);
	           lsjgDTO.setZqmc(zqmc);
	           lsjgDTO.setDate(date);
	           
	           lsjgDTO.setOpen(open);
	           lsjgDTO.setHigh(high);
	           lsjgDTO.setLow(low);
	           lsjgDTO.setClose(close);
	           lsjgDTO.setVolume(volume);
	           lsjgDTO.setAdjclose(adjclose);
	           java.sql.Date dateSql = new java.sql.Date(date.getTime()); 
	           //将数据保存到数据库中
	           try {
	        	//*****需要通过日期和证券代码查找，如果已经存在，则不需要添加
	        	   String sql = "select * from lsjg where zqdm='"+zqdm+"'and date(date)='"+dateSql+"'";
	        	   System.out.println(sql);
	        	   List listLsjg = lsjgBO.sqlQuery(sql,LsjgDTO.class);  
	        	   if(listLsjg.size()<1){
	        		   lsjgBO.add(lsjgDTO);
	        	   }
	        	   
	        	   
	        	   
				
			} catch (BSWException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
	     }
	     cu.CsvClose();
	     System.out.println("数据保存完毕！");
	 }

	
	 
	 public static void main(String[] args) throws IOException {

		  CSVUtil test = new CSVUtil();
		   String path="";
		   String zqdm="";
		   String zqmc=""; 
		   
		   zqdm="2582";  zqmc="好想你";
		   
		  
		   
//		   zqdm="600547";  zqmc="山东黄金";
		   
		   
		   
		   path = "C:/"+zqmc+".csv";
		   try {
			test.run(zqdm,path);
		} catch (BSWException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}


	 }
}
