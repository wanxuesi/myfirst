//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package com.fuguo.action.mygpmc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import bsw.base.BaseAction;

import com.fuguo.bo.MygpmcBO;
import com.fuguo.dto.MygpmcDTO;
import com.fuguo.form.MygpmcForm;

/** 
 * MyEclipse Struts
 * Creation date: 01-10-2008
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class MygpmcDeleteAction extends BaseAction {


	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO 自动生成方法存根
		MygpmcForm m = (MygpmcForm)form;
		Integer id = m.getId();
		
		MygpmcDTO tDTO = new MygpmcDTO();
		
		tDTO.setId(id);
		MygpmcBO tBO =new MygpmcBO();
		tBO.delete(tDTO);
	}

}

