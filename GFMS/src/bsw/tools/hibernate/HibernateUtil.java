package bsw.tools.hibernate;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;

import bsw.base.BaseDTO;
import bsw.fwk.PageRoll;
import bsw.tools.exception.BSWException;
import bsw.tools.io.ResourceBundleUtil;
import bsw.tools.log.LogUtil;
//import bsw.tools.io.ResourceBundleUtil;
//import bsw.tools.log.LogUtil;

//import com.fuguo.dto.UD;


/**
 * @描述: Hibernate工具类，通过该工具类可以方便的把数据持久化到数据库中,该工具类为单例模式

 * 
 */
public class HibernateUtil {

	private static HibernateUtil hibernateUtil = null;

	private HibernateUtil() {

	}

	/**
	 * 获取HibernateUtil工具类的实例
	 * 
	 * @return
	 */
	public static synchronized HibernateUtil getInstance() {
		if (hibernateUtil == null)
			hibernateUtil = new HibernateUtil();
		return hibernateUtil;
	}

	/**
	 * 描述：添加对象
	 * @param baseDTO
	 * @return
	 * @throws BSWException
	 */
	public BaseDTO add(BaseDTO baseDTO) throws BSWException {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateSessionFactory.currentSession();
			//LogUtil.info("bsw",ResourceBundleUtil.getString("INFO1"));
			transaction = session.beginTransaction();			
			Serializable s = session.save(baseDTO);
			//System.out.println(s);//返回的是id
			baseDTO.setId(s);
			transaction.commit();
			//LogUtil.info("bsw",ResourceBundleUtil.getString("ADD_SUCCESS"));
			return baseDTO;
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
			//LogUtil.error("bsw",ResourceBundleUtil.getString("ADD_FAIL"));
			e.printStackTrace();
			throw new BSWException("对象保存失败：" + e.getMessage());
		} finally {
			if (session != null)
				session.close();
		}

	}
	
	
	
	

	
	
	/**
	 * 
	 *描述：修改对象 
	 * @param baseDTO
	 * @throws BSWException
	 */
	public void update(BaseDTO baseDTO)throws BSWException{
		//修改
		Session session = null;
		Transaction transaction = null;
		
		try {
			//获取session对象
			session = HibernateSessionFactory.currentSession();
			//LogUtil.info("bsw",ResourceBundleUtil.getString("INFO1"));
			//开始 一个事务
			transaction = session.beginTransaction();
			
			//保存对象
			session.update(baseDTO);
			session.flush();
			//提交事务
			transaction.commit();
			//LogUtil.info("bsw",ResourceBundleUtil.getString("UPDATE_SUCCESS"));
		} catch (HibernateException e) {
			// TODO 自动生成 catch 块
			transaction.rollback();
			e.printStackTrace();
			//LogUtil.error("bsw",ResourceBundleUtil.getString("UPDATE_FAIL"));
			throw new BSWException("对象修改失败：" + e.getMessage());
		}finally{
			//关闭数据库连接
			if(session!=null)session.close();
			
		}
		
	}
	/**
	 * 
	 *描述： 删除单个对象 
	 * @param baseDTO
	 * @throws BSWException
	 */
	public void delete(BaseDTO baseDTO)throws BSWException{
		//删除
		Session session = null;
		Transaction transaction = null;
		
		try {
			//获取session对象
			session = HibernateSessionFactory.currentSession();
			//LogUtil.info("bsw",ResourceBundleUtil.getString("INFO1"));
			//开始 一个事务
			transaction = session.beginTransaction();
			Serializable id = baseDTO.getId();
			System.out.print(id);
			baseDTO = (BaseDTO)session.get(baseDTO.getClass(),id);
			//保存对象
			session.delete(baseDTO);
			//提交事务
			transaction.commit();
			//LogUtil.info("bsw",ResourceBundleUtil.getString("DELETE_SUCCESS"));
		} catch (HibernateException e) {
			// TODO 自动生成 catch 块
			transaction.rollback();
			//LogUtil.error("bsw",ResourceBundleUtil.getString("DELETE_FAIL"));
			throw new BSWException("对象删除失败：" + e.getMessage());
			
		}finally{
			//关闭数据库连接
			if(session!=null)session.close();
		}
		
	}
	/**
	 * 
	 * 删除多个对象
	 * @param baseDTO
	 * @throws BSWException
	 */
	public void delete(BaseDTO[] baseDTO)throws BSWException{
		for(int i=0;i<baseDTO.length;i++){
			this.delete(baseDTO[i]);
		}
	}
	/**
	 * 
	 *描述：查询单个对象 
	 * @param baseDTO
	 * @return
	 * @throws BSWException
	 */
	public BaseDTO query(BaseDTO baseDTO)throws BSWException{
		//查询单个对象
		Session session = null;
		Transaction transaction = null;
		
		try {
			//获取session对象
			session = HibernateSessionFactory.currentSession();
			//LogUtil.info("bsw",ResourceBundleUtil.getString("INFO1"));
			//开始 一个事务
			transaction = session.beginTransaction();
			//查询单个对象
			//System.out.println(baseDTO.getId());
			baseDTO = (BaseDTO)session.get(baseDTO.getClass(),baseDTO.getId());
			//提交事务
			transaction.commit();
			//LogUtil.info("bsw",ResourceBundleUtil.getString("QUERY_SUCCESS"));
		} catch (HibernateException e) {
			// TODO 自动生成 catch 块
			transaction.rollback();
			//LogUtil.error("bsw",ResourceBundleUtil.getString("QUERY_FAIL"));
			throw new BSWException("单个对象查询失败：" + e.getMessage());
		}finally{
			//关闭数据库连接
			
			if(session!=null)session.close();
			
		}
		return baseDTO;
		
	}
	
	
	public BaseDTO[] search(PageRoll pageRoll) throws HibernateException {

		Session session = null;
		
		session = HibernateSessionFactory.currentSession();
		Transaction t = session.beginTransaction();
		Query query = session.createQuery(pageRoll.getWhereClause());
		
		//如果总记数为-1，说明不是翻页
		int _totalCount = pageRoll.getTotalCount();
		if (_totalCount == -1) {
			pageRoll.setTotalCount(query.list().size());
		}
		
		//设置起始记录
		query.setFirstResult((pageRoll.getCurrentPage()-1)*pageRoll.getPageSize());
		query.setMaxResults(pageRoll.getPageSize());
		
		//返回记录集
		List list = query.list();
		
		//将记录集转换为需要的对象数组
		BaseDTO[] baseDTO = new BaseDTO[list.size()];
		for(int i=0;i<list.size();i++){
			baseDTO[i] = (BaseDTO)list.get(i);
		}
		
		t.commit();
		session.close();
		return baseDTO;
	}
	/**
	 * 
	 *描述：查询多个对象
	 * @param baseDTO
	 * @return
	 * @throws BSWException
	 */
	public BaseDTO[] loadAll(String hql) throws BSWException {
	
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateSessionFactory.currentSession();
			LogUtil.info("bsw",ResourceBundleUtil.getString("INFO1"));
			transaction = session.beginTransaction();
			List list = session.createQuery(hql).list();
			BaseDTO[] baseDTOs = new BaseDTO[list.size()];
			for (int i = 0; i < list.size(); i++) {
				baseDTOs[i] = (BaseDTO) list.get(i);
			}
			transaction.commit();
			LogUtil.info("bsw",ResourceBundleUtil.getString("QUERYALL_SUCCESS"));
			return baseDTOs;
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
			LogUtil.error("bsw",ResourceBundleUtil.getString("QUERYALL_FAIL"));
			throw new BSWException("对象查询失败：" + e.getMessage());
		} finally {
			if (session != null)
				session.close();
		}

	}
	
	/**
	 * 关联对象查询
	 */
	/**
	 * 
	 *描述:对象关联查询（元组查询）
	 * @param hql
	 * @return list 里面存放的为关联的对象数组list；如果关联2个对象，就是一条记录里有2个对象；
	 * 如果是几个字段属性，那么数组list中的某个数组里放着依次的属性值（没有属性名）。
	 * 
	 * @throws BSWException
	 */
	public List tuplesQuery(String hql) throws BSWException {
	
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateSessionFactory.currentSession();
			LogUtil.info("bsw",ResourceBundleUtil.getString("INFO1"));
			transaction = session.beginTransaction();
			List list = session.createQuery(hql).list();
			
			transaction.commit();
			LogUtil.info("bsw",ResourceBundleUtil.getString("QUERYALL_SUCCESS"));
			return list;
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
			LogUtil.error("bsw",ResourceBundleUtil.getString("QUERYALL_FAIL"));
			throw new BSWException("关联对象查询失败：" + e.getMessage());
		} finally {
			if (session != null)
				session.close();
		}

	}
	/**
	 * 
	 * @param sql
	 * @return List 带属性名的map（注意大小写）因为没有做映射配置。
	 * @throws BSWException
	 */
	public List<Map> sqlQuery(String sql) throws BSWException {
		
		//String sql = "select u.*,d.name as dwbm_name from user u,dwbm d where u.dwbm_id=d.id";
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateSessionFactory.currentSession();
			LogUtil.info("bsw",ResourceBundleUtil.getString("INFO1"));
			transaction = session.beginTransaction();
			List<Map> list = session.createSQLQuery(sql).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();//放入到map中

			transaction.commit();
			LogUtil.info("bsw",ResourceBundleUtil.getString("QUERYALL_SUCCESS"));
			return list;
			
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
			LogUtil.error("bsw",ResourceBundleUtil.getString("QUERYALL_FAIL"));
			throw new BSWException("关联对象查询失败：" + e.getMessage());
		} finally {
			if (session != null)
				session.close();
		}

	}
	
	public void sqlUpdateOrDel(String sql) throws BSWException {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateSessionFactory.currentSession();
			//LogUtil.info("bsw",ResourceBundleUtil.getString("INFO1"));
			transaction = session.beginTransaction();
			java.sql.Connection con=session.connection();
//			通过JDBC API执行用于批量更新的SQL语句
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.executeUpdate();
			transaction.commit();
			//LogUtil.info("bsw",ResourceBundleUtil.getString("QUERYALL_SUCCESS"));
			
			
		} catch (SQLException e) {
			transaction.rollback();
			e.printStackTrace();
			//LogUtil.error("bsw",ResourceBundleUtil.getString("QUERYALL_FAIL"));
			throw new BSWException("关联对象更新失败：" + e.getMessage());
		}  finally {
			if (session != null)
				session.close();
		}
		
		
		
		
		



		
	}
	 
	/**
	 * 关联对象查询基于sql
	 */
	/**
	 * 
	 *描述:对象关联查询（基于sql）db2有问题。是不是要注意大小写呢？？？（只有大写，果真是啊。）
	 * @param sql,需要组装的bean。
	 * @return List 里面放的class对象。
	 * 
	 * @throws BSWException
	 */

	public List sqlQuery(String sql,Class classArg) throws BSWException {
	
		//String sql = "select u.*,d.name as dwbm_name from user u,dwbm d where u.dwbm_id=d.id";
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateSessionFactory.currentSession();
			//LogUtil.info("bsw",ResourceBundleUtil.getString("INFO1"));
			transaction = session.beginTransaction();
			//List<Map> list = session.createSQLQuery(sql).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();//放入到map中
			List list = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(classArg)).list();
			
			transaction.commit();
			//LogUtil.info("bsw",ResourceBundleUtil.getString("QUERYALL_SUCCESS"));
			
//			Iterator it = list.iterator();
//			while(it.hasNext()){
//				Map map=(Map)it.next();
//				
//				System.out.println(map.get("dwbm_name"));
//			}
			
			return list;
			
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
			//LogUtil.error("bsw",ResourceBundleUtil.getString("QUERYALL_FAIL"));
			throw new BSWException("关联对象查询失败：" + e.getMessage());
		} finally {
			if (session != null)
				session.close();
		}

	}
	/**
	 *

	 * @param args
	 */
	public static void main(String[] args)throws BSWException{
		
		
		
	
	}

}
