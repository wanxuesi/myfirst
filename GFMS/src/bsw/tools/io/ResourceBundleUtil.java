package bsw.tools.io;

import java.util.ResourceBundle;

public class ResourceBundleUtil {
	//��ȡ��Դ�ļ�message_zh_CN.properties����Ϣ��
	private static ResourceBundle bundle = ResourceBundle.getBundle("bsw.tools.io.message");
	
	public static String getString(String key){
		return bundle.getString(key);
	}
}
