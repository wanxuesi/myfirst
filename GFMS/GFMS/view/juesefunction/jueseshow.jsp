
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

		<title>角色显示页面</title>

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
					序
				</td>
				<td align="center" class="listcelltitle">
					角色名
				</td>
				<td colspan="2" Width="30%" class="listcelltitle">
					操&nbsp;&nbsp;&nbsp;&nbsp;作
				</td>
			</tr>
				<tr>
					<td align="center" class="listcellrow">
						1
					</td>
					<td class="listcellrow" align="center">
					超级管理员
					</td>
					<td align="center" class="listcellrow">
						<input type="button" name="查看该角色功能" value="查看该角色功能"  onclick="javascript:document.location.href='<%=path%>/juesefunctionPreManage.do?juesename=超级管理员';">
					</td>
				</tr>
				<tr>
					<td align="center" class="listcellrow">
						2
					</td>
					<td class="listcellrow" align="center">
					省级管理员
					</td>
					<td align="center" class="listcellrow">
						<input type="button" name="查看该角色功能" value="查看该角色功能"  onclick="javascript:document.location.href='<%=path%>/juesefunctionPreManage.do?juesename=省级管理员';">
					</td>
				</tr>
				<tr>
					<td align="center" class="listcellrow">
						3
					</td>
					<td class="listcellrow" align="center">
					钻石用户
					</td>
					<td align="center" class="listcellrow">
						<input type="button" name="查看该角色功能" value="查看该角色功能"  onclick="javascript:document.location.href='<%=path%>/juesefunctionPreManage.do?juesename=钻石用户';">
					</td>
				</tr>
				<tr>
					<td align="center" class="listcellrow">
						4
					</td>
					<td class="listcellrow" align="center">
					金牌用户
					</td>
					<td align="center" class="listcellrow">
						<input type="button" name="查看该角色功能" value="查看该角色功能"  onclick="javascript:document.location.href='<%=path%>/juesefunctionPreManage.do?juesename=金牌用户';">
					</td>
				</tr>
				<tr>
					<td align="center" class="listcellrow">
						5
					</td>
					<td class="listcellrow" align="center">
					银牌用户
					</td>
					<td align="center" class="listcellrow">
						<input type="button" name="查看该角色功能" value="查看该角色功能"  onclick="javascript:document.location.href='<%=path%>/juesefunctionPreManage.do?juesename=银牌用户';">
					</td>
				</tr>
				<tr>
					<td align="center" class="listcellrow">
						6
					</td>
					<td class="listcellrow" align="center">
					普通用户
					</td>
					<td align="center" class="listcellrow">
						<input type="button" name="查看该角色功能" value="查看该角色功能"  onclick="javascript:document.location.href='<%=path%>/juesefunctionPreManage.do?juesename=普通用户';">
					</td>
				</tr>


		</TABLE>
	</body>
</html>
