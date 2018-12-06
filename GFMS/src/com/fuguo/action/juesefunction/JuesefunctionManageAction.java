package com.fuguo.action.juesefunction;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import bsw.base.BaseAction;

import com.fuguo.bo.JuesefunctionBO;
import com.fuguo.dto.JuesefunctionDTO;
import com.fuguo.form.JuesefunctionForm;
/**
 * 
 * @����:��λ����Ȩ�޹���  --�����������ӣ�ɾ��Ȩ��--��ѡ������ѡ��ȥ������
 * @��λ:�������
 * @����:wanxuesi@163.com
 * @����:��ѧ˼
 * @���ڣ�2008-6-3
 */
public class JuesefunctionManageAction extends BaseAction {

	/* ���� Javadoc��
	 * @see bsw.base.BaseAction#myLogic(org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//		���ձ����ݣ�����װ

		JuesefunctionForm sfForm = (JuesefunctionForm)form;
		String[] functioncodes = sfForm.getFunctioncodes();
		String juesename = sfForm.getJuesename();
		
		//��Juesefunction��ɾ��juesenameΪ�������û��������м�¼��
		JuesefunctionBO jsBO = new JuesefunctionBO();
		JuesefunctionDTO[] jsDTOs = jsBO.loadAll("from JuesefunctionDTO where juesename='"+juesename+"'");
		jsBO.delete(jsDTOs);
		
		//��Ӽ�¼��
		JuesefunctionDTO[] needJuesefunctionDTO = null;
		if(functioncodes!=null&&functioncodes.length>0){
			needJuesefunctionDTO = new JuesefunctionDTO[functioncodes.length];
			for(int i=0;i<needJuesefunctionDTO.length;i++){				
				needJuesefunctionDTO[i] = new JuesefunctionDTO();
				needJuesefunctionDTO[i].setJuesename(juesename);
				needJuesefunctionDTO[i].setFunctioncode(functioncodes[i]);
				jsBO.add(needJuesefunctionDTO[i]);
			}
		}

	
	}

}
