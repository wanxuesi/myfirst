package com.fuguo.action.data;

//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl


import java.util.ArrayList;
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
public class DataShowAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO �Զ����ɷ������

		DataForm m = (DataForm)form;
		BaseUserContext baseUserContext = (BaseUserContext) request.getSession().getAttribute("#BASEUSERCONTEXT#");
		int idUser  =baseUserContext.getId();
		String idStr  =Integer.toString(idUser);
		
		DataBO mBO = new DataBO();
		DataDTO[] dataDTO=mBO.loadAll("from DataDTO dataDTO where flag2='"+idStr+"' and dataDTO.date is not Null order by dataDTO.date desc");
		
		
		
		//��ȡ��ǰ�����ʽ�
//		��ȡ��Ϣ�����ܺͣ�
		Double KYZJ = 0.0;
		String sql4 = "select sum(shuju) shuju from data where flag2='"+idStr+"' and (name='�ʽ����' or name='��Ϣ����')"; 
//		�õ�Map�͵�list4
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
		
		
		
		
		//��ȡ��ǰ�ݶ�����
		
		Double DQFE = 0.0;
		String sql5 = "select sum(fene) fene from data where flag2='"+idStr+"' and name='�ʽ����' and (flag1='' or flag1 is Null)"; 
//		�õ�Map�͵�list5
		List list5 = dBO.sqlQuery(sql5,DataDTO.class);
		
		Iterator it5 = list5.iterator();
		DataDTO _dataDTO5=null;
		
		
		
		
		if(it5.hasNext()){
			_dataDTO5=(DataDTO)it5.next();
			DQFE  =_dataDTO5.getFene();
			if(DQFE==null){
				DQFE=0.0;
			}
		}
		
		
//		��������ֵ
		OrderBO uBO =new OrderBO();
//		��ȫ������hql
		List listTMP = uBO.sqlQuery("select id,zqdm,zqmc,cysl,cbj  from order where flag1='"+idStr+"'",OrderDTO.class);
		
		Iterator it = listTMP.iterator();
		OrderDTO _orderDTO=null;
		
		StockUtil sUtil = new StockUtil();
		double dqj = 0;
		double dqsz=0;
		int cysl=0;

		double GPSZ=0;

		while(it.hasNext()){
			_orderDTO=(OrderDTO)it.next();
			String zqdm = _orderDTO.getZqdm();
			cysl = _orderDTO.getCysl();
			
			dqj = sUtil.getDqjByZqdm(zqdm);//��ǰ�ۣ�
			dqsz = dqj*cysl;
			GPSZ+=dqsz;			
		}

		request.setAttribute("ZZC",String.format("%.2f", (KYZJ+GPSZ)));//���ʲ�
		
		request.setAttribute("KYZJ",String.format("%.2f", KYZJ));//�����ʽ�
		request.setAttribute("DQFE",String.format("%.2f", DQFE));//��ǰ�ݶ���
		
		
		request.setAttribute("DATA",dataDTO);
		
		Double DQJZ=(KYZJ+GPSZ)/DQFE;
		request.setAttribute("DQJZ",String.format("%.4f", DQJZ));//��ǰ��ֵ
		
	}

	
}

