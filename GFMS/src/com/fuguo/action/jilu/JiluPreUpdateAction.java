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
public class JiluPreUpdateAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
//		 TODO �Զ����ɷ������
		JiluForm m = (JiluForm)form;
		int id = m.getId();
		JiluDTO tDTO = new JiluDTO();
		
		tDTO.setId(id);
		
		JiluBO tBO =new JiluBO();
		tDTO=tBO.query(tDTO);
		
//		String dwname = tDTO.getTbdw().trim();//eg���ݹ���
//		BaseUserContext baseUserContext = (BaseUserContext) request.getSession().getAttribute("#BASEUSERCONTEXT#");
//		if(baseUserContext.allow("weekMeOnly")&&(!baseUserContext.getDwname().equals(dwname))){
//			throw new BSWException("�����ܼƻ��޸Ļ�ɾ�������ƣ�ֻ���޸Ļ�ɾ�������ŵļ�¼��");
//		}
//		

		request.setAttribute("JILU",tDTO);
	}

	

}

