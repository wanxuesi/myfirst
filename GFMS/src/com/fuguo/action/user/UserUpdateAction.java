//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package com.fuguo.action.user;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import bsw.base.BaseAction;
import bsw.fwk.BaseUserContext;
import bsw.tools.exception.BSWException;

import com.fuguo.bo.ConfigBO;
import com.fuguo.bo.DataBO;
import com.fuguo.bo.UserBO;
import com.fuguo.dto.ConfigDTO;
import com.fuguo.dto.DataDTO;
import com.fuguo.dto.UserDTO;
import com.fuguo.form.UserForm;
import com.fuguo.util.DateUtil;

/** 
 * MyEclipse Struts
 * Creation date: 07-16-2008
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class UserUpdateAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserForm d = (UserForm)form;
		int id = d.getId();
		String xm = d.getXm();
		String wx = d.getWx();
		String otherflag=d.getOtherflag();
		String dateendStr = d.getDateend();
		
		UserDTO bDTO = new UserDTO();
		
		bDTO.setId(id);
//		调用业务逻辑层
		UserBO bBO =new UserBO();
		bDTO=bBO.query(bDTO);
		
		bDTO.setOtherflag(otherflag);
		bDTO.setWx(wx);
		SimpleDateFormat   sdfymd   =   new   SimpleDateFormat("yyyy-MM-dd"); 
		Date dateend   =   sdfymd.parse(dateendStr);
		
		bDTO.setDateend(dateend);

		
		
		
		
		
		
		
		//调用业务逻辑层
		bBO.update(bDTO);
		
		
	}

}

