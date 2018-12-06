package com.fuguo.dto;

import java.io.Serializable;
import java.util.Date;

import bsw.base.BaseDTO;


/**
 * Jilu generated by MyEclipse - Hibernate Tools
 */

public class JiluDTO   extends BaseDTO {


    // Fields    

     private Integer id;
     private String timeid;
     private Date jysj;
     private String zqdm;
     private String zqmc;
     private String mmflag;
     private double cjjg;
     private Integer cjsl;
     private double cjje;
     private double qsje;
     private String ywmc;
     private String zjzh;
     private String jysmc;
     private String khdm;
     private String gdmc;
     private String bz;
     private String jifl;
     private String flag1;//计划状态
     private String flag2;//双击确认后，后变色/变亮
     private String flag3;//T 后一一对应的标记；可以加一列串号，比如数字1-100都行；取消的话，就要将串号清空；
     private String flag4;//临时做包含与否的状态用，不对数据库产生任何影响
     private String flag5;
     private String flag6;
     private String flag7;
     private String flag8;
     private String flag9;
     private String flag10;
     private String jysjStr;
     
     
     private Date jiludateMin;
     private Date jiludateMax;
     
     private Date datestart;
     private Date date;


    // Constructors

    public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public Date getDatestart() {
		return datestart;
	}


	public void setDatestart(Date datestart) {
		this.datestart = datestart;
	}


	public String getJysjStr() {
		return jysjStr;
	}


	public void setJysjStr(String jysjStr) {
		this.jysjStr = jysjStr;
	}


	/** default constructor */
    public JiluDTO() {
    }

    
    /** full constructor */
    public JiluDTO(String timeid, Date jysj, String zqdm, String zqmc, String mmflag, double cjjg, Integer cjsl, double cjje, double qsje, String ywmc, String zjzh, String jysmc, String khdm, String gdmc, String bz, String jifl, String flag1, String flag2, String flag3, String flag4, String flag5, String flag6, String flag7, String flag8, String flag9, String flag10) {
        this.timeid = timeid;
        this.jysj = jysj;
        this.zqdm = zqdm;
        this.zqmc = zqmc;
        this.mmflag = mmflag;
        this.cjjg = cjjg;
        this.cjsl = cjsl;
        this.cjje = cjje;
        this.qsje = qsje;
        this.ywmc = ywmc;
        this.zjzh = zjzh;
        this.jysmc = jysmc;
        this.khdm = khdm;
        this.gdmc = gdmc;
        this.bz = bz;
        this.jifl = jifl;
        this.flag1 = flag1;
        this.flag2 = flag2;
        this.flag3 = flag3;
        this.flag4 = flag4;
        this.flag5 = flag5;
        this.flag6 = flag6;
        this.flag7 = flag7;
        this.flag8 = flag8;
        this.flag9 = flag9;
        this.flag10 = flag10;
    }
    

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getTimeid() {
        return this.timeid;
    }
    
    public void setTimeid(String timeid) {
        this.timeid = timeid;
    }

    public Date getJysj() {
        return this.jysj;
    }
    
    public void setJysj(Date jysj) {
        this.jysj = jysj;
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

    public String getMmflag() {
        return this.mmflag;
    }
    
    public void setMmflag(String mmflag) {
        this.mmflag = mmflag;
    }

    public double getCjjg() {
        return this.cjjg;
    }
    
    public void setCjjg(double cjjg) {
        this.cjjg = cjjg;
    }

    public Integer getCjsl() {
        return this.cjsl;
    }
    
    public void setCjsl(Integer cjsl) {
        this.cjsl = cjsl;
    }

    public double getCjje() {
        return this.cjje;
    }
    
    public void setCjje(double cjje) {
        this.cjje = cjje;
    }

    public double getQsje() {
        return this.qsje;
    }
    
    public void setQsje(double qsje) {
        this.qsje = qsje;
    }

    public String getYwmc() {
        return this.ywmc;
    }
    
    public void setYwmc(String ywmc) {
        this.ywmc = ywmc;
    }

    public String getZjzh() {
        return this.zjzh;
    }
    
    public void setZjzh(String zjzh) {
        this.zjzh = zjzh;
    }

    public String getJysmc() {
        return this.jysmc;
    }
    
    public void setJysmc(String jysmc) {
        this.jysmc = jysmc;
    }

    public String getKhdm() {
        return this.khdm;
    }
    
    public void setKhdm(String khdm) {
        this.khdm = khdm;
    }

    public String getGdmc() {
        return this.gdmc;
    }
    
    public void setGdmc(String gdmc) {
        this.gdmc = gdmc;
    }

    public String getBz() {
        return this.bz;
    }
    
    public void setBz(String bz) {
        this.bz = bz;
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

    public String getFlag6() {
        return this.flag6;
    }
    
    public void setFlag6(String flag6) {
        this.flag6 = flag6;
    }

    public String getFlag7() {
        return this.flag7;
    }
    
    public void setFlag7(String flag7) {
        this.flag7 = flag7;
    }

    public String getFlag8() {
        return this.flag8;
    }
    
    public void setFlag8(String flag8) {
        this.flag8 = flag8;
    }

    public String getFlag9() {
        return this.flag9;
    }
    
    public void setFlag9(String flag9) {
        this.flag9 = flag9;
    }

    public String getFlag10() {
        return this.flag10;
    }
    
    public void setFlag10(String flag10) {
        this.flag10 = flag10;
    }


	@Override
	public void setId(Serializable id) {
		// TODO 自动生成方法存根
		this.id=(Integer)id;
		
	}


	public Date getJiludateMax() {
		return jiludateMax;
	}


	public void setJiludateMax(Date jiludateMax) {
		this.jiludateMax = jiludateMax;
	}


	public Date getJiludateMin() {
		return jiludateMin;
	}


	public void setJiludateMin(Date jiludateMin) {
		this.jiludateMin = jiludateMin;
	}
   








}