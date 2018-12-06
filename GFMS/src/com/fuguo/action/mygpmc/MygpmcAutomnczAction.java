//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package com.fuguo.action.mygpmc;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import bsw.base.BaseAction;
import bsw.fwk.BaseUserContext;
import bsw.tools.exception.BSWException;

import com.fuguo.bo.DataBO;
import com.fuguo.bo.JiluBO;
import com.fuguo.bo.LsjgBO;
import com.fuguo.bo.LsjgdateBO;
import com.fuguo.bo.LxBO;
import com.fuguo.bo.MygpmcBO;
import com.fuguo.dto.JiluDTO;
import com.fuguo.dto.LsjgDTO;
import com.fuguo.dto.LsjgdateDTO;
import com.fuguo.dto.LxDTO;
import com.fuguo.dto.MygpmcDTO;
import com.fuguo.form.Query_Of_AllForm;
import com.fuguo.util.DateUtil;
import com.fuguo.util.MathUtil;
import com.fuguo.util.StockUtil;

/** 
 * MyEclipse Struts
 * Creation date: 03-21-2008
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class MygpmcAutomnczAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		Query_Of_AllForm qForm = (Query_Of_AllForm)form;
//		��ȡids ��ת��Ϊint��
		String[] ids_Str=request.getParameterValues("ids");
		if(ids_Str==null){
			throw new BSWException("��û��ѡ���κ���ѡ�ɣ�");
		}
		String _lx = qForm.getLx().trim();//�������ͣ��õ������ߣ����ߣ�
		String[] lx=null;
		if(_lx==null||_lx.equals("")){
			
		}else{
			lx = _lx.split(";");
		}
		BaseUserContext baseUserContext = (BaseUserContext) request.getSession().getAttribute("#BASEUSERCONTEXT#");
		String xm = baseUserContext.getXm();
		
		
		DataBO dataBO  = new DataBO();
		
		StockUtil sUtil = new StockUtil();
		JiluBO jBO = new JiluBO();
		//ֻ�������˺Ų������д�ģ�飻
		if(!baseUserContext.getWx().equals("����")){
			throw new BSWException("���˺�Ϊ�������˺ţ���Ϊ��ɾ���������ݣ������޷��������������");
		}else{
			//���������˺ŵ�������գ�
			

			jBO.clearAllData(baseUserContext);
		}
		
		
		
		int idUser  =baseUserContext.getId();
		String idStr  =Integer.toString(idUser);
		String zjzh = baseUserContext.getNx();
		//����lx�����ƣ���ȡ�����͵��Զ�ģ��������ߣ�
		
		LxBO lBO = new LxBO();
		StringBuffer sb=new StringBuffer();
		sb.append("from LxDTO where (flag1='' or flag1='"+idStr+"') ");
		if(lx==null){
			//����
		}else{
			for(int i=0;i<lx.length;i++){
				if(i==0){
					sb.append(" and (name='"+lx[i]+"'");
				}else{
					sb.append(" or name='"+lx[i]+"'");
				}	
			}
		sb.append(")");
		}
		LxDTO[] lxDTOs = lBO.loadAll(sb.toString());
		//ͨ��name �õ����߲������������index��
		String flag2="";
		int maNumb=0;
		int MaxmaNumb=0;
		for(int i=0;i<lxDTOs.length;i++){
			flag2 = lxDTOs[i].getFlag2();
			flag2 = flag2.replace("MA","");
			flag2 = flag2.replace("ma","");
			
			maNumb = Integer.parseInt(flag2);
			lxDTOs[i].setMaNumb(maNumb);
			if(maNumb>MaxmaNumb){
				MaxmaNumb = maNumb;
			}
			lxDTOs[i].setArgsIndex(i);
		}
		
		
		
		Date   start_rqUtilDate=null;
		Date   end_rqUtilDate=null;
		
		
		//�����չ����������
		String _start_rq =qForm.getStart_rq().trim();
		String _end_rq = qForm.getEnd_rq().trim();
		SimpleDateFormat   sdfymd   =   new   SimpleDateFormat("yyyy-MM-dd");
		if(_start_rq.trim().equals("")||_end_rq.trim().equals("")){
			
		}else{
//			������ת��Ϊutil.Date����
 
			start_rqUtilDate   =   sdfymd.parse(_start_rq);
			
			end_rqUtilDate   =   sdfymd.parse(_end_rq);
		}
		//��ȡstart_rqUtilDateǰ��ģ�MaxmaNumb+10��*1.4 �����������ڣ�
		int dayNumb = (((MaxmaNumb+10)*14)/10);
		DateUtil dUtil = new DateUtil();
		start_rqUtilDate =dUtil.getBeforeNDay(start_rqUtilDate,dayNumb);//���¸�ֵ��start_rqUtilDate
		
		java.sql.Date start_rq = new java.sql.Date(start_rqUtilDate.getTime()); 
		
		
		java.sql.Date last_rq = new java.sql.Date(end_rqUtilDate.getTime());
		
		int[] ids=new int[ids_Str.length];
		for(int j=0;j<ids_Str.length;j++){
			ids[j] = Integer.parseInt(ids_Str[j]);
		}
		MygpmcBO tBO =new MygpmcBO();
		LsjgBO lsjgBO = new LsjgBO();
		//ѭ������ÿһ��id
		MygpmcDTO tDTO;
		//��ȡ��id��֤ȯ���룻
		String zqdm="";
		String zqmc="";
		String jysmc="";
		LsjgDTO[] lsjgDTOs=null;
		
		String defaultmoneyStr = (String)baseUserContext.getSystemconfig().get("defaultmoney");
		Double KYZJ = Double.parseDouble(defaultmoneyStr);//�����ʽ�ı�ݻ�ȡ��������Ϊ��ʼ�����û����ݣ�defaultmoney=KYZJ;
		
		//Double KYZJ =dataBO.getKYZJ(idStr);
		Double ONEKYZJ = KYZJ/(ids_Str.length*lxDTOs.length);  //�����ֻ��Ʊ���������׷������ʹ�õ��ʽ�
		
		//��������ÿֻ��Ʊ��������ѭ����ʱ�����ã�//ȷ�еĽ���Ӧ��ÿֻ��Ʊ��ÿ������ѭ����ʱ�����ã�
		Double tmpONESYZJ = ONEKYZJ;//û������ʱ����ֻ��Ʊ��Ĭ�Ͽ����ʽ�Ϊƽ��������ʽ�
		
		int tmpONESYGUPIAONUMBER=0;//û������ʱ����ֻ��Ʊ��ʣ���Ʊ��Ĭ��Ϊ0��
		
		for(int i=0;i<ids.length;i++ ){
			
			Date  indexUtilDate    =     sdfymd.parse(_start_rq);
			int INDEX=-1;//��Ż�ȡ�����ڵ�index��
			
			//��ÿһ��id���д���		
			//��һ���������id����������¼��			
			tDTO=new MygpmcDTO();
			tDTO.setId(ids[i]);
			tDTO = tBO.query(tDTO);			
			zqdm = tDTO.getZqdm();
			//StockUtil sUtil = new StockUtil();
//			�ж��Ƿ��ǻ�������ǻ�������ӡ��˰��
			boolean isJiJin = sUtil.isJiJin(zqdm);
			zqmc = tDTO.getZqmc();
			jysmc = tDTO.getFlag1();
			//ͨ����֤ȯ���룬����lsjgDate�еĿ�ʼ���ڣ��ͽ������ڣ�
			LsjgdateDTO lsjgdateDTO=new LsjgdateDTO();
			LsjgdateBO lsjgdateBO = new LsjgdateBO();
			
			
			lsjgdateDTO.setId(zqdm);
			lsjgdateDTO = lsjgdateBO.query(lsjgdateDTO);
			
//			��Ҫ�ж���û�и�lsjgDAte�����û�У���ʾ��ӣ�
			Date oldStartDate=null;
			Date oldEndDate=null;
			if(lsjgdateDTO==null){
				throw new BSWException("�ù�Ʊ("+zqdm+")��ʷ�۸���δ��ӣ��뵽����ʷ�۸����ڸ���������˵�������������ݣ�");
			}else{
			
//				�Զ���ȡ��֤ȯ������lsjgDate���д�ŵĿ�ʼ���ںͽ�ֹ����
				oldStartDate=lsjgdateDTO.getDatestart();
				oldEndDate=lsjgdateDTO.getDate();
			}
			
			if(!(start_rqUtilDate.getTime()>=oldStartDate.getTime() && end_rqUtilDate.getTime()<=oldEndDate.getTime())){
//				����ԭ���ķ�Χ�ڣ���oldStartDate��end_rqUtilDate�� 
				throw new BSWException("�ù�Ʊ("+zqdm+")��ʷ�۸� ��ʱ���("+start_rq+"~"+last_rq+")�Ĺ�Ʊ��ʷ�۸񲿷ֻ�û����ӣ���������"+last_rq+"�Ƿ��Ѿ�����أ������뵽����ʷ�۸����ڸ���������˵������ȱʧ���ֵ��������ݣ�"); 
			}
			
			//��������Ҫ�����������Ƿ���lsjgdate�����ڷ�Χ�ڣ�
			
			
			
			
			//������ڣ���ʾ������ʷ�۸����ں���ģ�⣻[��һ��]
			
			//Ĭ���Ѿ����ص������
			lsjgDTOs =lsjgBO.loadAll("from LsjgDTO lsjgDTO where lsjgDTO.zqdm='"+zqdm+"' and  lsjgDTO.flag1!='tmp' and ( date(lsjgDTO.date)>='"+start_rq+"' and date(lsjgDTO.date)<='"+last_rq+"') order by lsjgDTO.date");
			//System.out.println(zqdm+"���ص���ʷ�۸�lsjgDTOs�ĳ���Ϊ��"+lsjgDTOs.length);
			//�˴�Ӧ�û�ȡ���һ��DTO�ĸ�Ȩ���ӣ�
			//�˴�Ӧ�õ���һ��������double fqyzNow  =lsjgBO.getMaxFqyz(zqdm,start_rqUtilDate);
			int dtoLength = lsjgDTOs.length;
			//double maxFqyzNow = lsjgDTOs[dtoLength-1].getFqyz();//����׼ȷ��Ӧ����ϵͳ��ǰ���ڵĸ�Ȩ���ӣ�
			double maxFqyzNow = lsjgBO.getMaxFqyz(zqdm,start_rqUtilDate);//������׼ȷ
			double fqyz=0.0d;
			double close=0.0d;
			double tmpAdjclose;
			for(int y=0;y<dtoLength;y++){	
				fqyz = lsjgDTOs[y]	.getFqyz();	
				close = lsjgDTOs[y]	.getClose();
				tmpAdjclose = (close/maxFqyzNow)*fqyz;
				lsjgDTOs[y]	.setAdjclose(tmpAdjclose);
			}
			//�ҳ���lsjgDTOs�е��׸����ڵ���indexUtilDate��index��
			for(int k=0;k<dtoLength;k++){	
				if(lsjgDTOs[k].getDate().getTime()>=indexUtilDate.getTime()){
					INDEX=k;//��ȡk��ֵ������INDEX;
					break;
				}				
			}
			
			double maDouble[][] = new double[lxDTOs.length][lsjgDTOs.length];
			
			//����lxDTOs���ó�maDouble[0] Ӧ�÷�ʲô��
			//��ȡlxDTOs[i].getArgsIndex()==0 ...2��ma��ֵ��120,20,60;//���㲻���Ⱥ�
			//maDouble[0]��120��maDouble[1]��20��maDouble[2]��60
			
			MathUtil mathUtil = new MathUtil();
			String name="";
			
			
			for(int j=0;j<lxDTOs.length;j++){
				
//				ȷ�еĽ���Ӧ��ÿֻ��Ʊ��ÿ������ѭ����ʱ�����ã�
				tmpONESYZJ = ONEKYZJ;//û������ʱ����ֻ��Ʊ��Ĭ�Ͽ����ʽ�Ϊƽ��������ʽ�				
				tmpONESYGUPIAONUMBER=0;//û������ʱ����ֻ��Ʊ��ʣ���Ʊ��Ĭ��Ϊ0��
				
				name = lxDTOs[j].getName();//�����������ƣ�
				
				maNumb = lxDTOs[j].getMaNumb();//��ǰlxDTOs[i].getArgsIndex()==i ��ma��ֵ120;��
				
				//�г��߼���ϵ��K �Ļ�ȡ��
				double k=mathUtil.getJianCangXishu(maNumb);
				int tmpCANGWEIFlag= 0;//0���ղ֣�1�����ֲ֣�2�����������С�֣�3�����֣� Ĭ��Ϊ�ղ�0��
				
				
				//maDouble[j]=mathUtil.mathMa(lsjgDTOs,"close",maNumb);//������õ������̼�������뵽maDouble[j]�У�
				maDouble[j]=mathUtil.mathMa(lsjgDTOs,"adjclose",maNumb);//������õ���ǰ��Ȩ��������뵽maDouble[j]�У�
				//�˴�Ӧ�ý���ģ������������
				
				
				//int stepNumbs = 0;//Ĭ��һ������Ĺ�Ʊ����
				JiluDTO jiluDTO=null;
				//��Ҫ��INDEX+2 ��ʼ�жϣ�
				for(int h=INDEX+2;h<maDouble[j].length;h++){
//					�жϴ����͵ľ����Ƿ��Ϲգ���ƽ���Ϲգ��ɵ����Ƕ��У��� ���̼��Ƿ��ھ������棿
					if(tmpONESYGUPIAONUMBER==0){
						if(maDouble[j][h-2]!=0d&&maDouble[j][h-2]>=maDouble[j][h-1] && maDouble[j][h-1]<maDouble[j][h] && lsjgDTOs[h].getAdjclose()>maDouble[j][h]){
							
//							System.out.println(zqmc+name+",�������ڣ�"+lsjgDTOs[h].getDate());
//							System.out.println(zqmc+name+",���ָ��ֵ"+maDouble[j][h-2]);
//							System.out.println(zqmc+name+",���ָ��ֵ"+maDouble[j][h-1]);
//							System.out.println(zqmc+name+",���ָ��ֵ"+maDouble[j][h]);
//							System.out.println(zqmc+name+",�������̼�"+ lsjgDTOs[h].getClose());
//							����ǣ��Ǿ����룻
							
							//�������̼ۣ����������������������뵽stepNumbs;							
							//System.out.println(zqmc+name+",����ǰ�����ʽ�"+tmpONESYZJ);
							
							double lsgs = tmpONESYZJ/lsjgDTOs[h].getAdjclose();
							//System.out.println("���������ɢ������"+lsgs);
							
							tmpONESYGUPIAONUMBER = ( new Double( lsgs/100 ).intValue() )*100;
							
							//System.out.println("������Ĺ�����δ����Ӷ�𣩣�"+tmpONESYGUPIAONUMBER);
							//ͨ��lsjgDTOs����jiluDTO
							jiluDTO = new JiluDTO();
							
							jiluDTO.setTimeid(Long.toString(lsjgDTOs[h].getDate().getTime()));					        
					        jiluDTO.setJysj(lsjgDTOs[h].getDate());
					        jiluDTO.setZqdm(zqdm);
					        jiluDTO.setZqmc(zqmc);
					        jiluDTO.setMmflag("����");
					        jiluDTO.setCjjg(lsjgDTOs[h].getAdjclose());
					        
					        jiluDTO.setCjsl(tmpONESYGUPIAONUMBER);
					        jiluDTO.setCjje(lsjgDTOs[h].getAdjclose()*tmpONESYGUPIAONUMBER);
					        
					        Double qsje=sUtil.getQsje(idStr,jiluDTO.getCjje(),jysmc,jiluDTO.getMmflag(),isJiJin);//ʵ������ʱ�Ļ���������ʵ������ʱ�Ļ�����
							
					        if(tmpONESYZJ - Math.abs(qsje)<0){
					        	tmpONESYGUPIAONUMBER=tmpONESYGUPIAONUMBER-100;
					        	
					        	jiluDTO.setCjsl(tmpONESYGUPIAONUMBER);//�����趨�µ�����������
					        	jiluDTO.setCjje(lsjgDTOs[h].getAdjclose()*tmpONESYGUPIAONUMBER);//�����趨�µĳɽ���
						        
						        qsje=sUtil.getQsje(idStr,jiluDTO.getCjje(),jysmc,jiluDTO.getMmflag(),isJiJin);//�����趨ʵ������ʱ�Ļ�����
						        
						        //tmpONESYZJ = tmpONESYZJ - Math.abs(qsje);
					        }
					        
					        tmpONESYZJ = tmpONESYZJ - Math.abs(qsje);
					        
					        //System.out.println("ʵ�ʿ�����Ĺ������������гɱ�����"+tmpONESYGUPIAONUMBER);
					        
					    	//System.out.println(zqmc+name+",ʵ�����������ʽ�"+tmpONESYZJ);
							
//							if(mmflag.equals("����")){
//								
//								cjsl=(cjsl*(-1));
//							}
					        jiluDTO.setQsje(qsje);					        
					        jiluDTO.setYwmc("");//û���õĿ��ֶ�
					        jiluDTO.setZjzh(zjzh);
					        jiluDTO.setJysmc(jysmc);
					        jiluDTO.setKhdm(idStr);
					        jiluDTO.setGdmc(xm);
					        jiluDTO.setBz("�Զ����⽻��");
					        jiluDTO.setJifl(name);					        
					        jiluDTO.setFlag1("�Ѵ���");
					        jiluDTO.setFlag2("");
					        jiluDTO.setFlag3("");
					        jiluDTO.setFlag4("");
					        jiluDTO.setFlag5("");
					        jiluDTO.setFlag6("");
					        jiluDTO.setFlag7("");
					        jiluDTO.setFlag8("");
					        jiluDTO.setFlag9("");
					        jiluDTO.setFlag10("");
							//������������
					        //�˴��϶����Ѵ����߼��������Ӽ�¼ͬʱ���У�
							
					        jBO.logic(jiluDTO,idStr,baseUserContext,true);//��¼û�д��ڵ�����£�Ҫ��ӣ�Ϊtrue ,��lsjgdate�Ǽǣ�
					        //tmpONESYGUPIAONUMBER = tmpONESYGUPIAONUMBER;
					        tmpCANGWEIFlag= 3;//������λΪ3���֣�
						}else if(maDouble[j][h-1]!=0d&&maDouble[j][h-1]<maDouble[j][h] &&(lsjgDTOs[h-1].getAdjclose()<=maDouble[j][h-1] || lsjgDTOs[h-1].getLow()<=maDouble[j][h-1])&& lsjgDTOs[h].getAdjclose()>maDouble[j][h]){
							//�����ǳ������ϵ�,����ǰһ������̼ۻ�����ͼ�С�ڵ���ǰһ��ľ��� ���ҵ�ǰ������̼۸��ڵ�ǰ��ľ���
							//����С��λ���루�ڶ���㣩��
							double lsgs = (tmpONESYZJ/lsjgDTOs[h].getAdjclose())*0.5;
							//System.out.println("���������ɢ������"+lsgs);
							
							tmpONESYGUPIAONUMBER = ( new Double( lsgs/100 ).intValue() )*100;
							
							//System.out.println("������Ĺ�����δ����Ӷ�𣩣�"+tmpONESYGUPIAONUMBER);
							//ͨ��lsjgDTOs����jiluDTO
							jiluDTO = new JiluDTO();
							
							jiluDTO.setTimeid(Long.toString(lsjgDTOs[h].getDate().getTime()));					        
					        jiluDTO.setJysj(lsjgDTOs[h].getDate());
					        jiluDTO.setZqdm(zqdm);
					        jiluDTO.setZqmc(zqmc);
					        jiluDTO.setMmflag("����");
					        jiluDTO.setCjjg(lsjgDTOs[h].getAdjclose());
					        
					        jiluDTO.setCjsl(tmpONESYGUPIAONUMBER);
					        jiluDTO.setCjje(lsjgDTOs[h].getAdjclose()*tmpONESYGUPIAONUMBER);
					        
					        Double qsje=sUtil.getQsje(idStr,jiluDTO.getCjje(),jysmc,jiluDTO.getMmflag(),isJiJin);//ʵ������ʱ�Ļ���������ʵ������ʱ�Ļ�����
							
					        if(tmpONESYZJ - Math.abs(qsje)<0){
					        	tmpONESYGUPIAONUMBER=tmpONESYGUPIAONUMBER-100;
					        	
					        	jiluDTO.setCjsl(tmpONESYGUPIAONUMBER);//�����趨�µ�����������
					        	jiluDTO.setCjje(lsjgDTOs[h].getAdjclose()*tmpONESYGUPIAONUMBER);//�����趨�µĳɽ���
						        
						        qsje=sUtil.getQsje(idStr,jiluDTO.getCjje(),jysmc,jiluDTO.getMmflag(),isJiJin);//�����趨ʵ������ʱ�Ļ�����
						        
						        //tmpONESYZJ = tmpONESYZJ - Math.abs(qsje);
					        }
					        
					        tmpONESYZJ = tmpONESYZJ - Math.abs(qsje);
					        
					        //System.out.println("ʵ�ʿ�����Ĺ������������гɱ�����"+tmpONESYGUPIAONUMBER);
					        
					    	//System.out.println(zqmc+name+",ʵ�����������ʽ�"+tmpONESYZJ);
							
//							if(mmflag.equals("����")){
//								
//								cjsl=(cjsl*(-1));
//							}
					        jiluDTO.setQsje(qsje);					        
					        jiluDTO.setYwmc("");//û���õĿ��ֶ�
					        jiluDTO.setZjzh(zjzh);
					        jiluDTO.setJysmc(jysmc);
					        jiluDTO.setKhdm(idStr);
					        jiluDTO.setGdmc(xm);
					        jiluDTO.setBz("�Զ����⽻��");
					        jiluDTO.setJifl(name);
					        jiluDTO.setFlag1("�Ѵ���");
					        jiluDTO.setFlag2("");
					        jiluDTO.setFlag3("");
					        jiluDTO.setFlag4("");
					        jiluDTO.setFlag5("");
					        jiluDTO.setFlag6("");
					        jiluDTO.setFlag7("");
					        jiluDTO.setFlag8("");
					        jiluDTO.setFlag9("");
					        jiluDTO.setFlag10("");
							//������������
					        //�˴��϶����Ѵ����߼��������Ӽ�¼ͬʱ���У�
							
					        jBO.logic(jiluDTO,idStr,baseUserContext,true);//��¼û�д��ڵ�����£�Ҫ��ӣ�Ϊtrue ,��lsjgdate�Ǽǣ�
					        //tmpONESYGUPIAONUMBER = tmpONESYGUPIAONUMBER;
					        tmpCANGWEIFlag= 2;//������λΪ2 С��λ��							
						}						
					}else{
						//tmpONESYGUPIAONUMBER==N�������
						
						if(maDouble[j][h-1]>maDouble[j][h] && lsjgDTOs[h].getAdjclose()<maDouble[j][h]){
//							����ǣ��Ǿ�������������С��λtmpCANGWEIFlag2�����ǰ��tmpCANGWEIFlag1����������tmpCANGWEIFlag3��һ�����tmpCANGWEIFlag0
//							����DTO
							
							//System.out.println(zqmc+name+",����ǰ�����ʽ�"+tmpONESYZJ);
//							ͨ��lsjgDTOs����jiluDTO
							jiluDTO = new JiluDTO();
							
							jiluDTO.setTimeid(Long.toString(lsjgDTOs[h].getDate().getTime()));					        
					        jiluDTO.setJysj(lsjgDTOs[h].getDate());
					        jiluDTO.setZqdm(zqdm);
					        jiluDTO.setZqmc(zqmc);
					        jiluDTO.setMmflag("����");
					        jiluDTO.setCjjg(lsjgDTOs[h].getAdjclose());
					        jiluDTO.setCjsl(tmpONESYGUPIAONUMBER*(-1));
					        jiluDTO.setCjje(lsjgDTOs[h].getAdjclose()*tmpONESYGUPIAONUMBER);
					        
					        Double qsje=sUtil.getQsje(idStr,jiluDTO.getCjje(),jysmc,jiluDTO.getMmflag(),isJiJin);//ʵ������ʱ�Ļ���������ʵ������ʱ�Ļ�����
							
					        tmpONESYZJ = tmpONESYZJ + Math.abs(qsje);
					        
					        //System.out.println(zqmc+name+",����������ʽ�"+tmpONESYZJ);
//							
//							if(jiluDTO.getMmflag().equals("����")){
//								
//								cjsl=(cjsl*(-1));
//							}
					        jiluDTO.setQsje(qsje);					        
					        jiluDTO.setYwmc("");//û���õĿ��ֶ�
					        jiluDTO.setZjzh(zjzh);
					        jiluDTO.setJysmc(jysmc);
					        jiluDTO.setKhdm(idStr);
					        jiluDTO.setGdmc(xm);
					        jiluDTO.setBz("�Զ����⽻��");
					        jiluDTO.setJifl(name);
					        jiluDTO.setFlag1("�Ѵ���");
					        jiluDTO.setFlag2("");
					        jiluDTO.setFlag3("");
					        jiluDTO.setFlag4("");
					        jiluDTO.setFlag5("");
					        jiluDTO.setFlag6("");
					        jiluDTO.setFlag7("");
					        jiluDTO.setFlag8("");
					        jiluDTO.setFlag9("");
					        jiluDTO.setFlag10("");
					        
//					      ������������
					        //�˴��϶����Ѵ����߼��������Ӽ�¼ͬʱ���У�
							
					        jBO.logic(jiluDTO,idStr,baseUserContext,true);//��¼û�д��ڵ�����£�Ҫ��ӣ�Ϊtrue ,��lsjgdate�Ǽǣ�
					        
					        tmpONESYGUPIAONUMBER=0;
					        
					        tmpCANGWEIFlag= 0;//������Ϊ�ղ֣�
						}else if( (tmpCANGWEIFlag==2||tmpCANGWEIFlag==3) && maDouble[j][h-1]<=maDouble[j][h] && lsjgDTOs[h].getAdjclose()<maDouble[j][h]){
//							����ǣ��Ǿ��������ֲ�λ��
//							����DTO
							if(k!=0d){
								//�����С��֣���tmpCANGWEIFlag= 2;Ҳ�ǰ�����������
								int maichuGupiaonumber= ( new Double( (k*tmpONESYGUPIAONUMBER)/100 ).intValue() )*100;//������Ʊ������
								tmpONESYGUPIAONUMBER =tmpONESYGUPIAONUMBER -maichuGupiaonumber;//ʣ���Ʊ������
								//System.out.println(zqmc+name+",����ǰ�����ʽ�"+tmpONESYZJ);
//								ͨ��lsjgDTOs����jiluDTO
								jiluDTO = new JiluDTO();
								
								jiluDTO.setTimeid(Long.toString(lsjgDTOs[h].getDate().getTime()));					        
						        jiluDTO.setJysj(lsjgDTOs[h].getDate());
						        jiluDTO.setZqdm(zqdm);
						        jiluDTO.setZqmc(zqmc);
						        jiluDTO.setMmflag("����");
						        jiluDTO.setCjjg(lsjgDTOs[h].getAdjclose());
						        jiluDTO.setCjsl(maichuGupiaonumber*(-1));
						        jiluDTO.setCjje(lsjgDTOs[h].getAdjclose()*maichuGupiaonumber);
						        
						        Double qsje=sUtil.getQsje(idStr,jiluDTO.getCjje(),jysmc,jiluDTO.getMmflag(),isJiJin);//ʵ������ʱ�Ļ���������ʵ������ʱ�Ļ�����
								
						        tmpONESYZJ = tmpONESYZJ + Math.abs(qsje);
						        
						        //System.out.println(zqmc+name+",����������ʽ�"+tmpONESYZJ);
//								
//								if(jiluDTO.getMmflag().equals("����")){
//									
//									cjsl=(cjsl*(-1));
//								}
						        jiluDTO.setQsje(qsje);					        
						        jiluDTO.setYwmc("");//û���õĿ��ֶ�
						        jiluDTO.setZjzh(zjzh);
						        jiluDTO.setJysmc(jysmc);
						        jiluDTO.setKhdm(idStr);
						        jiluDTO.setGdmc(xm);
						        jiluDTO.setBz("�Զ����⽻��");
						        jiluDTO.setJifl(name);						        
						        jiluDTO.setFlag1("�Ѵ���");
						        jiluDTO.setFlag2("");
						        jiluDTO.setFlag3("");
						        jiluDTO.setFlag4("");
						        jiluDTO.setFlag5("");
						        jiluDTO.setFlag6("");
						        jiluDTO.setFlag7("");
						        jiluDTO.setFlag8("");
						        jiluDTO.setFlag9("");
						        jiluDTO.setFlag10("");
						        
//						      ������������
						        //�˴��϶����Ѵ����߼��������Ӽ�¼ͬʱ���У�
								
						        jBO.logic(jiluDTO,idStr,baseUserContext,true);//��¼û�д��ڵ�����£�Ҫ��ӣ�Ϊtrue ,��lsjgdate�Ǽǣ�
						        
						        //tmpONESYGUPIAONUMBER=tmpONESYGUPIAONUMBER;
						        //�ж��ǰ�֣�
						        tmpCANGWEIFlag= 1;//������
							}
							
						}else if (tmpCANGWEIFlag==1 && maDouble[j][h-1]<=maDouble[j][h] && lsjgDTOs[h].getAdjclose()>maDouble[j][h] ){
							//����ǣ��Ǿ����룻
							
							//�������̼ۣ����������������������뵽stepNumbs;
														
							//System.out.println(zqmc+name+",����ǰ�����ʽ�"+tmpONESYZJ);
							
							double lsgs = tmpONESYZJ/lsjgDTOs[h].getAdjclose();
							//System.out.println("���������ɢ������"+lsgs);
							int mairuGupiaonumber= ( new Double( lsgs/100 ).intValue() )*100;
							tmpONESYGUPIAONUMBER = tmpONESYGUPIAONUMBER+mairuGupiaonumber;
							
							//System.out.println("������Ĺ�����δ����Ӷ�𣩣�"+tmpONESYGUPIAONUMBER);
							//ͨ��lsjgDTOs����jiluDTO
							jiluDTO = new JiluDTO();
							
							jiluDTO.setTimeid(Long.toString(lsjgDTOs[h].getDate().getTime()));					        
					        jiluDTO.setJysj(lsjgDTOs[h].getDate());
					        jiluDTO.setZqdm(zqdm);
					        jiluDTO.setZqmc(zqmc);
					        jiluDTO.setMmflag("����");
					        jiluDTO.setCjjg(lsjgDTOs[h].getAdjclose());
					        
					        jiluDTO.setCjsl(mairuGupiaonumber);
					        jiluDTO.setCjje(lsjgDTOs[h].getAdjclose()*mairuGupiaonumber);
					        
					        Double qsje=sUtil.getQsje(idStr,jiluDTO.getCjje(),jysmc,jiluDTO.getMmflag(),isJiJin);//ʵ������ʱ�Ļ���������ʵ������ʱ�Ļ�����
							
					        if(tmpONESYZJ - Math.abs(qsje)<0){
					        	mairuGupiaonumber=mairuGupiaonumber-100;
					        	
					        	jiluDTO.setCjsl(mairuGupiaonumber);//�����趨�µ�����������
					        	jiluDTO.setCjje(lsjgDTOs[h].getAdjclose()*mairuGupiaonumber);//�����趨�µĳɽ���
						        
						        qsje=sUtil.getQsje(idStr,jiluDTO.getCjje(),jysmc,jiluDTO.getMmflag(),isJiJin);//�����趨ʵ������ʱ�Ļ�����
						        
						        //tmpONESYZJ = tmpONESYZJ - Math.abs(qsje);
					        }
					        
					        tmpONESYZJ = tmpONESYZJ - Math.abs(qsje);
					        
					        //System.out.println("ʵ�ʿ�����Ĺ������������гɱ�����"+tmpONESYGUPIAONUMBER);
					        
					    	//System.out.println(zqmc+name+",ʵ�����������ʽ�"+tmpONESYZJ);
							
//							if(mmflag.equals("����")){
//								
//								cjsl=(cjsl*(-1));
//							}
					        jiluDTO.setQsje(qsje);					        
					        jiluDTO.setYwmc("");//û���õĿ��ֶ�
					        jiluDTO.setZjzh(zjzh);
					        jiluDTO.setJysmc(jysmc);
					        jiluDTO.setKhdm(idStr);
					        jiluDTO.setGdmc(xm);
					        jiluDTO.setBz("�Զ����⽻��(ʹ�õ���ǰ��Ȩ��)");
					        jiluDTO.setJifl(name);					        
					        jiluDTO.setFlag1("�Ѵ���");
					        jiluDTO.setFlag2("");
					        jiluDTO.setFlag3("");
					        jiluDTO.setFlag4("");
					        jiluDTO.setFlag5("");
					        jiluDTO.setFlag6("");
					        jiluDTO.setFlag7("");
					        jiluDTO.setFlag8("");
					        jiluDTO.setFlag9("");
					        jiluDTO.setFlag10("");
							//������������
					        //�˴��϶����Ѵ����߼��������Ӽ�¼ͬʱ���У�
							
					        jBO.logic(jiluDTO,idStr,baseUserContext,true);//��¼û�д��ڵ�����£�Ҫ��ӣ�Ϊtrue ,��lsjgdate�Ǽǣ�
					        //tmpONESYGUPIAONUMBER = tmpONESYGUPIAONUMBER;
					        tmpCANGWEIFlag= 3;//������λΪ3���֣�
						}						
					}					
				}				
			}		
			//������Ҫ����request��5�����ݣ�zqdm,lxDTOs,lsjgDTOs,INDEX,maDouble;
			//request.setAttribute("ZQDM",zqdm);
			//request.setAttribute("LXDTOS",lxDTOs);
			//request.setAttribute("LSJGDTOS",lsjgDTOs);
			//request.setAttribute("INDEX",INDEX);
			//request.setAttribute("MADOUBLE",maDouble);	
			
			//��ʱ���������ͼ����ʾҳ���У�			
		}		
	}	
}

