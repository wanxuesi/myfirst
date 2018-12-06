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
//		��ȡids ��ת��Ϊint��
		String[] ids_Str=request.getParameterValues("ids");
		if(ids_Str==null){
			throw new BSWException("��û��ѡ���κ�δ�����¼��");
		}
		BaseUserContext baseUserContext = (BaseUserContext) request.getSession().getAttribute("#BASEUSERCONTEXT#");
		int idUser  =baseUserContext.getId();
		String idStr  =Integer.toString(idUser);
		int[] ids=new int[ids_Str.length];
		for(int j=0;j<ids_Str.length;j++){
			ids[j] = Integer.parseInt(ids_Str[j]);
		}
		JiluBO t=new JiluBO();
		//ѭ������ÿһ��id
		JiluDTO tDTO;
		
		for(int i=0;i<ids.length;i++ ){
			//��ÿһ��id���д���
			
			
			
			//��һ���������id����������¼��
			tDTO=new JiluDTO();
			tDTO.setId(ids[i]);
			tDTO = t.query(tDTO);
			//�жϡ�����״̬��
			//System.out.println(tDTO.getSjzt().trim());
			if(tDTO.getFlag1().trim().equals("δ����")){
				//�޸Ĵ��¶ȼƻ��ġ�����״̬��Ϊ"����׼"
				
				
				//Ӧ�öԸü�¼��order��list��data�Ƚ��в������ο�jiluAdd.do��
				//����mmflag����Ӧ�þͿ���ֱ�ӵ��ã�
				
				t.logic(tDTO,idStr,baseUserContext,false);//��¼�Ѿ����ڵ�����£���ҪҪ��ӣ���������ģ����߼���Ӵ�����lsjgdate�Ǽǣ�
				
				tDTO.setFlag1("�Ѵ���");
				t.update(tDTO);
			}
			
			
			
		}
		
	}

	

}

