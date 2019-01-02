//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package com.fuguo.action.ccgp;

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

import com.fuguo.bo.JiluBO;
import com.fuguo.bo.ListBO;
import com.fuguo.bo.OrderBO;
import com.fuguo.dto.JiluDTO;
import com.fuguo.dto.ListDTO;
import com.fuguo.dto.OrderDTO;
import com.fuguo.form.JiluForm;
import com.fuguo.util.StockUtil;

/** 
 * MyEclipse Struts
 * Creation date: 01-10-2008
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class ListShowOneAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JiluForm m = (JiluForm)form;
		BaseUserContext baseUserContext = (BaseUserContext) request.getSession().getAttribute("#BASEUSERCONTEXT#");
		int idUser  =baseUserContext.getId();
		String idStr  =Integer.toString(idUser);
		
		String zqdm = m.getZqdm();
		//通过证券代码获取jifl
		double dqj = 0;
		int cysl=0;
		double dqsz=0;
		ListBO uBO =new ListBO();
//		完全可以用hql
		List listTMP = uBO.sqlQuery("select id,zqdm,zqmc,cysl,jifl from list where flag1='"+idStr+"' and zqdm='"+zqdm+"'");
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
			
			//判断当前持有的股票里是否含有该股票；如果包含，则需要加上该股票的市值；
			if(mapjifl.containsKey(_jifl)){
				qsje = mapjifl.get(_jifl)+qsje;
			}
			mDTO.setQsje(qsje);
			
			listDTOs.add(mDTO);
		}
		
//		暂时没有将list中的持仓股票的市值算进来。
//		完全可以用hql
		String sql2 = "select zqdm,zqmc,cysl,jifl from list where  flag1='"+idStr+"' and zqdm='"+zqdm+"'"; 
		
//		调用业务逻辑层
		//得到Map型的list
		List list2 = tBO.sqlQuery(sql2);
		List listDTO2=new ArrayList();
		Iterator it2 = list2.iterator();
		ListDTO aDTO;
		
		
		
		
		
		
		while(it2.hasNext()){
			_map=(Map)it2.next();
			aDTO=new ListDTO();
			//mDTO.setId((Integer)_map.get("ID"));
			aDTO.setZqdm(zqdm);
			aDTO.setZqmc((String)_map.get("ZQMC"));
			aDTO.setCysl((Integer)_map.get("CYSL"));
			aDTO.setJifl((String)_map.get("JIFL"));

			listDTO2.add(aDTO);
		}
		
		ListDTO lDTO=null;
		String dataStr="[";
		   for(int i=0;i<listDTO2.size();i++){
			   lDTO =  (ListDTO)listDTO2.get(i);
			   if(i==0){
				   
				   dataStr+="['"+lDTO.getJifl()+"',"+lDTO.getCysl()+"]";
			   }else{
				   dataStr+=",['"+lDTO.getJifl()+"',"+lDTO.getCysl()+"]"; 
			   }
		   		
		   
		   }
		   
		   	dataStr+="]";
		   	
		   	//System.out.println(dataStr);
		   	request.setAttribute("dataStr",dataStr);
		
		
		request.setAttribute("JILU",listDTOs);
		
		
		
		
		request.setAttribute("LISTS",listDTO2);
		request.setAttribute("ZQDM",zqdm);
		
		String fullZqdm = sUtil.getFullZqdmByZqdm(zqdm);
		
		request.setAttribute("FULLZQDM",fullZqdm);
		
		
		//直接将历史盈亏等信息直接反映到该页面上；
		cysl=0;//持仓股数；
		
		double qsjes=0;
		
		//得出该股票的清算金额qsjes
		
		sql = "select sum(qsje) qsjes from jilu where  khdm='"+idStr+"' and  zqdm='"+zqdm+"'"; 
		
//		调用业务逻辑层
		
		//得到Map型的list
		List list3 = tBO.sqlQuery(sql);
		Iterator it3 = list3.iterator();
		_map=null;
		if(it3.hasNext()){
			_map=(Map)it3.next();
						
			qsjes = (Double)_map.get("QSJES");
			
		}
		
		//System.out.println("实际清算金额："+qsjes);

		
		//查找该order的一条记录
		
		OrderBO oBO = new OrderBO();
//		完全可以用hql
		List listOrder =oBO.sqlQuery("select id,zqdm,zqmc,cysl,cbj from order where   flag1='"+idStr+"' and zqdm='"+zqdm+"'");
				
		Iterator itOrder = listOrder.iterator();
		
		double cbj=0;
		cysl = 0;
		double xnQsje=0;
		double newCbj=0;
		String zqmc="";
		if(itOrder.hasNext()){
			_map=(Map)itOrder.next();
			cysl = (Integer)_map.get("CYSL");//原来的持有数量
			cbj  =(Double)_map.get("CBJ");//原来的成本
			
			zqmc = (String)_map.get("ZQMC");
		}
				
		xnQsje = cbj*cysl;
		if(xnQsje+qsjes<0){
			newCbj = Math.abs((xnQsje+qsjes)/cysl)+cbj;
		}else{
			newCbj = cbj-(xnQsje+qsjes)/cysl;
		}
			
		
		request.setAttribute("NEWCBJ",newCbj);//混合成本价
		request.setAttribute("ZQMC",zqmc);
	}

	

}

