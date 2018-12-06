//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package com.fuguo.action.lx;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import bsw.base.BaseAction;
import bsw.fwk.BaseUserContext;
import bsw.tools.exception.BSWException;

import com.fuguo.bo.ListBO;
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
public class LxDeleteAction extends BaseAction {


	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO �Զ����ɷ������
		LxForm m = (LxForm)form;
		int id = m.getId();
		
		BaseUserContext baseUserContext = (BaseUserContext)request.getSession().getAttribute("#BASEUSERCONTEXT#");
		    
		int idInt = baseUserContext.getId();
		String idStr = Integer.toString(idInt);
		
		LxDTO tDTO = new LxDTO();
		
		tDTO.setId(id);
		
		LxBO tBO = new LxBO();
		
		tDTO = tBO.query(tDTO);
		String name = tDTO.getName();
		String flag1 = tDTO.getFlag1();//����ǿգ����߲��Ǳ�ID���Ͳ���ɾ����
	    if(flag1==null){
	    	flag1="";
	    }
		//��Ҫ��ѯlist�����޸����͵ļ�¼������У����޷�ɾ����
		ListBO uBO =new ListBO();
		List listTMP = uBO.sqlQuery("select distinct jifl from  list where flag1='"+idStr+"'");
		
		Iterator itTMP = listTMP.iterator();
		Map _map=null;
		Map<String,String> mapjifl=new HashMap<String,String>();
		while(itTMP.hasNext()){
			_map=(Map)itTMP.next();
			
			
			String jifl = (String)_map.get("JIFL");
			
			
			mapjifl.put(jifl,jifl);
		}
		
		if(flag1.equals("")){
			throw new BSWException("ϵͳ�������ͣ��޷�ɾ����");
		}
		if(!flag1.equals(idStr)){
			throw new BSWException("�����û����Զ������ͣ��޷�ɾ����");
		}
		if(mapjifl.containsKey(name)){
//			list�в����������ͣ��Ϳ���ɾ��,�������������ʾ�쳣��
			throw new BSWException("��ǰ�ֲֹ�Ʊ�а����ý��׷��࣬��ʱ�޷�ɾ�����������ù�Ʊ�Ľ��׷�������й�Ʊ����ɾ����");
		}
		
		//������Ҫ���ˣ���ɾ����
		tBO.delete(tDTO);
		
		
	}

}

