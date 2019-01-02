//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package com.fuguo.action.jilu;

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

import com.fuguo.bo.DataBO;
import com.fuguo.bo.JiluBO;
import com.fuguo.bo.OrderBO;
import com.fuguo.dto.DataDTO;
import com.fuguo.dto.JiluDTO;
import com.fuguo.dto.OrderDTO;
import com.fuguo.util.StockUtil;

/** 
 * MyEclipse Struts
 * Creation date: 01-10-2008
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class YkfxShowAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		BaseUserContext baseUserContext = (BaseUserContext) request.getSession().getAttribute("#BASEUSERCONTEXT#");
		int idUser  =baseUserContext.getId();
		String idStr  =Integer.toString(idUser);
		//获取累计盈亏数值；
		Double  LJYK = 0.0;//包含持有股票的市值；
		Double  CYSZ = 0.0;//当前股票持有市值；
		OrderBO uBO =new OrderBO();
		List listTMP = uBO.sqlQuery("select id,zqdm,zqmc,cysl,cbj  from order where   flag1='"+idStr+"' ",OrderDTO.class);
		
		Iterator itTMP = listTMP.iterator();
		Map _map=null;
		OrderDTO mDTO;
		
		StockUtil sUtil = new StockUtil();
		double dqj = 0;
		double dqsz=0;
		int cysl=0;
		while(itTMP.hasNext()){
			mDTO=(OrderDTO)itTMP.next();
			
			
			String zqdm = mDTO.getZqdm();
			mDTO.setZqdm(zqdm);
			cysl =mDTO.getCysl();
			mDTO.setCysl(cysl);
			dqj = sUtil.getDqjByZqdm(zqdm);//当前价；		
			dqsz = dqj*cysl;//当前单只股票市值；
			CYSZ=dqsz+CYSZ;

		}
		
		
		
		
		
		
		
		
		//Double  cjjes = 0.0;
		String sql = "select sum(qsje) qsje from jilu  where  khdm='"+idStr+"' and flag1='已处理'"; 
		
//		调用业务逻辑层
		JiluBO tBO = new JiluBO();
		//得到Map型的list
		List list = tBO.sqlQuery(sql,JiluDTO.class);
		
		Iterator it = list.iterator();
		
		
		if(it.hasNext()){
			JiluDTO _jiluDTO=(JiluDTO)it.next();
			LJYK  =_jiluDTO.getQsje();
			//cjjes = (Double)_map.get("CJJES");
			if(LJYK==null){
				LJYK=0.0;
			}
		}
		LJYK=CYSZ+LJYK;
		
//		获取累计交易费；
		Double LJJYF = 0.0;
		String sql2 = "select sum(qsje) qsje,mmflag from jilu  where  khdm='"+idStr+"'  and flag1='已处理' group by mmflag "; 
//		得到Map型的list2
		List list2 = tBO.sqlQuery(sql2,JiluDTO.class);
		
		Iterator it2 = list2.iterator();
		JiluDTO _jiluDTO2=null;
		double qsjemairu=0.0;
		double qsjemairuAbs=0.0;
		double qsjemaichu=0.0;
		for(int i=0;i<list2.size();i++){
			_jiluDTO2=(JiluDTO)it2.next();
			if(i==0){
				//第一个为买入；为负数，绝对值为大值。
				
				qsjemairu = _jiluDTO2.getQsje();
				qsjemairuAbs  =Math.abs(qsjemairu);//去绝对值；大值；
				
			}else{
				
//				第一个为卖出；正数，小值
				qsjemaichu= _jiluDTO2.getQsje();
			}
		}
		
		String sql2_2 = "select sum(cjje) cjje,mmflag from jilu  where  khdm='"+idStr+"'  and flag1='已处理' group by mmflag"; 
//		得到Map型的list2
		List list2_2 = tBO.sqlQuery(sql2_2,JiluDTO.class);
		
		Iterator it2_2 = list2_2.iterator();
		JiluDTO _jiluDTO2_2=null;
		double cjjemairu=0.0;
		double cjjemaichu=0.0;
		for(int i=0;i<list2_2.size();i++){
			_jiluDTO2_2=(JiluDTO)it2_2.next();
			if(i==0){
				//第一个为买入；为负数，绝对值为大值。
				
				cjjemairu =_jiluDTO2_2.getCjje();
				
			}else{
				
//				第一个为卖出；正数，小值
				cjjemaichu= _jiluDTO2_2.getCjje();
			}
		}
		
		
		
		LJJYF=qsjemairuAbs-cjjemairu+cjjemaichu-qsjemaichu;
		
	
		//获取jilu表中的证券名称，代码；
		Map<String,String> zqmcMap =new HashMap<String,String>();
		
		String sql3 = "select distinct zqdm,zqmc from jilu where  khdm='"+idStr+"'  and flag1='已处理'"; 
//		得到Map型的list3
		List list3 = tBO.sqlQuery(sql3,JiluDTO.class);
		
		Iterator it3 = list3.iterator();
		JiluDTO _jiluDTO3=null;
		String _zqmc="";
		String zqmc ="";
		while(it3.hasNext()){
			_jiluDTO3=(JiluDTO)it3.next();
			_zqmc = _jiluDTO3.getZqmc();
			zqmc = _zqmc.replace(" ","&nbsp;");
			zqmc = _zqmc.replace("ST","S&nbsp;T&nbsp;");
			//美化下字符；
	        if(zqmc.length()==3){
			zqmc  =zqmc.substring(0,1)+" "+zqmc.substring(1,2)+" "+zqmc.substring(2,3);
	    
			}
			
			zqmcMap.put(_jiluDTO3.getZqdm(),zqmc);		
		}
		
		
//		获取股息红利总和；
		Double GXHL = 0.0;
		String sql4 = "select sum(shuju) shuju from data where name='股息红利' and  flag2='"+idStr+"'"; 
//		得到Map型的list4
		DataBO dBO  =new DataBO();
		List list4 = dBO.sqlQuery(sql4,DataDTO.class);
		
		Iterator it4 = list4.iterator();
		
		
		
		
		
		if(it4.hasNext()){
			DataDTO _dataDTO=(DataDTO)it4.next();
			GXHL  =_dataDTO.getShuju();
			if(GXHL==null){
				GXHL=0.0;
			}
		}
		
		
		
		
		request.setAttribute("LJYK",String.format("%.2f", LJYK));
		
		request.setAttribute("GXHL",String.format("%.2f", GXHL));//股息红利
		
		request.setAttribute("ZLJYK",String.format("%.2f", LJYK+GXHL));//总累计盈亏；
		
		request.setAttribute("LJJYF",String.format("%.2f", LJJYF));
		request.setAttribute("ZQMCS",zqmcMap);

		
	}

	

}

