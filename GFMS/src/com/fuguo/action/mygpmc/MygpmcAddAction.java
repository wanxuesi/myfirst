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
import bsw.tools.exception.BSWException;

import com.fuguo.bo.LsjgdateBO;
import com.fuguo.bo.MygpmcBO;
import com.fuguo.dto.LsjgdateDTO;
import com.fuguo.dto.MygpmcDTO;
import com.fuguo.form.MygpmcForm;
import com.fuguo.util.DateUtil;

/** 
 * MyEclipse Struts
 * Creation date: 01-10-2008
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class MygpmcAddAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO �Զ����ɷ������


		  MygpmcForm m = (MygpmcForm)form;
		  
		 String gpdm =m.getGpdm();
		 String gpmc =m.getGpmc();
		 String flag1 = m.getFlag1();
		 String flag2 = m.getFlag2();//�û�idStr
		 //��װ�����ݴ������
		 MygpmcDTO tDTO = new MygpmcDTO();
	      
	      tDTO.setZqdm(gpdm);
	      tDTO.setZqmc(gpmc);
	      tDTO.setFlag1(flag1);
	      tDTO.setFlag2(flag2);
	      
	      //��ʽ��һ�£����ȼ򻯣��������ݿ⣻
	      Integer zqdmInteger = Integer.parseInt(tDTO.getZqdm());		
			String zqdm=zqdmInteger.toString();
			tDTO.setZqdm(zqdm);
			gpmc = gpmc.replace(" ","");
			gpmc = gpmc.replace(" ","");
			tDTO.setZqmc(gpmc);
			
			
			
			
		
			MygpmcBO tBO =new MygpmcBO();
//			�ж��Ƿ���ڣ�
			boolean result = tBO.isHave(tDTO);
			if(result==true){
				throw new BSWException("SORRY,�ù�Ʊ�Ѿ�����ˣ������ظ����");
			}else{
				tBO.add(tDTO);
			}
			
		
			//2015-10-7��ӣ�
//			��Ҫ���/����lsjgdate��¼��
			LsjgdateDTO lsjgdateDTO  =new LsjgdateDTO();
			lsjgdateDTO.setId(zqdm);
			
			LsjgdateBO  lsjgdateBO  =new LsjgdateBO();
			DateUtil dUtil  =new DateUtil();
			LsjgdateDTO tmpDTO = lsjgdateBO.query(lsjgdateDTO);
			if(tmpDTO!=null&&tmpDTO.getDate().getYear()>0){
				//����иù�Ʊ��lsjgDate��¼������ӣ�
			}else{
				//���û�У��ͱ������lsjgDate��¼��
				lsjgdateDTO.setId(zqdm);
				lsjgdateDTO.setZqdm(zqdm);
				//��ȡ�����ھ���ǰ�����ڣ�
				Date startDate = dUtil.getBeforeNDay(new java.util.Date(),9);
				//��Ҫǿ��ת��Ϊ����ʱ������ڣ�
				SimpleDateFormat   sdf   =   new   SimpleDateFormat("yyyy-MM-dd");
				String tmpDateStr = sdf.format(startDate);
				startDate = sdf.parse(tmpDateStr);
				//Date endDate = dUtil.getBeforeNDay(new java.util.Date(),1);
				lsjgdateDTO.setDate(null);
				lsjgdateDTO.setDatestart(startDate);
//				��Ӹü�¼
				lsjgdateBO.add(lsjgdateDTO);

				
			}
		
	}

	

}

