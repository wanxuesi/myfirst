
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>��ɫ��ʾҳ��</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

		<link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css">
	</head>

	  <body  onload='dwr.util.useLoadingMessage();getOneGpyl();'>
  
		 <%@ include file="/view/message.jsp"%>
		
		<TABLE Width="90%" class="tableframe" align="center">
			<tr class="listtitle">
				<td align="center" class="listcelltitle">
					��
				</td>
				<td align="center" class="listcelltitle">
					��ɫ��
				</td>
				<td colspan="2" Width="30%" class="listcelltitle">
					��&nbsp;&nbsp;&nbsp;&nbsp;��
				</td>
			</tr>
				<tr>
					<td align="center" class="listcellrow">
						1
					</td>
					<td class="listcellrow" align="center">
					��������Ա
					</td>
					<td align="center" class="listcellrow">
						<input type="button" name="�鿴�ý�ɫ����" value="�鿴�ý�ɫ����"  onclick="javascript:document.location.href='<%=path%>/juesefunctionPreManage.do?juesename=��������Ա';">
					</td>
				</tr>
				<tr>
					<td align="center" class="listcellrow">
						2
					</td>
					<td class="listcellrow" align="center">
					ʡ������Ա
					</td>
					<td align="center" class="listcellrow">
						<input type="button" name="�鿴�ý�ɫ����" value="�鿴�ý�ɫ����"  onclick="javascript:document.location.href='<%=path%>/juesefunctionPreManage.do?juesename=ʡ������Ա';">
					</td>
				</tr>
				<tr>
					<td align="center" class="listcellrow">
						3
					</td>
					<td class="listcellrow" align="center">
					��ʯ�û�
					</td>
					<td align="center" class="listcellrow">
						<input type="button" name="�鿴�ý�ɫ����" value="�鿴�ý�ɫ����"  onclick="javascript:document.location.href='<%=path%>/juesefunctionPreManage.do?juesename=��ʯ�û�';">
					</td>
				</tr>
				<tr>
					<td align="center" class="listcellrow">
						4
					</td>
					<td class="listcellrow" align="center">
					�����û�
					</td>
					<td align="center" class="listcellrow">
						<input type="button" name="�鿴�ý�ɫ����" value="�鿴�ý�ɫ����"  onclick="javascript:document.location.href='<%=path%>/juesefunctionPreManage.do?juesename=�����û�';">
					</td>
				</tr>
				<tr>
					<td align="center" class="listcellrow">
						5
					</td>
					<td class="listcellrow" align="center">
					�����û�
					</td>
					<td align="center" class="listcellrow">
						<input type="button" name="�鿴�ý�ɫ����" value="�鿴�ý�ɫ����"  onclick="javascript:document.location.href='<%=path%>/juesefunctionPreManage.do?juesename=�����û�';">
					</td>
				</tr>
				<tr>
					<td align="center" class="listcellrow">
						6
					</td>
					<td class="listcellrow" align="center">
					��ͨ�û�
					</td>
					<td align="center" class="listcellrow">
						<input type="button" name="�鿴�ý�ɫ����" value="�鿴�ý�ɫ����"  onclick="javascript:document.location.href='<%=path%>/juesefunctionPreManage.do?juesename=��ͨ�û�';">
					</td>
				</tr>


		</TABLE>
	</body>
</html>
