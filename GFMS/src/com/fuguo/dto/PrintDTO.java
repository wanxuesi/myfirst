package com.fuguo.dto;

import java.util.Date;

public class PrintDTO {

	private String dwbm_name;
	private String dd;//�����ص㣻
	private Date jhksrq;//ͣ�翪ʼ����
	private Date jhjsrq;//ͣ���������
	private String jhksrqStr;//ͣ�翪ʼ����
	private String jhjsrqStr;//ͣ���������
	private String ts;//����
	private String gzxz;//��������
	private String fzr;//������
	private String bz;//��ע
	
	private String lb;//�չ����ƻ�ר��
	private String jhksrq_xq;//��ʼ���������ڼ�
	private String jhjsrq_xq;//�����������ڼ�
	
	private String totalTdsbfw;//�ϲ����ͣ���豸��Χ
	private int highDydj;//��ߵ�ѹ�ȼ�
	boolean hasJutineirongFlag;//����������û��--���ã�����Ҫ��ҪtotalGongzuoneirong
	private String totalGongzuoneirong;//�豸��Χ+����+���幤������+��Ҫ��������
	private int needLines=0; //������¼��Ҫ������
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
