package com.fuguo.dto;

import java.io.Serializable;

import bsw.base.BaseDTO;



/**
 * Function generated by MyEclipse - Hibernate Tools
 */

public class FunctionDTO  extends BaseDTO {


    // Fields    

     private String functioncode;
     private String funcname;


    // Constructors

    /** default constructor */
    public FunctionDTO() {
    }

    
    /** full constructor */
    public FunctionDTO(String funcname) {
        this.funcname = funcname;
    }
    

   
    // Property accessors

    public String getFunctioncode() {
        return this.functioncode;
    }
    
    public void setFunctioncode(String functioncode) {
        this.functioncode = functioncode;
    }

    public String getFuncname() {
        return this.funcname;
    }
    
    public void setFuncname(String funcname) {
        this.funcname = funcname;
    }


	@Override
	public Serializable getId() {
		// TODO 自动生成方法存根
		return this.functioncode;
	}


	@Override
	public void setId(Serializable id) {
		// TODO 自动生成方法存根
		this.functioncode = (String)id;
	}
   








}