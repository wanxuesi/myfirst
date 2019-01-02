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
		
		
		int cysl=0;//�ֲֹ�����
	
		double qsjes=0;
		
		//�ó��ù�Ʊ��������qsjes
		
		String sql = "select sum(qsje) qsje from jilu where  khdm='"+idStr+"' and zqdm='"+zqdm+"'"; 
		
//		����ҵ���߼���
		JiluBO tBO = new JiluBO();
		//�õ�Map�͵�list
		List list = tBO.sqlQuery(sql,JiluDTO.class);
		List listDTOs=new ArrayList();
		Iterator it = list.iterator();
		
		JiluDTO mDTO;
		if(it.hasNext()){
			mDTO=(JiluDTO)it.next();
						
			qsjes = mDTO.getQsje();
			
		}
		
		//System.out.println("ʵ�������"+qsjes);

		
		//���Ҹ�order��һ����¼
		
		OrderDTO oDTO = new OrderDTO();
		OrderBO oBO = new OrderBO();
		//��ȫ������hql��
		List listOrder =oBO.sqlQuery("select id,zqdm,zqmc,cysl,cbj from order where   flag1='"+idStr+"' and zqdm='"+zqdm+"'",OrderDTO.class);
		
//		˵���иü�¼��ֱ����orderUpdate;
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
			cysl = orderDTO.getCysl();//ԭ���ĳ�������
			zqmc = orderDTO.getZqmc();
			cbj  =orderDTO.getCbj();//ԭ���ĳɱ���
			//cjjes = (Double)_map.get("CJJES");
		}
		
		
		xnQsje = cbj*cysl;
		//System.out.println("���������"+xnQsje);
		
		//System.out.println("��������"+(xnQsje+qsjes));
		if(xnQsje+qsjes<0){
			newCbj = Math.abs((xnQsje+qsjes)/cysl)+cbj;
		}else{
			newCbj = cbj-(xnQsje+qsjes)/cysl;
		}
		
		//System.out.println("ԭ���ɱ��ۣ�"+cbj);
		//System.out.println("�µĳɱ��ۣ�"+newCbj);
		oDTO.setId(id);
		oDTO.setZqmc(zqmc);
		oDTO.setCysl(cysl);
		oDTO.setZqdm(zqdm);		
		//�����µĳɱ��ۣ�
		oDTO.setCbj(newCbj);
		oDTO.setFlag1(idStr);
		//System.out.println(newCbj);
		oBO.update(oDTO);
	}

	

}

