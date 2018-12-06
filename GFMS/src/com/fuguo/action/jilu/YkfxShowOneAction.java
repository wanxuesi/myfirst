//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package com.fuguo.action.jilu;

import java.util.ArrayList;
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

import com.fuguo.bo.GpmcBO;
import com.fuguo.bo.JiluBO;
import com.fuguo.bo.ListBO;
import com.fuguo.dto.GpmcDTO;
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
public class YkfxShowOneAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JiluForm m = (JiluForm)form;
		String zqdm = m.getZqdm();
		GpmcBO dBO=new GpmcBO();
		GpmcDTO gDTO =new GpmcDTO();
		gDTO.setZqdm(zqdm);
		gDTO  =dBO.query(gDTO);
		//String zqmc  =dBO.query(gDTO).getZqmc();
		
		BaseUserContext baseUserContext = (BaseUserContext) request.getSession().getAttribute("#BASEUSERCONTEXT#");
		int idUser  =baseUserContext.getId();
		String idStr  =Integer.toString(idUser);
		double dqj = 0;
		int cysl=0;
		double dqsz=0;
		ListBO uBO =new ListBO();
		List listTMP = uBO.sqlQuery("select id,zqdm,zqmc,cysl,jifl from list where  flag1='"+idStr+"' and zqdm='"+zqdm+"'");
		StockUtil sUtil = new StockUtil();
		Iterator itTMP = listTMP.iterator();
		Map _map=null;
		Map<String,Double> mapjifl=new HashMap<String,Double>();
		while(itTMP.hasNext()){
			_map=(Map)itTMP.next();
			
			String _zqdm = (String)_map.get("ZQDM");
			String jifl = (String)_map.get("JIFL");
			cysl = (Integer)_map.get("CYSL");
			
			dqj = sUtil.getDqjByZqdm(_zqdm);//当前价；
			dqsz = dqj*cysl;
			
			mapjifl.put(jifl,dqsz);
		}
		
		
		
		//通过证券代码获取ji类型
		
		String sql = "select jifl, sum(qsje) qsjes from jilu where  khdm='"+idStr+"' and zqdm='"+zqdm+"' group by jifl"; 
		
//		调用业务逻辑层
		JiluBO tBO = new JiluBO();
		//得到Map型的list
		List list = tBO.sqlQuery(sql);
		List listDTOs=new ArrayList();
		Iterator it = list.iterator();
		Double qsje=0.0;
		//Map _map=null;
		JiluDTO mDTO;
		while(it.hasNext()){
			_map=(Map)it.next();
			mDTO=new JiluDTO();
			//mDTO.setId((Integer)_map.get("ID"));
			mDTO.setZqdm(zqdm);
			//mDTO.setZqmc((String)_map.get("ZQMC"));
			String _jifl = (String)_map.get("JIFL");
			mDTO.setJifl(_jifl);
			
			qsje = (Double)_map.get("QSJES");
			
			//判断当前持有的股票里是否含有该股票；如果包含，则需要加上改股票的市值；
			if(mapjifl.containsKey(_jifl)){
				qsje = mapjifl.get(_jifl)+qsje;
			}
			mDTO.setQsje(qsje);
			
			listDTOs.add(mDTO);
		}
		
		
		request.setAttribute("JILU",listDTOs);
		
		request.setAttribute("GPMCDTO",gDTO);
		
	}

	

}

