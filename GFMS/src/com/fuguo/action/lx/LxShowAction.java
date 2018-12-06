package com.fuguo.action.lx;

//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import bsw.base.BaseAction;
import bsw.fwk.BaseUserContext;

import com.fuguo.bo.LxBO;
import com.fuguo.dto.LxDTO;
import com.fuguo.form.LxForm;




/** 
 * MyEclipse Struts
 * Creation date: 01-10-2008
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class LxShowAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO 自动生成方法存根

		LxForm m = (LxForm)form;
		BaseUserContext baseUserContext = (BaseUserContext) request.getSession().getAttribute("#BASEUSERCONTEXT#");
		int idUser  =baseUserContext.getId();
		String idStr  =Integer.toString(idUser);
		
		LxBO mBO = new LxBO();
		//显示系统交易分类和自己的交易分类
		LxDTO[] lxDTO=mBO.loadAll("from LxDTO lxDTO where flag1='"+idStr+"' or flag1='' or flag1 is Null  order by lxDTO.id asc");
		
		
		

		
		request.setAttribute("LX",lxDTO);

	}

	
}

