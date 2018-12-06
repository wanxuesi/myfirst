package com.fuguo.form;

import org.apache.struts.action.ActionForm;

public class SystemconfigForm extends ActionForm{
//	 Fields    
	 // Fields    

    private String functioncode;
    private String functionname;
    private String functionvalue;
    private String flag1;
    private String flag2;
	public String getFlag1() {
		return flag1;
	}
	public void setFlag1(String flag1) {
		this.flag1 = flag1;
	}
	public String getFlag2() {
		return flag2;
	}
	public void setFlag2(String flag2) {
		this.flag2 = flag2;
	}
	public String getFunctioncode() {
		return functioncode;
	}
	public void setFunctioncode(String functioncode) {
		this.functioncode = functioncode;
	}
	public String getFunctionname() {
		return functionname;
	}
	public void setFunctionname(String functionname) {
		this.functionname = functionname;
	}
	public String getFunctionvalue() {
		return functionvalue;
	}
	public void setFunctionvalue(String functionvalue) {
		this.functionvalue = functionvalue;
	}
   
	
}
