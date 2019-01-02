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
 * 添加对象
 * @param weekPlan0DTO
 * @return
 * @throws BSWException
 */
public OrderDTO add(OrderDTO orderDTO) throws BSWException{
	
	orderPO=new OrderPO();		
	
	return (OrderDTO)orderPO.add(orderDTO);
}

/**
 * 查询某记录的详细信息
 * @param order0DTO
 * @return
 * @throws BSWException
 */
public OrderDTO query(OrderDTO orderDTO) throws BSWException{
	orderPO=new OrderPO();
	return (OrderDTO)orderPO.query(orderDTO);
}


/**
 * 更新对象
 * @param order0DTO
 * @throws BSWException
 */
public void update(OrderDTO orderDTO) throws BSWException{	
	orderPO=new OrderPO();
	orderPO.update(orderDTO);
}


/**
 * 删除一个对象
 * @param orderDTO
 * @throws BSWException
 */
public void delete(OrderDTO orderDTO) throws BSWException{
	orderPO=new OrderPO();
	orderPO.delete(orderDTO);
}


/**
 * 删除多个对象
 * @param orderDTOS
 * @throws BSWException
 */
public void delete(OrderDTO[] orderDTOS) throws BSWException{
	orderPO=new OrderPO();
	orderPO.delete(orderDTOS);
}
/**
 * 查找所有信息
 * @return
 * @throws BSWException
 */
public OrderDTO[] loadAll(String _hql)throws BSWException{	
	orderPO=new OrderPO();	
	BaseDTO[] baseDTOS=(BaseDTO[])orderPO.loadAll(_hql);//为什么不可以直接放入到OrderDTOS中呢？因为不支持数组的多态（没有数组多态这个定义）
	OrderDTO[] orderDTOS=new OrderDTO[baseDTOS.length];
	for(int i=0;i<orderDTOS.length;i++){
		orderDTOS[i]=new OrderDTO();
		orderDTOS[i]=(OrderDTO)baseDTOS[i];
	}
	return orderDTOS;
}
/**
 * 分页显示相关信息
 * 
 * @return
 * @throws BSWException 
 */
public OrderDTO[] search(PageRoll pageRoll) {
//	String hql = "from OrderDTO";
//	pageRoll.setWhereClause(hql);
	//设置每页显示记录条数
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
 * 如果需要联合查询的时候，可以将该方法放入到业务类中；
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
// *描述:对象关联查询（基于sql）
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
	
	//解析list<Map>；
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
	//从baseusercontext中获取什么用户(角色)；

    int idUser  =baseUserContext.getId();
	String idStr  =Integer.toString(idUser);
	String juese = baseUserContext.getJuese();
	
	//录入参数；
	Map<String,String> juese_code_map=new HashMap<String,String>();
	juese_code_map.put("超级管理员","czglymax");
	juese_code_map.put("省级管理员","sjglymax");
	juese_code_map.put("钻石用户","zsyhmax");
	juese_code_map.put("金牌用户","jpyhmax");
	juese_code_map.put("银牌用户","ypyhmax");
	juese_code_map.put("普通用户","ptyhmax");
	
//	通过juese_code_map,角色，获取该角色的最大持仓股票数目；
	String systemconfigcode = juese_code_map.get(juese);
	
	String ccgpCountMaxStr = (String)baseUserContext.getSystemconfig().get(systemconfigcode);
	
	Integer ccgpCountMax = Integer.parseInt(ccgpCountMaxStr); 
	
	
	//获取目前该用户的持仓股票map类型；
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
//	mdto.setTbdw("省检常州工区");
//	mdto.setDiqu("常州");
//	dBO.add(mdto);
	List list = dBO.tuplesQuery("from OrderDTO");
	System.out.println(list.size());
	
	

	
	
}


}
