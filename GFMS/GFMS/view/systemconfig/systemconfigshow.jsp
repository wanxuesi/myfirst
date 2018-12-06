
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
    
    <title>系统配置显示</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css">
  </head>
  
  <body  onload='dwr.util.useLoadingMessage();getOneGpyl();'>
		 <%@ include file="/view/message.jsp"%>

    <CENTER>系统配置显示</CENTER>
    <table Width="90%" align="center">
			<tr >
			<td align="center">
			 
			 
			 <input type="button" name="新增系统配置相关" value="新增系统配置相关" class="button" onclick='javascript:addNew(); '>
					&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;
					<input type="button" name="刷新" value="&nbsp;&nbsp;&nbsp;刷&nbsp;&nbsp;新&nbsp;&nbsp;&nbsp;" class="button"  onclick="javascript:location.href='<%=path%>/systemconfigShow.do';">
			
			</td>
			</tr>
		</table>
	<FORM id="targetForm" name="targetForm" target="_blank" method="post">
		</FORM>
		
		
		
		<TABLE   Width="620"  class="tableframe" align="center">
			<tr class="listtitle">
				
				<td Width="20" align="center" class="listcelltitle">
					ID
				</td>
				<td Width="200" align="center" class="listcelltitle">
					系统配置代码
				</td>
				
				
				<td Width="200" align="center" class="listcelltitle">
					系统配置名称
				</td>
				
				<td Width="200" align="center" class="listcelltitle">
					系统配置值
				</td>
				
				
				
				
				
				
			</tr>
			<logic:iterate id="systemconfig" name="SYSTEMCONFIG" indexId="index"  type="com.fuguo.dto.SystemconfigDTO" scope="request">
				<tr ondblclick="showOne('<bean:write name="systemconfig" property="functioncode" />');"  onMouseOver="javascript:this.bgColor='#FFD39B'" onMouseOut="javascript:this.bgColor=''">
					<td Width="20" align="center" class="listcelltitle">
					${pageScope.index+1}
				</td>
					<td  Width="200"  align="center" class="listcellrow">
						<bean:write name="systemconfig" property="functioncode" />
					</td>
					

				<td Width="200" align="center" class="listcelltitle">
					<bean:write name="systemconfig" property="functionname" />
				</td>


					<td  Width="200" align="center" class="listcelltitle">
					<bean:write name="systemconfig" property="functionvalue" />
				</td>

				

				

			</logic:iterate>
		</TABLE>
		
			
		</body>
		<script language="javascript">
	
		function addNew(){
			targetForm.action ='<%=path%>/view/systemconfig/systemconfigadd.jsp'; 
			 targetForm.submit();
		}
			
		function showOne(functioncode){
			//alert(gpdm);
			targetForm.action ="<%=path%>/systemconfigShowOne.do?functioncode="+functioncode; 
			 targetForm.submit();
		
		
		}
		
	
	</script>
</html>
