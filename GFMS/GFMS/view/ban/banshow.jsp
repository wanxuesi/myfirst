
<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>����ά����ҳ��</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    
   
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css">
   
  </head>
  
  <body>
   <FORM id="targetForm" name="targetForm" target="_blank" method="post">
		</FORM>
		<CENTER><input type="button" name="��������" value="��������" class="button" onclick='javascript:addNew(); '>
		</CENTER>
			
    <TABLE   Width="90%" class="tableframe" align="center">
    <tr  class="listtitle">
    <td align="center" class="listcelltitle">���</td>
    <td align="center" class="listcelltitle">��������</td>
    <td align="center" class="listcelltitle">����ʡ��</td>
    <td width="20%" class="listcelltitle">��&nbsp;&nbsp;&nbsp;&nbsp;��</td>
    </tr>
    <logic:iterate id="ban" name="BANLIST" type="com.fuguo.dto.DanweiDTO" scope="request">
    <tr>
    <td align="center" class="listcellrow"><bean:write name="ban" property="id"/> </td>
    <td align="center" class="listcellrow"><bean:write name="ban" property="name"/> </td>
    
    <input type="hidden" name="parent" value='<bean:write name="ban" property="parent"/>'>
    <td align="center" class="listcellrow"><bean:write name="ban" property="parentName"/> </td>
    <td class="listcellrow"  align="center">
						<span style="cursor:hand"  onclick="javascript:document.location.href='<%=path%>/view/ban/banupdate.jsp?id=<bean:write name="ban"  property="id"/>&name=<bean:write name="ban"  property="name"/>&parent=<bean:write name="ban"  property="parent"/>';">
							<img name="update_mode_img" src="<%=path%>/images/edit.gif" style="Width:18;height:18" border="0" alt="�޸�"></span>&nbsp;<span style="cursor:hand"  onclick="javascript:if(confirm('��ȷ��ɾ��<bean:write name="ban"  property="name"/>��')){document.location.href='<%=path%>/banDelete.do?id=<bean:write name="ban"  property="id"/>';}">
							<img name="update_mode_img" src="<%=path%>/images/delete.gif" style="Width:18;height:18" border="0" alt="ɾ��"></span>
					</td>
    </tr>
    
    </logic:iterate>
    
    </TABLE>
    
   
  </body>
  <script language="javascript">
	
		function addNew(){
			targetForm.action ="<%=path%>/view/ban/banadd.jsp"; 
			 targetForm.submit();
		}
		
		
	</script>
</html>
