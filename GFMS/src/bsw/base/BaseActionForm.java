package bsw.base;

import org.apache.struts.action.ActionForm;

/**
 * 
 * @author Administrator
 *
 */


public abstract class BaseActionForm extends ActionForm {

	/**
	 * 把DTO转换为Form
	 * @param baseDTO
	 */
	public abstract void setDTO(BaseDTO baseDTO);
	
	/**
	 * 把Form转换为DTO
	 * @return
	 */
	public abstract BaseDTO getDTO();
	
}
