package com.fuguo.util;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HttpRequestUtil {

	public static String  getJson(String requestUrl){
        String jsonStr="";
        try {
			Document docfq = Jsoup.connect(requestUrl).get();
			
			jsonStr = docfq.body().toString().replace("<body>","").replace("</body>","");
			
			
		} catch (IOException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		
        
                
          
               
         return jsonStr; 
    }

   

    //����
    public static void main(String args [] ) {
    	
    	
      
    }
	
	
}
