package bsw.fwk;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


public class BaseUserContext {

	
	private Integer id;
    private Integer dwId;
    
    private String dwname;
    private String banzu;
    private String juese;
    
    private String nx;//�ʽ��˺�
    private String wx;//�ͻ�����
    private Date datestart;
    
    private Date dateend;
   
	/**
	 * �û���
	 */
	private String xm;
	
	/**
	 * ����
	 */
	private String kl;
	
	/**
	 * �Ƿ����Ա
	 */
	
	private boolean isAdmin;
	
	/**
	 * ��ǰ�û���ӵ�е�Ȩ���б�
	 */
	private Map<String,String> function = new HashMap<String,String>();
	private Map<String,String> systemconfig = new LinkedHashMap<String,String>();
	/**
	 * ���Ȩ��
	 * @param functionCode Ȩ�޹����룬Ҫ��Ψһ��
	 */
	public void addFunction(String functionId){
		function.put(functionId,functionId);
	}
	
	public void addSystemconfig(String functioncode,String functionvalue){
		systemconfig.put(functioncode,functionvalue);
		
	}
	
	public Map<String,String> getSystemconfig(){
		return systemconfig;
	}
	/**
	 * ��֤�Ƿ��и�Ȩ��
	 * @param functionCode Ȩ�޹�����
	 * @return true:˵�����û��в���Ȩ�� false:˵�����û�û�в���Ȩ��
	 */
	public boolean allow(String functionCode){
		return function.containsKey(functionCode);
	}

	


	/**
	 * @return ���� function��
	 */
	public Map<String, String> getFunction() {
		return function;
	}




	/**
	 * @param function Ҫ���õ� function��
	 */
	public void setFunction(Map<String, String> function) {
		this.function = function;
	}





	/**
	 * @return ���� isAdmin��
	 */
	public boolean isAdmin() {
		return isAdmin;
	}




	/**
	 * @param isAdmin Ҫ���õ� isAdmin��
	 */
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}




	



	public String getKl() {
		return kl;
	}




	public void setKl(String kl) {
		this.kl = kl;
	}




	




	public String getXm() {
		return xm;
	}




	public void setXm(String xm) {
		this.xm = xm;
	}




	public String getBanzu() {
		return banzu;
	}




	public void setBanzu(String banzu) {
		this.banzu = banzu;
	}




	public Integer getDwId() {
		return dwId;
	}




	public void setDwId(Integer dwId) {
		this.dwId = dwId;
	}




	public String getDwname() {
		return dwname;
	}




	public void setDwname(String dwname) {
		this.dwname = dwname;
	}




	public Integer getId() {
		return id;
	}




	public void setId(Integer id) {
		this.id = id;
	}

	public String getJuese() {
		return juese;
	}

	public void setJuese(String juese) {
		this.juese = juese;
	}

	public void setSystemconfig(Map<String, String> systemconfig) {
		this.systemconfig = systemconfig;
	}
	public String getNx() {
		return nx;
	}
	public void setNx(String nx) {
		this.nx = nx;
	}
	public String getWx() {
		return wx;
	}
	public void setWx(String wx) {
		this.wx = wx;
	}

	public Date getDateend() {
		return dateend;
	}

	public void setDateend(Date dateend) {
		this.dateend = dateend;
	}

	public Date getDatestart() {
		return datestart;
	}

	public void setDatestart(Date datestart) {
		this.datestart = datestart;
	}




	
	
	
}
