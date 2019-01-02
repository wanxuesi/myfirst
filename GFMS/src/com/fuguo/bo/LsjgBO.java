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
 * ��Ӷ���
 * @param gzly0DTO
 * @return
 * @throws BSWException
 */
public LsjgDTO add(LsjgDTO lsjgDTO) throws BSWException{
	
	lsjgPO=new LsjgPO();		
	
	return (LsjgDTO)lsjgPO.add(lsjgDTO);
}

/**
 * ��ѯĳ��¼����ϸ��Ϣ
 * @param lsjg0DTO
 * @return
 * @throws BSWException
 */
public LsjgDTO query(LsjgDTO lsjgDTO) throws BSWException{
	lsjgPO=new LsjgPO();
	return (LsjgDTO)lsjgPO.query(lsjgDTO);
}


/**
 * ���¶���
 * @param lsjg0DTO
 * @throws BSWException
 */
public void update(LsjgDTO lsjgDTO) throws BSWException{	
	lsjgPO=new LsjgPO();
	lsjgPO.update(lsjgDTO);
}


/**
 * ɾ��һ������
 * @param lsjgDTO
 * @throws BSWException
 */
public void delete(LsjgDTO lsjgDTO) throws BSWException{
	lsjgPO=new LsjgPO();
	lsjgPO.delete(lsjgDTO);
}


/**
 * ɾ���������
 * @param lsjgDTOS
 * @throws BSWException
 */
public void delete(LsjgDTO[] lsjgDTOS) throws BSWException{
	lsjgPO=new LsjgPO();
	lsjgPO.delete(lsjgDTOS);
}
/**
 * ����������Ϣ
 * @return
 * @throws BSWException
 */
public LsjgDTO[] loadAll(String _hql)throws BSWException{	
	lsjgPO=new LsjgPO();	
	BaseDTO[] baseDTOS=(BaseDTO[])lsjgPO.loadAll(_hql);//Ϊʲô������ֱ�ӷ��뵽LsjgDTOS���أ���Ϊ��֧������Ķ�̬��û�������̬������壩
	LsjgDTO[] lsjgDTOS=new LsjgDTO[baseDTOS.length];
	for(int i=0;i<lsjgDTOS.length;i++){
		lsjgDTOS[i]=new LsjgDTO();
		lsjgDTOS[i]=(LsjgDTO)baseDTOS[i];
	}
	return lsjgDTOS;
}
/**
 * ��ҳ��ʾ�����Ϣ
 * 
 * @return
 * @throws BSWException 
 */
public LsjgDTO[] search(PageRoll pageRoll) {
	String hql = "from LsjgDTO";
	pageRoll.setWhereClause(hql);
	//����ÿҳ��ʾ��¼����
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
 * �����Ҫ���ϲ�ѯ��ʱ�򣬿��Խ��÷������뵽ҵ�����У�
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
 *����:���������ѯ������sql��
 * @param sql,��Ҫ��װ��bean��
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

/**����URL�����ļ�������
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
//ͨ��2�����ڣ��͹�Ʊ���룬�Զ��ҳ��ù�Ʊ�����ƣ�//zqdmΪ�򵥵���ʽ��
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
		 throw new BSWException("ͨ��֤ȯ�����ȡ��Ʊ����ʧ�ܣ���鿴����Ʊ����ά�����У��Ƿ��Ѿ���Ӹù�Ʊ��Ϣ��"); 
	 }
	 
	 String fileName = "C:/"+zqmc+".csv";
	 //System.out.println(destUrl);
	 //System.out.println(fileName);
	 saveToFile(destUrl,fileName);
	 
	 return fileName;
}




/**
 * Ŀǰ�÷�����̫�ȶ���������Ҫ�޸�
 * 1������µ������¼�����ҳֲֹ�Ʊ����������ӵĹ�Ʊ��������ǰû�иù�Ʊ�Ĳ�����¼ʱֱ��ʹ�ã�
 * 2��autoUpdateLsjgs��zqdm��ʹ�ã�
 * @param startDate
 * @param endDate
 * @param zqdm
 * @throws BSWException
 * @throws IOException
 */
public void updateLsjgs(Date startDate, Date endDate, String zqdm)  throws BSWException,IOException{
	// TODO �Զ����ɷ������
	
	String fileName =getFileBy_DestUrlByDates_zqdm(startDate,endDate,zqdm);
	System.out.println("���������ص�"+fileName);
	
	//�˴���Ҫ�����ص��ļ����з����жϣ��Ƿ�����ȷ��ʽ���ļ�����
	//���ֻ��һ���س���������Ǵ����csv�ļ�
	
	CSVUtil test = new CSVUtil();
	test.run(zqdm,fileName);
	
}


public boolean isHave(LsjgDTO lsjgDTO)throws BSWException{
	boolean result = true;
	String zqdm = lsjgDTO.getZqdm();
	Date date  =lsjgDTO.getDate();
	java.sql.Date dateSql = new java.sql.Date(date.getTime()); 
//	*****��Ҫͨ�����ں�֤ȯ������ң�����Ѿ����ڣ�����Ҫ���
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
//	*****��Ҫͨ�����ں�֤ȯ������ң�����Ѿ����ڣ�����Ҫ���
	
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
	System.out.println("getMaxFqyz�м���ó���"+maxFqyz);
	return maxFqyz;
}


public List<LsjgDTO> getListByDatesAndZqdmFromSina(Date startDate,Date endDate,String zqdm)throws BSWException{

	 GpmcBO gBO = new GpmcBO();
	 GpmcDTO gDTO = new GpmcDTO();
	 gDTO.setId(zqdm);//�����list��
	 gDTO = gBO.query(gDTO);
	 String zqmc = gDTO.getZqmc();
	 if(zqmc==null||zqmc.equals("")){
		 throw new BSWException("ͨ��֤ȯ�����ȡ��Ʊ����ʧ�ܣ���鿴����Ʊ����ά�����У��Ƿ��Ѿ���Ӹù�Ʊ��Ϣ��"); 
	 } 
	
	 StockUtil sUtil = new StockUtil();
	 String fullZqdm = sUtil.getFullZqdmByZqdm3(zqdm);//6λ�������֣�
	 
	 //ͨ��startDate �������Ǽ�����ڼ����ȣ�
	 int startYear = startDate.getYear()+1900;
	 int startMonth = startDate.getMonth();
	 int startJd = (startMonth)/3+1;
	 
	 //ͨ��endDate �������Ǽ�����ڼ����ȣ�
	 int endYear = endDate.getYear()+1900;
	 int endMonth = endDate.getMonth();
	 int endJd = (endMonth)/3+1;
	 
	 int jdSize=endYear*4+endJd-(startYear*4+startJd)+1;
	 //�ж���Ҫѭ���Ĵ�����
	 //ƴװURL��ȡ���ӵ�list��
	 int tmpURLYear;
	 int tmpURLJd;
	 String URLFQ="";
	 String URL="";
	 JsoupParser jParser = new JsoupParser();
	 List<LsjgDTO> list = new ArrayList<LsjgDTO>();
	 for(int i=0;i<jdSize;i++){
		 //ͨ��i����Ҫ��ȡ�ĵ�ǰ���ȣ��㷨�����⣬�����һ�ξ���4���ȣ�����ͻ������⣻
		 tmpURLYear = startYear+ (startJd+i-1)/4;//����
		 tmpURLJd  =  (startJd+i)%4;//���ࣻ
		 if(tmpURLJd==0){
			 tmpURLJd=4; 
		 }
		
		 
		 URLFQ= "http://money.finance.sina.com.cn/corp/go.php/vMS_FuQuanMarketHistory/stockid/"+fullZqdm+".phtml?year="+tmpURLYear+"&jidu="+tmpURLJd;
		 
		 URL = "http://money.finance.sina.com.cn/corp/go.php/vMS_MarketHistory/stockid/"+fullZqdm+".phtml?year="+tmpURLYear+"&jidu="+tmpURLJd;
		 System.out.println(URL);
		 
		 list = jParser.getListLsjgsByUrl(URLFQ,URL,list,zqdm,zqmc);//����ǻ����жϻ���İ취��static String gupiaoORjijin(Integer zqdmInteger)������������Ȩ���ӣ�ֱ�ӽ���Ȩ����ֱ������Ϊ1

		 System.out.println(URL+"���ݷ��뵽list��");
	 }
	
	 
	return list;
}


/**
 * �����˻�ȡ���ݲ����£�
 * @param startDate
 * @param endDate
 * @param zqdm
 * //true ��ǰ���ڵļ�¼Ҳ���룬�����Ȩ����Ϊnull���ͱ��Ϊtmp��
 * @throws BSWException
 * @throws IOException
 */
public void updateLsjgsFromSina(Date startDate, Date endDate, String zqdm,boolean nowSystemDateIsAdd)  throws BSWException,IOException{
	// TODO �Զ����ɷ������
	SimpleDateFormat   sdf   =   new   SimpleDateFormat("yyyy-MM-dd");
	List list =getListByDatesAndZqdmFromSina(startDate,endDate,zqdm);
	System.out.println("������ȫ�����浽list��!");
	
	//System.out.println("������ʼ����getTimeֵ"+startDate.getTime());
	//System.out.println("���½�������getTimeֵ"+endDate.getTime());
	
	//�κ���Ӽ�¼ǰ����Ҫɾ�����Ϊ��tmp�ĸ�zqdm�ļ�¼��
	String sqlDel="delete from lsjg where zqdm='"+zqdm+"' and flag1='tmp'";
	sqlUpdateOrDel(sqlDel);
	Iterator it =list.iterator();
	LsjgDTO lsjgDTO=null;
	while(it.hasNext()){
		lsjgDTO  = (LsjgDTO)it.next();
		System.out.println("������¼������ֵ"+sdf.format(lsjgDTO.getDate())+"getTimeֵ"+lsjgDTO.getDate().getTime());
		if(nowSystemDateIsAdd==false){
			if(lsjgDTO.getDate().getTime()<startDate.getTime() || lsjgDTO.getDate().getTime()>endDate.getTime()){
				continue;
			}
		}else {
			//����ɾ���˰�2018-8-6����Ϊ����һЩ����Ī������Ķ�ʧ�����������жϰɣ��ܱ����ݲ���Ӻõ�
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
 * DWR ��¼������ҳ��(��ɫ��ҳ��)��ONLOADʹ�ã�
 * �Զ���ȡ�ֲֹ�Ʊ��order����Ĺ�Ʊ���룻
 * �Զ���ȡ��֤ȯ������lsjgDate���д�ŵĽ�ֹ���ڣ���Ϊ�µ���ʼ���ڣ�
 * �Զ���ȡ�µĽ�ֹ���ڣ�systemDate-1��
 * @throws BSWException
 * @throws IOException
 */
public void autoUpdateLsjgs() throws BSWException,IOException{
	
	OrderBO oBO = new OrderBO();
	OrderDTO[] oDTOs = oBO.loadAll("from OrderDTO");
	
	//	//�Զ���ȡ�ֲֹ�Ʊ��order����Ĺ�Ʊ���룻
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
//		�Զ���ȡ��֤ȯ������lsjgDate���д�ŵĽ�ֹ���ڣ�+1��Ϊ�µ���ʼ���ڣ�
		oldEndDate=lsjgdateDTO.getDate();
		
		startDate = dUtil.getAfterNDay(oldEndDate,1);	
		//�����ڽ����жϣ������Ƿ���Ҫ���£�
		//���startDate ��������+2����������վ�+1����Ϊ��ĩû��Ҫ���£�ֻ��Ҫ������һ��ʼ���£�
		xingqiji =  dUtil.getWeekOfDate(startDate);
		if(xingqiji.equals("(��)")){
			startDate = dUtil.getAfterNDay(startDate,2);
		}
		if(xingqiji.equals("(��)")){
			startDate = dUtil.getAfterNDay(startDate,1);
		}
		
		
		//�Ƚ�Ҫ���ص�2�����ڵ�������>=1 ����Ҫ���¡�	
		//startDate;
  		// endDate;
		int tmpNumbs = dUtil.getDateDiff(startDate,endDate);
		
		if(tmpNumbs>=1){
			updateLsjgs(startDate, endDate, zqdm);//
			
//			//�Զ���ȡ�µĽ�ֹ���ڣ�systemDate-1��
//			�ٸ�����lsjgdate���Date
			lsjgdateDTO.setId(zqdm);
			lsjgdateDTO.setZqdm(zqdm);			
			lsjgdateDTO.setDate(endDate);
//			��Ӹü�¼
			lsjgdateBO.update(lsjgdateDTO);
		}
		
	}

	
}



/**
 * DWR ��¼������ҳ��(��ɫ��ҳ��)��ONLOADʹ�ã�
 * �Զ���ȡ�ֲֹ�Ʊ��order����Ĺ�Ʊ���룻
 * �Զ���ȡ��֤ȯ������lsjgDate���д�ŵĽ�ֹ���ڣ���Ϊ�µ���ʼ���ڣ�
 * �Զ���ȡ�µĽ�ֹ���ڣ�systemDate-1��
 * @throws BSWException
 * @throws IOException
 * @throws ParseException 
 */
public int autoUpdateLsjgsFromSina() throws BSWException,IOException, ParseException{
	
	int returnFlag =0;
	//���ж������Ƿ��쳣
	
	HttpConnectUtil h = new HttpConnectUtil();
	String urlStr = "http://baidu.com/";
	boolean result = h.isConnect(urlStr);
	//System.out.println(result);
	if(result==false){
		return returnFlag=0;
	}
	
//	ͨ��session��ȡuserid��501����
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
	
	
	//����������˺ţ����Զ�������ѡ�ɵ���ʷ�۸�
	String zhanghaofenlei = baseUserContext.getWx();
	if(zhanghaofenlei.equals("ʵ��")){
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
	
	
	
	//	//�Զ���ȡ�ֲֹ�Ʊ��order����Ĺ�Ʊ���룻
//	OrderDTO[] oDTOs
	
	
	
//	
	//String zqdm="";
	LsjgdateDTO lsjgdateDTO=new LsjgdateDTO();
	LsjgdateBO lsjgdateBO = new LsjgdateBO();
	Date oldEndDate=null;
	Date startDate=null;
	String xingqiji="";
	//��ǰsystem���ڵ�ǰһ�죻
//	��Ҫǿ��ת��Ϊ����ʱ������ڣ�
	SimpleDateFormat   sdf   =   new   SimpleDateFormat("yyyy-MM-dd");
	String tmpDateStr = sdf.format(new java.util.Date());
	Date endDate = dUtil.getBeforeNDay(sdf.parse(tmpDateStr),1);
	for(int i=0;i<zqdms.length;i++){
		//zqdm = zqdms[i];
		lsjgdateDTO.setId(zqdms[i]);
		lsjgdateDTO = lsjgdateBO.query(lsjgdateDTO);
//		�Զ���ȡ��֤ȯ������lsjgDate���д�ŵĽ�ֹ���ڣ�+1��Ϊ�µ���ʼ���ڣ�
		oldEndDate=lsjgdateDTO.getDate();
		//�����������Ϊnull����ʾ������һ�θ��¶�û�й���
		if(oldEndDate==null){
			startDate = sdf.parse(sdf.format(lsjgdateDTO.getDatestart()));
		}else{
			startDate = dUtil.getAfterNDay(sdf.parse(sdf.format(oldEndDate)),1);
		}
		
		
		
			
		//�����ڽ����жϣ������Ƿ���Ҫ���£�
		//���startDate ��������+2����������վ�+1����Ϊ��ĩû��Ҫ���£�ֻ��Ҫ������һ��ʼ���£�
		xingqiji =  dUtil.getWeekOfDate(startDate);
		if(xingqiji.equals("(��)")){
			startDate = dUtil.getAfterNDay(startDate,2);
		}
		if(xingqiji.equals("(��)")){
			startDate = dUtil.getAfterNDay(startDate,1);
		}
		
		
		//�Ƚ�Ҫ���ص�2�����ڵ�������>=1 ����Ҫ���¡�	
		//startDate;
  		// endDate;
		int tmpNumbs = dUtil.getDateDiff(startDate,endDate);
		
		if(tmpNumbs>=1){
			updateLsjgsFromSina(startDate, endDate, zqdms[i],true);//
			
//			//�Զ���ȡ�µĽ�ֹ���ڣ�systemDate-1��
//			�ٸ�����lsjgdate���Date
			lsjgdateDTO.setId(zqdms[i]);
			lsjgdateDTO.setZqdm(zqdms[i]);
			
			String endDateStr = sdf.format(endDate);
			Date tmp =sdf.parse(endDateStr);
			lsjgdateDTO.setDate(tmp);
//			��Ӹü�¼
			lsjgdateBO.update(lsjgdateDTO);
		}
		
	}
	returnFlag=1;//�������ɹ���
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
	
	System.out.println(zqdm+"�ĸ�Ȩ�����ǣ�"+fqyz);
	
//	//String zqdm="600547";
//	
//	try {
//		String fileName =dBO.getFileBy_DestUrlByDates_zqdm(startDate,endDate,zqdm);
//		System.out.println("���������ص�"+fileName);
//		//��Ҫ�����ص��ļ����з���������ļ�������Ҫ�������أ�ѭ��5�λ���������ʾ�û������������أ�
//		//������ص��ļ���һ�У��ͱ�ʾ�����ˡ�������Ӧ���������أ�
//		
//	} catch (IOException e) {
//		// TODO �Զ����� catch ��
//		e.printStackTrace();
//	}

//	try {
//		dBO.autoUpdateLsjgs();
//	} catch (BSWException e) {
//		// TODO �Զ����� catch ��
//		e.printStackTrace();
//	} catch (IOException e) {
//		// TODO �Զ����� catch ��
//		e.printStackTrace();
//	}
	
}

}
