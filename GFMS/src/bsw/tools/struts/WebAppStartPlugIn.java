package bsw.tools.struts;

import javax.servlet.ServletException;

import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;

//import bsw.tools.cache.CacheUtil;


/**
 * @����: Struts PlugIn�����ϵͳ����ĳ�ʼ���������ñ������õ�ActionServlet�����С�

 */
public class WebAppStartPlugIn implements PlugIn {

	public void init(ActionServlet servlet, ModuleConfig config)
			throws ServletException {
//		��ʼ���������
//		CacheUtil.getInstance();
//		���õ�ǰӦ�û�������
//		LogUtil.info("gsf","���õ�ǰӦ�û�������");
		WebApplicationContext.setContext(servlet.getServletContext());
	}

	public void destroy() {
		//LogUtil.info("gsf", "CacheInitPlugIn ��ֹ");
	}
}
