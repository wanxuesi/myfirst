package com.fuguo.dto;

import java.io.Serializable;

import bsw.base.BaseDTO;



/**
 * Config generated by MyEclipse - Hibernate Tools
 */

public class ConfigDTO extends BaseDTO {


    // Fields    

     
     private String userid;
     private Integer onoff;
     private double confirmbfb;
     private double yinhuashuibuy;
     private double yinhuashuisell;
     private double onegupiaocangweibfb;
     private double onejiflcangweibfb;
     private double yongjin;
     private Integer yongjinmin;
     private double guohufei;
     private Integer guohufeimin;
     private String flag1;
     private String flag2;
     private String flag3;
     private String flag4;
     private String flag5;


    // Constructors

    /** default constructor */
    public ConfigDTO() {
    }

    
    /** full constructor */
    public ConfigDTO(String userid, Integer onoff, double confirmbfb, double yinhuashuibuy, double yinhuashuisell, double onegupiaocangweibfb, double onejiflcangweibfb, double yongjin, Integer yongjinmin, double guohufei, Integer guohufeimin, String flag1, String flag2, String flag3, String flag4, String flag5) {
        this.userid = userid;
        this.onoff = onoff;
        this.confirmbfb = confirmbfb;
        this.yinhuashuibuy = yinhuashuibuy;
        this.yinhuashuisell = yinhuashuisell;
        this.onegupiaocangweibfb = onegupiaocangweibfb;
        this.onejiflcangweibfb = onejiflcangweibfb;
        this.yongjin = yongjin;
        this.yongjinmin = yongjinmin;
        this.guohufei = guohufei;
        this.guohufeimin = guohufeimin;
        this.flag1 = flag1;
        this.flag2 = flag2;
        this.flag3 = flag3;
        this.flag4 = flag4;
        this.flag5 = flag5;
    }
    

   
    // Property accessors

  
    public String getUserid() {
        return this.userid;
    }
    
    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Integer getOnoff() {
        return this.onoff;
    }
    
    public void setOnoff(Integer onoff) {
        this.onoff = onoff;
    }

    public double getConfirmbfb() {
        return this.confirmbfb;
    }
    
    public void setConfirmbfb(double confirmbfb) {
        this.confirmbfb = confirmbfb;
    }

    public double getYinhuashuibuy() {
        return this.yinhuashuibuy;
    }
    
    public void setYinhuashuibuy(double yinhuashuibuy) {
        this.yinhuashuibuy = yinhuashuibuy;
    }

    public double getYinhuashuisell() {
        return this.yinhuashuisell;
    }
    
    public void setYinhuashuisell(double yinhuashuisell) {
        this.yinhuashuisell = yinhuashuisell;
    }

    public double getOnegupiaocangweibfb() {
        return this.onegupiaocangweibfb;
    }
    
    public void setOnegupiaocangweibfb(double onegupiaocangweibfb) {
        this.onegupiaocangweibfb = onegupiaocangweibfb;
    }

    public double getOnejiflcangweibfb() {
        return this.onejiflcangweibfb;
    }
    
    public void setOnejiflcangweibfb(double onejiflcangweibfb) {
        this.onejiflcangweibfb = onejiflcangweibfb;
    }

    public double getYongjin() {
        return this.yongjin;
    }
    
    public void setYongjin(double yongjin) {
        this.yongjin = yongjin;
    }

    public Integer getYongjinmin() {
        return this.yongjinmin;
    }
    
    public void setYongjinmin(Integer yongjinmin) {
        this.yongjinmin = yongjinmin;
    }

    public double getGuohufei() {
        return this.guohufei;
    }
    
    public void setGuohufei(double guohufei) {
        this.guohufei = guohufei;
    }

    public Integer getGuohufeimin() {
        return this.guohufeimin;
    }
    
    public void setGuohufeimin(Integer guohufeimin) {
        this.guohufeimin = guohufeimin;
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
		// TODO �Զ����ɷ������
		this.userid = (String)id;
		
	}


	@Override
	public Serializable getId() {
		// TODO �Զ����ɷ������
		return this.userid;
	}







}