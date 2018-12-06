package io;

import java.util.ResourceBundle;

public class ResourceBundleUtil {
	
//	读取资源文件message_zh_CN.properties的信息。
	public static ResourceBundle bundle = ResourceBundle.getBundle("io.message");
	

	public static String getString(String key){
		return bundle.getString(key);
	}

}
