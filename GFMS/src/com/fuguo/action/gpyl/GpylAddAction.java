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
public class GpylAddAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO 自动生成方法存根


		  GpylForm m = (GpylForm)form;
		
		  String lx = m.getLx();
		  String title =m.getTitle();
		  String content=m.getContent();
		  

		 String flag1 = m.getFlag1();//用户Id500
		 String flag2 = m.getFlag2();
		//封装成数据传输对象
	      GpylDTO tDTO = new GpylDTO();
	      
	      tDTO.setLx(lx);
	      tDTO.setTitle(title);
	      tDTO.setContent(content);
	      tDTO.setFlag1(flag1);
	      tDTO.setFlag2(flag2);
		
		GpylBO tBO =new GpylBO();
		tBO.add(tDTO);
		
		
	}

	

}

