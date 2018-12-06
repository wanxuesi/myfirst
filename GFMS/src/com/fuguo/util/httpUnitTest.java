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
////			��ö�Ӧ�ı����� 
//			WebTable webTable = wr.getTables()[0];
//			int rowCount = webTable.getRowCount();
//			System.out.println("\n���������" + rowCount);	
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (SAXException e) {
//			e.printStackTrace();
//		}
		
		
		
		WebConversation wc = new WebConversation();

		

		WebRequest req = new GetMethodWebRequest("http://vip.stock.finance.sina.com.cn/q/go.php/vInvestConsult/kind/xsjj/index.phtml?symbol=sz300538");

		HttpUnitOptions.setScriptingEnabled(false);//�������е�css��javascript��
		
		try {
			WebResponse resp = wc.getResponse ( req );
			WebTable webTable = resp.getTables()[0];
			
			int rowCount  = webTable.getRowCount();
			System.out.println("һ��������"+(rowCount));
			String dateTitleString = webTable.getCellAsText(1,2);
			String dateSxgfNumString = webTable.getCellAsText(1,3);
			System.out.println("���ڣ�"+dateTitleString+";������"+dateSxgfNumString);
			if(rowCount>1){
				//˵���н����Ϣ��
				
			}
		} catch (IOException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
	}


}
