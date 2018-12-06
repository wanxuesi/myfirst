package com.fuguo.form;

import java.util.Date;

import org.apache.struts.action.ActionForm;

public class DanweiForm extends ActionForm{
	private Integer id;
    private String name;
    private Integer parent;
    private String bz;
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getParent() {
		return parent;
	}
	public void setParent(Integer parent) {
		this.parent = parent;
	}
	
}
