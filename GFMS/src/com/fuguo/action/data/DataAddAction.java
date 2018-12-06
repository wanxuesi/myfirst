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
import com.fuguo.form.DataForm;
import com.fuguo.util.DateUtil;
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
		// TODO �Զ����ɷ������


		  DataForm m = (DataForm)form;
		 String name =m.getName();
		 double shuju = m.getShuju();
		// double fene = m.getFene();
		 //�˴�Ӧ�ø���������ݣ�����ݶ
		 SimpleDateFormat   sdfTime   =   new   SimpleDateFormat("yyyy-MM-dd HH:mm");
		 SimpleDateFormat   sdfDate   =   new   SimpleDateFormat("yyyy-MM-dd");
	     
	        String dateStr =m.getDate();
	        Date dateDate = sdfTime.parse(dateStr);
	        
	        String first_lastDay = sdfDate.format(dateDate);
		 String flag1 = m.getFlag1();
		 String flag2 = m.getFlag2();
		//��װ�����ݴ������
	      DataDTO tDTO = new DataDTO();
	      
	      tDTO.setName(name);
	      tDTO.setShuju(shuju);
	      tDTO.setDate(dateDate);
	      tDTO.setFlag1(flag1);
	      tDTO.setFlag2(flag2);//�û�id
	      
	      DataBO tBO =new DataBO();
	      
	      if(name.equals("�ʽ����")){
	    	  //������Ҫ�����ķݶ
		      
//	  	    ��ȡ��ǰ�ݶ�������
	    	  String sqlwhereDQFE = "and date(date)<='"+first_lastDay+"' ";
	  			Double DQFE = 0.0;
	  			String sql5 = "select sum(fene) fene from data where flag2='"+flag2+"' and name='�ʽ����' and (flag1='' or flag1 is Null)"; 
//	  			�õ�Map�͵�list5
	  			List list5 = tBO.sqlQuery(sql5);
	  			
	  			Iterator it5 = list5.iterator();
	  			Map _map5=null;
	  			
	  			
	  			
	  			
	  			if(it5.hasNext()){
	  				_map5=(Map)it5.next();
	  				DQFE  =(Double)_map5.get("FENE");
	  				if(DQFE==null){
	  					DQFE=0.0;
	  				}
	  			}
	  	      
	  	      
	  	     //��ȡ��ǰ��Ʊ��ֵGPSZ��
//	  			��������ֵ
	  			OrderBO uBO =new OrderBO();
	  			List listTMP = uBO.sqlQuery("select id,zqdm,zqmc,cysl,cbj  from order where flag1='"+flag2+"'");
	  			
	  			Iterator it = listTMP.iterator();
	  			Map _map=null;
	  			
	  			StockUtil sUtil = new StockUtil();
	  			double dqj = 0;
	  			double dqsz=0;
	  			int cysl=0;

	  			double GPSZ=0;

	  			while(it.hasNext()){
	  				_map=(Map)it.next();
	  				String zqdm = (String)_map.get("ZQDM");
	  				cysl = (Integer)_map.get("CYSL");
	  				
	  				dqj = sUtil.getDqjByZqdm(zqdm);//��ǰ�ۣ�
	  				dqsz = dqj*cysl;
	  				GPSZ+=dqsz;			
	  			}	
	  			
	  			
	  		//��ȡ�����ʽ�
	  			//��ȡ��ǰ�����ʽ�
//	  			��ȡ��Ϣ�����ܺͣ�
	  			Double KYZJ = 0.0;
	  			String sql4 = "select sum(shuju) shuju from data where flag2='"+flag2+"' and (name='�ʽ����' or name='��Ϣ����')"; 
//	  			�õ�Map�͵�list4
	  			DataBO dBO  =new DataBO();
	  			List list4 = dBO.sqlQuery(sql4);
	  			
	  			Iterator it4 = list4.iterator();
	  			Map _map4=null;
	  			
	  			
	  			
	  			
	  			if(it4.hasNext()){
	  				_map4=(Map)it4.next();
	  				KYZJ  =(Double)_map4.get("SHUJU");
	  				if(KYZJ==null){
	  					KYZJ=0.0;
	  				}
	  			}	
	  			
	  			double fene = (shuju/(GPSZ+KYZJ))*DQFE;
	  	      
	  	      tDTO.setFene(fene);//�ݶ�
	      }
	    
		
		
		tBO.add(tDTO);
		
		
	}

	

}

