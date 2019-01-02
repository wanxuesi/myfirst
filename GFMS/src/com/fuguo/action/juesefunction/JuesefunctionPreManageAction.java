package com.fuguo.action.juesefunction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import bsw.base.BaseAction;

import com.fuguo.bo.FunctionBO;
import com.fuguo.bo.JuesefunctionBO;
import com.fuguo.dto.FunctionDTO;
import com.fuguo.dto.JuesefunctionDTO;
import com.fuguo.form.JuesefunctionForm;
/**
 * 
 * @描述:打开user权限管理功能(点击“修改”按钮后出现的页面)
 * @单位:东瑞电力
 * @邮箱:wanxuesi@163.com
 * @作者:万学思
 * @日期：2008-6-3
 */

public class JuesefunctionPreManageAction extends BaseAction {

	/* （非 Javadoc）
	 * @see bsw.base.BaseAction#myLogic(org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	// TODO 自动生成方法存根
		
		
		//接收表单数据
		JuesefunctionForm jfForm = (JuesefunctionForm)form;
		//int id =jfForm.getId();
		JuesefunctionDTO jfDTO = new JuesefunctionDTO();
		String tmp_jf = jfForm.getJuesename().trim();//获取用户姓名/get编码
		
   		//编码转换工作。  		
   		byte[] tempBytejf =tmp_jf.getBytes("ISO-8859-1");
   		String juesename="";
   		juesename = new String(tempBytejf,"gb2312");
		
		//jfDTO.setJuesename(jf);
		
		
		//调用业务逻辑，显示所有的功能信息
		FunctionBO functionBO = new FunctionBO();
		FunctionDTO functionDTO[] =functionBO.loadAll("from FunctionDTO");
		
		//将所有的功能放到“functionForms”中去。
		request.setAttribute("FUNCTIONDTOS",functionDTO);	
		
		//调用业务逻辑，显示角色已有的权限
		JuesefunctionBO juesefunctionBO = new JuesefunctionBO();
		//还是可以用sqlquery查询
		List listDTOs = juesefunctionBO.sqlQuery("select jf.functioncode,f.funcname from Juesefunction jf,function f where jf.juesename='"+juesename+"' and jf.functioncode=f.functioncode",FunctionDTO.class);
		
		request.setAttribute("MYFUNCTIONDTOS",listDTOs);
		
		request.setAttribute("juesename",juesename);	//角色名称
	}

}
