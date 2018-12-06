package com.fuguo.util;
  
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.LinkedList;

import javax.xml.registry.infomodel.User;

import com.fuguo.dto.KxianDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
public class GsonTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自动生成方法存根
		
		
		String msg =" [{day:\"2016-06-07\",open:\"14.530\",high:\"14.530\",low:\"14.530\",close:\"14.530\",volume:\"11763\"},{day:\"2016-06-08\",open:\"15.980\",high:\"15.980\",low:\"15.980\",close:\"15.980\",volume:\"2778\"}]";
	
		
		
		Type listType = new TypeToken<LinkedList<KxianDTO>>(){}.getType();    
		Gson gson = new Gson();
		KxianDTO kDTO;
		LinkedList<KxianDTO> kxianDTOs = gson.fromJson(msg, listType);
		//对list进行解析；
		for(int i=0;i<kxianDTOs.size();i++){
			kDTO = kxianDTOs.get(i);
			
			System.out.println(kDTO.getDay());    
		    System.out.println(kDTO.getOpen());
		}
		
		String msgStr = gson.toJson(kxianDTOs);
	    
		
		System.out.println(msg);
		System.out.println(msgStr);
	
	}

}
