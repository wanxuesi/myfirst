//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package com.fuguo.action;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import bsw.base.BaseAction;
import bsw.fwk.BaseUserContext;
import bsw.tools.exception.BSWException;

import com.fuguo.bo.LoginBO;
import com.fuguo.dto.UserDTO;
import com.fuguo.form.UserForm;

public class LoginAction extends BaseAction {
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		//��ȡ���ݲ���װ��dto��
		UserForm userForm=(UserForm)form;
		String xm = userForm.getXm();
		String kl = userForm.getKl();
		String otherflag= userForm.getOtherflag();//��ʱ�������ж��Ƿ�Ҫ����cookies
		if(otherflag==null){
			otherflag="0";
		}else{
			otherflag="1";
		}
		UserDTO userDTO=new UserDTO();
		userDTO.setXm(xm);
		userDTO.setKl(kl);
		
		
		
		
		//����ҵ���߼���,�����ݱ��浽baseusercontext�С�
		LoginBO userBO = new LoginBO();
		try {
			BaseUserContext baseUserContext = userBO.login(userDTO);
//			�˴���Ҫ����coke
			if(otherflag.equals("1")){
				//���û�ID���������cookies�У��Ա��Զ���¼��
				xm = URLEncoder.encode(xm,"UTF-8");
				Cookie cookie1 = new Cookie("xm", xm);
				cookie1.setMaxAge(365*24*3600);
				response.addCookie(cookie1);
				Cookie cookie2 = new Cookie("kl", kl);
				cookie2.setMaxAge(365*24*3600);
				response.addCookie(cookie2);

				
				
				
			}else{
				
//				���times=0��ʱ�����cookies
				Cookie[] cookiesD=request.getCookies();
				 if(cookiesD!=null)   
				 {   
				     for(int   i=0;i<cookiesD.length;i++){
				    	 
				     if(cookiesD[i].getName().equals("xm")||cookiesD[i].getName().equals("kl"))   
				      {   
				          cookiesD[i].setMaxAge(0);   
				          response.addCookie(cookiesD[i]);   
				      }   
				      
				    }    
				} 
				
			}

			//���浽session��// ��ת���ɹ�ҳ�档
			request.getSession().setAttribute("#BASEUSERCONTEXT#",baseUserContext);
			Map<Integer,Integer> mapSession = new  HashMap<Integer,Integer>();//������¼��session
			request.getSession().setAttribute("#MAPSESSION#",mapSession);
			return mapping.findForward("success");
		} catch (BSWException e) {
			//�����쳣���׳�������ҳ�档
			request.setAttribute("EXCEPTION", e);
			return mapping.findForward("fail");		
		} catch (UnsupportedEncodingException e) {
			// TODO �Զ����� catch ��
			request.setAttribute("EXCEPTION", e);
			return mapping.findForward("fail");		
		}
		
		
	}


	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO �Զ����ɷ������
		
	}

}

