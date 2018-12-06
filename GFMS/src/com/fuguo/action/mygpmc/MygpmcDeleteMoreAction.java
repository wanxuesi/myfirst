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
//		��ȡids ��ת��Ϊint��
		String[] ids_Str=request.getParameterValues("ids");
		if(ids_Str==null){
			throw new BSWException("��û��ѡ���κ���ѡ�ɣ�");
		}
		int[] ids=new int[ids_Str.length];
		for(int j=0;j<ids_Str.length;j++){
			ids[j] = Integer.parseInt(ids_Str[j]);
		}
		MygpmcBO tBO =new MygpmcBO();
		//ѭ������ÿһ��id
		MygpmcDTO tDTO;
		int k=0;
		for(int i=0;i<ids.length;i++ ){
			//��ÿһ��id���д���
			
			
			
			//��һ���������id����������¼��
			tDTO=new MygpmcDTO();
			tDTO.setId(ids[i]);
			tBO.delete(tDTO);

			
			
			
		}

		
	}

	

}

