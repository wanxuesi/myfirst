package io;

import java.util.ResourceBundle;

public class ResourceBundleUtil {
	
//	��ȡ��Դ�ļ�message_zh_CN.properties����Ϣ��
	public static ResourceBundle bundle = ResourceBundle.getBundle("io.message");
	

	public static String getString(String key){
		return bundle.getString(key);
	}

}
