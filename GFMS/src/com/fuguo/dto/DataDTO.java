package com.fuguo.dto;

import java.io.Serializable;
import java.util.Date;

import bsw.base.BaseDTO;



/**
 * Data generated by MyEclipse - Hibernate Tools
 */

public class DataDTO extends BaseDTO  {


    // Fields    

     private Integer id;
     private String name;
     private double shuju;
     private Date date;
     private String flag1;
     private String flag2;
     private double fene;


    // Constructors

    /** default constructor */
    public DataDTO() {
    }

    
    /** full constructor */
    public DataDTO(String name, double shuju, String flag1, String flag2) {
        this.name = name;
        this.shuju = shuju;
        this.flag1 = flag1;
        this.flag2 = flag2;
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

    public double getShuju() {
        return this.shuju;
    }
    
    public void setShuju(double shuju) {
        this.shuju = shuju;
    }

    public String getFlag1() {
        return this.flag1;
    }
    
    public void setFlag1(String flag1) {
        this.flag1 = flag1;
    }

    public String getFlag2() {
        return this.flag2;
    }
    
    public void setFlag2(String flag2) {
        this.flag2 = flag2;
    }


	@Override
	public void setId(Serializable id) {
		// TODO 自动生成方法存根
		this.id = (Integer)id;
		
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public double getFene() {
		return fene;
	}


	public void setFene(double fene) {
		this.fene = fene;
	}
   








}