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
 * 添加对象
 * @param weekPlan0DTO
 * @return
 * @throws BSWException
 */
public SunDTO add(SunDTO sunDTO) throws BSWException{
	
	sunPO=new SunPO();		
	
	return (SunDTO)sunPO.add(sunDTO);
}

/**
 * 查询某记录的详细信息
 * @param sun0DTO
 * @return
 * @throws BSWException
 */
public SunDTO query(SunDTO sunDTO) throws BSWException{
	sunPO=new SunPO();
	return (SunDTO)sunPO.query(sunDTO);
}


/**
 * 更新对象
 * @param sun0DTO
 * @throws BSWException
 */
public void update(SunDTO sunDTO) throws BSWException{	
	sunPO=new SunPO();
	sunPO.update(sunDTO);
}


/**
 * 删除一个对象
 * @param sunDTO
 * @throws BSWException
 */
public void delete(SunDTO sunDTO) throws BSWException{
	sunPO=new SunPO();
	sunPO.delete(sunDTO);
}


/**
 * 删除多个对象
 * @param sunDTOS
 * @throws BSWException
 */
public void delete(SunDTO[] sunDTOS) throws BSWException{
	sunPO=new SunPO();
	sunPO.delete(sunDTOS);
}
/**
 * 查找所有信息
 * @return
 * @throws BSWException
 */
public SunDTO[] loadAll(String _hql)throws BSWException{	
	sunPO=new SunPO();	
	BaseDTO[] baseDTOS=(BaseDTO[])sunPO.loadAll(_hql);//为什么不可以直接放入到SunDTOS中呢？因为不支持数组的多态（没有数组多态这个定义）
	SunDTO[] sunDTOS=new SunDTO[baseDTOS.length];
	for(int i=0;i<sunDTOS.length;i++){
		sunDTOS[i]=new SunDTO();
		sunDTOS[i]=(SunDTO)baseDTOS[i];
	}
	return sunDTOS;
}
/**
 * 分页显示相关信息
 * 
 * @return
 * @throws BSWException 
 */
public SunDTO[] search(PageRoll pageRoll) {
//	String hql = "from SunDTO";
//	pageRoll.setWhereClause(hql);
	//设置每页显示记录条数
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
//		//放入到tdto中
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
////		对电压等级处理（去掉kV）
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
////		对工作场所 处理（拼接变电站）
//		if(sunDTO[i].getGzcs()==null){
//			sunDTO[i].setGzcs("");
//		}
//		tmpgzcs=sunDTO[i].getGzcs();
//		bdz=sunDTO[i].getBdz();
//		if(tmpgzcs.equals("线路")){
//			gzcs = tmpgzcs;
//		}else{
//			gzcs = bdz;
//		}
//
//		sunDTO[i].setGzcs(gzcs);
//		
//		
//		//是不是本单位进行判断
//		flag1=sunDTO[i].getFlag1();
//		if(flag1==null){
//			
//		}else if(flag1.equals("是")){			
//		}else{
//			
//			sunDTO[i].setGzbz(sunDTO[i].getSgdw());
//		}
		
		
		
		
	}
	return sunDTO;
}


/**
 * 如果需要联合查询的时候，可以将该方法放入到业务类中；
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
//	mdto.setTbdw("省检常州工区");
//	mdto.setDiqu("常州");
//	dBO.add(mdto);
	List list = dBO.tuplesQuery("from SunDTO");
	System.out.println(list.size());
	
	

	
	
}


}
