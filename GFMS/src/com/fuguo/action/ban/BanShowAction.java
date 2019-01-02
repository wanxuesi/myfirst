//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package com.fuguo.action.ban;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import bsw.base.BaseAction;

import com.fuguo.bo.DanweiBO;
import com.fuguo.dto.DanweiDTO;
import com.fuguo.dto.DwrDTO;

/** 
 * MyEclipse Struts
 * Creation date: 12-21-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class BanShowAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		DanweiBO d=new DanweiBO();
		String sql="select d1.*,d2.name as parentName from danwei d1,danwei d2 where d1.parent=d2.id and d1.parent>0 order by parentName";
		List<DanweiDTO> list = d.sqlQuery(sql,DanweiDTO.class);
		
		
		
		
		request.setAttribute("BANLIST",list);
	}

	
}

