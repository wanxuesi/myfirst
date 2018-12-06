//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package com.fuguo.action.jilu;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import bsw.base.BaseAction;
import bsw.fwk.BaseUserContext;
import bsw.tools.exception.BSWException;

import com.fuguo.bo.JiluBO;
import com.fuguo.dto.JiluDTO;
import com.fuguo.form.JiluForm;
import com.fuguo.util.StockUtil;

/** 
 * MyEclipse Struts
 * Creation date: 01-10-2008
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class JiluUpdateSpcsAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		��ȡids ��ת��Ϊint��
		String[] ids_Str=request.getParameterValues("ids");
		if(ids_Str==null){
			throw new BSWException("��û��ѡ���κ�δ�����¼��");
		}
		
		int[] ids=new int[ids_Str.length];
		for(int j=0;j<ids_Str.length;j++){
			ids[j] = Integer.parseInt(ids_Str[j]);
		}
		JiluBO t=new JiluBO();
		//ѭ������ÿһ��id
		JiluDTO tDTO;
		String zqmc="";
		String zqdm="";
		for(int i=0;i<ids.length;i++ ){
			//��ÿһ��id���д���
			
			
			
			//��һ���������id����������¼��
			tDTO=new JiluDTO();
			tDTO.setId(ids[i]);
			tDTO = t.query(tDTO);
			
			if(i==0){
				
				zqmc = tDTO.getZqmc();
				zqdm =tDTO.getZqdm();
				
			}
			
			//�жϡ�����״̬��
			 String flag2  = tDTO.getFlag2();
			 String flag3  = tDTO.getFlag3();
		        if(flag2==null||flag2.equals("")){
		        	
		        	flag2="1";
		        	
		        	flag3 = ids_Str[0];//flag3��Ҫ��ʶ����ֱ������2�ߵĵ�һ��id�ž��У���
		        }else{
		        	flag2="";
		        	
		        	flag3="";//flag3��Ҫ������룻
		        }
		        
		        
		        tDTO.setFlag2(flag2);
		        tDTO.setFlag3(flag3);
		      
			
		        t.update(tDTO);
			
			
			
		}
		
		
		
		
		
		
		

	        
	        
	       
		
		request.setAttribute("ZQMC",zqmc);
		request.setAttribute("ZQDM",zqdm);
		
	}

	

}

