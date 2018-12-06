package com.fuguo.action.juesefunction;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import bsw.base.BaseAction;

import com.fuguo.bo.JuesefunctionBO;
import com.fuguo.dto.JuesefunctionDTO;
import com.fuguo.form.JuesefunctionForm;
/**
 * 
 * @描述:单位部门权限管理  --可以任意的添加，删除权限--即选的来，选的去的那种
 * @单位:东瑞电力
 * @邮箱:wanxuesi@163.com
 * @作者:万学思
 * @日期：2008-6-3
 */
public class JuesefunctionManageAction extends BaseAction {

	/* （非 Javadoc）
	 * @see bsw.base.BaseAction#myLogic(org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//		接收表单数据，并封装

		JuesefunctionForm sfForm = (JuesefunctionForm)form;
		String[] functioncodes = sfForm.getFunctioncodes();
		String juesename = sfForm.getJuesename();
		
		//在Juesefunction中删除juesename为“班组用户”的所有记录；
		JuesefunctionBO jsBO = new JuesefunctionBO();
		JuesefunctionDTO[] jsDTOs = jsBO.loadAll("from JuesefunctionDTO where juesename='"+juesename+"'");
		jsBO.delete(jsDTOs);
		
		//添加记录；
		JuesefunctionDTO[] needJuesefunctionDTO = null;
		if(functioncodes!=null&&functioncodes.length>0){
			needJuesefunctionDTO = new JuesefunctionDTO[functioncodes.length];
			for(int i=0;i<needJuesefunctionDTO.length;i++){				
				needJuesefunctionDTO[i] = new JuesefunctionDTO();
				needJuesefunctionDTO[i].setJuesename(juesename);
				needJuesefunctionDTO[i].setFunctioncode(functioncodes[i]);
				jsBO.add(needJuesefunctionDTO[i]);
			}
		}

	
	}

}
