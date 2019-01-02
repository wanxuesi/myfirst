package bsw.tools.hibernate;

import java.lang.reflect.Field;
import java.util.List;
 
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.property.ChainedPropertyAccessor;
import org.hibernate.property.PropertyAccessor;
import org.hibernate.property.PropertyAccessorFactory;
import org.hibernate.property.Setter;
import org.hibernate.transform.ResultTransformer;
 
/**
 * 
 * @author luxiaoshuai
 *
 */
public class SQLColumnToBean implements ResultTransformer {
	private static final Log logger = LogFactory.getLog(SQLColumnToBean.class);
	private static final long serialVersionUID = 1L;
	private final Class<?> resultClass;
	private Setter[] setters;
	private PropertyAccessor propertyAccessor;
 
	public SQLColumnToBean(Class<?> resultClass) {
		if (resultClass == null)
			throw new IllegalArgumentException("resultClass cannot be null");
		this.resultClass = resultClass;
		propertyAccessor = new ChainedPropertyAccessor(new PropertyAccessor[] {
				PropertyAccessorFactory.getPropertyAccessor(resultClass, null),
				PropertyAccessorFactory.getPropertyAccessor("field") });
	}
 
	// ���ת��ʱ��HIBERNATE���ô˷���
	public Object transformTuple(Object[] tuple, String[] aliases) {
		Object result = null;
 
		try {
			if (setters == null) {// ���ȳ�ʼ����ȡ��Ŀ��POJO�������SETTER����
				setters = new Setter[aliases.length];
				// ע�⣬����ڴ���SQL��ʱ������query.addScalar(columnAlias)
				// �Ļ���������������addScalarָ�����С�
				for (int i = 0; i < aliases.length; i++) {
					String alias = aliases[i];
					if (alias != null && !"ROWNUM_".equals(alias)) {
						// �ҵ��߼���Ҫ����getSetterByColumnName�������棬��������HIBERNATE����һ������COPY��
						// �����������Ҫ��SETTER����
						setters[i] = getSetterByColumnName(alias);
					}
				}
			}
			result = resultClass.newInstance();
 
			// ����ʹ��SETTER�������POJO����
			for (int i = 0; i < aliases.length; i++) {
				if (setters[i] != null) {
					setters[i].set(result, tuple[i], null);
				}
			}
		} catch (InstantiationException e) {
			if(logger.isInfoEnabled()){
				logger.error("Could not instantiate resultclass: "+ resultClass.getName());
				logger.error(e.getMessage());
			}
			throw new HibernateException("Could not instantiate resultclass: "
					+ resultClass.getName());
		} catch (IllegalAccessException e) {
			if(logger.isInfoEnabled()){
				logger.error("Could not instantiate resultclass: "+ resultClass.getName());
				logger.error(e.getMessage());
			}
			throw new HibernateException("Could not instantiate resultclass: "
					+ resultClass.getName());
		} catch (HibernateException e) {
			if(logger.isInfoEnabled()){
				logger.error(e.getMessage());
			}
			e.printStackTrace();
		}
 
		return result;
	}
 
	// �������ݿ��ֶ�����POJO����JAVA�������������������ݿ��ֶ������磺USER_ID
	private Setter getSetterByColumnName(String alias) {
		// ȡ��POJO����������
		Field[] fields = resultClass.getDeclaredFields();
		if (fields == null || fields.length == 0) {
			throw new RuntimeException("ʵ��" + resultClass.getName() + "�����κ�����");
		}
		// ���ֶ��������е��¸�ȥ��
		String proName = alias.replaceAll("_", "").toLowerCase();
		for (Field field : fields) {
			if (field.getName().toLowerCase().equals(proName)) {// ȥ���¸ܵ��ֶ���������������Ե��ϣ���ȡ���SETTER����
				return propertyAccessor.getSetter(resultClass, field.getName());
			}
		}
		throw new RuntimeException(
				"�Ҳ������ݿ��ֶ� ��"
						+ alias
						+ " ��Ӧ��POJO���Ի���getter�������������ݿ��ֶ�ΪUSER_ID��USERID����ôJAVA����ӦΪuserId");
	}
 
	@SuppressWarnings("rawtypes")
	public List transformList(List collection) {
		return collection;
	}
}

