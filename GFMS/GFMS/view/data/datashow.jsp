
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.fuguo.util.DateUtil" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
  <script language="javascript" type="text/javascript" src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>
    
    <base href="<%=basePath%>">
    
    <title>资金进出及股息显示</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css">
  </head>
  <%
  	SimpleDateFormat   sdfymd   =   new   SimpleDateFormat("yyyy-MM-dd");
	Date nowDate = new Date();
	DateUtil dUtil = new DateUtil();
	Date fistDate = dUtil.getBeforeNDay(nowDate,100);
	
	String fistDateStr = sdfymd.format(fistDate);
	String nowDateStr = sdfymd.format(nowDate);
   %>
  <body  onload='dwr.util.useLoadingMessage();getOneGpyl();'> 
		 <%@ include file="/view/message.jsp"%>
    <CENTER>资金进出及股息显示</CENTER>
    <table Width="90%" align="center">
			<tr >
			<td align="center">
			 
			 
			 <input type="button" name="新增资金相关" value="新增资金相关" class="button" onclick='javascript:addNew(); '>
					<input type="button" name="刷新" value="&nbsp;&nbsp;&nbsp;刷&nbsp;&nbsp;新&nbsp;&nbsp;&nbsp;" class="button"  onclick="javascript:location.href='<%=path%>/dataShow.do';">
			</td>
			</tr>
		</table>
		<br>
		 <center>总资产：<bean:write name="ZZC" scope="request"/>
		 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;可用资金：${KYZJ}
		 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前份额数：${DQFE}<br>
		 <br>
		当前净值：<font color=red>${DQJZ}</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color=blue>备注：2018-11-14日 净值起始值：1.0000</font></center>
		
	<br>
		<center>		
				<form name="myForm2" action="" target="_blank" method="post">
				
				<input type="text" id="start_rq" name="start_rq" size="10" class="text" value="<%=fistDateStr%>"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
    		～
    	<input type="text" id="end_rq" name="end_rq" size="10" class="text" value="<%=nowDateStr%>"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
    	
    		   
				<INPUT name="b" id="b" type="button" value="净值折线图"  class="button" onClick="javascript:jingzhiZxt();">
				&nbsp;&nbsp;&nbsp;&nbsp;
				
				</form>
		</center>
	<FORM id="targetForm" name="targetForm" target="_blank" method="post">
		</FORM>
		
		
		
		<TABLE   Width="850"  class="tableframe" align="center">
			<tr class="listtitle">
				<td Width="60" align="center" class="listcelltitle">
					ID
				</td>
				
				<td Width="60" align="center" class="listcelltitle">
					资金名称
				</td>
				
				
				<td Width="60" align="center" class="listcelltitle">
					金额
				</td>
				<td Width="60" align="center" class="listcelltitle">
					份额
				</td>
				<td Width="120" align="center" class="listcelltitle">
					交易时间
				</td>
				<td Width="200" align="center" class="listcelltitle">
					备注
				</td>
				
				
				
				
				
				
			</tr>
			<logic:iterate id="data" name="DATA" indexId="index"  type="com.fuguo.dto.DataDTO" scope="request">
				<tr 
				<logic:equal value=""  name="data" property="flag1"> style="color:red" </logic:equal>
					
				
				onclick="showOne(<bean:write name="data" property="id" />);"  onMouseOver="javascript:this.bgColor='#FFD39B'" onMouseOut="javascript:this.bgColor=''">
					<td Width="60" align="center" class="listcellrow">
						
						<logic:equal value="股息红利" name="data" property="name"><font color="blue">${pageScope.index+1}</font></logic:equal>
					<logic:notEqual value="股息红利" name="data" property="name">${pageScope.index+1}</logic:notEqual>
					
					</td>
					<td  Width="60"  align="center" class="listcellrow">
					
					<logic:equal value="股息红利" name="data" property="name"><font color="blue"><bean:write name="data" property="name" /></font></logic:equal>
					<logic:notEqual value="股息红利" name="data" property="name"><bean:write name="data" property="name" /></logic:notEqual>
						
						
					</td>
					

				<td Width="60" align="center" class="listcelltitle">
					<logic:equal value="股息红利" name="data" property="name"><font color="blue"><bean:write name="data" property="shuju" format=".00" /></font></logic:equal>
					<logic:notEqual value="股息红利" name="data" property="name"><bean:write name="data" property="shuju" format=".00" /></logic:notEqual>
					
				</td>
				<td Width="60" align="center" class="listcelltitle">
				<logic:equal value="0" name="data" property="fene">--</logic:equal>
				<logic:notEqual value="0" name="data" property="fene"><bean:write name="data" property="fene" format=".00" /></logic:notEqual>	
					
				</td>

				<td Width="120" align="center" class="listcelltitle">
					<logic:equal value="股息红利" name="data" property="name"><font color="blue"><bean:write name="data" property="date"   format="yyyy-MM-dd HH:mm" /></font></logic:equal>
					<logic:notEqual value="股息红利" name="data" property="name"><bean:write name="data" property="date"   format="yyyy-MM-dd HH:mm" /></logic:notEqual>
					
				</td>
					<td  Width="200" align="center" class="listcelltitle">
					<logic:equal value="股息红利" name="data" property="name"><font color="blue"><bean:write name="data" property="flag1" /></font></logic:equal>
					<logic:notEqual value="股息红利" name="data" property="name"><bean:write name="data" property="flag1" /></logic:notEqual>
					
				</td>

				

				

			</logic:iterate>
		</TABLE>
		
			
		</body>
		<script language="javascript">
	function bijiao2(){
   	 //比较两个日期，如果开始日期大于结束日期，后面的自动换。
  	 var start_rq =document.getElementById("start_rq").value;
  	
   	 var end_rq   =document.getElementById("end_rq").value;
   	 //alert("aa");
   	if(start_rq>end_rq){
   		document.getElementById("end_rq").value=start_rq;
   	}
   }
		function addNew(){
			targetForm.action ='<%=path%>/view/data/dataadd.jsp'; 
			 targetForm.submit();
		}
		
		function showOne(id){
		
			targetForm.action ="<%=path%>/dataShowOne.do?id="+id; 
			 targetForm.submit();
		
		
		}
	 function jingzhiZxt(){
		bijiao2();
		document.all.myForm2.action ="<%=path%>/dataJingzhiZxt.do"; 
		
		
		document.all.myForm2.submit();
	}	
	
	</script>
</html>
