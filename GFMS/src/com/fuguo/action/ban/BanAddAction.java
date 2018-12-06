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

/** 
 * MyEclipse Struts
 * Creation date: 12-25-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class BanAddAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		DanweiForm d=(DanweiForm)form;
		//String bz = d.getBz().trim();
		String name = d.getName().trim();
		int	   parent = d.getParent();
		DanweiDTO dDTO = new DanweiDTO();
		//dDTO.setBz(bz);
		dDTO.setName(name);
		dDTO.setParent(parent);
		DanweiBO dBO=new DanweiBO();
		dBO.add(dDTO);
	}

	

}

