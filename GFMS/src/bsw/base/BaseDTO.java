package bsw.base;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**����������DTO�Ļ���
 * @��λ: ���չ�ҵѧԺ
 * @����: lixiaoyan19851122@163.com
 * @����: ��С�� 
 */
public abstract class BaseDTO implements Serializable {

	private Serializable id;
	
	/**
	 * ��Ҫʱ�������������
	 */
	private Map attribute = new HashMap();

	/**
	 * @return ���� id��
	 */
	public abstract Serializable getId();

	/**
	 * @param id Ҫ���õ� id��
	 */
	public abstract void setId(Serializable id);
	
	/**
	 * ��ȡ����ֵ
	 * @param key
	 * @return
	 */
	public Object getAttribute(String key){
		return attribute.get(key);
	}
	
	/**
	 * ���ö���ֵ
	 * @param key
	 * @param obj
	 */
	public void setAttribute(String key,Object obj){
		attribute.put(key,obj);
	}
}
