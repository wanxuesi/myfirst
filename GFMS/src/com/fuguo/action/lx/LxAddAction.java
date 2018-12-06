//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package com.fuguo.action.lx;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import bsw.base.BaseAction;
import bsw.tools.exception.BSWException;

import com.fuguo.bo.LxBO;
import com.fuguo.dto.LxDTO;
import com.fuguo.form.LxForm;

/** 
 * MyEclipse Struts
 * Creation date: 01-10-2008
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class LxAddAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO �Զ����ɷ������


		  LxForm m = (LxForm)form;
		
		  String name = m.getName();
		 if(name.trim().length()==0){
			 throw new BSWException("��ǰ���׷�������Ϊ�գ�");
		 }

		 String flag1 = m.getFlag1();//�û�Id500
		 String flag2 = m.getFlag2().trim();//���ͱ�ע��Ϊ�յĻ����Ͳ�֧���Զ����׽�ģ ����5MA,20MA,60MA,120MA,150MA
		
		 String _flag2 = flag2.replace("MA","").replace("ma","");
		 if(!_flag2.equals("")){
			 int flag2Inter = Integer.parseInt(_flag2);
			 if(flag2Inter<=0){
				 throw new BSWException("��ǰ���ͱ�ע�����Ͻ�ģ����"); 
			 }
		 }
		
		 //��װ�����ݴ������
	      LxDTO tDTO = new LxDTO();
	      
	      tDTO.setName(name);
	     
	      tDTO.setFlag1(flag1);
	      tDTO.setFlag2(flag2);
		
		LxBO tBO =new LxBO();
		tBO.add(tDTO);
		
		
	}

	

}

