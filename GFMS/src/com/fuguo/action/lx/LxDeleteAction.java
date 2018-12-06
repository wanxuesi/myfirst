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
		// TODO 自动生成方法存根
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
		String flag1 = tDTO.getFlag1();//如果是空，或者不是本ID，就不能删除；
	    if(flag1==null){
	    	flag1="";
	    }
		//需要查询list中有无该类型的记录，如果有，则无法删除；
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
			throw new BSWException("系统交易类型，无法删除！");
		}
		if(!flag1.equals(idStr)){
			throw new BSWException("其他用户的自定义类型，无法删除！");
		}
		if(mapjifl.containsKey(name)){
//			list中不包含该类型，就可以删除,如果包含，就提示异常；
			throw new BSWException("当前持仓股票中包含该交易分类，暂时无法删除，请卖出该股票的交易分类的所有股票后再删除！");
		}
		
		//都符合要求了，就删除；
		tBO.delete(tDTO);
		
		
	}

}

