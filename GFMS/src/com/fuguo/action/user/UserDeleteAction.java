//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package com.fuguo.action.user;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import bsw.base.BaseAction;

import com.fuguo.bo.ConfigBO;
import com.fuguo.bo.DataBO;
import com.fuguo.bo.JiluBO;
import com.fuguo.bo.ListBO;
import com.fuguo.bo.OrderBO;
import com.fuguo.bo.UserBO;
import com.fuguo.dto.ConfigDTO;
import com.fuguo.dto.DataDTO;
import com.fuguo.dto.UserDTO;
import com.fuguo.form.UserForm;

/** 
 * MyEclipse Struts
 * Creation date: 07-17-2008
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class UserDeleteAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO �Զ����ɷ������
		UserForm bForm = (UserForm)form;
		int id =bForm.getId();
		String idStr  =Integer.toString(id);
//		��Ҫ����������У�
		//jilu����������ݣ�
		JiluBO t=new JiluBO();
		String sqlJilu = "delete from jilu where khdm='"+idStr+"'";
		t.sqlUpdateOrDel(sqlJilu);
		//order��������ݣ�
		OrderBO orderBO=new OrderBO();
		String sqlOrder = "delete from order where flag1='"+idStr+"'";
		orderBO.sqlUpdateOrDel(sqlOrder);
		
		//list��������ݣ�
		ListBO listBO=new ListBO();
		String sqlList = "delete from list where flag1='"+idStr+"'";
		listBO.sqlUpdateOrDel(sqlList);
		//data��������ݣ�
		DataBO dataBO=new DataBO();
		String sqlData = "delete from data where flag2='"+idStr+"'";
		dataBO.sqlUpdateOrDel(sqlData);
		
		//ɾ�����û���config��¼��
		
		ConfigDTO tDTO  = new ConfigDTO();
		
		tDTO.setUserid(idStr);
		ConfigBO tBO = new ConfigBO();
		tDTO = tBO.query(tDTO);
		if(tDTO!=null){
			tBO.delete(tDTO);
		}
		
		
		
		
		
		
		UserBO bBO = new UserBO();
		
		UserDTO uDTO  =new UserDTO();
		uDTO.setId(id);
		bBO.delete(uDTO);
		
	}

	
	

}

