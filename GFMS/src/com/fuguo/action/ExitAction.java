/**
 * @描述: Hibernate工具类，通过该工具类可以方便的把数据持久化到数据库中,该工具类为单例模式
 * @单位: 江苏工业学院
 * @邮箱: lixiaoyan19851122@163.com
 * @作者: 李小燕
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
 * @单位: 富国信息
 * @邮箱: lixiaoyan19851122@163.com
 * @作者: 李小燕 
 */
public class ExitAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.getSession().removeAttribute("#BASEUSERCONTEXT#");			
		return mapping.findForward("exit");
	}
}
