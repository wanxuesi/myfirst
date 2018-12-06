package com.fuguo.util;
import java.io.IOException;

import org.xml.sax.SAXException;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.HttpUnitOptions;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import com.meterware.httpunit.WebTable;

public class httpUnitTest {
	
	
	public static void main(String[] args) {

//		WebConversation wc = new WebConversation();
//		WebResponse wr = null;
//		try {
//			wr = wc.getResponse( "http://vip.stock.finance.sina.com.cn/q/go.php/vInvestConsult/kind/xsjj/index.phtml?symbol=sh603131" );
////			获得对应的表格对象 
//			WebTable webTable = wr.getTables()[0];
//			int rowCount = webTable.getRowCount();
//			System.out.println("\n表格行数：" + rowCount);	
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (SAXException e) {
//			e.printStackTrace();
//		}
		
		
		
		WebConversation wc = new WebConversation();

		

		WebRequest req = new GetMethodWebRequest("http://vip.stock.finance.sina.com.cn/q/go.php/vInvestConsult/kind/xsjj/index.phtml?symbol=sz300538");

		HttpUnitOptions.setScriptingEnabled(false);//屏蔽所有的css，javascript；
		
		try {
			WebResponse resp = wc.getResponse ( req );
			WebTable webTable = resp.getTables()[0];
			
			int rowCount  = webTable.getRowCount();
			System.out.println("一共行数："+(rowCount));
			String dateTitleString = webTable.getCellAsText(1,2);
			String dateSxgfNumString = webTable.getCellAsText(1,3);
			System.out.println("日期："+dateTitleString+";数量："+dateSxgfNumString);
			if(rowCount>1){
				//说明有解禁信息；
				
			}
		} catch (IOException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
	}


}
