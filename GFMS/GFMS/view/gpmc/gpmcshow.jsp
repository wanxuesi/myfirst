
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
    
    <title>股票名称显示</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css">
  </head>
  
  <body  onload='dwr.util.useLoadingMessage();getOneGpyl();'>
		 <%@ include file="/view/message.jsp"%>

    <CENTER>股票名称显示</CENTER>
    <table Width="90%" align="center">
			<tr >
			<td align="center">
			 
			 
			 <input type="button" name="新增股票名称相关" value="新增股票名称相关" class="button" onclick='javascript:addNew(); '>
					&nbsp;&nbsp;&nbsp;
					<input type="button" name="批量更新" value="批量更新" class="button" onclick='javascript:addPiNew(); '>
					&nbsp;&nbsp;
					<input type="button" name="刷新" value="&nbsp;&nbsp;&nbsp;刷&nbsp;&nbsp;新&nbsp;&nbsp;&nbsp;" class="button"  onclick="javascript:location.href='<%=path%>/gpmcShow.do';">
			
			</td>
			</tr>
		</table>
	<FORM id="targetForm" name="targetForm" target="_blank" method="post">
		</FORM>
		
		
		
		<TABLE   Width="850"  class="tableframe" align="center">
			<tr class="listtitle">
				<td Width="60" align="center" class="listcelltitle">
					ID
				</td>
				
				<td Width="60" align="center" class="listcelltitle">
					证券名称
				</td>
				
				
				<td Width="60" align="center" class="listcelltitle">
					证券代码
				</td>
				
				<td Width="80" align="center" class="listcelltitle">
					备注
				</td>
				
				<td Width="80" align="center" class="listcelltitle">
					股票/基金
				</td>
				
				
				
				
			</tr>
			<logic:iterate id="gpmc" name="GPMC" indexId="index"  type="com.fuguo.dto.GpmcDTO" scope="request">
				<tr onclick="showOne('<bean:write name="gpmc" property="zqdm" />');"  onMouseOver="javascript:this.bgColor='#FFD39B'" onMouseOut="javascript:this.bgColor=''">
					<td Width="60" align="center" class="listcellrow">
						${pageScope.index+1}
					</td>
					<td  Width="60"  align="center" class="listcellrow">
						<bean:write name="gpmc" property="zqmc" />
					</td>
					

				<td Width="60" align="center" class="listcelltitle">
					<bean:write name="gpmc" property="zqdm" />
				</td>


					<td  Width="80" align="center" class="listcelltitle">
					<bean:write name="gpmc" property="flag1" />
				</td>
				<td  Width="80" align="center" class="listcelltitle">
					<logic:equal value="1" name="gpmc" property="flag2">股票</logic:equal>
					<logic:equal value="0" name="gpmc" property="flag2">基金</logic:equal>
				</td>
				

				

			</logic:iterate>
		</TABLE>
		
			
		</body>
		<script language="javascript">
	
		function addNew(){
			targetForm.action ='<%=path%>/view/gpmc/gpmcadd.jsp'; 
			 targetForm.submit();
		}
		function addPiNew(){
			targetForm.action ="<%=path%>/gpmcAdds.do"; 
			 targetForm.submit();
		}	
		function showOne(gpdm){
			//alert(gpdm);
			targetForm.action ="<%=path%>/gpmcShowOne.do?gpdm="+gpdm; 
			 targetForm.submit();
		
		
		}
		
	
	</script>
</html>
