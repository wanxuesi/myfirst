package com.fuguo.form;

import java.util.Date;

import org.apache.struts.action.ActionForm;

public class DataForm extends ActionForm{
	private Integer id;
    private String name;
    private double shuju;
    private String date;
    private String flag1;
    private String flag2;
    private double fene;
	public double getFene() {
		return fene;
	}
	public void setFene(double fene) {
		this.fene = fene;
	}
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
	public double getShuju() {
		return shuju;
	}
	public void setShuju(double shuju) {
		this.shuju = shuju;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
