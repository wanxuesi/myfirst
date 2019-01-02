package com.fuguo.bo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import bsw.base.BaseBO;
import bsw.base.BaseDTO;
import bsw.fwk.PageRoll;
import bsw.tools.exception.BSWException;

import com.fuguo.dto.JueseDTO;
import com.fuguo.po.DanweiPO;
import com.fuguo.po.JuesePO;

public class JueseBO implements BaseBO{
JuesePO juesePO = null;


/**
 * 添加对象
 * @param juese0DTO
 * @return
 * @throws BSWException
 */
public JueseDTO add(JueseDTO jueseDTO) throws BSWException{
	
	juesePO=new JuesePO();		
	
	return (JueseDTO)juesePO.add(jueseDTO);
}

/**
 * 查询某记录的详细信息
 * @param juese0DTO
 * @return
 * @throws BSWException
 */
public JueseDTO query(JueseDTO jueseDTO) throws BSWException{
	juesePO=new JuesePO();
	return (JueseDTO)juesePO.query(jueseDTO);
}


/**
 * 更新对象
 * @param juese0DTO
 * @throws BSWException
 */
public void update(JueseDTO jueseDTO) throws BSWException{	
	juesePO=new JuesePO();
	juesePO.update(jueseDTO);
}


/**
 * 删除一个对象
 * @param jueseDTO
 * @throws BSWException
 */
public void delete(JueseDTO jueseDTO) throws BSWException{
	juesePO=new JuesePO();
	juesePO.delete(jueseDTO);
}


/**
 * 删除多个对象
 * @param jueseDTOS
 * @throws BSWException
 */
public void delete(JueseDTO[] jueseDTOS) throws BSWException{
	juesePO=new JuesePO();
	juesePO.delete(jueseDTOS);
}
/**
 * 查找所有信息
 * @return
 * @throws BSWException
 */
public JueseDTO[] loadAll(String _hql)throws BSWException{	
	juesePO=new JuesePO();	
	BaseDTO[] baseDTOS=(BaseDTO[])juesePO.loadAll(_hql);//为什么不可以直接放入到JueseDTOS中呢？因为不支持数组的多态（没有数组多态这个定义）
	JueseDTO[] jueseDTOS=new JueseDTO[baseDTOS.length];
	for(int i=0;i<jueseDTOS.length;i++){
		jueseDTOS[i]=new JueseDTO();
		jueseDTOS[i]=(JueseDTO)baseDTOS[i];
	}
	return jueseDTOS;
}
/**
 * 分页显示相关信息
 * 
 * @return
 * @throws BSWException 
 */
public JueseDTO[] search(PageRoll pageRoll) {
	String hql = "from JueseDTO";
	pageRoll.setWhereClause(hql);
	//设置每页显示记录条数
	pageRoll.setPageSize(8);
	JuesePO juesePO = new JuesePO();
	BaseDTO[] baseDTO = juesePO.search(pageRoll);
	JueseDTO[] jueseDTO = null;
	jueseDTO = new JueseDTO[baseDTO.length];
	for (int i = 0; i < jueseDTO.length; i++) {
		jueseDTO[i] = (JueseDTO) baseDTO[i];
	}
	return jueseDTO;
}


/**
 * 如果需要联合查询的时候，可以将该方法放入到业务类中；
 * @param hql
 * @return
 * @throws BSWException
 */
public List tuplesQuery(String hql) throws BSWException {
	juesePO=new JuesePO();
	List list = juesePO.tuplesQuery(hql);
	
	
	
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
	juesePO=new JuesePO();
	List list =juesePO.sqlQuery(sql,classArg);
	
	return list;
}




/**
 * dwr 专用
 * @param args
 */

public Map getJueses() throws BSWException{
	Map map=new HashMap();
	juesePO=new JuesePO();
	List list =juesePO.sqlQuery("select name from juese",JueseDTO.class);
	//解析list<Map>；
	Iterator it = list.iterator();
	JueseDTO jueseDTO=null;
	while(it.hasNext()){
		jueseDTO=(JueseDTO)it.next();
		map.put(jueseDTO.getName(),jueseDTO.getName());
	}
	
	
	return map;
}


public static void main(String[] args)throws BSWException{
	JueseBO dBO=new JueseBO();
	
	


	
	

	
	
}


}
