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
public class HbAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JiluForm m = (JiluForm)form;
		
		BaseUserContext baseUserContext = (BaseUserContext) request.getSession().getAttribute("#BASEUSERCONTEXT#");
		int idUser  =baseUserContext.getId();
		String idStr  =Integer.toString(idUser);
		
		String zqdm = m.getZqdm();
		
		
		int cysl=0;//持仓股数；
	
		double qsjes=0;
		
		//得出该股票的清算金额qsjes
		
		String sql = "select sum(qsje) qsje from jilu where  khdm='"+idStr+"' and zqdm='"+zqdm+"'"; 
		
//		调用业务逻辑层
		JiluBO tBO = new JiluBO();
		//得到Map型的list
		List list = tBO.sqlQuery(sql,JiluDTO.class);
		List listDTOs=new ArrayList();
		Iterator it = list.iterator();
		
		JiluDTO mDTO;
		if(it.hasNext()){
			mDTO=(JiluDTO)it.next();
						
			qsjes = mDTO.getQsje();
			
		}
		
		//System.out.println("实际清算金额："+qsjes);

		
		//查找该order的一条记录
		
		OrderDTO oDTO = new OrderDTO();
		OrderBO oBO = new OrderBO();
		//完全可以用hql。
		List listOrder =oBO.sqlQuery("select id,zqdm,zqmc,cysl,cbj from order where   flag1='"+idStr+"' and zqdm='"+zqdm+"'",OrderDTO.class);
		
//		说明有该记录，直接在orderUpdate;
		//oDTO.setZqdm(zqdm);
		
		
		Iterator itOrder = listOrder.iterator();
		
		
		String zqmc="";
		double cbj=0;
		Integer id=0;
		cysl = 0;
		double xnQsje=0;
		double newCbj=0;
		if(itOrder.hasNext()){
			OrderDTO orderDTO=(OrderDTO)itOrder.next();
			id = orderDTO.getId();
			cysl = orderDTO.getCysl();//原来的持有数量
			zqmc = orderDTO.getZqmc();
			cbj  =orderDTO.getCbj();//原来的成本价
			//cjjes = (Double)_map.get("CJJES");
		}
		
		
		xnQsje = cbj*cysl;
		//System.out.println("虚拟清算金额："+xnQsje);
		
		//System.out.println("混合清算金额："+(xnQsje+qsjes));
		if(xnQsje+qsjes<0){
			newCbj = Math.abs((xnQsje+qsjes)/cysl)+cbj;
		}else{
			newCbj = cbj-(xnQsje+qsjes)/cysl;
		}
		
		//System.out.println("原来成本价："+cbj);
		//System.out.println("新的成本价："+newCbj);
		oDTO.setId(id);
		oDTO.setZqmc(zqmc);
		oDTO.setCysl(cysl);
		oDTO.setZqdm(zqdm);		
		//更新新的成本价；
		oDTO.setCbj(newCbj);
		oDTO.setFlag1(idStr);
		//System.out.println(newCbj);
		oBO.update(oDTO);
	}

	

}

