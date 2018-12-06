//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package com.fuguo.action.tsxg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import bsw.base.BaseAction;
import bsw.tools.exception.BSWException;

import com.fuguo.bo.GpmcBO;
import com.fuguo.dto.GpmcDTO;
import com.fuguo.dto.MygpmcDTO;
import com.fuguo.form.GpmcForm;

/** 
 * MyEclipse Struts
 * Creation date: 01-10-2008
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class VolDeleteAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO 自动生成方法存根
//		获取ids 并转换为int型
		String zqdm =request.getParameter("zqdm");
		if(zqdm==null){
			throw new BSWException("您没有选择任何记录！");
		}

	    //批量更新；
		GpmcBO tBO = new GpmcBO();
//		对每一个id进行处理
		GpmcDTO tDTO = new GpmcDTO();

		tDTO.setId(zqdm);
		
		tDTO = tBO.query(tDTO);
		
		
		tDTO.setVolflag(0);
		tBO.update(tDTO);
		
		
		
		
	}

	

}

