package com.fuguo.bo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import bsw.base.BaseBO;
import bsw.base.BaseDTO;
import bsw.fwk.PageRoll;
import bsw.tools.exception.BSWException;

import com.fuguo.dto.JuesefunctionDTO;
import com.fuguo.po.JuesefunctionPO;

public class JuesefunctionBO implements BaseBO{
JuesefunctionPO juesefunctionPO = null;


/**
 * 添加对象
 * @param juesefunction0DTO
 * @return
 * @throws BSWException
 */
public JuesefunctionDTO add(JuesefunctionDTO juesefunctionDTO) throws BSWException{
	
	juesefunctionPO=new JuesefunctionPO();		
	
	return (JuesefunctionDTO)juesefunctionPO.add(juesefunctionDTO);
}

/**
 * 查询某记录的详细信息
 * @param juesefunction0DTO
 * @return
 * @throws BSWException
 */
public JuesefunctionDTO query(JuesefunctionDTO juesefunctionDTO) throws BSWException{
	juesefunctionPO=new JuesefunctionPO();
	return (JuesefunctionDTO)juesefunctionPO.query(juesefunctionDTO);
}


/**
 * 更新对象
 * @param juesefunction0DTO
 * @throws BSWException
 */
public void update(JuesefunctionDTO juesefunctionDTO) throws BSWException{	
	juesefunctionPO=new JuesefunctionPO();
	juesefunctionPO.update(juesefunctionDTO);
}


/**
 * 删除一个对象
 * @param juesefunctionDTO
 * @throws BSWException
 */
public void delete(JuesefunctionDTO juesefunctionDTO) throws BSWException{
	juesefunctionPO=new JuesefunctionPO();
	juesefunctionPO.delete(juesefunctionDTO);
}


/**
 * 删除多个对象
 * @param juesefunctionDTOS
 * @throws BSWException
 */
public void delete(JuesefunctionDTO[] juesefunctionDTOS) throws BSWException{
	juesefunctionPO=new JuesefunctionPO();
	juesefunctionPO.delete(juesefunctionDTOS);
}
/**
 * 查找所有信息
 * @return
 * @throws BSWException
 */
public JuesefunctionDTO[] loadAll(String _hql)throws BSWException{	
	juesefunctionPO=new JuesefunctionPO();	
	BaseDTO[] baseDTOS=(BaseDTO[])juesefunctionPO.loadAll(_hql);//为什么不可以直接放入到JuesefunctionDTOS中呢？因为不支持数组的多态（没有数组多态这个定义）
	JuesefunctionDTO[] juesefunctionDTOS=new JuesefunctionDTO[baseDTOS.length];
	for(int i=0;i<juesefunctionDTOS.length;i++){
		juesefunctionDTOS[i]=new JuesefunctionDTO();
		juesefunctionDTOS[i]=(JuesefunctionDTO)baseDTOS[i];
	}
	return juesefunctionDTOS;
}
/**
 * 分页显示相关信息
 * 
 * @return
 * @throws BSWException 
 */
public JuesefunctionDTO[] search(PageRoll pageRoll) {
	String hql = "from JuesefunctionDTO";
	pageRoll.setWhereClause(hql);
	//设置每页显示记录条数
	pageRoll.setPageSize(8);
	JuesefunctionPO juesefunctionPO = new JuesefunctionPO();
	BaseDTO[] baseDTO = juesefunctionPO.search(pageRoll);
	JuesefunctionDTO[] juesefunctionDTO = null;
	juesefunctionDTO = new JuesefunctionDTO[baseDTO.length];
	for (int i = 0; i < juesefunctionDTO.length; i++) {
		juesefunctionDTO[i] = (JuesefunctionDTO) baseDTO[i];
	}
	return juesefunctionDTO;
}


/**
 * 如果需要联合查询的时候，可以将该方法放入到业务类中；
 * @param hql
 * @return
 * @throws BSWException
 */
public List tuplesQuery(String hql) throws BSWException {
	juesefunctionPO=new JuesefunctionPO();
	List list = juesefunctionPO.tuplesQuery(hql);
	
	
	
	return list;
}
/**
 * 
 *描述:对象关联查询（基于sql）
 * @param sql,需要组装的bean。
 * @return List
 * 
 * @throws BSWException
 */
public List sqlQuery(String sql,Class classArg) throws BSWException {
	juesefunctionPO=new JuesefunctionPO();
	List list =juesefunctionPO.sqlQuery(sql,classArg);
	
	return list;
}

public List sqlQuery(String sql) throws BSWException {
	juesefunctionPO=new JuesefunctionPO();
	List list =juesefunctionPO.sqlQuery(sql);
	
	return list;
}


/**
 * dwr 专用
 * @param args
 */

public Map getJuesefunctions() throws BSWException{
	Map map=new HashMap();
	juesefunctionPO=new JuesefunctionPO();
	List list =juesefunctionPO.sqlQuery("select name from juesefunction");
	//解析list<Map>；
	Iterator it = list.iterator();
	Map _map=null;
	while(it.hasNext()){
		_map=(Map)it.next();
		map.put(_map.get("NAME"),_map.get("NAME"));
	}
	
	
	return map;
}


public static void main(String[] args)throws BSWException{
	JuesefunctionBO dBO=new JuesefunctionBO();
	
	


	
	

	
	
}


}
