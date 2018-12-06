package bsw.tools.io;

import java.util.ResourceBundle;

public class ResourceBundleUtil {
	//读取资源文件message_zh_CN.properties的信息。
	private static ResourceBundle bundle = ResourceBundle.getBundle("bsw.tools.io.message");
	
	public static String getString(String key){
		return bundle.getString(key);
	}
}
