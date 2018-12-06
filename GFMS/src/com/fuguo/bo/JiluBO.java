package com.fuguo.bo;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import bsw.base.BaseBO;
import bsw.base.BaseDTO;
import bsw.fwk.BaseUserContext;
import bsw.fwk.PageRoll;
import bsw.tools.exception.BSWException;

import com.fuguo.dto.DataDTO;
import com.fuguo.dto.JiluDTO;
import com.fuguo.dto.ListDTO;
import com.fuguo.dto.LsjgdateDTO;
import com.fuguo.dto.OrderDTO;
import com.fuguo.po.JiluPO;
import com.fuguo.util.DateUtil;

public class JiluBO implements BaseBO{
JiluPO jiluPO = null;


/**
 * ��Ӷ���
 * @param weekPlan0DTO
 * @return
 * @throws BSWException
 */
public JiluDTO add(JiluDTO jiluDTO) throws BSWException{
	
	jiluPO=new JiluPO();		
	
	return (JiluDTO)jiluPO.add(jiluDTO);
}

/**
 * ��ѯĳ��¼����ϸ��Ϣ
 * @param jilu0DTO
 * @return
 * @throws BSWException
 */
public JiluDTO query(JiluDTO jiluDTO) throws BSWException{
	jiluPO=new JiluPO();
	return (JiluDTO)jiluPO.query(jiluDTO);
}


/**
 * ���¶���
 * @param jilu0DTO
 * @throws BSWException
 */
public void update(JiluDTO jiluDTO) throws BSWException{	
	jiluPO=new JiluPO();
	jiluPO.update(jiluDTO);
}


/**
 * ɾ��һ������
 * @param jiluDTO
 * @throws BSWException
 */
public void delete(JiluDTO jiluDTO) throws BSWException{
	jiluPO=new JiluPO();
	jiluPO.delete(jiluDTO);
}


/**
 * ɾ���������
 * @param jiluDTOS
 * @throws BSWException
 */
public void delete(JiluDTO[] jiluDTOS) throws BSWException{
	jiluPO=new JiluPO();
	jiluPO.delete(jiluDTOS);
}
/**
 * ����������Ϣ
 * @return
 * @throws BSWException
 */
public JiluDTO[] loadAll(String _hql)throws BSWException{	
	jiluPO=new JiluPO();	
	BaseDTO[] baseDTOS=(BaseDTO[])jiluPO.loadAll(_hql);//Ϊʲô������ֱ�ӷ��뵽JiluDTOS���أ���Ϊ��֧������Ķ�̬��û�������̬������壩
	JiluDTO[] jiluDTOS=new JiluDTO[baseDTOS.length];
	for(int i=0;i<jiluDTOS.length;i++){
		jiluDTOS[i]=new JiluDTO();
		jiluDTOS[i]=(JiluDTO)baseDTOS[i];
	}
	return jiluDTOS;
}
/**
 * ��ҳ��ʾ�����Ϣ
 * 
 * @return
 * @throws BSWException 
 */
public JiluDTO[] search(PageRoll pageRoll) {
//	String hql = "from JiluDTO";
//	pageRoll.setWhereClause(hql);
	//����ÿҳ��ʾ��¼����
	pageRoll.setPageSize(8);
	JiluPO jiluPO = new JiluPO();
	BaseDTO[] baseDTO = jiluPO.search(pageRoll);
	JiluDTO[] jiluDTO = null;
	SimpleDateFormat   sdf   =   new   SimpleDateFormat("yyyy-MM-dd HH:mm");
	DateUtil dateUtil=new DateUtil();
	Date jhkssj;
	Date jhjssj;
	String jhkssjStr;
	String jhjssjStr;
	String jhkssjWeek;
	String jhjssjWeek;
	String tmpgzcs;
	String gzcs;
	String bdz;
	String tbdw;
	String flag1;
	jiluDTO = new JiluDTO[baseDTO.length];
	for (int i = 0; i < jiluDTO.length; i++) {
		jiluDTO[i] = (JiluDTO) baseDTO[i];	
		
	}
	return jiluDTO;
}


/**
 * �����Ҫ���ϲ�ѯ��ʱ�򣬿��Խ��÷������뵽ҵ�����У�
 * @param hql
 * @return
 * @throws BSWException
 */
public List tuplesQuery(String hql) throws BSWException {
	jiluPO=new JiluPO();
	List list = jiluPO.tuplesQuery(hql);
	
	
	
	return list;
}


/**
 * 
 *����:���������ѯ������sql��
 * @param sql
 * @return List<Map>
 * 
 * @throws BSWException
 */
public List sqlQuery(String sql) throws BSWException {
	jiluPO=new JiluPO();
	List list =jiluPO.sqlQuery(sql);
	
	return list;
}


public List sqlQuery(String sql,Class classArg) throws BSWException {
	jiluPO=new JiluPO();
	List list =jiluPO.sqlQuery(sql,classArg);
	
	return list;
}
public void sqlUpdateOrDel(String sql) throws BSWException{
	jiluPO=new JiluPO();
	jiluPO.sqlUpdateOrDel(sql);
}

public void clearAllData(BaseUserContext baseUserContext)throws BSWException{
//	��Ҫ����������У�
	//jilu����������ݣ�
	int idUser  =baseUserContext.getId();
	String idStr  =Integer.toString(idUser);
	String sqlJilu = "delete from jilu where khdm='"+idStr+"'";
	sqlUpdateOrDel(sqlJilu);
	//order��������ݣ�
	OrderBO orderBO=new OrderBO();
	String sqlOrder = "delete from order where flag1='"+idStr+"'";
	orderBO.sqlUpdateOrDel(sqlOrder);
	
	//list��������ݣ�
	ListBO listBO=new ListBO();
	String sqlList = "delete from list where flag1='"+idStr+"'";
	listBO.sqlUpdateOrDel(sqlList);
	//data��������ݣ�
	DataBO dataBO=new DataBO();
	String sqlData = "delete from data where flag2='"+idStr+"'";
	dataBO.sqlUpdateOrDel(sqlData);
	DataDTO dDTO=new DataDTO();
	dDTO.setName("�ʽ����");
	
	String shujuStr = (String)baseUserContext.getSystemconfig().get("defaultmoney");
	Double shuju = Double.parseDouble(shujuStr);
	//double shuju=200000d;
	//DecimalFormat df=(DecimalFormat)NumberFormat.getInstance();

	//System.out.println(df.format(shuju));
	dDTO.setShuju(shuju);
//	��ʼ���ݶ�����
	dDTO.setFene(shuju);//1Ԫ��Ӧ1.00�ķݶ
	dDTO.setDate(new Date());
	dDTO.setFlag2(idStr);
	dataBO.add(dDTO);
	
	
	
}
/**
 * ��δ�������ݣ��趨Ϊ�Ѵ�������ʱ����̨������߼����㣻
 * @param jiluDTO
 * @throws BSWException
 */
public void logic(JiluDTO jiluDTO,String userIdStr,BaseUserContext baseUserContext, boolean isAddJilu)throws BSWException {

	
	
    String zqdm =jiluDTO.getZqdm();
    String zqmc =jiluDTO.getZqmc();
    String mmflag =jiluDTO.getMmflag();
    
//  �ж��Ƿ��������ù�Ʊ
    boolean isCcgpOK=false;
    OrderBO oBO = new OrderBO();
    if(mmflag.equals("����")){
    	isCcgpOK  = oBO.isCcgpOK(zqdm,baseUserContext);
    }else{
    	isCcgpOK=true;
    }
    if(isCcgpOK==false){
    	throw new BSWException("sorry,��ʱ�޷�����ù�Ʊ���ֲֹ�Ʊ����������������һֻ�ֲֹ�Ʊ�����������û�Ȩ�ޣ�");
    }
    //double cjjg =jiluDTO.getCjjg();
    int cjsl =jiluDTO.getCjsl();
    //double cjje =jiluDTO.getCjje();
    double qsje = jiluDTO.getQsje();
    String jifl =jiluDTO.getJifl(); 
    Date jysjDate = jiluDTO.getJysj();
		
        
    JiluBO tBO =new JiluBO();
	//��order�в��ң����޸�zqdm�����û�У���ӡ�
	//OrderBO oBO = new OrderBO();
	OrderDTO oDTO = new OrderDTO();
	
	ListBO lBO = new ListBO();
	ListDTO lDTO = new ListDTO();
	List list =oBO.sqlQuery("select id,zqdm,cysl,cbj from order where  flag1='"+userIdStr+"' and   zqdm='"+zqdm+"'");
	List listlist =lBO.sqlQuery("select id,zqdm,cysl,jifl from list where  flag1='"+userIdStr+"' and   zqdm='"+zqdm+"' and jifl='"+jifl+"'");
	
	
	if(list.size()==0){
		
		if(mmflag.equals("����")){
			
			if(isAddJilu==true){
				tBO.add(jiluDTO);
			}
//			˵��û�иü�¼��ֱ����order��list�мӼ��ɣ�
			oDTO.setZqdm(zqdm);
			oDTO.setZqmc(zqmc);
			oDTO.setCysl(cjsl);
			//Ҫ���³ɱ��۵ġ�
			oDTO.setCbj(Math.abs(qsje)/cjsl);
			oDTO.setFlag1(userIdStr);
			oBO.add(oDTO);
			//order���ǿյĻ���˵��list�п϶�Ϊ�գ�ֱ����Ӽ��ɣ�
			lDTO.setZqdm(zqdm);
			lDTO.setZqmc(zqmc);
			lDTO.setCysl(cjsl);
			lDTO.setJifl(jifl);
			lDTO.setFlag1(userIdStr);
			
			lBO.add(lDTO);
			//2015-10-7��ӣ�
//			��Ҫ���/����lsjgdate��¼��
			LsjgdateDTO lsjgdateDTO  =new LsjgdateDTO();
			lsjgdateDTO.setId(zqdm);
			
			LsjgdateBO  lsjgdateBO  =new LsjgdateBO();
			DateUtil dUtil  =new DateUtil();
			LsjgdateDTO tmpDTO = lsjgdateBO.query(lsjgdateDTO);
			
			//���д�����bug-null
			if(tmpDTO!=null&&tmpDTO.getDate()!=null&&tmpDTO.getDate().getYear()>0){
				//����иù�Ʊ��lsjgDate��¼������ӣ�
			}else{
				//���û�У��ͱ������lsjgDate��¼��
				lsjgdateDTO.setId(zqdm);
				lsjgdateDTO.setZqdm(zqdm);
				//��ȡ�����ھ���ǰ�����ڣ�
				Date startDate = dUtil.getBeforeNDay(jysjDate,9);
				//Date endDate = dUtil.getBeforeNDay(new java.util.Date(),1);
				lsjgdateDTO.setDate(null);
				lsjgdateDTO.setDatestart(startDate);
//				��Ӹü�¼
				lsjgdateBO.add(lsjgdateDTO);
				
//				//���¸ù�Ʊ����ʷ�۸�
//				LsjgBO lsjgBO = new LsjgBO();
//				lsjgBO.updateLsjgs(startDate,endDate,zqdm);
//				
//				//�ٸ�����lsjgdate���Date
//				lsjgdateDTO.setId(zqdm);
//				lsjgdateDTO.setZqdm(zqdm);
//				
//				lsjgdateDTO.setDate(endDate);
////				��Ӹü�¼
//				lsjgdateBO.update(lsjgdateDTO);
				
			}
			
		}else{
			//��Ϊlist.size()==0��order��û�иù�Ʊ������
			throw new BSWException("�޷��������ù�Ʊ�ֲ���Ϊ0��");
		}
		
		
	}else{
		//order���е������size>0������Ҫupdate2����
		if(mmflag.equals("����")){
			
			if(isAddJilu==true){
				tBO.add(jiluDTO);
			}
//			˵���иü�¼��ֱ����orderUpdate;
			oDTO.setZqdm(zqdm);
			oDTO.setZqmc(zqmc);
			
			Iterator it = list.iterator();
			
			Map _map=null;
			Integer cysl=0;
			double cbj=0;
			Integer id=0;
			if(it.hasNext()){
				_map=(Map)it.next();
				id = (Integer)_map.get("ID");
				cysl = (Integer)_map.get("CYSL");//ԭ���ĳ�������
				
				cbj  =(Double)_map.get("CBJ");//ԭ���ĳɱ���
				//cjjes = (Double)_map.get("CJJES");
			}
			
			
//			Ҫ���µ��Ӻ�ĳ�����������
			oDTO.setId(id);
			oDTO.setCysl(cjsl+cysl);
			
			//Ҫ����������ӵĳɱ��۵ġ�
			oDTO.setCbj((Math.abs(qsje)+cysl*cbj)/(cjsl+cysl));
			oDTO.setFlag1(userIdStr);
			oBO.update(oDTO);
			
			
			
			//list�з�2�������
			
			
			Iterator itlist = listlist.iterator();
//			һ����û�и�jifl��
			if(listlist.size()==0){
				//ֱ����Ӹý��׷����list���ݣ�
				lDTO.setZqdm(zqdm);
				lDTO.setZqmc(zqmc);
				lDTO.setCysl(cjsl);
				lDTO.setJifl(jifl);
				lDTO.setFlag1(userIdStr);
				lBO.add(lDTO);
				
			}else{
//				һ�����и�jifl��
				Map _maplist=null;
				Integer cysllist=0;
				Integer idList = 0;
				
				if(itlist.hasNext()){
					_maplist=(Map)itlist.next();
					idList = (Integer)_maplist.get("ID");
					cysllist = (Integer)_maplist.get("CYSL");//ԭ���ĳ�������
					
	
				}
				
				lDTO.setId(idList);
//				Ҫ���µ��Ӻ�ĳ�����������
				lDTO.setCysl(cysllist+cjsl);					
				//order���ǿյĻ���˵��list�п϶�Ϊ�գ�ֱ����Ӽ��ɣ�
				lDTO.setZqdm(zqdm);
				lDTO.setZqmc(zqmc);
				lDTO.setJifl(jifl);	
				lDTO.setFlag1(userIdStr);
				lBO.update(lDTO);
			}
			
			
		}else{
			if(isAddJilu==true){
				tBO.add(jiluDTO);
			}
//			order���е������size>0����
			//mmflag.equals("����")
			
			
			//��Ҫupdate2����
			//��Ϊ����ʱ��ͨ�����׷���ѡ����Զ������������������������Բ���Ҫ�жϡ�ֱ���������ɡ�
			//��update���ű�
			

//			˵���иü�¼��ֱ����orderUpdate;
			oDTO.setZqdm(zqdm);
			oDTO.setZqmc(zqmc);
			
			Iterator it = list.iterator();
			
			Map _map=null;
			Integer cysl=0;
			double cbj=0;
			Integer idOrder= 0;
			if(it.hasNext()){
				_map=(Map)it.next();
				idOrder =  (Integer)_map.get("ID");
				cysl = (Integer)_map.get("CYSL");//ԭ���ĳ�������
				
				cbj  =(Double)_map.get("CBJ");//ԭ���ĳɱ���
				//cjjes = (Double)_map.get("CJJES");
			}
			
			oDTO.setId(idOrder);
			oDTO.setFlag1(userIdStr);
			
//			Ҫ������������Ӻ�ĳ�����������
			int cysltmp=0;
			cysltmp = cysl-Math.abs(cjsl);
			oDTO.setCysl(cysltmp);
			if(cysltmp==0){
				oDTO.setCbj(0);
			}else{
//				Ҫ����������ĵ��ӳɱ��۵ġ�
				oDTO.setCbj((cysl*cbj-Math.abs(qsje))/(cysl-Math.abs(cjsl)));
			}

			
			oBO.update(oDTO);
			
			
			
			
			
			
			Iterator itlist = listlist.iterator();

//			�϶��и�jifl��
			Map _maplist=null;
			Integer cysllist=0;
			Integer idList=0;
			if(itlist.hasNext()){
				_maplist=(Map)itlist.next();
				idList = (Integer)_maplist.get("ID");
				cysllist = (Integer)_maplist.get("CYSL");//ԭ���ĳ�������
				

			}
			
			lDTO.setId(idList);
//			Ҫ���µ��Ӻ�ĳ�����������
			lDTO.setCysl(cysllist-Math.abs(cjsl));					
			//order���ǿյĻ���˵��list�п϶�Ϊ�գ�ֱ����Ӽ��ɣ�
			lDTO.setZqdm(zqdm);
			lDTO.setZqmc(zqmc);
			lDTO.setJifl(jifl);	
			lDTO.setFlag1(userIdStr);
			lBO.update(lDTO);
		
			
		}
		
		
		
		
	}
	
	//����Ҫ����һ��delete����cyslΪ0��order��list��ɾ����
	lBO.sqlUpdateOrDel("delete from list where cysl=0");
	oBO.sqlUpdateOrDel("delete from order where cysl=0");
	
	//��������Qsje ��������������Ϊ��������Ϊ�������뵽Data���У�
	DataBO dBO = new DataBO();
	DataDTO dDTO = new DataDTO();
	dDTO.setName("�ʽ����");
	dDTO.setShuju(qsje);
	dDTO.setDate(new Date());
	dDTO.setFlag1("��Ʊ"+mmflag+"������");
	dDTO.setFlag2(userIdStr);
	dBO.add(dDTO);
	

	
	
	
	
	
}

public static void main(String[] args)throws BSWException{
	JiluBO dBO=new JiluBO();
//	JiluDTO mdto = new JiluDTO();
//	mdto.setTbdw("ʡ�쳣�ݹ���");
//	mdto.setDiqu("����");
//	dBO.add(mdto);
	List list = dBO.tuplesQuery("from JiluDTO");
	System.out.println(list.size());
	
	

	
	
}

public void logicDelete(JiluDTO jiluDTO,String userIdStr) throws BSWException{
	 	String zqdm =jiluDTO.getZqdm();
	    String zqmc =jiluDTO.getZqmc();
	    String mmflag =jiluDTO.getMmflag();
	    //double cjjg =jiluDTO.getCjjg();
	    int cjsl =jiluDTO.getCjsl();
	    //double cjje =jiluDTO.getCjje();
	    double qsje = jiluDTO.getQsje();
	    String jifl =jiluDTO.getJifl(); 
			
	        

		//��order�в��ң����޸�zqdm�����û�У���ӡ�
		OrderBO oBO = new OrderBO();
		OrderDTO oDTO = new OrderDTO();
		
		ListBO lBO = new ListBO();
		ListDTO lDTO = new ListDTO();
		List listorder =oBO.sqlQuery("select id,zqdm,cysl,cbj from order where  flag1='"+userIdStr+"' and  zqdm='"+zqdm+"'");
		List listlist =lBO.sqlQuery("select id,zqdm,cysl,jifl from list where  flag1='"+userIdStr+"' and  zqdm='"+zqdm+"' and jifl='"+jifl+"'");
		
		//���order�����м�¼��
		if(listorder.size()==0){
			//����س���������������
			if(mmflag.equals("����")){
				
				
//				˵��û�иü�¼��ֱ����order��list�мӼ��ɣ�
				oDTO.setZqdm(zqdm);
				oDTO.setZqmc(zqmc);
				oDTO.setCysl(cjsl);
				//Ҫ���³ɱ��۵ġ�
				oDTO.setCbj(Math.abs(qsje)/cjsl);
				
				oBO.add(oDTO);
				//order���ǿյĻ���˵��list�п϶�Ϊ�գ�ֱ����Ӽ��ɣ�
				lDTO.setZqdm(zqdm);
				lDTO.setZqmc(zqmc);
				lDTO.setCysl(cjsl);
				lDTO.setJifl(jifl);
				lDTO.setFlag1(userIdStr);
				
				lBO.add(lDTO);
				
			}else{
				
				//����������� (mmflag.equals("����"))
				
				//��Ϊlist.size()==0��order��û�иù�Ʊ������
				throw new BSWException("�޷������á����롱���͵ļ�¼���ù�Ʊ�ֲ���Ϊ0��˵���Ѿ������ˣ�");
			}
			
			
		}else{
			//order���е������size>0������Ҫupdate2����
			if(mmflag.equals("����")){
				
				
//				˵���иü�¼��ֱ����orderUpdate;
				oDTO.setZqdm(zqdm);
				oDTO.setZqmc(zqmc);
				
				Iterator it = listorder.iterator();
				
				Map _map=null;
				Integer cysl=0;
				double cbj=0;
				Integer id=0;
				if(it.hasNext()){
					_map=(Map)it.next();
					id = (Integer)_map.get("ID");
					cysl = (Integer)_map.get("CYSL");//ԭ���ĳ�������
					
					cbj  =(Double)_map.get("CBJ");//ԭ���ĳɱ���
					//cjjes = (Double)_map.get("CJJES");
				}
				
				
//				Ҫ���µ��Ӻ�ĳ�����������
				oDTO.setId(id);
				oDTO.setCysl(Math.abs(cjsl)+cysl);
				
				//Ҫ����������������ӵĳɱ��۵ġ�
				oDTO.setCbj((Math.abs(qsje)+cysl*cbj)/(Math.abs(cjsl)+cysl));
				oDTO.setFlag1(userIdStr);
				
				oBO.update(oDTO);
				
				
				
				//list�з�2�������
				
				
				Iterator itlist = listlist.iterator();
//				һ����û�и�jifl��
				if(listlist.size()==0){
					//ֱ����Ӹý��׷����list���ݣ�
					lDTO.setZqdm(zqdm);
					lDTO.setZqmc(zqmc);
					lDTO.setCysl(Math.abs(cjsl));
					lDTO.setJifl(jifl);
					lDTO.setFlag1(userIdStr);
					lBO.add(lDTO);
					
				}else{
//					һ�����и�jifl��
					Map _maplist=null;
					Integer cysllist=0;
					Integer idList = 0;
					
					if(itlist.hasNext()){
						_maplist=(Map)itlist.next();
						idList = (Integer)_maplist.get("ID");
						cysllist = (Integer)_maplist.get("CYSL");//ԭ���ĳ�������
						
		
					}
					
					lDTO.setId(idList);
//					Ҫ���µ��Ӻ�ĳ�����������
					lDTO.setCysl(cysllist+Math.abs(cjsl));					
					
					lDTO.setZqdm(zqdm);
					lDTO.setZqmc(zqmc);
					lDTO.setJifl(jifl);	
					lDTO.setFlag1(userIdStr);
					lBO.update(lDTO);
				}
				
				
			}else{
				
//				order���е������size>0����
				//����������Ϊmmflag.equals("����")
				
				
				//��Ҫupdate2����
				//��Ϊ����ʱ��ͨ�����׷���ѡ����Զ������������������������Բ���Ҫ�жϡ�ֱ���������ɡ�
				//��update���ű�
				

//				˵���иü�¼��ֱ����orderUpdate;
				oDTO.setZqdm(zqdm);
				oDTO.setZqmc(zqmc);
				
				Iterator it = listorder.iterator();
				
				Map _map=null;
				Integer cysl=0;
				double cbj=0;
				Integer idOrder= 0;
				if(it.hasNext()){
					_map=(Map)it.next();
					idOrder =  (Integer)_map.get("ID");
					cysl = (Integer)_map.get("CYSL");//ԭ���ĳ�������
					
					cbj  =(Double)_map.get("CBJ");//ԭ���ĳɱ���
					//cjjes = (Double)_map.get("CJJES");
				}
				
				oDTO.setId(idOrder);
//				Ҫ������������Ӻ�ĳ�����������
				int cysltmp=0;
				cysltmp = cysl-Math.abs(cjsl);
				oDTO.setCysl(cysltmp);
				if(cysltmp==0){
					oDTO.setCbj(0);
				}else{
//					Ҫ����������ĵ��ӳɱ��۵ġ�
					oDTO.setCbj((cysl*cbj-Math.abs(qsje))/(cysl-Math.abs(cjsl)));
				}
				oDTO.setFlag1(userIdStr);
				oBO.update(oDTO);
				
				
				
				
				
				
				Iterator itlist = listlist.iterator();

//				�϶��и�jifl��
				Map _maplist=null;
				Integer cysllist=0;
				Integer idList=0;
				if(itlist.hasNext()){
					_maplist=(Map)itlist.next();
					idList = (Integer)_maplist.get("ID");
					cysllist = (Integer)_maplist.get("CYSL");//ԭ���ĳ�������
					

				}
				
				lDTO.setId(idList);
//				Ҫ���µ��Ӻ�ĳ�����������
				lDTO.setCysl(cysllist-Math.abs(cjsl));					
				//order���ǿյĻ���˵��list�п϶�Ϊ�գ�ֱ����Ӽ��ɣ�
				lDTO.setZqdm(zqdm);
				lDTO.setZqmc(zqmc);
				lDTO.setJifl(jifl);	
				lDTO.setFlag1(userIdStr);
				
				lBO.update(lDTO);
			
				
			}
			
			
			
			
		}
		
		//����Ҫ����һ��delete����cyslΪ0��order��list��ɾ����
		lBO.sqlUpdateOrDel("delete from list where cysl=0");
		oBO.sqlUpdateOrDel("delete from order where cysl=0");
		
		//��������Qsje ��������������Ϊ��������Ϊ�������뵽Data���У�
		DataBO dBO = new DataBO();
		DataDTO dDTO = new DataDTO();
		dDTO.setName("�ʽ����");
		dDTO.setShuju(-qsje);
		dDTO.setDate(new Date());
		dDTO.setFlag1("������Ʊ"+mmflag+"������");
		dDTO.setFlag2(userIdStr);
		dBO.add(dDTO);
		

		
	
}


}
