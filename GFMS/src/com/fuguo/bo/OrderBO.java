package com.fuguo.bo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import bsw.base.BaseBO;
import bsw.base.BaseDTO;
import bsw.fwk.BaseUserContext;
import bsw.fwk.PageRoll;
import bsw.tools.exception.BSWException;

import com.fuguo.dto.OrderDTO;
import com.fuguo.po.DataPO;
import com.fuguo.po.OrderPO;
import com.fuguo.util.DateUtil;

public class OrderBO implements BaseBO{
OrderPO orderPO = null;


/**
 * ��Ӷ���
 * @param weekPlan0DTO
 * @return
 * @throws BSWException
 */
public OrderDTO add(OrderDTO orderDTO) throws BSWException{
	
	orderPO=new OrderPO();		
	
	return (OrderDTO)orderPO.add(orderDTO);
}

/**
 * ��ѯĳ��¼����ϸ��Ϣ
 * @param order0DTO
 * @return
 * @throws BSWException
 */
public OrderDTO query(OrderDTO orderDTO) throws BSWException{
	orderPO=new OrderPO();
	return (OrderDTO)orderPO.query(orderDTO);
}


/**
 * ���¶���
 * @param order0DTO
 * @throws BSWException
 */
public void update(OrderDTO orderDTO) throws BSWException{	
	orderPO=new OrderPO();
	orderPO.update(orderDTO);
}


/**
 * ɾ��һ������
 * @param orderDTO
 * @throws BSWException
 */
public void delete(OrderDTO orderDTO) throws BSWException{
	orderPO=new OrderPO();
	orderPO.delete(orderDTO);
}


/**
 * ɾ���������
 * @param orderDTOS
 * @throws BSWException
 */
public void delete(OrderDTO[] orderDTOS) throws BSWException{
	orderPO=new OrderPO();
	orderPO.delete(orderDTOS);
}
/**
 * ����������Ϣ
 * @return
 * @throws BSWException
 */
public OrderDTO[] loadAll(String _hql)throws BSWException{	
	orderPO=new OrderPO();	
	BaseDTO[] baseDTOS=(BaseDTO[])orderPO.loadAll(_hql);//Ϊʲô������ֱ�ӷ��뵽OrderDTOS���أ���Ϊ��֧������Ķ�̬��û�������̬������壩
	OrderDTO[] orderDTOS=new OrderDTO[baseDTOS.length];
	for(int i=0;i<orderDTOS.length;i++){
		orderDTOS[i]=new OrderDTO();
		orderDTOS[i]=(OrderDTO)baseDTOS[i];
	}
	return orderDTOS;
}
/**
 * ��ҳ��ʾ�����Ϣ
 * 
 * @return
 * @throws BSWException 
 */
public OrderDTO[] search(PageRoll pageRoll) {
//	String hql = "from OrderDTO";
//	pageRoll.setWhereClause(hql);
	//����ÿҳ��ʾ��¼����
	pageRoll.setPageSize(8);
	OrderPO orderPO = new OrderPO();
	BaseDTO[] baseDTO = orderPO.search(pageRoll);
	OrderDTO[] orderDTO = null;
	SimpleDateFormat   sdf   =   new   SimpleDateFormat("yyyy-MM-dd HH:mm");
	DateUtil dateUtil=new DateUtil();
	Date jhkssj;
	Date jhjssj;
	String jhkssjStr;
	String jhjssjStr;
	String jhkssjWeek;
	String jhjssjWeek;
	String tmpgzcs;
	String gzcs;
	String bdz;
	String tbdw;
	String flag1;
	orderDTO = new OrderDTO[baseDTO.length];
	for (int i = 0; i < orderDTO.length; i++) {
		orderDTO[i] = (OrderDTO) baseDTO[i];
		
		
		

		
		
		
		
	}
	return orderDTO;
}


/**
 * �����Ҫ���ϲ�ѯ��ʱ�򣬿��Խ��÷������뵽ҵ�����У�
 * @param hql
 * @return
 * @throws BSWException
 */
public List tuplesQuery(String hql) throws BSWException {
	orderPO=new OrderPO();
	List list = orderPO.tuplesQuery(hql);
	
	
	
	return list;
}


///**
// * 
// *����:���������ѯ������sql��
// * @param sql
// * @return List<Map>
// * 
// * @throws BSWException
// */
//public List sqlQuery(String sql) throws BSWException {
//	orderPO=new OrderPO();
//	List list =orderPO.sqlQuery(sql);
//	
//	return list;
//}


public List sqlQuery(String sql,Class classArg) throws BSWException {
	orderPO=new OrderPO();
	List list =orderPO.sqlQuery(sql,classArg);
	
	return list;
}
public void sqlUpdateOrDel(String sql) throws BSWException{
	orderPO=new OrderPO();
	orderPO.sqlUpdateOrDel(sql);
}

public Map getOrders(String userID) throws BSWException{
	Map<String,String> map=new HashMap<String,String>();
	orderPO=new OrderPO();
	List list =orderPO.sqlQuery("select id,zqdm,zqmc,cysl,cbj  from order where flag1='"+userID+"'", OrderDTO.class);
	
	//����list<Map>��
	Iterator it = list.iterator();
	OrderDTO orderDTO=null;
	while(it.hasNext()){
		orderDTO=(OrderDTO)it.next();
		map.put(orderDTO.getZqdm(),orderDTO.getZqdm());
	}
	
	
	return map;
}


public boolean isCcgpOK(String zqdm,BaseUserContext baseUserContext) throws BSWException{
	boolean result=false;
	//��baseusercontext�л�ȡʲô�û�(��ɫ)��

    int idUser  =baseUserContext.getId();
	String idStr  =Integer.toString(idUser);
	String juese = baseUserContext.getJuese();
	
	//¼�������
	Map<String,String> juese_code_map=new HashMap<String,String>();
	juese_code_map.put("��������Ա","czglymax");
	juese_code_map.put("ʡ������Ա","sjglymax");
	juese_code_map.put("��ʯ�û�","zsyhmax");
	juese_code_map.put("�����û�","jpyhmax");
	juese_code_map.put("�����û�","ypyhmax");
	juese_code_map.put("��ͨ�û�","ptyhmax");
	
//	ͨ��juese_code_map,��ɫ����ȡ�ý�ɫ�����ֲֹ�Ʊ��Ŀ��
	String systemconfigcode = juese_code_map.get(juese);
	
	String ccgpCountMaxStr = (String)baseUserContext.getSystemconfig().get(systemconfigcode);
	
	Integer ccgpCountMax = Integer.parseInt(ccgpCountMaxStr); 
	
	
	//��ȡĿǰ���û��ĳֲֹ�Ʊmap���ͣ�
	Map<String,String> map = getOrders(idStr);

	if(map.containsKey(zqdm)){
		result = true;
	}else if(map.size()<ccgpCountMax){
		result = true;
	}else{};
	
	return result;
}


public static void main(String[] args)throws BSWException{
	OrderBO dBO=new OrderBO();
//	OrderDTO mdto = new OrderDTO();
//	mdto.setTbdw("ʡ�쳣�ݹ���");
//	mdto.setDiqu("����");
//	dBO.add(mdto);
	List list = dBO.tuplesQuery("from OrderDTO");
	System.out.println(list.size());
	
	

	
	
}


}
