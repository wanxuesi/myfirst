//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package com.fuguo.action.jilu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import bsw.base.BaseAction;
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
public class JiluDeleteMoreAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		��ȡids ��ת��Ϊint��
		String[] ids_Str=request.getParameterValues("ids");
		if(ids_Str==null){
			throw new BSWException("��û��ѡ���κμ�¼��");
		}
		int[] ids=new int[ids_Str.length];
		for(int j=0;j<ids_Str.length;j++){
			ids[j] = Integer.parseInt(ids_Str[j]);
		}
		JiluBO t=new JiluBO();
		//ѭ������ÿһ��id
		JiluDTO tDTO;
		int k=0;
		for(int i=0;i<ids.length;i++ ){
			//��ÿһ��id���д���
			
			
			
			//��һ���������id����������¼��
			tDTO=new JiluDTO();
			tDTO.setId(ids[i]);
			tDTO = t.query(tDTO);
//			String timeid = tDTO.getTimeid();
//			//�жϡ��ƻ�״̬��
//			//System.out.println(tDTO.getSjzt().trim());
//			if(tDTO.getJhzt().trim().equals("δ�༭")){
//				
			if(tDTO.getFlag1().equals("δ����")){
				t.delete(tDTO);
			}
				
				
			
//
//			}else{
//				//k++;
//				t.delete(tDTO);
//				//ͬ��ɾ���չ�����¼��
////				ͨ��timeidɾ���չ�����¼
//				DayplanBO dBO = new DayplanBO();
//				String sql = "select id,timeid from dayplan where timeid='"+timeid+"'";
//				List list =dBO.sqlQuery(sql);
//				//��ȡ������¼��id��
//				Iterator it = list.iterator();
//				Map _map=null;
//				DayplanDTO dDTO;
//				if(it.hasNext()){
//					_map=(Map)it.next();
//					
//					dDTO=new DayplanDTO();
//					dDTO.setId((Integer)_map.get("ID"));
//					
//					dBO.delete(dDTO);
//				}
//			}
			
			
			
		}
//		if(k>0){
//			throw new BSWException("����"+k+"�����ѱ༭����¼û��ɾ����");
//		}
		
	}

	

}

