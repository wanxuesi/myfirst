//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package com.fuguo.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @����:��ӡ��form�ࡣ
 * @��λ:�������
 * @����:wanxuesi@163.com
 * @����:��ѧ˼
 * @���ڣ�2008-6-24
 */
public class PrintForm extends ActionForm {
	private String queryDateSql;//**�ƻ�����
	private int[] queryIds;//���е�id
	public String getQueryDateSql() {
		return queryDateSql;
	}
	public void setQueryDateSql(String queryDateSql) {
		this.queryDateSql = queryDateSql;
	}
	public int[] getQueryIds() {
		return queryIds;
	}
	public void setQueryIds(int[] queryIds) {
		this.queryIds = queryIds;
	}
	
}

