
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
    
    <title>股票交易分类显示</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css">
  </head>
  
  <body  onload='dwr.util.useLoadingMessage();getOneGpyl();'>
  
		 <%@ include file="/view/message.jsp"%>
  
    <CENTER>股票交易分类显示</CENTER>
    <br>
    <table Width="90%" align="center">
			<tr >
			<td align="center">
			 
			 
			 <input type="button" name="新增交易分类相关" value="新增交易分类相关" class="button" onclick='javascript:addNew(); '>
					<input type="button" name="刷新" value="&nbsp;&nbsp;&nbsp;刷&nbsp;&nbsp;新&nbsp;&nbsp;&nbsp;" class="button"  onclick="javascript:location.href='<%=path%>/lxShow.do';">
					<br><br>
					<font color="red">注：红色为系统的交易分类，个人用户无法删除</font>
			</td>
			</tr>
		</table>
		 
	<FORM id="targetForm" name="targetForm" target="_blank" method="post">
		</FORM>
		
		
		
		<TABLE   Width="500"  class="tableframe" align="center">
			<tr class="listtitle">
				<td Width="50" align="center" class="listcelltitle">
					ID
				</td>
				
				<td Width="450" align="center" class="listcelltitle">
					交易分类
				</td>
				<td Width="450" align="center" class="listcelltitle">
					对应自动模拟参数
				</td>
				
				
				
				
				
				
				
				
				
				
			</tr>
			<logic:iterate id="lx" name="LX" indexId="index"  type="com.fuguo.dto.LxDTO" scope="request">
				<tr onclick="showOne(<bean:write name="lx" property="id" />);"  onMouseOver="javascript:this.bgColor='#FFD39B'" onMouseOut="javascript:this.bgColor=''">
					<td Width="50" align="center" class="listcellrow">
					<logic:equal value="" name="lx" property="flag1"><font color="red">${pageScope.index+1}</font></logic:equal>
					<logic:notEqual value="" name="lx" property="flag1">${pageScope.index+1}</logic:notEqual>
				
					</td>
					<td  Width="450"  align="center" class="listcellrow">
					<logic:equal value="" name="lx" property="flag1"><font color="red"><bean:write name="lx" property="name" /></font></logic:equal>
					<logic:notEqual value="" name="lx" property="flag1"><bean:write name="lx" property="name" /></logic:notEqual>
				
					
						
					</td>
					
					<td  Width="450"  align="center" class="listcellrow">
					<logic:equal value="" name="lx" property="flag2"><font color="red"><bean:write name="lx" property="flag2" /></font></logic:equal>
					<logic:notEqual value="" name="lx" property="flag2"><bean:write name="lx" property="flag2" /></logic:notEqual>
				
					
						
					</td>
				


					

				

				

			</logic:iterate>
		</TABLE>
		
			
		</body>
		<script language="javascript">
	
		function addNew(){
			targetForm.action ='<%=path%>/view/lx/lxadd.jsp'; 
			 targetForm.submit();
		}
		
		function showOne(id){
		
			targetForm.action ="<%=path%>/lxShowOne.do?id="+id; 
			 targetForm.submit();
		
		
		}
		
	
	</script>
</html>
