package com.fuguo.bo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import bsw.base.BaseBO;
import bsw.base.BaseDTO;
import bsw.fwk.PageRoll;
import bsw.tools.exception.BSWException;

import com.fuguo.dto.SunDTO;
import com.fuguo.po.SunPO;
import com.fuguo.util.DateUtil;

public class SunBO implements BaseBO{
SunPO sunPO = null;


/**
 * ��Ӷ���
 * @param weekPlan0DTO
 * @return
 * @throws BSWException
 */
public SunDTO add(SunDTO sunDTO) throws BSWException{
	
	sunPO=new SunPO();		
	
	return (SunDTO)sunPO.add(sunDTO);
}

/**
 * ��ѯĳ��¼����ϸ��Ϣ
 * @param sun0DTO
 * @return
 * @throws BSWException
 */
public SunDTO query(SunDTO sunDTO) throws BSWException{
	sunPO=new SunPO();
	return (SunDTO)sunPO.query(sunDTO);
}


/**
 * ���¶���
 * @param sun0DTO
 * @throws BSWException
 */
public void update(SunDTO sunDTO) throws BSWException{	
	sunPO=new SunPO();
	sunPO.update(sunDTO);
}


/**
 * ɾ��һ������
 * @param sunDTO
 * @throws BSWException
 */
public void delete(SunDTO sunDTO) throws BSWException{
	sunPO=new SunPO();
	sunPO.delete(sunDTO);
}


/**
 * ɾ���������
 * @param sunDTOS
 * @throws BSWException
 */
public void delete(SunDTO[] sunDTOS) throws BSWException{
	sunPO=new SunPO();
	sunPO.delete(sunDTOS);
}
/**
 * ����������Ϣ
 * @return
 * @throws BSWException
 */
public SunDTO[] loadAll(String _hql)throws BSWException{	
	sunPO=new SunPO();	
	BaseDTO[] baseDTOS=(BaseDTO[])sunPO.loadAll(_hql);//Ϊʲô������ֱ�ӷ��뵽SunDTOS���أ���Ϊ��֧������Ķ�̬��û�������̬������壩
	SunDTO[] sunDTOS=new SunDTO[baseDTOS.length];
	for(int i=0;i<sunDTOS.length;i++){
		sunDTOS[i]=new SunDTO();
		sunDTOS[i]=(SunDTO)baseDTOS[i];
	}
	return sunDTOS;
}
/**
 * ��ҳ��ʾ�����Ϣ
 * 
 * @return
 * @throws BSWException 
 */
public SunDTO[] search(PageRoll pageRoll) {
//	String hql = "from SunDTO";
//	pageRoll.setWhereClause(hql);
	//����ÿҳ��ʾ��¼����
	pageRoll.setPageSize(8);
	SunPO sunPO = new SunPO();
	BaseDTO[] baseDTO = sunPO.search(pageRoll);
	SunDTO[] sunDTO = null;
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
	sunDTO = new SunDTO[baseDTO.length];
	for (int i = 0; i < sunDTO.length; i++) {
		sunDTO[i] = (SunDTO) baseDTO[i];
		
		
		
//		jhkssj=sunDTO[i].getJhkssj();		
//		jhjssj=sunDTO[i].getJhjssj();
//		
//		jhkssjStr=sdf.format(jhkssj);
//		jhjssjStr=sdf.format(jhjssj);
//		
//		jhkssjWeek = dateUtil.getWeekOfDate(jhkssj);
//		jhjssjWeek = dateUtil.getWeekOfDate(jhjssj);
//		
//		jhkssjStr=jhkssjStr.replace(" ","<br>");
//		jhjssjStr=jhjssjStr.replace(" ","<br>");
//		//���뵽tdto��
//		sunDTO[i].setJhkssjStr(jhkssjStr+jhkssjWeek);
//		sunDTO[i].setJhjssjStr(jhjssjStr+jhjssjWeek);
//		
//		if(sunDTO[i].getGzjgorxl()==null){
//			sunDTO[i].setGzjgorxl("");
//		}
//		sunDTO[i].setGzjgorxl(sunDTO[i].getGzjgorxl().replace("\r\n","<br>"));
//		sunDTO[i].setGznr(sunDTO[i].getGznr().replace("\r\n","<br>"));
//		sunDTO[i].setGzjgorxl(sunDTO[i].getGzjgorxl().replace("\n","<br>"));
//		sunDTO[i].setGznr(sunDTO[i].getGznr().replace("\n","<br>"));
////		�Ե�ѹ�ȼ�����ȥ��kV��
//		if(sunDTO[i].getDydj()==null){
//			sunDTO[i].setDydj("");
//		}
//		sunDTO[i].setDydj(sunDTO[i].getDydj().replace("kV",""));
//		if(sunDTO[i].getGzly()==null){
//			sunDTO[i].setGzly("");
//		}
//		sunDTO[i].setGzly(sunDTO[i].getGzly().replace(";","<br>"));
//		if(sunDTO[i].getGzbz()!=null){
//			sunDTO[i].setGzbz(sunDTO[i].getGzbz().replace(";","<br>"));
//		}
////		�Թ������� ����ƴ�ӱ��վ��
//		if(sunDTO[i].getGzcs()==null){
//			sunDTO[i].setGzcs("");
//		}
//		tmpgzcs=sunDTO[i].getGzcs();
//		bdz=sunDTO[i].getBdz();
//		if(tmpgzcs.equals("��·")){
//			gzcs = tmpgzcs;
//		}else{
//			gzcs = bdz;
//		}
//
//		sunDTO[i].setGzcs(gzcs);
//		
//		
//		//�ǲ��Ǳ���λ�����ж�
//		flag1=sunDTO[i].getFlag1();
//		if(flag1==null){
//			
//		}else if(flag1.equals("��")){			
//		}else{
//			
//			sunDTO[i].setGzbz(sunDTO[i].getSgdw());
//		}
		
		
		
		
	}
	return sunDTO;
}


/**
 * �����Ҫ���ϲ�ѯ��ʱ�򣬿��Խ��÷������뵽ҵ�����У�
 * @param hql
 * @return
 * @throws BSWException
 */
public List tuplesQuery(String hql) throws BSWException {
	sunPO=new SunPO();
	List list = sunPO.tuplesQuery(hql);
	
	
	
	return list;
}





public List sqlQuery(String sql,Class classArg) throws BSWException {
	sunPO=new SunPO();
	List list =sunPO.sqlQuery(sql,classArg);
	
	return list;
}

public static void main(String[] args)throws BSWException{
	SunBO dBO=new SunBO();
//	SunDTO mdto = new SunDTO();
//	mdto.setTbdw("ʡ�쳣�ݹ���");
//	mdto.setDiqu("����");
//	dBO.add(mdto);
	List list = dBO.tuplesQuery("from SunDTO");
	System.out.println(list.size());
	
	

	
	
}


}
