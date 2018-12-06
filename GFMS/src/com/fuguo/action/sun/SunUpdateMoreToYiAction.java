//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package com.fuguo.action.sun;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import bsw.base.BaseAction;
import bsw.tools.exception.BSWException;

import com.fuguo.bo.SunBO;
import com.fuguo.dto.SunDTO;

/** 
 * MyEclipse Struts
 * Creation date: 03-21-2008
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class SunUpdateMoreToYiAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		获取ids 并转换为int型
		String[] ids_Str=request.getParameterValues("ids");
		if(ids_Str==null){
			throw new BSWException("您没有选择任何未处理记录！");
		}
		int[] ids=new int[ids_Str.length];
		for(int j=0;j<ids_Str.length;j++){
			ids[j] = Integer.parseInt(ids_Str[j]);
		}
		SunBO t=new SunBO();
		//循环处理每一个id
		SunDTO tDTO;
		
		for(int i=0;i<ids.length;i++ ){
			//对每一个id进行处理
			
			
			
			//（一）根据这个id查找这条记录。
			tDTO=new SunDTO();
			tDTO.setId(ids[i]);
			tDTO = t.query(tDTO);
			//判断“数据状态”
			//System.out.println(tDTO.getSjzt().trim());
			if(tDTO.getJhzt().trim().equals("未处理")){
				//修改此月度计划的“数据状态”为"已批准"
				tDTO.setJhzt("已处理");
				t.update(tDTO);
				
				
			

			}
			
			
			
		}
		
	}

	

}

