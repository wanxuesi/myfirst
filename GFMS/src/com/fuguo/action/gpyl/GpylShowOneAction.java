//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package com.fuguo.action.gpyl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import bsw.base.BaseAction;

import com.fuguo.bo.GpylBO;
import com.fuguo.dto.GpylDTO;
import com.fuguo.form.GpylForm;

/** 
 * MyEclipse Struts
 * Creation date: 01-10-2008
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class GpylShowOneAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO 自动生成方法存根
		GpylForm m = (GpylForm)form;
		int id = m.getId();
		
		GpylDTO tDTO = new GpylDTO();
		
		tDTO.setId(id);
		GpylBO tBO = new GpylBO();
		tDTO=tBO.query(tDTO);

		
		request.setAttribute("GPYL",tDTO);

		
	}

	

}

