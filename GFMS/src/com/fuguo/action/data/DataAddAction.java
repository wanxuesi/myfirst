//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package com.fuguo.action.data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import bsw.base.BaseAction;

import com.fuguo.bo.DataBO;
import com.fuguo.bo.OrderBO;
import com.fuguo.dto.DataDTO;
import com.fuguo.dto.OrderDTO;
import com.fuguo.form.DataForm;
import com.fuguo.util.StockUtil;

/** 
 * MyEclipse Struts
 * Creation date: 01-10-2008
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class DataAddAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO 自动生成方法存根


		  DataForm m = (DataForm)form;
		 String name =m.getName();
		 double shuju = m.getShuju();
		// double fene = m.getFene();
		 //此处应该根据相关数据，计算份额；
		 SimpleDateFormat   sdfTime   =   new   SimpleDateFormat("yyyy-MM-dd HH:mm");
		 SimpleDateFormat   sdfDate   =   new   SimpleDateFormat("yyyy-MM-dd");
	     
	        String dateStr =m.getDate();
	        Date dateDate = sdfTime.parse(dateStr);
	        
	        String first_lastDay = sdfDate.format(dateDate);
		 String flag1 = m.getFlag1();
		 String flag2 = m.getFlag2();
		//封装成数据传输对象
	      DataDTO tDTO = new DataDTO();
	      
	      tDTO.setName(name);
	      tDTO.setShuju(shuju);
	      tDTO.setDate(dateDate);
	      tDTO.setFlag1(flag1);
	      tDTO.setFlag2(flag2);//用户id
	      
	      DataBO tBO =new DataBO();
	      
	      if(name.equals("资金进出")){
	    	  //计算需要增减的份额；
		      
//	  	    获取当前份额总数；
	    	  String sqlwhereDQFE = "and date(date)<='"+first_lastDay+"' ";
	  			Double DQFE = 0.0;
	  			String sql5 = "select sum(fene) fene from data where flag2='"+flag2+"' and name='资金进出' and (flag1='' or flag1 is Null)"; 
//	  			得到Map型的list5
	  			List list5 = tBO.sqlQuery(sql5,DataDTO.class);
	  			
	  			Iterator it5 = list5.iterator();
	  			DataDTO dataDTO=null;
	  			
	  			
	  			
	  			
	  			if(it5.hasNext()){
	  				dataDTO=(DataDTO)it5.next();
	  				DQFE  =dataDTO.getFene();
	  				if(DQFE==null){
	  					DQFE=0.0;
	  				}
	  			}
	  	      
	  	      
	  	     //获取当前股票市值GPSZ；
//	  			计算总市值
	  			OrderBO uBO =new OrderBO();
	  			List listTMP = uBO.sqlQuery("select id,zqdm,zqmc,cysl,cbj  from order where flag1='"+flag2+"'" ,OrderDTO.class);
	  			
	  			Iterator it = listTMP.iterator();
	  			OrderDTO orderDTO=null;
	  			
	  			StockUtil sUtil = new StockUtil();
	  			double dqj = 0;
	  			double dqsz=0;
	  			int cysl=0;

	  			double GPSZ=0;

	  			while(it.hasNext()){
	  				orderDTO=(OrderDTO)it.next();
	  				String zqdm = orderDTO.getZqdm();
	  				cysl = orderDTO.getCysl();
	  				
	  				dqj = sUtil.getDqjByZqdm(zqdm);//当前价；
	  				dqsz = dqj*cysl;
	  				GPSZ+=dqsz;			
	  			}	
	  			
	  			
	  		//获取可用资金
	  			//获取当前可用资金；
//	  			获取股息红利总和；
	  			Double KYZJ = 0.0;
	  			String sql4 = "select sum(shuju) shuju from data where flag2='"+flag2+"' and (name='资金进出' or name='股息红利')"; 
//	  			得到Map型的list4
	  			DataBO dBO  =new DataBO();
	  			List list4 = dBO.sqlQuery(sql4,DataDTO.class);
	  			
	  			
	  			Iterator it4 = list4.iterator();
	  			DataDTO _dataDTO=null;
	  			
	  			
	  			
	  			
	  			if(it4.hasNext()){
	  				_dataDTO=(DataDTO)it4.next();
	  				KYZJ  =_dataDTO.getShuju();
	  				if(KYZJ==null){
	  					KYZJ=0.0;
	  				}
	  			}	
	  			
	  			double fene = (shuju/(GPSZ+KYZJ))*DQFE;
	  	      
	  	      tDTO.setFene(fene);//份额
	      }
	    
		
		
		tBO.add(tDTO);
		
		
	}

	

}

