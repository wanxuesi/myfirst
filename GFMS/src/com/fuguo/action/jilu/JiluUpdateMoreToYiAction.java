//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package com.fuguo.action.jilu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import bsw.base.BaseAction;
import bsw.fwk.BaseUserContext;
import bsw.tools.exception.BSWException;

import com.fuguo.bo.JiluBO;
import com.fuguo.dto.JiluDTO;

/** 
 * MyEclipse Struts
 * Creation date: 03-21-2008
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class JiluUpdateMoreToYiAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		获取ids 并转换为int型
		String[] ids_Str=request.getParameterValues("ids");
		if(ids_Str==null){
			throw new BSWException("您没有选择任何未处理记录！");
		}
		BaseUserContext baseUserContext = (BaseUserContext) request.getSession().getAttribute("#BASEUSERCONTEXT#");
		int idUser  =baseUserContext.getId();
		String idStr  =Integer.toString(idUser);
		int[] ids=new int[ids_Str.length];
		for(int j=0;j<ids_Str.length;j++){
			ids[j] = Integer.parseInt(ids_Str[j]);
		}
		JiluBO t=new JiluBO();
		//循环处理每一个id
		JiluDTO tDTO;
		
		for(int i=0;i<ids.length;i++ ){
			//对每一个id进行处理
			
			
			
			//（一）根据这个id查找这条记录。
			tDTO=new JiluDTO();
			tDTO.setId(ids[i]);
			tDTO = t.query(tDTO);
			//判断“数据状态”
			//System.out.println(tDTO.getSjzt().trim());
			if(tDTO.getFlag1().trim().equals("未处理")){
				//修改此月度计划的“数据状态”为"已批准"
				
				
				//应该对该记录的order，list，data等进行操作，参考jiluAdd.do。
				//根据mmflag，等应该就可以直接调用；
				
				t.logic(tDTO,idStr,baseUserContext,false);//记录已经存在的情况下，不要要添加，批量导入的，做逻辑添加处理，做lsjgdate登记；
				
				tDTO.setFlag1("已处理");
				t.update(tDTO);
			}
			
			
			
		}
		
	}

	

}

