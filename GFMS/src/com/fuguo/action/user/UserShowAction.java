//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package com.fuguo.action.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import bsw.base.BaseAction;

import com.fuguo.bo.UserBO;
import com.fuguo.dto.DanweiDTO;
import com.fuguo.dto.UserDTO;

public class UserShowAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		 TODO 自动生成方法存根
		UserBO uBO =new UserBO();
		//支持sql么？
		List listTMP = uBO.tuplesQuery("select user.* ,danwei.name from user,danwei where user.dwId=danwei.id order by user.dwId");
		
		Iterator it = listTMP.iterator();
		Map _map=null;
		UserDTO mDTO;
		DanweiDTO dDTO;
		
		Object[] tuple2;
		List list=new ArrayList();
		while(it.hasNext()){
			tuple2=(Object[])it.next();			
			mDTO=(UserDTO)tuple2[0];
			dDTO=(DanweiDTO)tuple2[1];			
			mDTO.setDwname(dDTO.getName());
			list.add(mDTO);
		}
		
		request.setAttribute("USER",list);
		
	}


}

