package bsw.base;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.runtime.HttpJspBase;

import bsw.fwk.BaseUserContext;

/**
 * 
 * @author Administrator
 *
 */

public class BaseJSP extends HttpJspBase {

	
	public static String root = "/";
	
	
	/** 
	 * ʵ�ָ��෽��  
	 * @see org.apache.jasper.runtime.HttpJspBase#_jspService(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void _jspService(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
	}


	/**
	 * ���ص�ǰ�û�����
	 * @param request
	 * @param response
	 * @return baseUserContext ��ǰ�û�������������
	 */
	public BaseUserContext getUser(HttpServletRequest request){
		BaseUserContext baseUserContext = (BaseUserContext)request.getSession().getAttribute("#BASEUSERCONTEXT#");
		return baseUserContext;
	}

	/**
	 * �����󱣴��ڵ�ǰSession�С�
	 * 
	 * @param request Jspҳ��request����
	 * @param index Session���������
	 * @param obj Session����ֵ��
	 */
	public void addObjectToSession(
			javax.servlet.http.HttpServletRequest request,
			java.lang.String index, java.lang.Object obj) {
		HttpSession session = request.getSession(false);
		if (session == null)
			session = request.getSession(true);
		if (obj == null) {
			session.removeAttribute(index);
		} else {
			session.setAttribute(index, obj);
		}
	}

	/**
	 * ��ȡSession����
	 * 
	 * @param request Jspҳ��request����
	 * @param index  Session����ֵ��
	 * @return Session����ֵ
	 */
	public Object getObjectFromSession(HttpServletRequest request, String index) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			return session.getAttribute(index);
		} else
			return null;
	}	
}
