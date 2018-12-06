package com.fuguo.form;

import java.util.Date;

import org.apache.struts.action.ActionForm;

public class LsjgForm extends ActionForm{
	private Integer id;
    private String zqdm;
    private String zqmc;
    private String date;
    private double open;
    private double high;
    private double low;
    private double close;
    private double volume;
    private double adjclose;
    private String flag1;
    private String flag2;
	public double getAdjclose() {
		return adjclose;
	}
	public void setAdjclose(double adjclose) {
		this.adjclose = adjclose;
	}
	public double getClose() {
		return close;
	}
	public void setClose(double close) {
		this.close = close;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getFlag1() {
		return flag1;
	}
	public void setFlag1(String flag1) {
		this.flag1 = flag1;
	}
	public String getFlag2() {
		return flag2;
	}
	public void setFlag2(String flag2) {
		this.flag2 = flag2;
	}
	public double getHigh() {
		return high;
	}
	public void setHigh(double high) {
		this.high = high;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public double getLow() {
		return low;
	}
	public void setLow(double low) {
		this.low = low;
	}
	public double getOpen() {
		return open;
	}
	public void setOpen(double open) {
		this.open = open;
	}
	public double getVolume() {
		return volume;
	}
	public void setVolume(double volume) {
		this.volume = volume;
	}
	public String getZqdm() {
		return zqdm;
	}
	public void setZqdm(String zqdm) {
		this.zqdm = zqdm;
	}
	public String getZqmc() {
		return zqmc;
	}
	public void setZqmc(String zqmc) {
		this.zqmc = zqmc;
	}
}
