//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package com.fuguo.action.gpyl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import bsw.base.BaseAction;

import com.fuguo.bo.GpylPrintNowBO;
import com.fuguo.form.PrintForm;


public class GpylPrintAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		PrintForm printForm = (PrintForm)form;
		int queryIds[] = printForm.getQueryIds();
		String queryDateSql = "投资语录汇总";//queryAction 里面存放。
		
		response.setContentType("application/vnd.ms-excel");
		//response.setHeader ( "Content-Disposition" ,"attachment;filename="+new String("导出Excel.xls".getBytes(),"iso-8859-1")) ;
		response.setHeader ( "Content-Disposition" ,"filename="+new String(queryDateSql.getBytes(),"iso-8859-1")) ;
		
		String aa=request.getRealPath("/")+"jsp\\";
		String trace=aa+"语录导出模板.xls";
		GpylPrintNowBO mpBO = new GpylPrintNowBO();
		
		mpBO.printToFile(queryIds,trace, response.getOutputStream());		
		
		response.getOutputStream().flush();
		response.getOutputStream().close();
		
	}
}