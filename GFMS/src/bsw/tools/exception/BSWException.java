package bsw.tools.exception;

/**
 * @����: ����쳣��
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
	 * ʵ�ָ��෽��  
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return super.getMessage();
	}
}
