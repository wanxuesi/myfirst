package com.fuguo.util;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import com.fuguo.bo.GpmcBO;
import com.fuguo.dto.GpmcDTO;
public class HttpDownFileDemo {

	 private static int BUFFER_SIZE = 8096;

	 /**根据URL下载文件并保存
	 * @param destUrl String
	 * @param fileName String
	 * @throws Exception
	 */
	 public void saveToFile(String destUrl, String fileName) throws IOException {
	 
	  FileOutputStream fos = null;
	  BufferedInputStream bis = null;
	  HttpURLConnection httpUrl = null;
	  URL url = null;
	  byte[] buf = new byte[BUFFER_SIZE];
	  int size = 0;
	 
	  url = new URL(destUrl);
	  httpUrl = (HttpURLConnection) url.openConnection();
	  httpUrl.connect();
	  bis = new BufferedInputStream(httpUrl.getInputStream());
	  fos = new FileOutputStream(fileName);
	  while ((size = bis.read(buf)) != -1)
	   fos.write(buf, 0, size);
	  fos.close();
	  bis.close();
	  httpUrl.disconnect();
	 }
	 
	 //http://table.finance.yahoo.com/table.csv?a=0&b=1&c=2012&d=3&e=19&f=2012&s=600000.ss
	 //通过2个日期，和股票代码，自动找出该股票的名称；//zqdm为简单的形式；
//	 public String getDestUrlByDates_zqdm(Date startDate,Date endDate,String zqdm){
//		 //startDate
//		 int a = startDate.getMonth();
//		 int b = startDate.getDate();
//		 int c = startDate.getYear()+1900;
//		 
//		 //endDate
//		 int d = endDate.getMonth();
//		 int e = endDate.getDate();
//		 int f = endDate.getYear()+1900;
//		 
//		 StockUtil sUtil = new StockUtil();
//		 String fullZqdm = sUtil.getFullZqdmByZqdm2(zqdm);
//		 
//		 String destUrl = "http://table.finance.yahoo.com/table.csv?a="+a+"&b="+b+"&c="+c+"&d="+d+"&e="+e+"&f="+f+"&s="+fullZqdm;
//		 GpmcBO gBO = new GpmcBO();
//		 GpmcDTO gDTO = new GpmcDTO();
//		 gDTO.setId(zqdm);
//		 gDTO = gBO.query(gDTO);
//		 return null;
//	 }
	public static void main(String[] args) {
		// TODO 自动生成方法存根
		HttpDownFileDemo httpdownFile = new HttpDownFileDemo();
		try {
			httpdownFile.saveToFile("http://table.finance.yahoo.com/table.csv?s=600547.ss","C:/山东黄金.csv");
			
			httpdownFile.saveToFile("http://table.finance.yahoo.com/table.csv?s=002582.sz","C:/好想你.csv");
			System.out.println("数据已下载到C盘");
		} catch (IOException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

	}

}
