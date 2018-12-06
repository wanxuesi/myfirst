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
		//��ȡ�ۼ�ӯ����ֵ��
		Double  LJYK = 0.0;//�������й�Ʊ����ֵ��
		Double  CYSZ = 0.0;//��ǰ��Ʊ������ֵ��
		OrderBO uBO =new OrderBO();
		List listTMP = uBO.sqlQuery("select id,zqdm,zqmc,cysl,cbj  from order where   flag1='"+idStr+"' ");
		
		Iterator itTMP = listTMP.iterator();
		Map _map=null;
		OrderDTO mDTO;
		
		StockUtil sUtil = new StockUtil();
		double dqj = 0;
		double dqsz=0;
		int cysl=0;
		while(itTMP.hasNext()){
			_map=(Map)itTMP.next();
			mDTO=new OrderDTO();
			//mDTO.setId((Integer)_map.get("ID"));
			//mDTO.setZqmc((String)_map.get("ZQMC"));
			String zqdm = (String)_map.get("ZQDM");
			mDTO.setZqdm(zqdm);
			cysl = (Integer)_map.get("CYSL");
			mDTO.setCysl(cysl);
			dqj = sUtil.getDqjByZqdm(zqdm);//��ǰ�ۣ�		
			dqsz = dqj*cysl;//��ǰ��ֻ��Ʊ��ֵ��
			CYSZ=dqsz+CYSZ;

		}
		
		
		
		
		
		
		
		
		//Double  cjjes = 0.0;
		String sql = "select sum(qsje) qsjes from jilu  where  khdm='"+idStr+"' and flag1='�Ѵ���'"; 
		
//		����ҵ���߼���
		JiluBO tBO = new JiluBO();
		//�õ�Map�͵�list
		List list = tBO.sqlQuery(sql);
		
		Iterator it = list.iterator();
		
		
		if(it.hasNext()){
			_map=(Map)it.next();
			LJYK  =(Double)_map.get("QSJES");
			//cjjes = (Double)_map.get("CJJES");
			if(LJYK==null){
				LJYK=0.0;
			}
		}
		LJYK=CYSZ+LJYK;
		
//		��ȡ�ۼƽ��׷ѣ�
		Double LJJYF = 0.0;
		String sql2 = "select sum(qsje) qsjes,mmflag from jilu  where  khdm='"+idStr+"'  and flag1='�Ѵ���' group by mmflag "; 
//		�õ�Map�͵�list2
		List list2 = tBO.sqlQuery(sql2);
		
		Iterator it2 = list2.iterator();
		Map _map2=null;
		double qsjemairu=0.0;
		double qsjemairuAbs=0.0;
		double qsjemaichu=0.0;
		for(int i=0;i<list2.size();i++){
			_map2=(Map)it2.next();
			if(i==0){
				//��һ��Ϊ���룻Ϊ����������ֵΪ��ֵ��
				
				qsjemairu = (Double)_map2.get("QSJES");
				qsjemairuAbs  =Math.abs(qsjemairu);//ȥ����ֵ����ֵ��
				
			}else{
				
//				��һ��Ϊ������������Сֵ
				qsjemaichu= (Double)_map2.get("QSJES");
			}
		}
		
		String sql2_2 = "select sum(cjje) cjjes,mmflag from jilu  where  khdm='"+idStr+"'  and flag1='�Ѵ���' group by mmflag"; 
//		�õ�Map�͵�list2
		List list2_2 = tBO.sqlQuery(sql2_2);
		
		Iterator it2_2 = list2_2.iterator();
		Map _map2_2=null;
		double cjjemairu=0.0;
		double cjjemaichu=0.0;
		for(int i=0;i<list2_2.size();i++){
			_map2_2=(Map)it2_2.next();
			if(i==0){
				//��һ��Ϊ���룻Ϊ����������ֵΪ��ֵ��
				
				cjjemairu = (Double)_map2_2.get("CJJES");
				
			}else{
				
//				��һ��Ϊ������������Сֵ
				cjjemaichu= (Double)_map2_2.get("CJJES");
			}
		}
		
		
		
		LJJYF=qsjemairuAbs-cjjemairu+cjjemaichu-qsjemaichu;
		
	
		//��ȡjilu���е�֤ȯ���ƣ����룻
		Map<String,String> zqmcMap =new HashMap<String,String>();
		
		String sql3 = "select distinct zqdm,zqmc from jilu where  khdm='"+idStr+"'  and flag1='�Ѵ���'"; 
//		�õ�Map�͵�list3
		List list3 = tBO.sqlQuery(sql3);
		
		Iterator it3 = list3.iterator();
		Map _map3=null;
		String _zqmc="";
		String zqmc ="";
		while(it3.hasNext()){
			_map3=(Map)it3.next();
			_zqmc = (String)_map3.get("ZQMC");
			zqmc = _zqmc.replace(" ","&nbsp;");
			zqmc = _zqmc.replace("ST","S&nbsp;T&nbsp;");
			//�������ַ���
	        if(zqmc.length()==3){
			zqmc  =zqmc.substring(0,1)+" "+zqmc.substring(1,2)+" "+zqmc.substring(2,3);
	    
			}
			
			zqmcMap.put((String)_map3.get("ZQDM"),zqmc);		
		}
		
		
//		��ȡ��Ϣ�����ܺͣ�
		Double GXHL = 0.0;
		String sql4 = "select sum(shuju) shuju from data where name='��Ϣ����' and  flag2='"+idStr+"'"; 
//		�õ�Map�͵�list4
		DataBO dBO  =new DataBO();
		List list4 = dBO.sqlQuery(sql4);
		
		Iterator it4 = list4.iterator();
		Map _map4=null;
		
		
		
		
		if(it4.hasNext()){
			_map4=(Map)it4.next();
			GXHL  =(Double)_map4.get("SHUJU");
			if(GXHL==null){
				GXHL=0.0;
			}
		}
		
		
		
		
		request.setAttribute("LJYK",String.format("%.2f", LJYK));
		
		request.setAttribute("GXHL",String.format("%.2f", GXHL));//��Ϣ����
		
		request.setAttribute("ZLJYK",String.format("%.2f", LJYK+GXHL));//���ۼ�ӯ����
		
		request.setAttribute("LJJYF",String.format("%.2f", LJJYF));
		request.setAttribute("ZQMCS",zqmcMap);

		
	}

	

}

