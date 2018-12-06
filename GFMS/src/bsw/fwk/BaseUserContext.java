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
    
    private String nx;//资金账号
    private String wx;//客户代码
    private Date datestart;
    
    private Date dateend;
   
	/**
	 * 用户名
	 */
	private String xm;
	
	/**
	 * 密码
	 */
	private String kl;
	
	/**
	 * 是否管理员
	 */
	
	private boolean isAdmin;
	
	/**
	 * 当前用户所拥有的权限列表
	 */
	private Map<String,String> function = new HashMap<String,String>();
	private Map<String,String> systemconfig = new LinkedHashMap<String,String>();
	/**
	 * 添加权限
	 * @param functionCode 权限功能码，要求唯一性
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
	 * 验证是否有该权限
	 * @param functionCode 权限功能码
	 * @return true:说明该用户有操作权限 false:说明该用户没有操作权限
	 */
	public boolean allow(String functionCode){
		return function.containsKey(functionCode);
	}

	


	/**
	 * @return 返回 function。
	 */
	public Map<String, String> getFunction() {
		return function;
	}




	/**
	 * @param function 要设置的 function。
	 */
	public void setFunction(Map<String, String> function) {
		this.function = function;
	}





	/**
	 * @return 返回 isAdmin。
	 */
	public boolean isAdmin() {
		return isAdmin;
	}




	/**
	 * @param isAdmin 要设置的 isAdmin。
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
