/**
 * @����: Hibernate�����࣬ͨ���ù�������Է���İ����ݳ־û������ݿ���,�ù�����Ϊ����ģʽ
 * @��λ: ���չ�ҵѧԺ
 * @����: lixiaoyan19851122@163.com
 * @����: ��С��
 * 
 */
package com.fuguo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @��λ: ������Ϣ
 * @����: lixiaoyan19851122@163.com
 * @����: ��С�� 
 */
public class ExitAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.getSession().removeAttribute("#BASEUSERCONTEXT#");			
		return mapping.findForward("exit");
	}
}
