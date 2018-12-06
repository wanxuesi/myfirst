package com.fuguo.action.ssfenxi;

//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl


import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import bsw.base.BaseAction;

import com.fuguo.dto.KxianDTO;
import com.fuguo.form.JiluForm;
import com.fuguo.util.HttpRequestUtil;
import com.fuguo.util.StockUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


/** 
 * MyEclipse Struts
 * Creation date: 01-10-2008
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class SsfenxiJsonAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO 自动生成方法存根

		JiluForm m = (JiluForm)form;
		String zqdm  = m.getZqdm();
		String fullzqdm = StockUtil.getFullZqdmByZqdm(zqdm);
	
		String flag1=m.getFlag1();
		
		
		
		//HttpRequestUtil hUtil = new HttpRequestUtil();
		
		String url = "http://money.finance.sina.com.cn/quotes_service/api/json_v2.php/CN_MarketData.getKLineData?symbol="+fullzqdm+"&scale="+flag1+"&ma=no&datalen=1023";
		//String msg = hUtil.getJson(url).replace("\"","").replace("day","\"date\"").replace("open","\"open\"").replace("high","\"high\"").replace("low","\"low\"").replace("close","\"close\"").replace("volume","\"volume\"");
		String msg = HttpRequestUtil.getJson(url);
		//System.out.println(msg);
		
		Type listType = new TypeToken<LinkedList<KxianDTO>>(){}.getType();    
		Gson gson = new Gson();
		KxianDTO kDTO;
		LinkedList<KxianDTO> kxianDTOs = gson.fromJson(msg, listType);
		//对list进行解析；
		
		long dateTime;
		double open;
	    double high;
	    double low;
	    double close;
	    
	    SimpleDateFormat   sdf;
	    if(flag1.equals("240")){
	    	sdf   =   new   SimpleDateFormat("yyyy-MM-dd");
	    	
	    }else{
	    	sdf   =   new   SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    }
	     
		StringBuffer sb  =new StringBuffer();
		sb.append("[");
		for(int i=0;i<kxianDTOs.size();i++){
			kDTO = kxianDTOs.get(i);
			
			
			dateTime = sdf.parse(kDTO.getDay()).getTime();//这个地方有问题
			open = kDTO.getOpen();
			high = kDTO.getHigh();
			low  = kDTO.getLow();
			close = kDTO.getClose();
			if(i<1){
				sb.append("["+dateTime+","+open+","+high+","+low+","+close+"]");
			}else{
				sb.append(",["+dateTime+","+open+","+high+","+low+","+close+"]");
			}
		}
		sb.append("]");
		
		//System.out.println(sb.toString());
		
		

		
		//只能将这个String 转换成二维数组了。
		
		response.setCharacterEncoding("utf-8");//解决汉字乱码
		PrintWriter out = null; 
		
		        out = response.getWriter();  
		        out.append(sb.toString());  
		       
		    
		        if (out != null) {  
		            out.close();  
		        }  
		   
		

		
		
	}

	
}

