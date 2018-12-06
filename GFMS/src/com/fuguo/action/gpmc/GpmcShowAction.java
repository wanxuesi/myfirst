package com.fuguo.action.gpmc;

//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import bsw.base.BaseAction;

import com.fuguo.bo.GpmcBO;
import com.fuguo.dto.GpmcDTO;
import com.fuguo.form.GpmcForm;




/** 
 * MyEclipse Struts
 * Creation date: 01-10-2008
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class GpmcShowAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO 自动生成方法存根

		GpmcForm m = (GpmcForm)form;
		
		
		GpmcBO mBO = new GpmcBO();
		//GpmcDTO[] dataDTO=mBO.loadAll("from GpmcDTO gpmcDTO  where gpmcDTO.flag2='1' order by gpmcDTO.flag2 desc,flag1,zqdm");
		GpmcDTO[] dataDTO=mBO.loadAll("from GpmcDTO gpmcDTO  order by gpmcDTO.flag2 desc,flag1,zqdm");//显示基金
		
		request.setAttribute("GPMC",dataDTO);

	}

	
}

