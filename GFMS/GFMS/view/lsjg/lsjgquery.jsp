
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="bsw.fwk.BaseUserContext"%>
<html>
  <head>
    
    
    <title>My JSP 'query.jsp' starting page</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
      <script language="javascript" type="text/javascript" src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>
   <base href="<%=basePath%>">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
	<script type="text/javascript" src="js/engine.js"></script>
	<script type="text/javascript" src="js/util.js"></script>
	<script type="text/javascript" src="dwr/interface/danweibo.js"></script> 
 	<script type="text/javascript" src="js/checkboks.js"></script>
	   
	<script language="javascript">   
   
	
	
  
   
   


   
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
   
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css">
	
	
  </head>
  <%
    			BaseUserContext baseUserContext = (BaseUserContext)request.getSession().getAttribute("#BASEUSERCONTEXT#");
  			    String xm =	baseUserContext.getXm().trim();
  			    String dwname = baseUserContext.getDwname();
  			    if(dwname==null){
  			    dwname="";
  			    }else{
  			    dwname=dwname+";";
  			    }
  			   
    		%>
 <body  onload='dwr.util.useLoadingMessage();getOneGpyl();'>
	<%@ include file="/view/message.jsp"%>  
         <INPUT name="qp" id="qp" type="button" value="&nbsp;&nbsp;全&nbsp;屏&nbsp;&nbsp;"  class="button" onClick="javascript:quanping();">
         <br>
    <FORM id="targetForm" name="targetForm" target="_blank" method="post">
	</FORM>
    <form name="myForm" id="myForm" action="lsjgQuery.do" method="post">
			<table width="98%"  class="tableframe" align="center">
			<tr  class="listtitle">
    		        <td colspan="7">  &nbsp;&nbsp; 历史价格查询功能 </td>
    		</tr>
			
			<tr>
		<td colspan="2">
		
		
		
		
		证券名称&nbsp;<input type="text" id="zqmc" name="zqmc" value="" class="text">
		</td>
		<td colspan="2">		
		证券代码&nbsp;<input type="text" id="zqdm" name="zqdm" value="" class="text">
		</td>
		
		<td colspan="4">
		 查找时间段
		
		<input type="text" id="start_rq" name="start_rq" size="10" class="text"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
    		 ～
    	<input type="text" id="end_rq" name="end_rq" size="10" class="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
    		
		
		</td>
		
		<tr class="listtitle">
					<td colspan="7" >
										
					<INPUT name="a" id="a" type="button" value="&nbsp;&nbsp;查&nbsp;找&nbsp;&nbsp;" class="button" onclick="javascript:queryGo();">
					
					

					</td>
					
		</tr>
	</table>
		</form>
  </body>
  <script language="javascript">
  
  
		 		
		
	function queryGo(){
	
		
		
		document.all.myForm.submit();
	}
function quanping(){
	
			targetForm.action ="<%=path%>/view/lsjg/lsjgquery.jsp"; 
			 targetForm.submit();		
		
	}
</script>
</html>
