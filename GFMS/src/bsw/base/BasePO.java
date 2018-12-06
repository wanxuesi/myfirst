package bsw.base;


import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;

import bsw.fwk.PageRoll;
import bsw.tools.exception.BSWException;
import bsw.tools.hibernate.HibernateUtil;

/**
 * @描述：所有PO的基类
 
 */

public abstract class BasePO {

	/**
	 * 获取HibernateUtil工具类的实例
	 * 
	 * @return
	 */
	protected HibernateUtil hibernateUtil = HibernateUtil.getInstance();

	/**
	 * 描述：添加对象
	 * @param baseDTO
	 * @return
	 * @throws BSWException
	 */
	public BaseDTO add(BaseDTO baseDTO)throws BSWException{			
			baseDTO = (BaseDTO)hibernateUtil.add(baseDTO);		
		return baseDTO;
	}

	/**
	 * 描述：添加对象
	 * @param baseDTO
	 * @return
	 * @throws BSWException
	 */
	public void update(BaseDTO baseDTO)throws BSWException{			
			hibernateUtil.update(baseDTO);		
	}

	/**
	 * 描述：删除单个对象
	 * @param baseDTO
	 * @return
	 * @throws BSWException
	 */
	public void delete(BaseDTO baseDTO)throws BSWException{			
			hibernateUtil.delete(baseDTO);		
	}
	/**
	 * 描述：删除多个对象
	 * @param baseDTO
	 * @return
	 * @throws BSWException
	 */
	public void delete(BaseDTO[] baseDTO)throws BSWException{			
		hibernateUtil.delete(baseDTO);		
	}
	/**
	 * 描述：查询单个对象
	 * @param baseDTO
	 * @return
	 * @throws BSWException
	 */
	public BaseDTO query(BaseDTO baseDTO)throws BSWException{		
			baseDTO = (BaseDTO)hibernateUtil.query(baseDTO);
		return baseDTO;
	}

	/**
	 * 描述：查询多个对象
	 * @param baseDTO
	 * @return
	 * @throws BSWException
	 */
	public BaseDTO[] loadAll(String hql)throws BSWException{	
		BaseDTO[] baseDTOs = (BaseDTO[])hibernateUtil.loadAll(hql);
		return baseDTOs;
	}
	/**
	 * 分页查找方法
	 * @param pageRoll
	 * @return
	 * @throws HibernateException
	 */
	public BaseDTO[] search(PageRoll pageRoll) throws HibernateException {	
		BaseDTO[] baseDTOs = (BaseDTO[])hibernateUtil.search(pageRoll);
		return baseDTOs;
	}
	
	/**
	 * 
	 *描述:对象关联查询（元组查询）
	 * @param hql
	 * @return list 里面存放的为关联的对象数组list；如果关联2个对象，就是一条记录里有2个对象；
	 * 
	 * @throws BSWException
	 */
	
	public List tuplesQuery(String hql) throws BSWException {
		List list = hibernateUtil.tuplesQuery(hql);
		return list;
	}
	
	
	
	/**
	 * 
	 *描述:对象关联查询（基于sql）
	 * @param sql
	 * @return  List 带属性名的map（注意大小写）因为没有做映射配置。
	 * 
	 * @throws BSWException
	 */
	public List sqlQuery(String sql) throws BSWException {
		List list =hibernateUtil.sqlQuery(sql);
		return list;
	}
	/**
	 * 
	 *描述:对象关联查询（基于sql）db2有问题；
	 * @param sql,需要组装的bean。
	 * @return BaseDTO[]
	 * 
	 * @throws BSWException
	 */
	public List sqlQuery(String sql,Class classArg) throws BSWException {
		List list = hibernateUtil.sqlQuery(sql,classArg);
		return list;
	}
	
	public void sqlUpdateOrDel(String sql) throws BSWException {
		hibernateUtil.sqlUpdateOrDel(sql);
		
	}
}
