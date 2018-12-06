package bsw.base;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**描述：所有DTO的基类
 * @单位: 江苏工业学院
 * @邮箱: lixiaoyan19851122@163.com
 * @作者: 李小燕 
 */
public abstract class BaseDTO implements Serializable {

	private Serializable id;
	
	/**
	 * 需要时可用来保存对象
	 */
	private Map attribute = new HashMap();

	/**
	 * @return 返回 id。
	 */
	public abstract Serializable getId();

	/**
	 * @param id 要设置的 id。
	 */
	public abstract void setId(Serializable id);
	
	/**
	 * 获取对象值
	 * @param key
	 * @return
	 */
	public Object getAttribute(String key){
		return attribute.get(key);
	}
	
	/**
	 * 设置对象值
	 * @param key
	 * @param obj
	 */
	public void setAttribute(String key,Object obj){
		attribute.put(key,obj);
	}
}
