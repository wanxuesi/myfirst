package com.fuguo.dto;

import java.io.Serializable;

import bsw.base.BaseDTO;



/**
 * 测试用
 */

public class UD    extends BaseDTO  {
	/**
	 * 
	 */
	
	private int  DWID;
	private String XM;
	private String NAME;//单位名称

    




	@Override
	public Serializable getId() {
		// TODO 自动生成方法存根
		return null;
	}




	@Override
	public void setId(Serializable id) {
		// TODO 自动生成方法存根
		
	}




	



	public int getDWID() {
		return DWID;
	}




	public void setDWID(int dwid) {
		DWID = dwid;
	}




	public String getNAME() {
		return NAME;
	}




	public void setNAME(String name) {
		NAME = name;
	}




	public String getXM() {
		return XM;
	}




	public void setXM(String xm) {
		XM = xm;
	}




	




	
   
   








}