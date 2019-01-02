package bsw.tools.exception;
/**
 * 
 * @单位: 江苏理工学院
 * @邮箱: wanxuesi@163.com
 * @作者: 万学思
 * @描述: 所有类的自定义异常类
 *
 * @日期: 2019-1-1
 */
public class BSWException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4401775941691162587L;

	
	/**
	 * 
	 * @param exceptionMessage
	 */
	public BSWException(String exceptionMessage){
		super(exceptionMessage);
	}
	
	/**
	 *  
	 * 实现父类方法  
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return super.getMessage();
	}
}
