package com.fuguo.dto;

import java.io.Serializable;

import bsw.base.BaseDTO;



/**
 * Order generated by MyEclipse - Hibernate Tools
 */

public class OrderDTO  extends BaseDTO{


    // Fields    

     private Integer id;
     private String zqdm;
     private String zqmc;
     private Integer cysl;//持有数量
     private double cbj;
     private String flag1;
     private String flag2;
     private String flag3;
     private String flag4;
     private String flag5;
     private double scj;//市场价
     private double dqsz;//当前市值；
     private double yk;//盈亏
     private String ykbl;//盈亏比例
     
     private String listStr; 


    // Constructors

    public String getListStr() {
		return listStr;
	}


	public void setListStr(String listStr) {
		this.listStr = listStr;
	}


	public double getScj() {
		return scj;
	}


	public void setScj(double scj) {
		this.scj = scj;
	}


	public double getYk() {
		return yk;
	}


	public void setYk(double yk) {
		this.yk = yk;
	}


	public String getYkbl() {
		return ykbl;
	}


	public void setYkbl(String ykbl) {
		this.ykbl = ykbl;
	}


	/** default constructor */
    public OrderDTO() {
    }

    
    /** full constructor */
    public OrderDTO(String zqdm, String zqmc, Integer cysl, double cbj, String flag1, String flag2, String flag3, String flag4, String flag5) {
        this.zqdm = zqdm;
        this.zqmc = zqmc;
        this.cysl = cysl;
        this.cbj = cbj;
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

    public double getCbj() {
        return this.cbj;
    }
    
    public void setCbj(double cbj) {
        this.cbj = cbj;
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


	public double getDqsz() {
		return dqsz;
	}


	public void setDqsz(double dqsz) {
		this.dqsz = dqsz;
	}






}