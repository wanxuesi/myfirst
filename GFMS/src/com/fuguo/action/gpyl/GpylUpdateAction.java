//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package com.fuguo.action.gpyl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import bsw.base.BaseAction;
import bsw.fwk.BaseUserContext;
import bsw.tools.exception.BSWException;

import com.fuguo.bo.GpylBO;
import com.fuguo.dto.GpylDTO;
import com.fuguo.form.GpylForm;

/**
 * MyEclipse Struts Creation date: 01-10-2008
 * 
 * XDoclet definition:
 * 
 * @struts.action validate="true"
 */
public class GpylUpdateAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GpylForm m = (GpylForm) form;
		
		
		String flag1 = m.getFlag1();//用户Id500
		
		
		BaseUserContext baseUserContext = (BaseUserContext)request.getSession().getAttribute("#BASEUSERCONTEXT#");
		    
		int idInt = baseUserContext.getId();
		String idStr = Integer.toString(idInt);
		
		if(!flag1.equals(idStr)){
			throw new BSWException("该语录为其他用户添加，请勿修改！");
		}
		String lx = m.getLx();
		String title =m.getTitle();
		String content=m.getContent();
		  

		 
		 String flag2 = m.getFlag2();//记录时间
		
		int id = m.getId();

		GpylDTO tDTO = new GpylDTO();

		tDTO.setId(id);
		GpylBO tBO = new GpylBO();
		tDTO = tBO.query(tDTO);

		 tDTO.setLx(lx);
	      tDTO.setTitle(title);
	      tDTO.setContent(content);
	      tDTO.setFlag1(flag1);
	      tDTO.setFlag2(flag2);
		  tBO.update(tDTO);

	}

}
