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
 * @����:��userȨ�޹�����(������޸ġ���ť����ֵ�ҳ��)
 * @��λ:�������
 * @����:wanxuesi@163.com
 * @����:��ѧ˼
 * @���ڣ�2008-6-3
 */

public class JuesefunctionPreManageAction extends BaseAction {

	/* ���� Javadoc��
	 * @see bsw.base.BaseAction#myLogic(org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	// TODO �Զ����ɷ������
		
		
		//���ձ�����
		JuesefunctionForm jfForm = (JuesefunctionForm)form;
		//int id =jfForm.getId();
		JuesefunctionDTO jfDTO = new JuesefunctionDTO();
		String tmp_jf = jfForm.getJuesename().trim();//��ȡ�û�����/get����
		
   		//����ת��������  		
   		byte[] tempBytejf =tmp_jf.getBytes("ISO-8859-1");
   		String juesename="";
   		juesename = new String(tempBytejf,"gb2312");
		
		//jfDTO.setJuesename(jf);
		
		
		//����ҵ���߼�����ʾ���еĹ�����Ϣ
		FunctionBO functionBO = new FunctionBO();
		FunctionDTO functionDTO[] =functionBO.loadAll("from FunctionDTO");
		
		//�����еĹ��ܷŵ���functionForms����ȥ��
		request.setAttribute("FUNCTIONDTOS",functionDTO);	
		
		//����ҵ���߼�����ʾ��ɫ���е�Ȩ��
		JuesefunctionBO juesefunctionBO = new JuesefunctionBO();
		//���ǿ�����sqlquery��ѯ
		List listDTOs = juesefunctionBO.sqlQuery("select jf.functioncode,f.funcname from Juesefunction jf,function f where jf.juesename='"+juesename+"' and jf.functioncode=f.functioncode",FunctionDTO.class);
		
		request.setAttribute("MYFUNCTIONDTOS",listDTOs);
		
		request.setAttribute("juesename",juesename);	//��ɫ����
	}

}
