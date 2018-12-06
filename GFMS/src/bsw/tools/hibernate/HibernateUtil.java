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
 * @����: Hibernate�����࣬ͨ���ù�������Է���İ����ݳ־û������ݿ���,�ù�����Ϊ����ģʽ

 * 
 */
public class HibernateUtil {

	private static HibernateUtil hibernateUtil = null;

	private HibernateUtil() {

	}

	/**
	 * ��ȡHibernateUtil�������ʵ��
	 * 
	 * @return
	 */
	public static synchronized HibernateUtil getInstance() {
		if (hibernateUtil == null)
			hibernateUtil = new HibernateUtil();
		return hibernateUtil;
	}

	/**
	 * ��������Ӷ���
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
			//System.out.println(s);//���ص���id
			baseDTO.setId(s);
			transaction.commit();
			//LogUtil.info("bsw",ResourceBundleUtil.getString("ADD_SUCCESS"));
			return baseDTO;
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
			//LogUtil.error("bsw",ResourceBundleUtil.getString("ADD_FAIL"));
			e.printStackTrace();
			throw new BSWException("���󱣴�ʧ�ܣ�" + e.getMessage());
		} finally {
			if (session != null)
				session.close();
		}

	}
	
	
	
	

	
	
	/**
	 * 
	 *�������޸Ķ��� 
	 * @param baseDTO
	 * @throws BSWException
	 */
	public void update(BaseDTO baseDTO)throws BSWException{
		//�޸�
		Session session = null;
		Transaction transaction = null;
		
		try {
			//��ȡsession����
			session = HibernateSessionFactory.currentSession();
			//LogUtil.info("bsw",ResourceBundleUtil.getString("INFO1"));
			//��ʼ һ������
			transaction = session.beginTransaction();
			
			//�������
			session.update(baseDTO);
			session.flush();
			//�ύ����
			transaction.commit();
			//LogUtil.info("bsw",ResourceBundleUtil.getString("UPDATE_SUCCESS"));
		} catch (HibernateException e) {
			// TODO �Զ����� catch ��
			transaction.rollback();
			e.printStackTrace();
			//LogUtil.error("bsw",ResourceBundleUtil.getString("UPDATE_FAIL"));
			throw new BSWException("�����޸�ʧ�ܣ�" + e.getMessage());
		}finally{
			//�ر����ݿ�����
			if(session!=null)session.close();
			
		}
		
	}
	/**
	 * 
	 *������ ɾ���������� 
	 * @param baseDTO
	 * @throws BSWException
	 */
	public void delete(BaseDTO baseDTO)throws BSWException{
		//ɾ��
		Session session = null;
		Transaction transaction = null;
		
		try {
			//��ȡsession����
			session = HibernateSessionFactory.currentSession();
			//LogUtil.info("bsw",ResourceBundleUtil.getString("INFO1"));
			//��ʼ һ������
			transaction = session.beginTransaction();
			Serializable id = baseDTO.getId();
			System.out.print(id);
			baseDTO = (BaseDTO)session.get(baseDTO.getClass(),id);
			//�������
			session.delete(baseDTO);
			//�ύ����
			transaction.commit();
			//LogUtil.info("bsw",ResourceBundleUtil.getString("DELETE_SUCCESS"));
		} catch (HibernateException e) {
			// TODO �Զ����� catch ��
			transaction.rollback();
			//LogUtil.error("bsw",ResourceBundleUtil.getString("DELETE_FAIL"));
			throw new BSWException("����ɾ��ʧ�ܣ�" + e.getMessage());
			
		}finally{
			//�ر����ݿ�����
			if(session!=null)session.close();
		}
		
	}
	/**
	 * 
	 * ɾ���������
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
	 *��������ѯ�������� 
	 * @param baseDTO
	 * @return
	 * @throws BSWException
	 */
	public BaseDTO query(BaseDTO baseDTO)throws BSWException{
		//��ѯ��������
		Session session = null;
		Transaction transaction = null;
		
		try {
			//��ȡsession����
			session = HibernateSessionFactory.currentSession();
			//LogUtil.info("bsw",ResourceBundleUtil.getString("INFO1"));
			//��ʼ һ������
			transaction = session.beginTransaction();
			//��ѯ��������
			//System.out.println(baseDTO.getId());
			baseDTO = (BaseDTO)session.get(baseDTO.getClass(),baseDTO.getId());
			//�ύ����
			transaction.commit();
			//LogUtil.info("bsw",ResourceBundleUtil.getString("QUERY_SUCCESS"));
		} catch (HibernateException e) {
			// TODO �Զ����� catch ��
			transaction.rollback();
			//LogUtil.error("bsw",ResourceBundleUtil.getString("QUERY_FAIL"));
			throw new BSWException("���������ѯʧ�ܣ�" + e.getMessage());
		}finally{
			//�ر����ݿ�����
			
			if(session!=null)session.close();
			
		}
		return baseDTO;
		
	}
	
	
	public BaseDTO[] search(PageRoll pageRoll) throws HibernateException {

		Session session = null;
		
		session = HibernateSessionFactory.currentSession();
		Transaction t = session.beginTransaction();
		Query query = session.createQuery(pageRoll.getWhereClause());
		
		//����ܼ���Ϊ-1��˵�����Ƿ�ҳ
		int _totalCount = pageRoll.getTotalCount();
		if (_totalCount == -1) {
			pageRoll.setTotalCount(query.list().size());
		}
		
		//������ʼ��¼
		query.setFirstResult((pageRoll.getCurrentPage()-1)*pageRoll.getPageSize());
		query.setMaxResults(pageRoll.getPageSize());
		
		//���ؼ�¼��
		List list = query.list();
		
		//����¼��ת��Ϊ��Ҫ�Ķ�������
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
	 *��������ѯ�������
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
			throw new BSWException("�����ѯʧ�ܣ�" + e.getMessage());
		} finally {
			if (session != null)
				session.close();
		}

	}
	
	/**
	 * ���������ѯ
	 */
	/**
	 * 
	 *����:���������ѯ��Ԫ���ѯ��
	 * @param hql
	 * @return list �����ŵ�Ϊ�����Ķ�������list���������2�����󣬾���һ����¼����2������
	 * ����Ǽ����ֶ����ԣ���ô����list�е�ĳ��������������ε�����ֵ��û������������
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
			throw new BSWException("���������ѯʧ�ܣ�" + e.getMessage());
		} finally {
			if (session != null)
				session.close();
		}

	}
	/**
	 * 
	 * @param sql
	 * @return List ����������map��ע���Сд����Ϊû����ӳ�����á�
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
			List<Map> list = session.createSQLQuery(sql).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();//���뵽map��

			transaction.commit();
			LogUtil.info("bsw",ResourceBundleUtil.getString("QUERYALL_SUCCESS"));
			return list;
			
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
			LogUtil.error("bsw",ResourceBundleUtil.getString("QUERYALL_FAIL"));
			throw new BSWException("���������ѯʧ�ܣ�" + e.getMessage());
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
//			ͨ��JDBC APIִ�������������µ�SQL���
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.executeUpdate();
			transaction.commit();
			//LogUtil.info("bsw",ResourceBundleUtil.getString("QUERYALL_SUCCESS"));
			
			
		} catch (SQLException e) {
			transaction.rollback();
			e.printStackTrace();
			//LogUtil.error("bsw",ResourceBundleUtil.getString("QUERYALL_FAIL"));
			throw new BSWException("�����������ʧ�ܣ�" + e.getMessage());
		}  finally {
			if (session != null)
				session.close();
		}
		
		
		
		
		



		
	}
	 
	/**
	 * ���������ѯ����sql
	 */
	/**
	 * 
	 *����:���������ѯ������sql��db2�����⡣�ǲ���Ҫע���Сд�أ�������ֻ�д�д�������ǰ�����
	 * @param sql,��Ҫ��װ��bean��
	 * @return List ����ŵ�class����
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
			//List<Map> list = session.createSQLQuery(sql).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();//���뵽map��
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
			throw new BSWException("���������ѯʧ�ܣ�" + e.getMessage());
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
