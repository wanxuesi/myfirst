package bsw.base;

import org.apache.struts.action.ActionForm;

/**
 * 
 * @author Administrator
 *
 */


public abstract class BaseActionForm extends ActionForm {

	/**
	 * ��DTOת��ΪForm
	 * @param baseDTO
	 */
	public abstract void setDTO(BaseDTO baseDTO);
	
	/**
	 * ��Formת��ΪDTO
	 * @return
	 */
	public abstract BaseDTO getDTO();
	
}
