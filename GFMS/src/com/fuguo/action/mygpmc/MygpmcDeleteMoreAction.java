//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package com.fuguo.action.mygpmc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import bsw.base.BaseAction;
import bsw.tools.exception.BSWException;

import com.fuguo.bo.MygpmcBO;
import com.fuguo.dto.MygpmcDTO;

/** 
 * MyEclipse Struts
 * Creation date: 03-21-2008
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class MygpmcDeleteMoreAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		获取ids 并转换为int型
		String[] ids_Str=request.getParameterValues("ids");
		if(ids_Str==null){
			throw new BSWException("您没有选择任何自选股！");
		}
		int[] ids=new int[ids_Str.length];
		for(int j=0;j<ids_Str.length;j++){
			ids[j] = Integer.parseInt(ids_Str[j]);
		}
		MygpmcBO tBO =new MygpmcBO();
		//循环处理每一个id
		MygpmcDTO tDTO;
		int k=0;
		for(int i=0;i<ids.length;i++ ){
			//对每一个id进行处理
			
			
			
			//（一）根据这个id查找这条记录。
			tDTO=new MygpmcDTO();
			tDTO.setId(ids[i]);
			tBO.delete(tDTO);

			
			
			
		}

		
	}

	

}

