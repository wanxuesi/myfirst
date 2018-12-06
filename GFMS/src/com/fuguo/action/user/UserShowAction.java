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
import com.fuguo.dto.UserDTO;

public class UserShowAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		 TODO 自动生成方法存根
		UserBO uBO =new UserBO();
		List listTMP = uBO.sqlQuery("select user.* ,danwei.name from user,danwei where user.dwId=danwei.id order by user.dwId");
		
		Iterator it = listTMP.iterator();
		Map _map=null;
		UserDTO mDTO;
		
		
		List list=new ArrayList();
		while(it.hasNext()){
			_map=(Map)it.next();
			mDTO=new UserDTO();
			mDTO.setId((Integer)_map.get("ID"));
			mDTO.setXm((String)_map.get("XM"));
			mDTO.setDwname((String)_map.get("NAME"));
			mDTO.setOtherflag((String)_map.get("OTHERFLAG"));
			mDTO.setDatestart((Date)_map.get("DATESTART"));
			mDTO.setDateend((Date)_map.get("DATEEND"));
			
			mDTO.setWx((String)_map.get("WX"));
			list.add(mDTO);
		}
		
		request.setAttribute("USER",list);
		
	}


}

