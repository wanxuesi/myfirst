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
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		
        
                
          
               
         return jsonStr; 
    }

   

    //测试
    public static void main(String args [] ) {
    	
    	
      
    }
	
	
}
