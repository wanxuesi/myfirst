//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package com.fuguo.action.sun;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import bsw.base.BaseAction;

import com.fuguo.bo.SunBO;
import com.fuguo.dto.SunDTO;
import com.fuguo.form.SunForm;

/** 
 * MyEclipse Struts
 * Creation date: 01-10-2008
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class SunPreUpdateAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
//		 TODO 自动生成方法存根
		SunForm m = (SunForm)form;
		int id = m.getId();
		SunDTO tDTO = new SunDTO();
		
		tDTO.setId(id);
		
		SunBO tBO =new SunBO();
		tDTO=tBO.query(tDTO);
		
//		String dwname = tDTO.getTbdw().trim();//eg常州工区
//		BaseUserContext baseUserContext = (BaseUserContext) request.getSession().getAttribute("#BASEUSERCONTEXT#");
//		if(baseUserContext.allow("weekMeOnly")&&(!baseUserContext.getDwname().equals(dwname))){
//			throw new BSWException("您的周计划修改或删除有限制，只能修改或删除本部门的记录！");
//		}
//		

		request.setAttribute("SUN",tDTO);
	}

	

}

