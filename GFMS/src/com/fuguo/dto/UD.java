package com.fuguo.dto;

import java.io.Serializable;

import bsw.base.BaseDTO;



/**
 * ������
 */

public class UD    extends BaseDTO  {
	/**
	 * 
	 */
	
	private int  DWID;
	private String XM;
	private String NAME;//��λ����

    




	@Override
	public Serializable getId() {
		// TODO �Զ����ɷ������
		return null;
	}




	@Override
	public void setId(Serializable id) {
		// TODO �Զ����ɷ������
		
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