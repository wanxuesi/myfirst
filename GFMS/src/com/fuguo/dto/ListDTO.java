package com.fuguo.dto;

import java.io.Serializable;

import bsw.base.BaseDTO;



/**
 * List generated by MyEclipse - Hibernate Tools
 */

public class ListDTO    extends BaseDTO  {


    // Fields    

     private Integer id;
     private String zqdm;
     private String zqmc;
     private Integer cysl;//持有数量；
     private String jifl;
     private String flag1;
     private String flag2;
     private String flag3;
     private String flag4;
     private String flag5;


    // Constructors

    /** default constructor */
    public ListDTO() {
    }

    
    /** full constructor */
    public ListDTO(String zqdm, String zqmc, Integer cysl, String jifl, String flag1, String flag2, String flag3, String flag4, String flag5) {
        this.zqdm = zqdm;
        this.zqmc = zqmc;
        this.cysl = cysl;
        this.jifl = jifl;
        this.flag1 = flag1;
        this.flag2 = flag2;
        this.flag3 = flag3;
        this.flag4 = flag4;
        this.flag5 = flag5;
    }
    

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getZqdm() {
        return this.zqdm;
    }
    
    public void setZqdm(String zqdm) {
        this.zqdm = zqdm;
    }

    public String getZqmc() {
        return this.zqmc;
    }
    
    public void setZqmc(String zqmc) {
        this.zqmc = zqmc;
    }

    public Integer getCysl() {
        return this.cysl;
    }
    
    public void setCysl(Integer cysl) {
        this.cysl = cysl;
    }

    public String getJifl() {
        return this.jifl;
    }
    
    public void setJifl(String jifl) {
        this.jifl = jifl;
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

    public String getFlag3() {
        return this.flag3;
    }
    
    public void setFlag3(String flag3) {
        this.flag3 = flag3;
    }

    public String getFlag4() {
        return this.flag4;
    }
    
    public void setFlag4(String flag4) {
        this.flag4 = flag4;
    }

    public String getFlag5() {
        return this.flag5;
    }
    
    public void setFlag5(String flag5) {
        this.flag5 = flag5;
    }
   

	@Override
	public void setId(Serializable id) {
		// TODO 自动生成方法存根
		this.id=(Integer)id;
		
	}







}