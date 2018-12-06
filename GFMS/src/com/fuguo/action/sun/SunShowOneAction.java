//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package com.fuguo.action.sun;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import bsw.base.BaseAction;

import com.fuguo.bo.BodyFileBO;
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
public class SunShowOneAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SunForm m = (SunForm)form;
		int id = m.getId();
		SunDTO tDTO = new SunDTO();
		
		tDTO.setId(id);
		
		SunBO tBO =new SunBO();
		tDTO=tBO.query(tDTO);
		
		request.setAttribute("SUN",tDTO);

		
	}

	

}

