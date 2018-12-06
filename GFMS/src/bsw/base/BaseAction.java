//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package bsw.base;

import java.io.BufferedReader;
import java.io.FileReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import bsw.fwk.BaseUserContext;


import bsw.tools.exception.BSWException;
/**
 * 
 * 
 * @����:����Action�Ļ��࣬����ת��ҳ�档
 * @����:wanxuesi@163.com
 * @����:��ѧ˼
 *
 */

public abstract class BaseAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		
		// ��session�л�ȡ�û���Ϣ����
		BaseUserContext baseUserContext = (BaseUserContext) request.getSession().getAttribute("#BASEUSERCONTEXT#");
		String errorMessage="";
		 //�ж��û��Ƿ��Ѿ���¼-----Ȩ����֤, 
//		�û��Ƿ��ѵ�¼
		if(baseUserContext==null){
			errorMessage = "����û�е�¼��";
			request.setAttribute("EXCEPTION",new BSWException(errorMessage));
			return mapping.findForward("fail");
		}
		
		//��ȡ�û������ù��ܵĹ�����(�˴���Action��path������Ϊ������)
		String path = mapping.getPath();
		path = path.substring(1);
		
		
		
//��ʱ����	
//		//�û��Ƿ��в����ù��ܵ�Ȩ��
//		if(!baseUserContext.allow(path)){
//			errorMessage = "�Բ�����û�в����ù��ܵ�Ȩ�ޣ��������Ա��ϵ��";
//			request.setAttribute("EXCEPTION",new BSWException(errorMessage));
//			return mapping.findForward("fail");
//		}
		
			
		
		
		
		 try {
			 //���������ʵ�ַ���
			 myexecute(mapping,form,request, response);
		 	 return mapping.findForward("success");
		 } catch (Exception e) {
			 request.setAttribute("EXCEPTION",e);
			 return mapping.findForward("fail");
		 }
	}

	/**
	 * ҵ����Ʒ������������ʵ�ָ÷���
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public abstract void myexecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception;
}

