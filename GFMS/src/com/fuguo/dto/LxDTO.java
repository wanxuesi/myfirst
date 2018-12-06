package com.fuguo.dto;

import java.io.Serializable;

import bsw.base.BaseDTO;



/**
 * Gzly generated by MyEclipse - Hibernate Tools
 */

public class LxDTO   extends BaseDTO {


    // Fields    

     private Integer id;
     private String name;
     private String flag1;
     private String flag2;
     private int maNumb;
     private int argsIndex;


    // Constructors

    /** default constructor */
    public LxDTO() {
    }

    
    /** full constructor */
    public LxDTO(String name, String flag1, String flag2) {
        this.name = name;
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
		this.id=(Integer)id;
		
	}


	public int getArgsIndex() {
		return argsIndex;
	}


	public void setArgsIndex(int argsIndex) {
		this.argsIndex = argsIndex;
	}


	public int getMaNumb() {
		return maNumb;
	}


	public void setMaNumb(int maNumb) {
		this.maNumb = maNumb;
	}
   








}