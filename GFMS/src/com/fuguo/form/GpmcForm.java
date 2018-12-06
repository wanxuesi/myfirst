package com.fuguo.form;

import java.util.Date;

import org.apache.struts.action.ActionForm;

public class GpmcForm extends ActionForm{
    private String gpdm;
    private String gpmc;
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
	public String getGpdm() {
		return gpdm;
	}
	public void setGpdm(String gpdm) {
		this.gpdm = gpdm;
	}
	public String getGpmc() {
		return gpmc;
	}
	public void setGpmc(String gpmc) {
		this.gpmc = gpmc;
	}
	
	
	
}
