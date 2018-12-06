//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package com.fuguo.action.jilu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import bsw.base.BaseAction;

import com.fuguo.bo.JiluBO;
import com.fuguo.dto.JiluDTO;
import com.fuguo.form.JiluForm;

/** 
 * MyEclipse Struts
 * Creation date: 01-10-2008
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class JiluDeleteAction extends BaseAction {


	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO 自动生成方法存根
		JiluForm m = (JiluForm)form;
		int id = m.getId();
		
		JiluDTO tDTO = new JiluDTO();
		
		tDTO.setId(id);
		JiluBO tBO = new JiluBO();
		tDTO = tBO.query(tDTO);
		
		//String dwname = tDTO.getTbdw().trim();//eg常州工区
//		BaseUserContext baseUserContext = (BaseUserContext) request.getSession().getAttribute("#BASEUSERCONTEXT#");
//		if(baseUserContext.allow("weekMeOnly")&&(!baseUserContext.getDwname().equals(dwname))){
//			throw new BSWException("您的周计划修改或删除有限制，只能修改或删除本部门的记录！");
//		}
//		String timeid = tDTO.getTimeid();
//		if(tDTO.getJhzt().equals("已编辑")){
			//throw new BSWException("该条记录为已编辑状态，无法删除！");
//		}	
			tBO.delete(tDTO);
	}

}

