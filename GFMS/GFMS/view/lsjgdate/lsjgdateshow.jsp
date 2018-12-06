
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
        <script language="javascript" type="text/javascript" src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>
    <base href="<%=basePath%>">
    
    <title>股票历史价格日期显示</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css">
  </head>
  
  <body  onload='dwr.util.useLoadingMessage();getOneGpyl();'>
		 <%@ include file="/view/message.jsp"%>


<form name="myForm" id="myForm" action="lsjgAdds.do" method="post">
			<table width="850"  class="tableframe" align="center">
			<tr  class="listtitle">
    		        <td">  &nbsp;&nbsp; 历史价批量添加功能 </td>
    		</tr>
			
			<tr>
		<td  class="listcellrow">
		

		证券代码&nbsp;<input type="text" id="zqdm" name="zqdm" value="" class="text">
		
		 添加时间段
		
		<input type="text" id="start_rq" name="start_rq" size="10" class="text"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
    		 ～
    	<input type="text" id="end_rq" name="end_rq" size="10" class="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
    		
		
		</td>
			</tr>
		
		<tr class="listtitle">
					
					<td>					
					<INPUT name="a" id="a" type="button" value="&nbsp;&nbsp;添&nbsp;&nbsp;加&nbsp;&nbsp;"  class="button" onClick="javascript:addGo();">
					</td>
					
					
		</tr>
	</table>
		</form>


    <CENTER>股票历史价格日期显示</CENTER>
    <table Width="90%" align="center">
			<tr >
			<td align="center">		 
					<input type="button" name="刷新" value="&nbsp;&nbsp;&nbsp;刷&nbsp;&nbsp;新&nbsp;&nbsp;&nbsp;" class="button"  onclick="javascript:location.href='<%=path%>/lsjgdateShow.do';">
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
					证券代码
				</td>
				<td Width="60" align="center" class="listcelltitle">
					证券名称
				</td>
				<td Width="120" align="center" class="listcelltitle">
					开始更新日期
				</td>
				<td Width="120" align="center" class="listcelltitle">
					截止更新日期
				</td>
				
				
				
				
				
				
				
				
			</tr>
			<logic:iterate id="lsjgdate" name="LSJGDATE" indexId="index"  type="com.fuguo.dto.LsjgdateDTO" scope="request">
				<tr onMouseOver="javascript:this.bgColor='#FFD39B'" onMouseOut="javascript:this.bgColor=''">
					<td Width="60" align="center" class="listcellrow">
						${pageScope.index+1}
					</td>
					
					<td  Width="60"  align="center" class="listcellrow">
						<bean:write name="lsjgdate" property="zqdm" />
					</td>
					<td  Width="60"  align="center" class="listcellrow">
						<bean:write name="lsjgdate" property="zqmc" />
					</td>
					<td  Width="120"  align="center" class="listcellrow">
						<bean:write name="lsjgdate" property="datestart" format="yyyy-MM-dd" />
					</td>
					<td  Width="120"  align="center" class="listcellrow">
						<bean:write name="lsjgdate" property="date" format="yyyy-MM-dd" />
					</td>
					

				

			</logic:iterate>
		</TABLE>
		
			
		</body>
		<script language="javascript">
			function addGo(){
		
		if((parseInt($("zqdm").value)>=0 && parseInt($("zqdm").value)<=700000)){
			document.all.myForm.submit();
		
		}else{
			alert("证券代码不符合格式！");
		}
		
		
		
	}
	 function bijiao(){
   	 //比较两个日期，如果开始日期大于结束日期，后面的自动换。
  	 var start_rq =document.getElementById("start_rq").value;
   	 var end_rq   =document.getElementById("end_rq").value;
   	 //alert("aa");
   	if(start_rq>end_rq){
   		document.getElementById("end_rq").value=start_rq;
   	}
   }
	</script>
</html>
