package com.fuguo.form;

import java.util.Date;

import org.apache.struts.action.ActionForm;

public class GpylForm extends ActionForm{
	private Integer id;
	private String lx;
    private String title;
    private String content;
    private String flag1;
    private String flag2;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public String getLx() {
		return lx;
	}
	public void setLx(String lx) {
		this.lx = lx;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
