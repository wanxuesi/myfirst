package bsw.tools.exception;
/**
 * 
 * @��λ: ������ѧԺ
 * @����: wanxuesi@163.com
 * @����: ��ѧ˼
 * @����: ��������Զ����쳣��
 *
 * @����: 2019-1-1
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
