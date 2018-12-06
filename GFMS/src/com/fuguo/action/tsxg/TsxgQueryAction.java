//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package com.fuguo.action.tsxg;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import bsw.base.BaseAction;

import com.fuguo.bo.GpmcBO;
import com.fuguo.form.Query_Of_AllForm;

/** 
 * MyEclipse Struts
 * Creation date: 03-20-2008
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class TsxgQueryAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		��ȡ���ݲ���װ
		Query_Of_AllForm qForm = (Query_Of_AllForm)form;
		String xxjh="��ɫѡ��ִ�н��";
		String queryDateSql=xxjh;
		
		
		Date   queryDateDate=null;
		
		
		//�����չ����������
		String _queryDate =qForm.getQueryDate().trim();
		queryDateSql=queryDateSql+"("+_queryDate+")";
		
//		������ת��Ϊutil.Date����
		SimpleDateFormat   sdfymd   =   new   SimpleDateFormat("yyyy-MM-dd"); 
		queryDateDate   =   sdfymd.parse(_queryDate);
			
		
		
		int zsz = qForm.getZsz();//����ֵ ��
		
		long zszLong = (long)zsz*(long)100000000;//Ԫ
		
		int ltsz = qForm.getLtsz();//��ͨ��ֵ  ��
		
		long ltszLong =(long)ltsz*(long)100000000;//Ԫ
		
		
		//���ò��Һ���List list gpmcBO.tsxgQuery(zszLong,ltszLong,queryDateDate)������List<GpmcDTO>;
		GpmcBO  gpmcBO  =new GpmcBO();
		List gpmcList  = gpmcBO.tsxgQuery(zszLong,ltszLong,queryDateDate);
		
		//����
		Collections.sort(gpmcList);
		
		request.setAttribute("LIST",gpmcList);
		request.getSession().setAttribute("queryDate",queryDateSql);
		
	}

	

}

