package com.fuguo.action.ban;

//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import bsw.base.BaseAction;

import com.fuguo.bo.DanweiBO;
import com.fuguo.dto.DanweiDTO;
import com.fuguo.form.DanweiForm;


public class BanUpdateAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		DanweiForm d=(DanweiForm)form;
		DanweiDTO dDTO = new DanweiDTO();
		dDTO.setId(d.getId());
		//dDTO.setBz(d.getBz().trim());
		dDTO.setName(d.getName().trim());
		dDTO.setParent(d.getParent());
		DanweiBO dBO = new DanweiBO();
		dBO.update(dDTO);
		
	}


}

