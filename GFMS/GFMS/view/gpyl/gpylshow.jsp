
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
    
    <title>股票语录/文章/感悟显示</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css">
  </head>
  
  <body  onload='dwr.util.useLoadingMessage();getOneGpyl();'>
  
		 <%@ include file="/view/message.jsp"%>
  
    <CENTER>股票语录/文章/感悟显示</CENTER>
    <br>
    <table Width="90%" align="center">
			<tr >
			<td align="center">
			 
			 
			 <input type="button" name="新增语录/文章/感悟相关" value="新增语录/文章/感悟相关" class="button" onclick='javascript:addNew(); '>
					<input type="button" name="刷新" value="&nbsp;&nbsp;&nbsp;刷&nbsp;&nbsp;新&nbsp;&nbsp;&nbsp;" class="button"  onclick="javascript:location.href='<%=path%>/gpylShow.do';">
			<input type="button" value="导出语录到Excel" class="button" onclick="toExcel()"/>
			</td>
			</tr>
		</table>
	<form id="queryDateSqlForm" name="queryDateSqlForm"  action="" target="_blank" method="post">
  		<input type="hidden" id="isSend" name="isSend" value="0">
  							
  	</form>	
		 
	<FORM id="targetForm" name="targetForm" target="_blank" method="post">
		</FORM>
		
		
		
		<TABLE   Width="900"  class="tableframe" align="center">
			<tr class="listtitle">
				<td Width="50" align="center" class="listcelltitle">
					ID
				</td>
				
				<td Width="50" align="center" class="listcelltitle">
					类型
				</td>
				<td Width="120" align="center" class="listcelltitle">
					记录时间
				</td>
				
				<td Width="800" align="center" class="listcelltitle">
					标题
				</td>
				
				
				
				
				
				
				
				
			</tr>
			<logic:iterate id="gpyl" name="GPYL" indexId="index"  type="com.fuguo.dto.GpylDTO" scope="request">
				
				<tr 
					<logic:equal value="语录"  name="gpyl" property="lx"> style="color:red" </logic:equal>
					<logic:equal value="文章"  name="gpyl" property="lx"> style="color:#000000" </logic:equal>
					<logic:equal value="感悟"  name="gpyl" property="lx"> style="color:blue" </logic:equal> onclick="showOne(<bean:write name="gpyl" property="id" />);"  onMouseOver="javascript:this.bgColor='#FFD39B'" onMouseOut="javascript:this.bgColor=''">
					<td Width="50" align="center" class="listcellrow">
						${pageScope.index+1}
						<input type="hidden"  name="allIds" value="<bean:write name="gpyl" property="id" />"/>
					
					</td>
					<td  Width="50"  align="center" class="listcellrow">
					<bean:write name="gpyl" property="lx" />					
						
						
					</td>
					<td Width="120" align="center" class="listcelltitle">
					<bean:write name="gpyl" property="flag2"/>
				</td>

				<td Width="800" align="left" class="listcelltitle">
					<bean:write name="gpyl" property="title" />
				</td>

				</tr>	

				

				

			</logic:iterate>
		</TABLE>
		
			
		</body>
		<script language="javascript">
	
		function addNew(){
			targetForm.action ='<%=path%>/view/gpyl/gpyladd.jsp'; 
			 targetForm.submit();
		}
		
		function showOne(id){
		
			targetForm.action ="<%=path%>/gpylShowOne.do?id="+id; 
			 targetForm.submit();
		
		
		}
		
		function toExcel() {
			var isSend = document.getElementById("isSend");
			if(isSend.value=="0"){
					var allIds = document.getElementsByName("allIds");
					var len = allIds.length;
					for( var k=0;k<len;k++){
						var   input=document.createElement("<input type='hidden'>");   
		  				
		  				input.id="queryIds";
		  				input.name="queryIds"; 
		  				//alert(allIds[k].value); 
		  				input.value=allIds[k].value;
		
		  				document.getElementById("queryDateSqlForm").appendChild(input);  	
					}
					
					document.getElementById("isSend").value="1";		
			
			
			}
			
			
			document.getElementById("queryDateSqlForm").action="gpylPrint.do";
			document.getElementById("queryDateSqlForm").submit();
		}
	</script>
</html>
