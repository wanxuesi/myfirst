package com.fuguo.bo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import uk.ltd.getahead.dwr.WebContext;
import uk.ltd.getahead.dwr.WebContextFactory;
import bsw.base.BaseBO;
import bsw.base.BaseDTO;
import bsw.fwk.BaseUserContext;
import bsw.fwk.PageRoll;
import bsw.tools.exception.BSWException;

import com.fuguo.dto.GpylDTO;
import com.fuguo.po.GpylPO;

public class GpylBO implements BaseBO{
GpylPO gpylPO = null;


/**
 * ��Ӷ���
 * @param gzly0DTO
 * @return
 * @throws BSWException
 */
public GpylDTO add(GpylDTO gpylDTO) throws BSWException{
	
	gpylPO=new GpylPO();		
	
	return (GpylDTO)gpylPO.add(gpylDTO);
}

/**
 * ��ѯĳ��¼����ϸ��Ϣ
 * @param gpyl0DTO
 * @return
 * @throws BSWException
 */
public GpylDTO query(GpylDTO gpylDTO) throws BSWException{
	gpylPO=new GpylPO();
	return (GpylDTO)gpylPO.query(gpylDTO);
}


/**
 * ���¶���
 * @param gpyl0DTO
 * @throws BSWException
 */
public void update(GpylDTO gpylDTO) throws BSWException{	
	gpylPO=new GpylPO();
	gpylPO.update(gpylDTO);
}


/**
 * ɾ��һ������
 * @param gpylDTO
 * @throws BSWException
 */
public void delete(GpylDTO gpylDTO) throws BSWException{
	gpylPO=new GpylPO();
	gpylPO.delete(gpylDTO);
}


/**
 * ɾ���������
 * @param gpylDTOS
 * @throws BSWException
 */
public void delete(GpylDTO[] gpylDTOS) throws BSWException{
	gpylPO=new GpylPO();
	gpylPO.delete(gpylDTOS);
}
/**
 * ����������Ϣ
 * @return
 * @throws BSWException
 */
public GpylDTO[] loadAll(String _hql)throws BSWException{	
	gpylPO=new GpylPO();	
	BaseDTO[] baseDTOS=(BaseDTO[])gpylPO.loadAll(_hql);//Ϊʲô������ֱ�ӷ��뵽GpylDTOS���أ���Ϊ��֧������Ķ�̬��û�������̬������壩
	GpylDTO[] gpylDTOS=new GpylDTO[baseDTOS.length];
	for(int i=0;i<gpylDTOS.length;i++){
		gpylDTOS[i]=new GpylDTO();
		gpylDTOS[i]=(GpylDTO)baseDTOS[i];
	}
	return gpylDTOS;
}
/**
 * ��ҳ��ʾ�����Ϣ
 * 
 * @return
 * @throws BSWException 
 */
public GpylDTO[] search(PageRoll pageRoll) {
	String hql = "from GpylDTO";
	pageRoll.setWhereClause(hql);
	//����ÿҳ��ʾ��¼����
	pageRoll.setPageSize(8);
	GpylPO gpylPO = new GpylPO();
	BaseDTO[] baseDTO = gpylPO.search(pageRoll);
	GpylDTO[] gpylDTO = null;
	gpylDTO = new GpylDTO[baseDTO.length];
	for (int i = 0; i < gpylDTO.length; i++) {
		gpylDTO[i] = (GpylDTO) baseDTO[i];
	}
	return gpylDTO;
}


/**
 * �����Ҫ���ϲ�ѯ��ʱ�򣬿��Խ��÷������뵽ҵ�����У�
 * @param hql
 * @return
 * @throws BSWException
 */
public List tuplesQuery(String hql) throws BSWException {
	gpylPO=new GpylPO();
	List list = gpylPO.tuplesQuery(hql);
	
	
	
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
	gpylPO=new GpylPO();
	List list =gpylPO.sqlQuery(sql,classArg);
	
	return list;
}

public List sqlQuery(String sql) throws BSWException {
	gpylPO=new GpylPO();
	List list =gpylPO.sqlQuery(sql);
	
	return list;
}

public void sqlUpdateOrDel(String sql) throws BSWException{
	gpylPO=new GpylPO();
	gpylPO.sqlUpdateOrDel(sql);
}
/**
 * dwr ר��
 * @param args
 */

public String getOneGpyl() throws BSWException{
	String message="";
	Map map=new HashMap();
	gpylPO=new GpylPO();
	//��ʾ������¼��
	GpylDTO[] gplydtos =loadAll("from GpylDTO gply  where gply.lx='��¼'");
	//����list<Map>��
	
	int listCount = gplydtos.length;
	//��ȡһ�������[0,listCount)���������listCount�����»�ȡ��
//	System.out.println("����Ϊ��"+listCount);
	
	
	//����Session�в��ң����û�У��ͷ��ý�ȥ��ͬʱ��ʾ��������
	WebContext webc = WebContextFactory.get();
	HttpServletRequest request = webc.getHttpServletRequest();
	Map mapSession = (Map)request.getSession().getAttribute("#MAPSESSION#");
	
	int randomInt;
	
	while(1>0){
		
		randomInt =(int)(0+Math.random()*listCount );
		boolean isContain=mapSession.containsKey(randomInt);
		if(isContain==false){
			mapSession.put(randomInt,randomInt);
			request.getSession().setAttribute("#MAPSESSION#",mapSession);
			message  = gplydtos[randomInt].getTitle();
			//System.out.println(randomInt+":"+message);			
			break;
		}
		//System.out.println("�������"+randomInt+"�Ѱ����������������ɡ�����������");		
	}	
	//System.out.println("mapSession��Ŀǰ����Ϊ��"+mapSession.size());
	
	//����У����Ż�ȡ��һ����ָ��map�ĸ���=listCount��Ȼ�����Map��
	if(mapSession.size()>listCount*0.7){
		mapSession.clear();
		//System.out.println("�����ﵽ69*0.7�����Ϊ��");
		request.getSession().setAttribute("#MAPSESSION#",mapSession);
	}
	
	return message;
}

/**
 * ��ȡ��Ʊ�����ר�ýӿ�
 * @return
 * @throws BSWException
 */
public String[] getOneGpgw() throws BSWException{
	
	WebContext webc = WebContextFactory.get();
	HttpServletRequest request = webc.getHttpServletRequest();
	BaseUserContext baseUserContext = (BaseUserContext) request.getSession().getAttribute("#BASEUSERCONTEXT#");
	int idUser  =baseUserContext.getId();
	String idStr  =Integer.toString(idUser);
	
	String[] message= new String[3];
	gpylPO=new GpylPO();
	//��ʾ������¼��
	GpylDTO[] gplydtos =loadAll("from GpylDTO gply  where flag1='"+idStr+"' and gply.lx='����'");
	//����list<Map>��
	
	int listCount = gplydtos.length;
	//��ȡһ�������[0,listCount)���������listCount�����»�ȡ��
//	System.out.println("����Ϊ��"+listCount);
	
	if(listCount==0){
		message[0]="";
		message[1]="";
		message[2]="";
		
	}else{

		int randomInt;
		randomInt =(int)(0+Math.random()*listCount );
		String t1 = gplydtos[randomInt].getTitle();
		String t2  = gplydtos[randomInt].getContent().replaceAll("\r\n","<br>");
		String t3  = gplydtos[randomInt].getFlag2();
		message[0]=t1;
		message[1]=t2;
		message[2]=t3;
	}
	
	
	
	
	
	return message;
}
public static void main(String[] args)throws BSWException{

	
	

	
	
}


}
