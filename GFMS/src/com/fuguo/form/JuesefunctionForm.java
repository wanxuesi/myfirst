package com.fuguo.form;

import java.util.Date;

import org.apache.struts.action.ActionForm;

public class JuesefunctionForm extends ActionForm{
	private Integer id;
    private String juesename;
    private String functioncode;
    private String[] functioncodes;
    private String otherflag;
	public String getFunctioncode() {
		return functioncode;
	}
	public void setFunctioncode(String functioncode) {
		this.functioncode = functioncode;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getJuesename() {
		return juesename;
	}
	public void setJuesename(String juesename) {
		this.juesename = juesename;
	}
	public String getOtherflag() {
		return otherflag;
	}
	public void setOtherflag(String otherflag) {
		this.otherflag = otherflag;
	}
	public String[] getFunctioncodes() {
		return functioncodes;
	}
	public void setFunctioncodes(String[] functioncodes) {
		this.functioncodes = functioncodes;
	}
	
}
