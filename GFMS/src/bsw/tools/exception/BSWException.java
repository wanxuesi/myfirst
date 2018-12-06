package bsw.tools.exception;

/**
 * @描述: 框架异常类
 *

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
