//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package com.fuguo.action.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import bsw.base.BaseAction;

import com.fuguo.bo.UserBO;
import com.fuguo.dto.UserDTO;
import com.fuguo.form.UserForm;

/** 
 * MyEclipse Struts
 * Creation date: 07-16-2008
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class UserShowOneAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO 自动生成方法存根
		UserForm d = (UserForm)form;
		int id = d.getId();
		UserDTO bDTO = new UserDTO();
		
		bDTO.setId(id);
//		调用业务逻辑层
		UserBO bBO =new UserBO();
		bDTO=bBO.query(bDTO);
		request.setAttribute("USER",bDTO);
		
	}

}

