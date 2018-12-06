package com.fuguo.dto;

import java.util.Date;

public class PrintDTO {

	private String dwbm_name;
	private String dd;//工作地点；
	private Date jhksrq;//停电开始日期
	private Date jhjsrq;//停电结束日期
	private String jhksrqStr;//停电开始日期
	private String jhjsrqStr;//停电结束日期
	private String ts;//天数
	private String gzxz;//工作性质
	private String fzr;//负责人
	private String bz;//备注
	
	private String lb;//日工作计划专用
	private String jhksrq_xq;//开始日期是星期几
	private String jhjsrq_xq;//结日期是星期几
	
	private String totalTdsbfw;//合并后的停电设备范围
	private int highDydj;//最高电压等级
	boolean hasJutineirongFlag;//具体内容有没有--作用：决定要不要totalGongzuoneirong
	private String totalGongzuoneirong;//设备范围+描述+具体工作内容+主要工作内容
	private int needLines=0; //这条记录需要的行数
	private int shs=0;
	private int shishu=0;
	private int hs=0;
	public int getHs() {
		return hs;
	}
	public void setHs(int hs) {
		this.hs = hs;
	}
	public int getShishu() {
		return shishu;
	}
	public void setShishu(int shishu) {
		this.shishu = shishu;
	}
	public int getShs() {
		return shs;
	}
	public void setShs(int shs) {
		this.shs = shs;
	}
	public int getHighDydj() {
		return highDydj;
	}
	public void setHighDydj(int highDydj) {
		this.highDydj = highDydj;
	}
	
	public String getTotalTdsbfw() {
		return totalTdsbfw;
	}
	public void setTotalTdsbfw(String totalTdsbfw) {
		this.totalTdsbfw = totalTdsbfw;
	}
	public String getTotalGongzuoneirong() {
		return totalGongzuoneirong;
	}
	public void setTotalGongzuoneirong(String totalGongzuoneirong) {
		this.totalGongzuoneirong = totalGongzuoneirong;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getDd() {
		return dd;
	}
	public void setDd(String dd) {
		this.dd = dd;
	}
	public String getGzxz() {
		return gzxz;
	}
	public void setGzxz(String gzxz) {
		this.gzxz = gzxz;
	}
	public Date getJhjsrq() {
		return jhjsrq;
	}
	public void setJhjsrq(Date jhjsrq) {
		this.jhjsrq = jhjsrq;
	}
	public Date getJhksrq() {
		return jhksrq;
	}
	public void setJhksrq(Date jhksrq) {
		this.jhksrq = jhksrq;
	}
	public String getTs() {
		return ts;
	}
	public void setTs(String ts) {
		this.ts = ts;
	}
	public String getDwbm_name() {
		return dwbm_name;
	}
	public void setDwbm_name(String dwbm_name) {
		this.dwbm_name = dwbm_name;
	}
	public boolean isHasJutineirongFlag() {
		return hasJutineirongFlag;
	}
	public void setHasJutineirongFlag(boolean hasJutineirongFlag) {
		this.hasJutineirongFlag = hasJutineirongFlag;
	}
	public String getLb() {
		return lb;
	}
	public void setLb(String lb) {
		this.lb = lb;
	}
	public int getNeedLines() {
		return needLines;
	}
	public void setNeedLines(int needLines) {
		this.needLines = needLines;
	}
	public String getFzr() {
		return fzr;
	}
	public void setFzr(String fzr) {
		this.fzr = fzr;
	}
	public String getJhjsrq_xq() {
		return jhjsrq_xq;
	}
	public void setJhjsrq_xq(String jhjsrq_xq) {
		this.jhjsrq_xq = jhjsrq_xq;
	}
	public String getJhksrq_xq() {
		return jhksrq_xq;
	}
	public void setJhksrq_xq(String jhksrq_xq) {
		this.jhksrq_xq = jhksrq_xq;
	}
	public String getJhjsrqStr() {
		return jhjsrqStr;
	}
	public void setJhjsrqStr(String jhjsrqStr) {
		this.jhjsrqStr = jhjsrqStr;
	}
	public String getJhksrqStr() {
		return jhksrqStr;
	}
	public void setJhksrqStr(String jhksrqStr) {
		this.jhksrqStr = jhksrqStr;
	}
}
