package com.fuguo.bo;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import bsw.base.BaseBO;
import bsw.base.BaseDTO;
import bsw.fwk.BaseUserContext;
import bsw.fwk.PageRoll;
import bsw.tools.exception.BSWException;

import com.fuguo.dto.GpmcDTO;
import com.fuguo.dto.LsjgDTO;
import com.fuguo.dto.LsjgdateDTO;
import com.fuguo.dto.MygpmcDTO;
import com.fuguo.dto.OrderDTO;
import com.fuguo.po.LsjgPO;
import com.fuguo.util.CSVUtil;
import com.fuguo.util.DateUtil;
import com.fuguo.util.HttpConnectUtil;
import com.fuguo.util.JsoupParser;
import com.fuguo.util.StockUtil;
public class LsjgBO implements BaseBO{
LsjgPO lsjgPO = null;
DateUtil dUtil  =new DateUtil();

/**
 * 添加对象
 * @param gzly0DTO
 * @return
 * @throws BSWException
 */
public LsjgDTO add(LsjgDTO lsjgDTO) throws BSWException{
	
	lsjgPO=new LsjgPO();		
	
	return (LsjgDTO)lsjgPO.add(lsjgDTO);
}

/**
 * 查询某记录的详细信息
 * @param lsjg0DTO
 * @return
 * @throws BSWException
 */
public LsjgDTO query(LsjgDTO lsjgDTO) throws BSWException{
	lsjgPO=new LsjgPO();
	return (LsjgDTO)lsjgPO.query(lsjgDTO);
}


/**
 * 更新对象
 * @param lsjg0DTO
 * @throws BSWException
 */
public void update(LsjgDTO lsjgDTO) throws BSWException{	
	lsjgPO=new LsjgPO();
	lsjgPO.update(lsjgDTO);
}


/**
 * 删除一个对象
 * @param lsjgDTO
 * @throws BSWException
 */
public void delete(LsjgDTO lsjgDTO) throws BSWException{
	lsjgPO=new LsjgPO();
	lsjgPO.delete(lsjgDTO);
}


/**
 * 删除多个对象
 * @param lsjgDTOS
 * @throws BSWException
 */
public void delete(LsjgDTO[] lsjgDTOS) throws BSWException{
	lsjgPO=new LsjgPO();
	lsjgPO.delete(lsjgDTOS);
}
/**
 * 查找所有信息
 * @return
 * @throws BSWException
 */
public LsjgDTO[] loadAll(String _hql)throws BSWException{	
	lsjgPO=new LsjgPO();	
	BaseDTO[] baseDTOS=(BaseDTO[])lsjgPO.loadAll(_hql);//为什么不可以直接放入到LsjgDTOS中呢？因为不支持数组的多态（没有数组多态这个定义）
	LsjgDTO[] lsjgDTOS=new LsjgDTO[baseDTOS.length];
	for(int i=0;i<lsjgDTOS.length;i++){
		lsjgDTOS[i]=new LsjgDTO();
		lsjgDTOS[i]=(LsjgDTO)baseDTOS[i];
	}
	return lsjgDTOS;
}
/**
 * 分页显示相关信息
 * 
 * @return
 * @throws BSWException 
 */
public LsjgDTO[] search(PageRoll pageRoll) {
	String hql = "from LsjgDTO";
	pageRoll.setWhereClause(hql);
	//设置每页显示记录条数
	pageRoll.setPageSize(8);
	LsjgPO lsjgPO = new LsjgPO();
	BaseDTO[] baseDTO = lsjgPO.search(pageRoll);
	LsjgDTO[] lsjgDTO = null;
	lsjgDTO = new LsjgDTO[baseDTO.length];
	for (int i = 0; i < lsjgDTO.length; i++) {
		lsjgDTO[i] = (LsjgDTO) baseDTO[i];
	}
	return lsjgDTO;
}


/**
 * 如果需要联合查询的时候，可以将该方法放入到业务类中；
 * @param hql
 * @return
 * @throws BSWException
 */
public List tuplesQuery(String hql) throws BSWException {
	lsjgPO=new LsjgPO();
	List list = lsjgPO.tuplesQuery(hql);
	
	
	
	return list;
}
/**
 * 
 *描述:对象关联查询（基于sql）
 * @param sql,需要组装的bean。
 * @return List
 * 
 * @throws BSWException
 */
public List sqlQuery(String sql,Class classArg) throws BSWException {
	lsjgPO=new LsjgPO();
	List list =lsjgPO.sqlQuery(sql,classArg);
	
	return list;
}





private static int BUFFER_SIZE = 8096;

/**根据URL下载文件并保存
* @param destUrl String
* @param fileName String
* @throws Exception
*/
public void saveToFile(String destUrl, String fileName) throws BSWException,IOException {

	 System.out.println(destUrl);
	 System.out.println(fileName);	
 FileOutputStream fos = null;
 BufferedInputStream bis = null;
 HttpURLConnection httpUrl = null;
 URL url = null;
 byte[] buf = new byte[BUFFER_SIZE];
 int size = 0;

 url = new URL(destUrl);
 httpUrl = (HttpURLConnection) url.openConnection();
 httpUrl.connect();
 bis = new BufferedInputStream(httpUrl.getInputStream());
 fos = new FileOutputStream(fileName);
 while ((size = bis.read(buf)) != -1)
  fos.write(buf, 0, size);
 fos.close();
 bis.close();
 httpUrl.disconnect();
}

//http://table.finance.yahoo.com/table.csv?a=0&b=1&c=2012&d=3&e=19&f=2012&s=600000.ss
//通过2个日期，和股票代码，自动找出该股票的名称；//zqdm为简单的形式；
public String getFileBy_DestUrlByDates_zqdm(Date startDate,Date endDate,String zqdm) throws BSWException, IOException{
	 //startDate
	 int a = startDate.getMonth();
	 int b = startDate.getDate();
	 int c = startDate.getYear()+1900;
	 
	 //endDate
	 int d = endDate.getMonth();
	 int e = endDate.getDate();
	 int f = endDate.getYear()+1900;
	 
	 StockUtil sUtil = new StockUtil();
	 String fullZqdm = sUtil.getFullZqdmByZqdm2(zqdm);
	 
	 String destUrl = "http://table.finance.yahoo.com/table.csv?a="+a+"&b="+b+"&c="+c+"&d="+d+"&e="+e+"&f="+f+"&s="+fullZqdm;
	 GpmcBO gBO = new GpmcBO();
	 GpmcDTO gDTO = new GpmcDTO();
	 gDTO.setId(zqdm);
	 gDTO = gBO.query(gDTO);
	 String zqmc = gDTO.getZqmc();
	 if(zqmc==null||zqmc.equals("")){
		 throw new BSWException("通过证券代码获取股票名称失败，请查看（股票名称维护）中，是否已经添加该股票信息！"); 
	 }
	 
	 String fileName = "C:/"+zqmc+".csv";
	 //System.out.println(destUrl);
	 //System.out.println(fileName);
	 saveToFile(destUrl,fileName);
	 
	 return fileName;
}




/**
 * 目前该方法不太稳定；后续需要修改
 * 1，添加新的买入记录，并且持仓股票里面是新添加的股票，并且以前没有该股票的操作记录时直接使用；
 * 2，autoUpdateLsjgs（zqdm）使用；
 * @param startDate
 * @param endDate
 * @param zqdm
 * @throws BSWException
 * @throws IOException
 */
public void updateLsjgs(Date startDate, Date endDate, String zqdm)  throws BSWException,IOException{
	// TODO 自动生成方法存根
	
	String fileName =getFileBy_DestUrlByDates_zqdm(startDate,endDate,zqdm);
	System.out.println("数据已下载到"+fileName);
	
	//此处需要对下载的文件进行分析判断，是否是正确格式的文件？？
	//如果只有一个回车键，则就是错误的csv文件
	
	CSVUtil test = new CSVUtil();
	test.run(zqdm,fileName);
	
}


public boolean isHave(LsjgDTO lsjgDTO)throws BSWException{
	boolean result = true;
	String zqdm = lsjgDTO.getZqdm();
	Date date  =lsjgDTO.getDate();
	java.sql.Date dateSql = new java.sql.Date(date.getTime()); 
//	*****需要通过日期和证券代码查找，如果已经存在，则不需要添加
	   String sql = "select * from lsjg where zqdm='"+zqdm+"'and date(date)='"+dateSql+"'";
	   
	   List listLsjg = sqlQuery(sql,LsjgDTO.class);  
	   if(listLsjg.size()<1){
		   result=false;
	   }
	
	return result;
}

public double getMaxFqyz(String zqdm,Date start_rqUtilDate)throws BSWException{
	double maxFqyz=1.0d;
	
	
	java.sql.Date dateSql = new java.sql.Date(start_rqUtilDate.getTime()); 
//	*****需要通过日期和证券代码查找，如果已经存在，则不需要添加
	
	   String sql = "select max(fqyz) as FQYZ from lsjg where zqdm='"+zqdm+"' and flag1!='tmp'  and  date(date)>='"+dateSql+"'";
	   
	   List listLsjg = sqlQuery(sql,LsjgDTO.class);  
	   if(listLsjg.size()<1){
		  return maxFqyz;
	   }else{
		   LsjgDTO lsjgDTO = (LsjgDTO)listLsjg.get(0);
		   maxFqyz = lsjgDTO.getFqyz();
		   if(maxFqyz<=1){
			   maxFqyz=1.0d; 
		   }
	   }
	System.out.println("getMaxFqyz中计算得出："+maxFqyz);
	return maxFqyz;
}


public List<LsjgDTO> getListByDatesAndZqdmFromSina(Date startDate,Date endDate,String zqdm)throws BSWException{

	 GpmcBO gBO = new GpmcBO();
	 GpmcDTO gDTO = new GpmcDTO();
	 gDTO.setId(zqdm);//存放在list中
	 gDTO = gBO.query(gDTO);
	 String zqmc = gDTO.getZqmc();
	 if(zqmc==null||zqmc.equals("")){
		 throw new BSWException("通过证券代码获取股票名称失败，请查看（股票名称维护）中，是否已经添加该股票信息！"); 
	 } 
	
	 StockUtil sUtil = new StockUtil();
	 String fullZqdm = sUtil.getFullZqdmByZqdm3(zqdm);//6位数的数字；
	 
	 //通过startDate 该日期是几几年第几季度；
	 int startYear = startDate.getYear()+1900;
	 int startMonth = startDate.getMonth();
	 int startJd = (startMonth)/3+1;
	 
	 //通过endDate 该日期是几几年第几季度；
	 int endYear = endDate.getYear()+1900;
	 int endMonth = endDate.getMonth();
	 int endJd = (endMonth)/3+1;
	 
	 int jdSize=endYear*4+endJd-(startYear*4+startJd)+1;
	 //判断需要循环的次数；
	 //拼装URL获取叠加的list；
	 int tmpURLYear;
	 int tmpURLJd;
	 String URLFQ="";
	 String URL="";
	 JsoupParser jParser = new JsoupParser();
	 List<LsjgDTO> list = new ArrayList<LsjgDTO>();
	 for(int i=0;i<jdSize;i++){
		 //通过i计算要获取的当前季度；算法有问题，如果第一次就是4季度，后面就会有问题；
		 tmpURLYear = startYear+ (startJd+i-1)/4;//求整
		 tmpURLJd  =  (startJd+i)%4;//求余；
		 if(tmpURLJd==0){
			 tmpURLJd=4; 
		 }
		
		 
		 URLFQ= "http://money.finance.sina.com.cn/corp/go.php/vMS_FuQuanMarketHistory/stockid/"+fullZqdm+".phtml?year="+tmpURLYear+"&jidu="+tmpURLJd;
		 
		 URL = "http://money.finance.sina.com.cn/corp/go.php/vMS_MarketHistory/stockid/"+fullZqdm+".phtml?year="+tmpURLYear+"&jidu="+tmpURLJd;
		 System.out.println(URL);
		 
		 list = jParser.getListLsjgsByUrl(URLFQ,URL,list,zqdm,zqmc);//如果是基金（判断基金的办法：static String gupiaoORjijin(Integer zqdmInteger)），就跳过复权因子，直接将复权因子直接设置为1

		 System.out.println(URL+"数据放入到list中");
	 }
	
	 
	return list;
}


/**
 * 从新浪获取数据并更新；
 * @param startDate
 * @param endDate
 * @param zqdm
 * //true 当前日期的记录也加入，如果复权因子为null，就标记为tmp；
 * @throws BSWException
 * @throws IOException
 */
public void updateLsjgsFromSina(Date startDate, Date endDate, String zqdm,boolean nowSystemDateIsAdd)  throws BSWException,IOException{
	// TODO 自动生成方法存根
	SimpleDateFormat   sdf   =   new   SimpleDateFormat("yyyy-MM-dd");
	List list =getListByDatesAndZqdmFromSina(startDate,endDate,zqdm);
	System.out.println("数据已全部保存到list中!");
	
	//System.out.println("更新起始日期getTime值"+startDate.getTime());
	//System.out.println("更新结束日期getTime值"+endDate.getTime());
	
	//任何添加记录前，需要删除标记为：tmp的该zqdm的记录；
	String sqlDel="delete from lsjg where zqdm='"+zqdm+"' and flag1='tmp'";
	sqlUpdateOrDel(sqlDel);
	Iterator it =list.iterator();
	LsjgDTO lsjgDTO=null;
	while(it.hasNext()){
		lsjgDTO  = (LsjgDTO)it.next();
		System.out.println("此条记录的日期值"+sdf.format(lsjgDTO.getDate())+"getTime值"+lsjgDTO.getDate().getTime());
		if(nowSystemDateIsAdd==false){
			if(lsjgDTO.getDate().getTime()<startDate.getTime() || lsjgDTO.getDate().getTime()>endDate.getTime()){
				continue;
			}
		}else {
			//还是删除了吧2018-8-6，因为总有一些数据莫名其妙的丢失，宁可冗余判断吧，总比数据不添加好点
			//if(lsjgDTO.getDate().getTime()<startDate.getTime()){
			//	continue;
			//}
		}
		
		if(!isHave(lsjgDTO)){
			add(lsjgDTO);
		}
		
	}
	
	
	
}
/**
 * DWR 登录后，在主页面(蓝色的页面)的ONLOAD使用；
 * 自动获取持仓股票（order表）里的股票代码；
 * 自动获取该证券代码在lsjgDate表中存放的截止日期，作为新的起始日期；
 * 自动获取新的截止日期，systemDate-1；
 * @throws BSWException
 * @throws IOException
 */
public void autoUpdateLsjgs() throws BSWException,IOException{
	
	OrderBO oBO = new OrderBO();
	OrderDTO[] oDTOs = oBO.loadAll("from OrderDTO");
	
	//	//自动获取持仓股票（order表）里的股票代码；
//	OrderDTO[] oDTOs
	
	
	
//	
	String zqdm="";
	LsjgdateDTO lsjgdateDTO=new LsjgdateDTO();
	LsjgdateBO lsjgdateBO = new LsjgdateBO();
	Date oldEndDate=null;
	Date startDate=null;
	String xingqiji="";
	Date endDate = dUtil.getBeforeNDay(new java.util.Date(),1);
	for(int i=0;i<oDTOs.length;i++){
		zqdm = oDTOs[i].getZqdm();
		lsjgdateDTO.setId(zqdm);
		lsjgdateDTO = lsjgdateBO.query(lsjgdateDTO);
//		自动获取该证券代码在lsjgDate表中存放的截止日期，+1作为新的起始日期；
		oldEndDate=lsjgdateDTO.getDate();
		
		startDate = dUtil.getAfterNDay(oldEndDate,1);	
		//对日期进行判断，看看是否需要更新；
		//如果startDate 是周六就+2，如果是周日就+1；因为周末没必要更新，只需要从星期一开始更新；
		xingqiji =  dUtil.getWeekOfDate(startDate);
		if(xingqiji.equals("(六)")){
			startDate = dUtil.getAfterNDay(startDate,2);
		}
		if(xingqiji.equals("(日)")){
			startDate = dUtil.getAfterNDay(startDate,1);
		}
		
		
		//比较要下载的2个日期的天数相差，>=1 就需要更新。	
		//startDate;
  		// endDate;
		int tmpNumbs = dUtil.getDateDiff(startDate,endDate);
		
		if(tmpNumbs>=1){
			updateLsjgs(startDate, endDate, zqdm);//
			
//			//自动获取新的截止日期，systemDate-1；
//			再更新下lsjgdate里的Date
			lsjgdateDTO.setId(zqdm);
			lsjgdateDTO.setZqdm(zqdm);			
			lsjgdateDTO.setDate(endDate);
//			添加该记录
			lsjgdateBO.update(lsjgdateDTO);
		}
		
	}

	
}



/**
 * DWR 登录后，在主页面(蓝色的页面)的ONLOAD使用；
 * 自动获取持仓股票（order表）里的股票代码；
 * 自动获取该证券代码在lsjgDate表中存放的截止日期，作为新的起始日期；
 * 自动获取新的截止日期，systemDate-1；
 * @throws BSWException
 * @throws IOException
 * @throws ParseException 
 */
public int autoUpdateLsjgsFromSina() throws BSWException,IOException, ParseException{
	
	int returnFlag =0;
	//先判断网络是否异常
	
	HttpConnectUtil h = new HttpConnectUtil();
	String urlStr = "http://baidu.com/";
	boolean result = h.isConnect(urlStr);
	//System.out.println(result);
	if(result==false){
		return returnFlag=0;
	}
	
//	通过session获取userid‘501’；
 	org.directwebremoting.WebContext wc = org.directwebremoting.WebContextFactory.get(); 

    //javax.servlet.http.HttpServletRequest request = wc.getHttpServletRequest();  
    javax.servlet.http.HttpSession session = wc.getSession();
    BaseUserContext baseUserContext = (BaseUserContext)session.getAttribute("#BASEUSERCONTEXT#");
    int idUser  =baseUserContext.getId();
	String idStr  =Integer.toString(idUser);
	OrderBO oBO = new OrderBO();
	MygpmcBO mBO = new MygpmcBO();
	MygpmcDTO[] mDTOs=null;
	OrderDTO[] oDTOs=null;
	
	String[] zqdms=null;
	
	
	//如果是虚拟账号，就自动更新自选股的历史价格；
	String zhanghaofenlei = baseUserContext.getWx();
	if(zhanghaofenlei.equals("实际")){
		oDTOs= oBO.loadAll("from OrderDTO orderDTO where orderDTO.flag1='"+idStr+"'");		
		zqdms = new String[oDTOs.length];
		for(int i=0;i<oDTOs.length;i++){
			zqdms[i] = oDTOs[i].getZqdm();
		}
	}else{
		mDTOs=mBO.loadAll("from MygpmcDTO mygpmcDTO where flag2='"+idStr+"'order by mygpmcDTO.flag1,zqdm");		
		zqdms = new String[mDTOs.length];
		for(int i=0;i<mDTOs.length;i++){
			zqdms[i] = mDTOs[i].getZqdm();
		}
	}
	
	
	
	//	//自动获取持仓股票（order表）里的股票代码；
//	OrderDTO[] oDTOs
	
	
	
//	
	//String zqdm="";
	LsjgdateDTO lsjgdateDTO=new LsjgdateDTO();
	LsjgdateBO lsjgdateBO = new LsjgdateBO();
	Date oldEndDate=null;
	Date startDate=null;
	String xingqiji="";
	//当前system日期的前一天；
//	需要强制转换为不带时间的日期；
	SimpleDateFormat   sdf   =   new   SimpleDateFormat("yyyy-MM-dd");
	String tmpDateStr = sdf.format(new java.util.Date());
	Date endDate = dUtil.getBeforeNDay(sdf.parse(tmpDateStr),1);
	for(int i=0;i<zqdms.length;i++){
		//zqdm = zqdms[i];
		lsjgdateDTO.setId(zqdms[i]);
		lsjgdateDTO = lsjgdateBO.query(lsjgdateDTO);
//		自动获取该证券代码在lsjgDate表中存放的截止日期，+1作为新的起始日期；
		oldEndDate=lsjgdateDTO.getDate();
		//如果结束日期为null；表示该日期一次更新都没有过。
		if(oldEndDate==null){
			startDate = sdf.parse(sdf.format(lsjgdateDTO.getDatestart()));
		}else{
			startDate = dUtil.getAfterNDay(sdf.parse(sdf.format(oldEndDate)),1);
		}
		
		
		
			
		//对日期进行判断，看看是否需要更新；
		//如果startDate 是周六就+2，如果是周日就+1；因为周末没必要更新，只需要从星期一开始更新；
		xingqiji =  dUtil.getWeekOfDate(startDate);
		if(xingqiji.equals("(六)")){
			startDate = dUtil.getAfterNDay(startDate,2);
		}
		if(xingqiji.equals("(日)")){
			startDate = dUtil.getAfterNDay(startDate,1);
		}
		
		
		//比较要下载的2个日期的天数相差，>=1 就需要更新。	
		//startDate;
  		// endDate;
		int tmpNumbs = dUtil.getDateDiff(startDate,endDate);
		
		if(tmpNumbs>=1){
			updateLsjgsFromSina(startDate, endDate, zqdms[i],true);//
			
//			//自动获取新的截止日期，systemDate-1；
//			再更新下lsjgdate里的Date
			lsjgdateDTO.setId(zqdms[i]);
			lsjgdateDTO.setZqdm(zqdms[i]);
			
			String endDateStr = sdf.format(endDate);
			Date tmp =sdf.parse(endDateStr);
			lsjgdateDTO.setDate(tmp);
//			添加该记录
			lsjgdateBO.update(lsjgdateDTO);
		}
		
	}
	returnFlag=1;//处理结果成功！
	return returnFlag;
}
public void sqlUpdateOrDel(String sql) throws BSWException {
	lsjgPO=new LsjgPO();
	lsjgPO.sqlUpdateOrDel( sql);
}


public static void main(String[] args)throws BSWException, ParseException{
	LsjgBO dBO=new LsjgBO();

	SimpleDateFormat   sdfymd   =   new   SimpleDateFormat("yyyy-MM-dd");
	String startDateStr="2016-08-01";
	String endDateStr = "2015-9-17";
	
	Date startDate = sdfymd.parse(startDateStr);
	Date endDate = sdfymd.parse(endDateStr);
	String zqdm="2695";
	
	Double fqyz = dBO.getMaxFqyz(zqdm,startDate);
	
	System.out.println(zqdm+"的复权因子是："+fqyz);
	
//	//String zqdm="600547";
//	
//	try {
//		String fileName =dBO.getFileBy_DestUrlByDates_zqdm(startDate,endDate,zqdm);
//		System.out.println("数据已下载到"+fileName);
//		//需要对下载的文件进行分析；如果文件出错，需要重新下载，循环5次还出错，就提示用户，待会再下载；
//		//如果下载的文件是一行，就表示出错了。。。。应该重新下载；
//		
//	} catch (IOException e) {
//		// TODO 自动生成 catch 块
//		e.printStackTrace();
//	}

//	try {
//		dBO.autoUpdateLsjgs();
//	} catch (BSWException e) {
//		// TODO 自动生成 catch 块
//		e.printStackTrace();
//	} catch (IOException e) {
//		// TODO 自动生成 catch 块
//		e.printStackTrace();
//	}
	
}

}
