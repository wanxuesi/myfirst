package com.fuguo.dto;

import java.io.Serializable;

import bsw.base.BaseDTO;



/**
 * Juese generated by MyEclipse - Hibernate Tools
 */

public class JueseDTO   extends BaseDTO  {


    // Fields    

     private Integer id;
     private String name;
     private String otherflag;


    // Constructors

    /** default constructor */
    public JueseDTO() {
    }

    
    /** full constructor */
    public JueseDTO(String name, String otherflag) {
        this.name = name;
        this.otherflag = otherflag;
    }
    

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getOtherflag() {
        return this.otherflag;
    }
    
    public void setOtherflag(String otherflag) {
        this.otherflag = otherflag;
    }


 

	@Override
	public void setId(Serializable id) {
		this.id=(Integer)id;
		
	}
   








}